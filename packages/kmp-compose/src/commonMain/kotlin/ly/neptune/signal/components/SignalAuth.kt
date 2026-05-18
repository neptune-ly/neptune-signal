package ly.neptune.signal.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalAuthMetrics
import ly.neptune.signal.theme.SignalComponentMetrics
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

enum class SignalAuthStep {
    Credentials,
    Verification,
    Password,
}

enum class SignalAuthStateIcon {
    Lock,
    Phone,
    Key,
    Shield,
}

@Composable
fun SignalLoginPanel(
    customerId: String,
    password: String,
    onCustomerIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit,
    onNonCustomer: () -> Unit,
    modifier: Modifier = Modifier,
    onPasskeyLogin: (() -> Unit)? = null,
    onForgotPassword: (() -> Unit)? = null,
    title: String = "تسجيل الدخول",
    subtitle: String = "رقم العميل وكلمة المرور نفسها. الجهاز الجديد يحتاج تحقق قبل الدخول.",
    customerIdLabel: String = "رقم العميل",
    passwordLabel: String = "كلمة المرور",
    loginLabel: String = "دخول",
    passkeyLabel: String = "مفتاح الجهاز",
    forgotPasswordLabel: String = "نسيت كلمة المرور؟",
    nonCustomerLabel: String = "لست عميلا",
    securityNote: String = "يتم فحص التطبيق والجهاز والجلسة قبل الدخول إلى الحساب.",
    bankName: String = "Neptune.",
    poweredBy: String = "Powered by Neptune. Signal",
    brandMark: String = "N.",
    brandVisual: (@Composable () -> Unit)? = null,
    languageLabel: String? = null,
    languageContentDescription: String = "Language",
    onLanguageClick: (() -> Unit)? = null,
    appearanceLabel: String? = null,
    appearanceContentDescription: String = "Appearance",
    onAppearanceClick: (() -> Unit)? = null,
    credentialsStepLabel: String = "البيانات",
    verificationStepLabel: String = "التحقق",
    passwordStepLabel: String = "كلمة المرور",
    showFlowIndicator: Boolean = false,
    fillAvailableHeight: Boolean = false,
    customerIdKeyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    passwordKeyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    errorText: String? = null,
    loading: Boolean = false,
    loginEnabled: Boolean = customerId.isNotBlank() && password.isNotBlank(),
) {
    val colors = SignalTheme.colors
    Column(
        modifier = modifier
            .fillMaxWidth()
            .then(if (fillAvailableHeight) Modifier.fillMaxHeight() else Modifier)
            .padding(vertical = SignalSpacing.x1),
        verticalArrangement = Arrangement.spacedBy(SignalAuthMetrics.contentGap),
    ) {
        SignalAuthMasthead(
            bankName = bankName,
            poweredBy = poweredBy,
            mark = brandMark,
            brandVisual = brandVisual,
            languageLabel = languageLabel,
            languageContentDescription = languageContentDescription,
            onLanguageClick = onLanguageClick,
            appearanceLabel = appearanceLabel,
            appearanceContentDescription = appearanceContentDescription,
            onAppearanceClick = onAppearanceClick,
        )

        SignalAuthTrustStrip(
            title = title,
            subtitle = subtitle,
            icon = SignalAuthStateIcon.Lock,
            primaryChip = credentialsStepLabel,
            secondaryChip = verificationStepLabel,
            tertiaryChip = passwordStepLabel,
        )

        SignalAuthPanel {
            if (errorText != null) {
                SignalAuthErrorBanner(message = signalAuthDisplayError(errorText))
            }

            SignalTextField(
                value = customerId,
                onValueChange = onCustomerIdChange,
                label = customerIdLabel,
                enabled = !loading,
                keyboardOptions = customerIdKeyboardOptions,
            )

            SignalTextField(
                value = password,
                onValueChange = onPasswordChange,
                label = passwordLabel,
                enabled = !loading,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = passwordKeyboardOptions,
            )

            if (onForgotPassword != null) {
                Text(
                    text = forgotPasswordLabel,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(enabled = !loading, onClick = onForgotPassword)
                        .padding(horizontal = SignalSpacing.x2, vertical = SignalSpacing.x1),
                    color = if (colors.dark || colors.black) colors.bankSecondary else colors.bankPrimary,
                    style = SignalTheme.typography.labelLarge,
                    textAlign = TextAlign.End,
                )
            }

            SignalButton(
                label = loginLabel,
                onClick = onLogin,
                modifier = Modifier.fillMaxWidth(),
                loading = loading,
                enabled = loginEnabled,
            )

            if (onPasskeyLogin != null) {
                SignalAuthPasskeyButton(
                    label = passkeyLabel,
                    onClick = onPasskeyLogin,
                    enabled = !loading,
                )
            }

            SignalButton(
                label = nonCustomerLabel,
                onClick = onNonCustomer,
                modifier = Modifier.fillMaxWidth(),
                variant = SignalButtonVariant.Secondary,
                enabled = !loading,
            )

            SignalAuthNote(text = securityNote)
        }
        if (fillAvailableHeight) {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun SignalOtpChallengePanel(
    code: String,
    onCodeChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onResend: (() -> Unit)? = null,
    title: String = "تحقق الجهاز",
    subtitle: String = "أدخل رمز التحقق لإكمال العملية.",
    codeLabel: String = "رمز التحقق",
    submitLabel: String = "متابعة",
    backLabel: String = "رجوع",
    resendLabel: String = "إعادة الإرسال",
    note: String? = null,
    credentialsStepLabel: String = "البيانات",
    verificationStepLabel: String = "التحقق",
    passwordStepLabel: String = "كلمة المرور",
    loading: Boolean = false,
    enabled: Boolean = code.length == 6,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SignalAuthMetrics.contentGap),
    ) {
        SignalAuthStateCard(
            title = title,
            subtitle = subtitle,
            credentialsStepLabel = credentialsStepLabel,
            verificationStepLabel = verificationStepLabel,
            passwordStepLabel = passwordStepLabel,
            showFlowIndicator = true,
            activeStep = SignalAuthStep.Verification,
            icon = SignalAuthStateIcon.Phone,
        )
        SignalAuthPanel {
            SignalOtpCodeField(
                value = code,
                onValueChange = onCodeChange,
                label = codeLabel,
                enabled = !loading,
            )
            SignalButton(
                label = submitLabel,
                onClick = onSubmit,
                modifier = Modifier.fillMaxWidth(),
                loading = loading,
                enabled = enabled,
            )
            if (onResend != null) {
                SignalButton(
                    label = resendLabel,
                    onClick = onResend,
                    modifier = Modifier.fillMaxWidth(),
                    variant = SignalButtonVariant.Secondary,
                    enabled = !loading,
                )
            }
            SignalButton(
                label = backLabel,
                onClick = onBack,
                modifier = Modifier.fillMaxWidth(),
                variant = SignalButtonVariant.Secondary,
                enabled = !loading,
            )
            if (note != null) {
                SignalAuthNote(text = note)
            }
        }
    }
}

@Composable
fun SignalPasswordChangePanel(
    currentPassword: String,
    newPassword: String,
    confirmPassword: String,
    onCurrentPasswordChange: (String) -> Unit,
    onNewPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = "تغيير كلمة المرور",
    subtitle: String = "عيّن كلمة مرور جديدة للمتابعة.",
    currentPasswordLabel: String = "كلمة المرور الحالية",
    newPasswordLabel: String = "كلمة المرور الجديدة",
    confirmPasswordLabel: String = "تأكيد كلمة المرور",
    submitLabel: String = "حفظ ومتابعة",
    backLabel: String = "رجوع",
    note: String? = null,
    credentialsStepLabel: String = "البيانات",
    verificationStepLabel: String = "التحقق",
    passwordStepLabel: String = "كلمة المرور",
    mismatchError: String? = null,
    loading: Boolean = false,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SignalAuthMetrics.contentGap),
    ) {
        SignalAuthStateCard(
            title = title,
            subtitle = subtitle,
            credentialsStepLabel = credentialsStepLabel,
            verificationStepLabel = verificationStepLabel,
            passwordStepLabel = passwordStepLabel,
            showFlowIndicator = true,
            activeStep = SignalAuthStep.Password,
            icon = SignalAuthStateIcon.Key,
        )
        SignalAuthPanel {
            SignalTextField(
                value = currentPassword,
                onValueChange = onCurrentPasswordChange,
                label = currentPasswordLabel,
                visualTransformation = PasswordVisualTransformation(),
                enabled = !loading,
            )
            SignalTextField(
                value = newPassword,
                onValueChange = onNewPasswordChange,
                label = newPasswordLabel,
                visualTransformation = PasswordVisualTransformation(),
                enabled = !loading,
            )
            SignalTextField(
                value = confirmPassword,
                onValueChange = onConfirmPasswordChange,
                label = confirmPasswordLabel,
                visualTransformation = PasswordVisualTransformation(),
                enabled = !loading,
                errorText = mismatchError,
            )
            SignalButton(
                label = submitLabel,
                onClick = onSubmit,
                modifier = Modifier.fillMaxWidth(),
                loading = loading,
                enabled = !loading && currentPassword.isNotBlank() && newPassword.isNotBlank() && newPassword == confirmPassword,
            )
            SignalButton(
                label = backLabel,
                onClick = onBack,
                modifier = Modifier.fillMaxWidth(),
                variant = SignalButtonVariant.Secondary,
                enabled = !loading,
            )
            if (note != null) {
                SignalAuthNote(text = note)
            }
        }
    }
}

