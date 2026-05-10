package ly.neptune.signal.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

enum class SignalAccountHeaderMode {
    Card,
    DetailStage,
}

@Composable
fun SignalAccountHeader(
    accountName: String,
    balance: String,
    iban: String,
    modifier: Modifier = Modifier,
    alias: String? = null,
    masked: Boolean = false,
    mode: SignalAccountHeaderMode = SignalAccountHeaderMode.Card,
    actions: @Composable () -> Unit = {},
    onCopyIban: (() -> Unit)? = null,
    onShareIban: (() -> Unit)? = null,
    onCopyAlias: (() -> Unit)? = null,
    onShareAlias: (() -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val shapes = SignalTheme.shapes
    val dimensions = SignalTheme.dimensions
    val layoutDirection = LocalLayoutDirection.current
    var entered by remember { mutableStateOf(mode == SignalAccountHeaderMode.Card) }
    LaunchedEffect(mode, accountName, iban) {
        entered = true
    }
    val stageProgress by animateFloatAsState(
        targetValue = if (entered) 1f else 0f,
        animationSpec = spring(dampingRatio = 0.84f, stiffness = 520f),
        label = "signal-account-stage-progress",
    )
    val shape = when (mode) {
        SignalAccountHeaderMode.Card -> RoundedCornerShape(shapes.lg)
        SignalAccountHeaderMode.DetailStage -> RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = shapes.xl,
            bottomEnd = shapes.xl,
        )
    }
    val stageMinHeight = when (mode) {
        SignalAccountHeaderMode.Card -> dimensions.accountCardMinHeight
        SignalAccountHeaderMode.DetailStage -> 276.dp
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = stageMinHeight)
            .graphicsLayer {
                if (mode == SignalAccountHeaderMode.DetailStage) {
                    transformOrigin = TransformOrigin(0.5f, 0f)
                    scaleX = 0.982f + stageProgress * 0.018f
                    scaleY = 0.965f + stageProgress * 0.035f
                    translationY = (1f - stageProgress) * 18f
                    alpha = 0.90f + stageProgress * 0.10f
                }
            }
            .clip(shape)
            .background(colors.bankPrimary, shape)
    ) {
        if (mode == SignalAccountHeaderMode.DetailStage) {
            SignalAccountStageOrbits(
                color = colors.bankSecondary.copy(alpha = 0.22f),
                accent = colors.bankAccent.copy(alpha = 0.72f),
                layoutDirection = layoutDirection,
            )
            SignalAccountDetailStageContent(
                accountName = accountName,
                balance = balance,
                iban = iban,
                alias = alias,
                masked = masked,
                actions = actions,
                onCopyIban = onCopyIban,
                onShareIban = onShareIban,
                onCopyAlias = onCopyAlias,
                onShareAlias = onShareAlias,
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SignalSpacing.x4),
                verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = accountName, color = colors.textInverse, style = typography.sectionTitle)
                    actions()
                }
                Text(
                    text = if (masked) "••••••" else balance,
                    color = colors.textInverse,
                    style = typography.displayBalance.copy(fontFeatureSettings = "tnum"),
                    maxLines = 1,
                )
                SignalValueLine(
                    label = "IBAN",
                    value = iban,
                    format = SignalValueFormat.Iban,
                    masked = masked,
                    copyable = onCopyIban != null,
                    shareable = onShareIban != null,
                    onCopy = onCopyIban,
                    onShare = onShareIban,
                    tone = SignalValueTone.Surface,
                )
                if (alias != null) {
                    SignalValueLine(
                        label = "Alias",
                        value = alias,
                        format = SignalValueFormat.Alias,
                        masked = masked,
                        copyable = onCopyAlias != null,
                        shareable = onShareAlias != null,
                        onCopy = onCopyAlias,
                        onShare = onShareAlias,
                        tone = SignalValueTone.Surface,
                    )
                }
            }
        }
    }
}