@Composable
fun SignalPasswordResetRequestPanel(
    customerId: String,
    onCustomerIdChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    title: String = "استعادة كلمة المرور",
    subtitle: String = "أدخل رقم العميل لإرسال رمز تحقق.",
    customerIdLabel: String = "رقم العميل",
    submitLabel: String = "إرسال رمز التحقق",
    backLabel: String = "رجوع",
    note: String? = null,
    errorText: String? = null,
    loading: Boolean = false,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SignalAuthMetrics.contentGap),
    ) {
        SignalAuthStateCard(title = title, subtitle = subtitle, icon = SignalAuthStateIcon.Shield)
        SignalAuthPanel {
            if (errorText != null) {
                SignalAuthErrorBanner(message = signalAuthDisplayError(errorText))
            }
            SignalTextField(
                value = customerId,
                onValueChange = onCustomerIdChange,
                label = customerIdLabel,
                enabled = !loading,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            SignalButton(
                label = submitLabel,
                onClick = onSubmit,
                modifier = Modifier.fillMaxWidth(),
                loading = loading,
                enabled = customerId.isNotBlank() && !loading,
            )
            SignalButton(
                label = backLabel,
                onClick = onBack,
                modifier = Modifier.fillMaxWidth(),
                variant = SignalButtonVariant.Secondary,
                enabled = !loading,
            )
            if (note != null) {
                SignalAuthNote(text = note)
            }
        }
    }
}

@Composable
fun SignalPasswordResetVerifyPanel(
    code: String,
    newPassword: String,
    confirmPassword: String,
    onCodeChange: (String) -> Unit,
    onNewPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    onResend: (() -> Unit)? = null,
    title: String = "تعيين كلمة مرور جديدة",
    subtitle: String = "أدخل الرمز وكلمة المرور الجديدة.",
    codeLabel: String = "رمز التحقق",
    newPasswordLabel: String = "كلمة المرور الجديدة",
    confirmPasswordLabel: String = "تأكيد كلمة المرور",
    submitLabel: String = "حفظ ومتابعة",
    backLabel: String = "رجوع",
    resendLabel: String = "إعادة الإرسال",
    note: String? = null,
    mismatchError: String? = null,
    errorText: String? = null,
    credentialsStepLabel: String = "البيانات",
    verificationStepLabel: String = "التحقق",
    passwordStepLabel: String = "كلمة المرور",
    loading: Boolean = false,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SignalAuthMetrics.contentGap),
    ) {
        SignalAuthStateCard(
            title = title,
            subtitle = subtitle,
            credentialsStepLabel = credentialsStepLabel,
            verificationStepLabel = verificationStepLabel,
            passwordStepLabel = passwordStepLabel,
            showFlowIndicator = true,
            activeStep = SignalAuthStep.Password,
            icon = SignalAuthStateIcon.Key,
        )
        SignalAuthPanel {
            if (errorText != null) {
                SignalAuthErrorBanner(message = signalAuthDisplayError(errorText))
            }
            SignalOtpCodeField(
                value = code,
                onValueChange = onCodeChange,
                label = codeLabel,
                enabled = !loading,
            )
            SignalTextField(
                value = newPassword,
                onValueChange = onNewPasswordChange,
                label = newPasswordLabel,
                visualTransformation = PasswordVisualTransformation(),
                enabled = !loading,
            )
            SignalTextField(
                value = confirmPassword,
                onValueChange = onConfirmPasswordChange,
                label = confirmPasswordLabel,
                visualTransformation = PasswordVisualTransformation(),
                enabled = !loading,
                errorText = mismatchError,
            )
            SignalButton(
                label = submitLabel,
                onClick = onSubmit,
                modifier = Modifier.fillMaxWidth(),
                loading = loading,
                enabled = !loading && code.length == 6 && newPassword.isNotBlank() && newPassword == confirmPassword,
            )
            if (onResend != null) {
                SignalButton(
                    label = resendLabel,
                    onClick = onResend,
                    modifier = Modifier.fillMaxWidth(),
                    variant = SignalButtonVariant.Secondary,
                    enabled = !loading,
                )
            }
            SignalButton(
                label = backLabel,
                onClick = onBack,
                modifier = Modifier.fillMaxWidth(),
                variant = SignalButtonVariant.Secondary,
                enabled = !loading,
            )
            if (note != null) {
                SignalAuthNote(text = note)
            }
        }
    }
}

@Composable
fun SignalOtpCodeField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    length: Int = 6,
    enabled: Boolean = true,
) {
    val colors = SignalTheme.colors
    val normalized = value.filter { it.isDigit() }.take(length)
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
    ) {
        Text(
            text = label,
            color = colors.onSurfaceVariant,
            style = SignalTheme.typography.fieldLabel,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
        )
        BasicTextField(
            value = normalized,
            onValueChange = { next -> onValueChange(next.filter { it.isDigit() }.take(length)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            cursorBrush = SolidColor(Color.Transparent),
            textStyle = SignalTheme.typography.fieldValue.copy(color = Color.Transparent),
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.fillMaxWidth()) {
                    Box(modifier = Modifier.size(1.dp)) {
                        innerTextField()
                    }
                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            repeat(length) { index ->
                                val char = normalized.getOrNull(index)?.toString().orEmpty()
                                Surface(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(SignalTheme.dimensions.fieldHeight),
                                    color = colors.surface,
                                    shape = RoundedCornerShape(SignalTheme.shapes.md),
                                    border = BorderStroke(
                                        1.dp,
                                        if (char.isNotEmpty()) colors.bankAccent else colors.outlineVariant,
                                    ),
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = char,
                                            color = colors.onSurface,
                                            style = SignalTheme.typography.fieldValue,
                                            maxLines = 1,
                                        )
                                    }
                                }
	                            }
	                        }
	                    }
	                }
	            },
        )
    }
}