@Composable
private fun SignalAccountDetailStageContent(
    accountName: String,
    balance: String,
    iban: String,
    alias: String?,
    masked: Boolean,
    actions: @Composable () -> Unit,
    onCopyIban: (() -> Unit)?,
    onShareIban: (() -> Unit)?,
    onCopyAlias: (() -> Unit)?,
    onShareAlias: (() -> Unit)?,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 18.dp, vertical = SignalSpacing.x4),
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            actions()
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = accountName,
                    color = colors.textInverse,
                    style = typography.screenTitle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = "بيانات الحساب والطلبات",
                    color = colors.textInverse.copy(alpha = 0.64f),
                    style = typography.statusPill,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = if (masked) "••••••" else balance,
                modifier = Modifier.weight(1f),
                color = colors.textInverse,
                style = typography.displayBalance.copy(fontFeatureSettings = "tnum"),
                maxLines = 1,
                overflow = TextOverflow.Clip,
            )
            Text(
                text = "نشط",
                modifier = Modifier
                    .clip(RoundedCornerShape(SignalTheme.shapes.full))
                    .background(Color.White.copy(alpha = 0.14f))
                    .padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x1),
                color = colors.textInverse.copy(alpha = 0.86f),
                style = typography.statusPill,
                maxLines = 1,
            )
        }
        SignalStageIdPanel(
            iban = iban,
            alias = alias,
            masked = masked,
            onCopyIban = onCopyIban,
            onShareIban = onShareIban,
            onCopyAlias = onCopyAlias,
            onShareAlias = onShareAlias,
        )
    }
}

@Composable
private fun SignalAccountStageOrbits(color: Color, accent: Color, layoutDirection: LayoutDirection) {
    Canvas(modifier = Modifier.fillMaxWidth().heightIn(min = 286.dp)) {
        val stripWidth = 5.dp.toPx()
        val stripX = if (layoutDirection == LayoutDirection.Rtl) size.width - stripWidth else 0f
        drawCircle(
            color = color,
            radius = size.width * 0.56f,
            center = Offset(size.width * 0.08f, size.height * 0.1f),
            style = Stroke(width = 2.dp.toPx()),
        )
        drawCircle(
            color = accent.copy(alpha = 0.36f),
            radius = size.width * 0.38f,
            center = Offset(size.width * 0.88f, size.height * 0.94f),
            style = Stroke(width = 2.dp.toPx()),
        )
        drawRoundRect(
            color = accent,
            topLeft = Offset(stripX, size.height * 0.30f),
            size = Size(stripWidth, size.height * 0.54f),
            cornerRadius = CornerRadius(stripWidth, stripWidth),
        )
    }
}

@Composable
private fun SignalStageIdPanel(
    iban: String,
    alias: String?,
    masked: Boolean,
    onCopyIban: (() -> Unit)?,
    onShareIban: (() -> Unit)?,
    onCopyAlias: (() -> Unit)?,
    onShareAlias: (() -> Unit)?,
) {
    val shape = RoundedCornerShape(18.dp)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White.copy(alpha = 0.10f), shape)
            .border(BorderStroke(1.dp, Color.White.copy(alpha = 0.14f)), shape)
            .padding(horizontal = SignalSpacing.x3, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        if (alias != null) {
            SignalStageIdLine(
                label = "NPT Alias",
                value = if (masked) "••••••" else alias,
                onCopy = onCopyAlias,
                onShare = onShareAlias,
            )
        }
        SignalStageIdLine(
            label = "IBAN",
            value = if (masked) "LY•••• •••• •••• ••••" else compactStageIban(iban),
            onCopy = onCopyIban,
            onShare = onShareIban,
            monospace = true,
        )
    }
}

@Composable
private fun SignalStageIdLine(
    label: String,
    value: String,
    onCopy: (() -> Unit)?,
    onShare: (() -> Unit)?,
    monospace: Boolean = false,
) {
    val valueStyle = if (monospace) {
        SignalTheme.typography.statusPill.copy(fontFamily = FontFamily.Monospace)
    } else {
        SignalTheme.typography.statusPill
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            modifier = Modifier.width(62.dp),
            color = SignalTheme.colors.textInverse.copy(alpha = 0.54f),
            style = SignalTheme.typography.statusPill,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            Text(
                text = value,
                modifier = Modifier
                    .weight(1f)
                    .horizontalScroll(rememberScrollState()),
                color = SignalTheme.colors.textInverse.copy(alpha = 0.90f),
                style = valueStyle,
                maxLines = 1,
                overflow = TextOverflow.Clip,
                softWrap = false,
            )
        }
        if (onCopy != null) {
            SignalStageMiniAction(onClick = onCopy) {
                SignalCopyGlyph(color = SignalTheme.colors.textInverse)
            }
        }
        if (onShare != null) {
            SignalStageMiniAction(onClick = onShare) {
                SignalShareGlyph(color = SignalTheme.colors.textInverse)
            }
        }
    }
}

@Composable
private fun SignalStageMiniAction(onClick: () -> Unit, content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clickable(onClick = onClick)
            .padding(7.dp),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

private fun compactStageIban(value: String): String {
    val clean = value.filter { it.isLetterOrDigit() }
    if (clean.isEmpty()) return value
    val groups = clean.chunked(4)
    if (groups.size <= 5) return groups.joinToString(" ")
    return groups.take(5).joinToString(" ") + " " + groups.drop(5).joinToString("")
}