@Composable
private fun SignalAuthBrandHeader(
    bankName: String,
    poweredBy: String,
    mark: String,
    brandVisual: (@Composable () -> Unit)?,
    modifier: Modifier = Modifier,
    languageLabel: String? = null,
    languageContentDescription: String = "Language",
    onLanguageClick: (() -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val resolvedLanguageDescription = languageLabel?.let { "$languageContentDescription: $it" } ?: languageContentDescription
    val headerContentColor = if (colors.dark) colors.onSurface else colors.bankPrimary
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(SignalAuthMetrics.headerHeight),
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = SignalAuthMetrics.headerIconBox + SignalSpacing.x2),
            verticalArrangement = Arrangement.spacedBy(SignalSpacing.x1),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val logoModifier = if (brandVisual != null) {
                Modifier.size(SignalAuthMetrics.headerLogoBox)
            } else {
                Modifier
                    .size(SignalAuthMetrics.headerLogoBox)
                    .clip(RoundedCornerShape(SignalAuthMetrics.headerLogoRadius))
                    .background(colors.bankAccent)
            }
            Box(
                modifier = logoModifier,
                contentAlignment = Alignment.Center,
            ) {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    if (brandVisual != null) {
                        brandVisual()
                    } else if (mark == "N.") {
                        SignalAuthNeptuneGlyph(color = Color.White)
                    } else {
                        Text(
                            text = mark,
                            color = Color.White,
                            style = SignalTheme.typography.pageTitle,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
            Text(
                text = bankName,
                color = headerContentColor,
                style = SignalTheme.typography.rowTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = poweredBy,
                color = colors.onSurfaceVariant,
                style = SignalTheme.typography.rowMeta,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        if (onLanguageClick != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(SignalAuthMetrics.headerIconBox)
                    .semantics { contentDescription = resolvedLanguageDescription }
                    .clickable(onClick = onLanguageClick)
                    .padding(SignalSpacing.x2),
                contentAlignment = Alignment.Center,
            ) {
                SignalAuthLanguageGlyph(color = headerContentColor)
            }
        }
    }
}

@Composable
private fun SignalAuthErrorBanner(message: String) {
    val colors = SignalTheme.colors
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .semantics { contentDescription = message },
        color = colors.errorContainer,
        contentColor = colors.onErrorContainer,
        shape = RoundedCornerShape(SignalTheme.shapes.md),
        border = BorderStroke(1.dp, colors.error.copy(alpha = 0.38f)),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x2),
            horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SignalIcon(
                name = SignalIconName.Shield,
                tint = colors.error,
                size = 20.dp,
            )
            Text(
                text = message,
                modifier = Modifier.weight(1f),
                color = colors.onErrorContainer,
                style = SignalTheme.typography.rowMeta,
                textAlign = TextAlign.Start,
            )
        }
    }
}

private fun signalAuthDisplayError(raw: String): String {
    val trimmed = raw.trim()
    if (!trimmed.startsWith("{")) return trimmed
    val message = Regex(""""message"\s*:\s*"((?:\\.|[^"\\])*)"""")
        .find(trimmed)
        ?.groupValues
        ?.getOrNull(1)
        ?.let(::signalAuthUnescapeJson)
        ?.trim()
    return message?.takeIf { it.isNotBlank() } ?: "تعذر إكمال الطلب. حاول مرة أخرى."
}

private fun signalAuthUnescapeJson(value: String): String = buildString {
    var index = 0
    while (index < value.length) {
        val ch = value[index]
        if (ch == '\\' && index + 1 < value.length) {
            when (val next = value[index + 1]) {
                '"', '\\', '/' -> append(next)
                'n' -> append('\n')
                'r' -> append('\r')
                't' -> append('\t')
                else -> append(next)
            }
            index += 2
        } else {
            append(ch)
            index += 1
        }
    }
}

@Composable
private fun SignalAuthMasthead(
    bankName: String,
    poweredBy: String,
    mark: String,
    brandVisual: (@Composable () -> Unit)?,
    modifier: Modifier = Modifier,
    languageLabel: String? = null,
    languageContentDescription: String = "Language",
    onLanguageClick: (() -> Unit)? = null,
    appearanceLabel: String? = null,
    appearanceContentDescription: String = "Appearance",
    onAppearanceClick: (() -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val resolvedLanguageDescription = languageLabel?.let { "$languageContentDescription: $it" } ?: languageContentDescription
    val resolvedAppearanceDescription = appearanceLabel?.let { "$appearanceContentDescription: $it" } ?: appearanceContentDescription
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = SignalSpacing.x1),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .width(92.dp)
                .height(58.dp),
            contentAlignment = Alignment.Center,
        ) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                if (brandVisual != null) {
                    brandVisual()
                } else if (mark == "N.") {
                    SignalAuthNeptuneGlyph(color = if (colors.dark || colors.black) Color.White else colors.bankPrimary)
                } else {
                    Text(
                        text = mark,
                        color = if (colors.dark || colors.black) Color.White else colors.bankPrimary,
                        style = SignalTheme.typography.pageTitle,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = bankName,
                color = if (colors.dark) colors.onSurface else colors.bankPrimary,
                style = SignalTheme.typography.screenTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = poweredBy,
                color = colors.onSurfaceVariant,
                style = SignalTheme.typography.rowMeta,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(SignalSpacing.x1),
            horizontalAlignment = Alignment.End,
        ) {
            if (onLanguageClick != null) {
                SignalAuthQuickAction(
                    label = languageLabel,
                    contentDescription = resolvedLanguageDescription,
                    onClick = onLanguageClick,
                    icon = { SignalAuthLanguageGlyph(color = it) },
                )
            }
            if (onAppearanceClick != null) {
                SignalAuthQuickAction(
                    label = appearanceLabel,
                    contentDescription = resolvedAppearanceDescription,
                    onClick = onAppearanceClick,
                    icon = { SignalIcon(name = SignalIconName.Appearance, tint = it, size = SignalAuthMetrics.headerGlyph) },
                )
            }
        }
    }
}

@Composable
private fun SignalAuthQuickAction(
    label: String?,
    contentDescription: String,
    onClick: () -> Unit,
    icon: @Composable (Color) -> Unit,
) {
    val colors = SignalTheme.colors
    val contentColor = if (colors.dark || colors.black) colors.onSurface else colors.bankPrimary
    Surface(
        modifier = Modifier
            .heightIn(min = 42.dp)
            .semantics { this.contentDescription = contentDescription }
            .clickable(onClick = onClick),
        color = if (colors.dark || colors.black) colors.surfaceContainer else colors.surfaceContainerLow,
        contentColor = contentColor,
        shape = RoundedCornerShape(SignalTheme.shapes.full),
        border = BorderStroke(1.dp, colors.outlineVariant.copy(alpha = 0.72f)),
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = SignalSpacing.x3, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x1),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            icon(contentColor)
            if (label != null) {
                Text(
                    text = label,
                    color = contentColor,
                    style = SignalTheme.typography.statusPill,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
private fun SignalAuthStateCard(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    credentialsStepLabel: String = "البيانات",
    verificationStepLabel: String = "التحقق",
    passwordStepLabel: String = "كلمة المرور",
    showFlowIndicator: Boolean = false,
    activeStep: SignalAuthStep = SignalAuthStep.Credentials,
    icon: SignalAuthStateIcon = SignalAuthStateIcon.Lock,
) {
    val colors = SignalTheme.colors
    val onBrand = Color.White
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = colors.bankPrimary,
        contentColor = onBrand,
        shape = RoundedCornerShape(SignalAuthMetrics.stateRadius),
        border = BorderStroke(1.dp, colors.bankPrimary.copy(alpha = 0.36f)),
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SignalAuthMetrics.statePadding),
                verticalArrangement = Arrangement.spacedBy(SignalAuthMetrics.panelGap),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(SignalAuthMetrics.contentGap),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(SignalAuthMetrics.stateIconBox)
                            .clip(RoundedCornerShape(SignalAuthMetrics.stateIconRadius))
                            .background(onBrand.copy(alpha = 0.92f)),
                        contentAlignment = Alignment.Center,
                    ) {
                        SignalAuthStateGlyph(icon = icon, color = colors.bankPrimary)
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                    ) {
                        Text(
                            text = title,
                            color = onBrand,
                            style = SignalTheme.typography.screenTitle,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                            text = subtitle,
                            color = onBrand.copy(alpha = 0.74f),
                            style = SignalTheme.typography.rowMeta,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
                if (showFlowIndicator) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x1),
                    ) {
                        SignalAuthStepChip(
                            label = credentialsStepLabel,
                            selected = activeStep == SignalAuthStep.Credentials,
                            modifier = Modifier.weight(1f),
                            onBrand = true,
                        )
                        SignalAuthStepChip(
                            label = verificationStepLabel,
                            selected = activeStep == SignalAuthStep.Verification,
                            modifier = Modifier.weight(1f),
                            onBrand = true,
                        )
                        SignalAuthStepChip(
                            label = passwordStepLabel,
                            selected = activeStep == SignalAuthStep.Password,
                            modifier = Modifier.weight(1f),
                            onBrand = true,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SignalAuthTrustStrip(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    icon: SignalAuthStateIcon = SignalAuthStateIcon.Lock,
    primaryChip: String = "البيانات",
    secondaryChip: String = "التحقق",
    tertiaryChip: String = "كلمة المرور",
) {
    val colors = SignalTheme.colors
    val panelColor = if (colors.dark || colors.black) colors.surfaceContainer else colors.surfaceCard
    val accentColor = if (colors.dark || colors.black) colors.bankSecondary else colors.bankPrimary
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = panelColor,
        contentColor = colors.onSurface,
        shape = RoundedCornerShape(28.dp),
        border = BorderStroke(1.dp, colors.outlineVariant.copy(alpha = if (colors.dark || colors.black) 0.42f else 0.52f)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x3),
            verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(accentColor.copy(alpha = if (colors.dark || colors.black) 0.18f else 0.12f)),
                    contentAlignment = Alignment.Center,
                ) {
                    SignalAuthStateGlyph(icon = icon, color = accentColor)
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(3.dp),
                ) {
                    Text(
                        text = title,
                        color = colors.onSurface,
                        style = SignalTheme.typography.titleLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = subtitle,
                        color = colors.onSurfaceVariant,
                        style = SignalTheme.typography.bodySmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SignalAuthMiniChip(primaryChip, accentColor)
                SignalAuthMiniChip(secondaryChip, colors.bankSecondary)
                SignalAuthMiniChip(tertiaryChip, if (colors.dark || colors.black) colors.bankSecondary else colors.bankPrimary)
            }
        }
    }
}

@Composable
private fun SignalAuthMiniChip(label: String, color: Color) {
    Surface(
        color = color.copy(alpha = if (SignalTheme.colors.dark || SignalTheme.colors.black) 0.14f else 0.10f),
        contentColor = color,
        shape = RoundedCornerShape(SignalTheme.shapes.full),
        border = BorderStroke(1.dp, color.copy(alpha = 0.22f)),
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = SignalSpacing.x2, vertical = 5.dp),
            color = color,
            style = SignalTheme.typography.statusPill,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun SignalAuthStateGlyph(icon: SignalAuthStateIcon, color: Color) {
    SignalIcon(
        name = when (icon) {
            SignalAuthStateIcon.Lock -> SignalIconName.Lock
            SignalAuthStateIcon.Phone -> SignalIconName.Smartphone
            SignalAuthStateIcon.Key -> SignalIconName.Key
            SignalAuthStateIcon.Shield -> SignalIconName.Shield
        },
        tint = color,
        size = SignalAuthMetrics.stateGlyph,
    )
}

@Composable
private fun SignalAuthPanel(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = SignalTheme.colors
    val panelColor = when {
        colors.black -> colors.surfaceContainerLow
        colors.dark -> colors.surfaceContainerLowest
        else -> colors.surfaceCard
    }
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = panelColor,
        shape = RoundedCornerShape(SignalAuthMetrics.panelRadius),
        border = BorderStroke(1.dp, colors.outlineVariant.copy(alpha = if (colors.dark) 0.70f else 0.58f)),
    ) {
        Column(
            modifier = Modifier.padding(SignalAuthMetrics.panelPadding),
            verticalArrangement = Arrangement.spacedBy(SignalAuthMetrics.panelGap),
            content = content,
        )
    }
}

@Composable
private fun SignalAuthPasskeyButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val colors = SignalTheme.colors
    val clickModifier = if (enabled) Modifier.clickable(onClick = onClick) else Modifier
    val enabledContentColor = if (colors.dark) colors.onSurface else colors.bankPrimary
    val contentColor = if (enabled) enabledContentColor else colors.onSurfaceVariant.copy(alpha = 0.38f)
    val passkeyColor = colors.bankSecondary
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(SignalAuthMetrics.actionHeight)
            .then(clickModifier),
        color = passkeyColor.copy(alpha = if (colors.dark) 0.18f else 0.10f),
        contentColor = contentColor,
        shape = RoundedCornerShape(SignalTheme.shapes.lg),
        border = BorderStroke(1.dp, passkeyColor.copy(alpha = if (enabled) 0.32f else 0.12f)),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = SignalSpacing.x3),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(modifier = Modifier.size(SignalComponentMetrics.standardIcon), contentAlignment = Alignment.Center) {
                SignalAuthPasskeyGlyph(color = contentColor, size = SignalComponentMetrics.smallActionIcon)
            }
            Spacer(modifier = Modifier.width(SignalSpacing.x2))
            Text(
                text = label,
                color = contentColor,
                style = SignalTheme.typography.button,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun SignalAuthLanguageGlyph(
    color: Color,
    modifier: Modifier = Modifier,
) {
    SignalIcon(name = SignalIconName.Languages, tint = color, modifier = modifier, size = SignalAuthMetrics.headerGlyph)
}

@Composable
private fun SignalAuthNeptuneGlyph(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier.size(SignalComponentMetrics.standardIcon)) {
        val stroke = Stroke(width = 2.35.dp.toPx())
        val points = listOf(
            Offset(size.width * 0.50f, size.height * 0.12f),
            Offset(size.width * 0.82f, size.height * 0.34f),
            Offset(size.width * 0.82f, size.height * 0.68f),
            Offset(size.width * 0.50f, size.height * 0.90f),
            Offset(size.width * 0.18f, size.height * 0.68f),
            Offset(size.width * 0.18f, size.height * 0.34f),
        )
        points.forEachIndexed { index, point ->
            drawLine(
                color = color,
                start = point,
                end = points[(index + 1) % points.size],
                strokeWidth = stroke.width,
            )
        }
        drawLine(
            color = color,
            start = Offset(size.width * 0.30f, size.height * 0.40f),
            end = Offset(size.width * 0.50f, size.height * 0.54f),
            strokeWidth = stroke.width,
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.50f, size.height * 0.54f),
            end = Offset(size.width * 0.70f, size.height * 0.40f),
            strokeWidth = stroke.width,
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.30f, size.height * 0.64f),
            end = Offset(size.width * 0.50f, size.height * 0.50f),
            strokeWidth = stroke.width,
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.50f, size.height * 0.50f),
            end = Offset(size.width * 0.70f, size.height * 0.64f),
            strokeWidth = stroke.width,
        )
    }
}

@Composable
private fun SignalAuthStepChip(
    label: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onBrand: Boolean = false,
) {
    val colors = SignalTheme.colors
    val contentColor = when {
        onBrand && selected -> colors.bankPrimary
        onBrand -> Color.White.copy(alpha = 0.70f)
        selected -> Color.White
        else -> colors.onSurfaceVariant
    }
    val containerColor = when {
        onBrand && selected -> Color.White.copy(alpha = 0.92f)
        onBrand -> Color.White.copy(alpha = 0.12f)
        selected -> colors.bankPrimary
        else -> colors.surfaceContainer
    }
    val borderColor = when {
        onBrand && selected -> Color.White.copy(alpha = 0.92f)
        onBrand -> Color.White.copy(alpha = 0.14f)
        selected -> colors.bankPrimary
        else -> colors.outlineVariant
    }
    Surface(
        modifier = modifier.height(28.dp),
        color = containerColor,
        contentColor = contentColor,
        shape = RoundedCornerShape(SignalTheme.shapes.full),
        border = BorderStroke(1.dp, borderColor),
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = label,
                style = SignalTheme.typography.statusPill,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun SignalAuthLockGlyph(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier.size(SignalAuthMetrics.stateGlyph)) {
        val stroke = Stroke(width = 2.2.dp.toPx())
        val left = size.width * 0.22f
        val right = size.width * 0.78f
        val top = size.height * 0.46f
        val bottom = size.height * 0.88f
        drawLine(color = color, start = Offset(left, top), end = Offset(right, top), strokeWidth = stroke.width)
        drawLine(color = color, start = Offset(left, top), end = Offset(left, bottom), strokeWidth = stroke.width)
        drawLine(color = color, start = Offset(right, top), end = Offset(right, bottom), strokeWidth = stroke.width)
        drawLine(color = color, start = Offset(left, bottom), end = Offset(right, bottom), strokeWidth = stroke.width)
        drawArc(
            color = color,
            startAngle = 200f,
            sweepAngle = 140f,
            useCenter = false,
            topLeft = Offset(size.width * 0.30f, size.height * 0.10f),
            size = androidx.compose.ui.geometry.Size(size.width * 0.40f, size.height * 0.56f),
            style = stroke,
        )
    }
}

@Composable
private fun SignalAuthPhoneGlyph(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier.size(SignalAuthMetrics.stateGlyph)) {
        val stroke = Stroke(width = 2.2.dp.toPx())
        val left = size.width * 0.30f
        val right = size.width * 0.70f
        val top = size.height * 0.10f
        val bottom = size.height * 0.90f
        drawLine(color = color, start = Offset(left, top), end = Offset(right, top), strokeWidth = stroke.width)
        drawLine(color = color, start = Offset(right, top), end = Offset(right, bottom), strokeWidth = stroke.width)
        drawLine(color = color, start = Offset(right, bottom), end = Offset(left, bottom), strokeWidth = stroke.width)
        drawLine(color = color, start = Offset(left, bottom), end = Offset(left, top), strokeWidth = stroke.width)
        drawLine(
            color = color,
            start = Offset(size.width * 0.44f, size.height * 0.78f),
            end = Offset(size.width * 0.56f, size.height * 0.78f),
            strokeWidth = stroke.width,
        )
        drawCircle(
            color = color,
            radius = size.minDimension * 0.055f,
            center = Offset(size.width * 0.78f, size.height * 0.24f),
            style = stroke,
        )
    }
}

@Composable
private fun SignalAuthKeyGlyph(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier.size(SignalAuthMetrics.stateGlyph)) {
        val stroke = Stroke(width = 2.2.dp.toPx())
        drawCircle(
            color = color,
            radius = size.minDimension * 0.18f,
            center = Offset(size.width * 0.34f, size.height * 0.40f),
            style = stroke,
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.48f, size.height * 0.52f),
            end = Offset(size.width * 0.84f, size.height * 0.78f),
            strokeWidth = stroke.width,
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.68f, size.height * 0.66f),
            end = Offset(size.width * 0.62f, size.height * 0.78f),
            strokeWidth = stroke.width,
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.78f, size.height * 0.74f),
            end = Offset(size.width * 0.72f, size.height * 0.86f),
            strokeWidth = stroke.width,
        )
    }
}

@Composable
private fun SignalAuthPasskeyGlyph(
    color: Color,
    modifier: Modifier = Modifier,
    size: androidx.compose.ui.unit.Dp = SignalComponentMetrics.rowIconGlyph,
) {
    SignalIcon(name = SignalIconName.Key, tint = color, modifier = modifier, size = size)
}

@Composable
private fun SignalAuthNote(
    text: String,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = colors.surfaceContainerLowest,
        shape = RoundedCornerShape(SignalTheme.shapes.md),
        border = BorderStroke(1.dp, colors.outlineVariant),
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(SignalAuthMetrics.noteIconBox)
                    .clip(RoundedCornerShape(SignalAuthMetrics.noteIconRadius))
                    .background(colors.bankPrimary),
                contentAlignment = Alignment.Center,
            ) {
                SignalIcon(name = SignalIconName.Shield, tint = Color.White, size = SignalAuthMetrics.noteGlyph)
            }
            Text(
                text = text,
                color = colors.onSurfaceVariant,
                style = SignalTheme.typography.rowMeta,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun SignalAuthShieldGlyph(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier.size(SignalAuthMetrics.noteGlyph)) {
        val stroke = Stroke(width = 2.dp.toPx())
        val top = Offset(size.width * 0.50f, size.height * 0.10f)
        val left = Offset(size.width * 0.18f, size.height * 0.24f)
        val right = Offset(size.width * 0.82f, size.height * 0.24f)
        val lowerLeft = Offset(size.width * 0.25f, size.height * 0.58f)
        val lowerRight = Offset(size.width * 0.75f, size.height * 0.58f)
        val bottom = Offset(size.width * 0.50f, size.height * 0.88f)

        drawLine(color = color, start = top, end = left, strokeWidth = stroke.width)
        drawLine(color = color, start = top, end = right, strokeWidth = stroke.width)
        drawLine(color = color, start = left, end = lowerLeft, strokeWidth = stroke.width)
        drawLine(color = color, start = right, end = lowerRight, strokeWidth = stroke.width)
        drawLine(color = color, start = lowerLeft, end = bottom, strokeWidth = stroke.width)
        drawLine(color = color, start = lowerRight, end = bottom, strokeWidth = stroke.width)
        drawLine(
            color = color,
            start = Offset(size.width * 0.38f, size.height * 0.48f),
            end = Offset(size.width * 0.48f, size.height * 0.60f),
            strokeWidth = stroke.width,
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.48f, size.height * 0.60f),
            end = Offset(size.width * 0.66f, size.height * 0.40f),
            strokeWidth = stroke.width,
        )
    }
}

@Immutable
data class SignalTermsSection(
    val title: String,
    val blocks: List<SignalTermsBlock>,
)

@Immutable
sealed interface SignalTermsBlock {
    public data class Paragraph(val text: String) : SignalTermsBlock
    public data class BulletList(val items: List<String>) : SignalTermsBlock
    public data class Callout(val text: String) : SignalTermsBlock
}

@Composable
fun SignalTermsPanel(
    title: String,
    subtitle: String,
    sections: List<SignalTermsSection>,
    acceptText: String,
    acceptLabel: String,
    onAccept: () -> Unit,
    modifier: Modifier = Modifier,
    versionLabel: String? = null,
    loading: Boolean = false,
    pinnedAction: Boolean = false,
) {
    val colors = SignalTheme.colors
    if (pinnedAction) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = SignalSpacing.x4)
                    .padding(top = SignalSpacing.x4, bottom = 132.dp),
                verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
            ) {
                SignalTermsHeader(title, subtitle, versionLabel)
                SignalTermsSections(sections)
            }
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = colors.surface.copy(alpha = 0.96f),
                tonalElevation = 0.dp,
            ) {
                Column(
                    modifier = Modifier.padding(SignalSpacing.x4),
                    verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
                ) {
                    SignalTermsAcceptText(acceptText)
                    SignalButton(
                        label = acceptLabel,
                        onClick = onAccept,
                        modifier = Modifier.fillMaxWidth(),
                        loading = loading,
                    )
                }
            }
        }
        return
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(SignalSpacing.x4),
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
    ) {
        SignalTermsHeader(title, subtitle, versionLabel)
        SignalTermsSections(sections)
        SignalTermsAcceptText(acceptText)

        SignalButton(
            label = acceptLabel,
            onClick = onAccept,
            modifier = Modifier.fillMaxWidth(),
            loading = loading,
        )
    }
}

@Composable
private fun SignalTermsHeader(title: String, subtitle: String, versionLabel: String?) {
    val colors = SignalTheme.colors
    Surface(
        color = colors.primaryContainer,
        contentColor = colors.onPrimaryContainer,
        shape = RoundedCornerShape(SignalTheme.shapes.lg),
        border = BorderStroke(1.dp, colors.outlineVariant),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SignalSpacing.x4),
            verticalArrangement = Arrangement.spacedBy(SignalSpacing.x1),
        ) {
            if (versionLabel != null) {
                Text(
                    text = versionLabel,
                    color = colors.bankAccent,
                    style = SignalTheme.typography.statusPill,
                )
            }
            Text(
                text = title,
                style = SignalTheme.typography.titleLarge,
                color = colors.bankPrimary,
            )
            Text(
                text = subtitle,
                style = SignalTheme.typography.bodyMedium,
                color = colors.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun SignalTermsSections(sections: List<SignalTermsSection>) {
    Column(verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        sections.forEachIndexed { index, section ->
            SignalTermsSectionCard(
                index = index + 1,
                section = section,
            )
        }
    }
}

@Composable
private fun SignalTermsAcceptText(acceptText: String) {
    val colors = SignalTheme.colors
    Surface(
        color = colors.surfaceContainer,
        shape = RoundedCornerShape(SignalTheme.shapes.md),
        border = BorderStroke(1.dp, colors.outlineVariant),
    ) {
        Text(
            text = acceptText,
            modifier = Modifier.padding(SignalSpacing.x3),
            color = colors.onSurfaceVariant,
            style = SignalTheme.typography.bodySmall,
        )
    }
}

@Composable
private fun SignalTermsSectionCard(
    index: Int,
    section: SignalTermsSection,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = colors.surfaceContainerLowest,
        shape = RoundedCornerShape(SignalTheme.shapes.md),
        border = BorderStroke(1.dp, colors.outlineVariant),
    ) {
        Row(
            modifier = Modifier.padding(SignalSpacing.x3),
            horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
        ) {
            Surface(
                color = colors.primaryContainer,
                contentColor = colors.bankPrimary,
                shape = RoundedCornerShape(SignalTheme.shapes.sm),
            ) {
                Text(
                    text = index.toString(),
                    modifier = Modifier.padding(horizontal = SignalSpacing.x2, vertical = SignalSpacing.x1),
                    style = SignalTheme.typography.statusPill,
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
            ) {
                Text(
                    text = section.title,
                    color = colors.bankPrimary,
                    style = SignalTheme.typography.rowTitle,
                )
                section.blocks.forEach { block ->
                    SignalTermsBlockView(block = block)
                }
            }
        }
    }
}

@Composable
private fun SignalTermsBlockView(block: SignalTermsBlock) {
    val colors = SignalTheme.colors
    when (block) {
        is SignalTermsBlock.Paragraph -> Text(
            text = block.text,
            color = colors.onSurfaceVariant,
            style = SignalTheme.typography.bodySmall,
        )

        is SignalTermsBlock.BulletList -> Column(verticalArrangement = Arrangement.spacedBy(SignalSpacing.x1)) {
            block.items.forEach { item ->
                Row(horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
                    Canvas(
                        modifier = Modifier
                            .padding(top = 6.dp)
                            .size(5.dp),
                    ) {
                        drawCircle(color = colors.bankAccent)
                    }
                    Text(
                        text = item,
                        modifier = Modifier.weight(1f),
                        color = colors.onSurfaceVariant,
                        style = SignalTheme.typography.bodySmall,
                    )
                }
            }
        }

        is SignalTermsBlock.Callout -> Surface(
            color = colors.primaryContainer,
            contentColor = colors.bankPrimary,
            shape = RoundedCornerShape(SignalTheme.shapes.sm),
            border = BorderStroke(1.dp, colors.outlineVariant),
        ) {
            Text(
                text = block.text,
                modifier = Modifier.padding(SignalSpacing.x2),
                style = SignalTheme.typography.bodySmall,
            )
        }
    }
}
