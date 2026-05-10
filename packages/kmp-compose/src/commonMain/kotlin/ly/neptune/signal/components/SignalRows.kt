package ly.neptune.signal.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalComponentMetrics
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

enum class SignalRowTone {
    Neutral,
    Primary,
    Accent,
    Success,
    Warning,
    Danger,
}

@Composable
fun SignalBankingRow(
    title: String,
    metadata: String,
    modifier: Modifier = Modifier,
    amount: String? = null,
    tone: SignalRowTone = SignalRowTone.Neutral,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
    leading: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val shape = RoundedCornerShape(SignalTheme.shapes.xs)
    val borderColor = colors.outlineVariant.copy(alpha = if (colors.dark) 0.72f else 0.92f)
    val clickableModifier = if (onClick != null) {
        Modifier.clickable(enabled = enabled, onClick = onClick)
    } else {
        Modifier
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = SignalTheme.dimensions.rowMinHeight)
            .clip(shape)
            .background(colors.surfaceContainerLowest, shape)
            .then(clickableModifier)
            .border(BorderStroke(1.dp, borderColor), shape)
            .padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
    ) {
        if (leading != null) {
            SignalIconSurface(tone = tone, content = leading)
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(SignalSpacing.x1),
        ) {
            Text(
                text = title,
                color = colors.onSurface,
                style = typography.rowTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = metadata,
                color = colors.onSurfaceVariant,
                style = typography.rowMeta,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        if (amount != null) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Text(
                    text = amount,
                    modifier = Modifier.widthIn(min = 64.dp),
                    color = toneColor(tone),
                    style = typography.titleMedium.copy(fontFeatureSettings = "tnum"),
                    textAlign = TextAlign.End,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        trailing?.invoke()
    }
}

@Composable
fun SignalAccountRow(
    name: String,
    accountType: String,
    status: String,
    iban: String,
    balance: String,
    modifier: Modifier = Modifier,
    masked: Boolean = false,
    compactIdentifier: String = signalCompactIban(iban),
    onClick: () -> Unit,
    trailing: (@Composable () -> Unit)? = null,
) {
    SignalAccountSummaryRow(
        name = name,
        accountType = accountType,
        status = status,
        compactIdentifier = compactIdentifier,
        balance = balance,
        masked = masked,
        modifier = modifier,
        onClick = onClick,
        trailing = trailing,
    )
}

@Composable
fun SignalAccountSummaryRow(
    name: String,
    accountType: String,
    status: String,
    compactIdentifier: String,
    balance: String,
    modifier: Modifier = Modifier,
    masked: Boolean = false,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val shape = RoundedCornerShape(SignalTheme.shapes.xs)
    val borderColor = colors.outlineVariant.copy(alpha = if (colors.dark) 0.72f else 0.92f)
    val clickableModifier = if (onClick != null) {
        Modifier.clickable(enabled = enabled, onClick = onClick)
    } else {
        Modifier
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 82.dp)
            .clip(shape)
            .background(colors.surfaceContainerLowest, shape)
            .then(clickableModifier)
            .border(BorderStroke(1.dp, borderColor), shape)
            .padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
    ) {
        Box(
            modifier = Modifier
                .size(width = 4.dp, height = 52.dp)
                .background(colors.bankSecondary, RoundedCornerShape(SignalTheme.shapes.full)),
        )
        SignalIconSurface(tone = SignalRowTone.Primary) {
            SignalAccountGlyph()
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = name,
                color = colors.onSurface,
                style = typography.rowTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = accountType,
                color = colors.onSurfaceVariant,
                style = typography.rowMeta,
                maxLines = 1,
                overflow = TextOverflow.Clip,
            )
            Text(
                text = "$status · $compactIdentifier",
                color = colors.onSurfaceVariant,
                style = typography.rowMeta,
                maxLines = 1,
                overflow = TextOverflow.Clip,
            )
        }
        Column(
            modifier = Modifier.widthIn(min = 112.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
        ) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Text(
                    text = if (masked) "••••••" else balance,
                    color = if (colors.dark) colors.onSurface else colors.bankPrimary,
                    style = typography.pageTitle.copy(fontFeatureSettings = "tnum"),
                    textAlign = TextAlign.End,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        when {
            trailing != null -> trailing.invoke()
            onClick != null -> SignalChevronGlyph()
        }
    }
}

@Composable
fun SignalIdentifierChip(
    text: String,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    Surface(
        modifier = modifier,
        color = colors.surfaceContainer,
        shape = RoundedCornerShape(SignalTheme.shapes.full),
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = SignalSpacing.x2, vertical = SignalSpacing.x1),
            color = colors.onSurfaceVariant,
            style = SignalTheme.typography.statusPill,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun SignalStatusChip(
    text: String,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    val containerColor = if (colors.dark) colors.primaryContainer.copy(alpha = 0.72f) else colors.primaryContainer
    Surface(
        modifier = modifier,
        color = containerColor,
        shape = RoundedCornerShape(SignalTheme.shapes.full),
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = SignalSpacing.x2, vertical = SignalSpacing.x1),
            color = colors.onPrimaryContainer,
            style = SignalTheme.typography.statusPill,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun SignalChevronGlyph(modifier: Modifier = Modifier) {
    val color = SignalTheme.colors.onSurfaceVariant
    val layoutDirection = LocalLayoutDirection.current
    Canvas(modifier = modifier.size(18.dp)) {
        val stroke = Stroke(width = 2.2.dp.toPx(), cap = StrokeCap.Round)
        if (layoutDirection == LayoutDirection.Rtl) {
            drawLine(color, Offset(size.width * 0.64f, size.height * 0.22f), Offset(size.width * 0.36f, size.height * 0.50f), stroke.width, StrokeCap.Round)
            drawLine(color, Offset(size.width * 0.36f, size.height * 0.50f), Offset(size.width * 0.64f, size.height * 0.78f), stroke.width, StrokeCap.Round)
        } else {
            drawLine(color, Offset(size.width * 0.36f, size.height * 0.22f), Offset(size.width * 0.64f, size.height * 0.50f), stroke.width, StrokeCap.Round)
            drawLine(color, Offset(size.width * 0.64f, size.height * 0.50f), Offset(size.width * 0.36f, size.height * 0.78f), stroke.width, StrokeCap.Round)
        }
    }
}

@Composable
fun SignalAccountGlyph(modifier: Modifier = Modifier) {
    val color = SignalTheme.colors.onPrimary
    Canvas(modifier = modifier.size(20.dp)) {
        val stroke = Stroke(width = 2.dp.toPx())
        drawRoundRect(
            color = color,
            topLeft = Offset(size.width * 0.18f, size.height * 0.18f),
            size = Size(size.width * 0.64f, size.height * 0.64f),
            cornerRadius = CornerRadius(3.dp.toPx(), 3.dp.toPx()),
            style = stroke,
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.18f, size.height * 0.43f),
            end = Offset(size.width * 0.82f, size.height * 0.43f),
            strokeWidth = stroke.width,
        )
        drawLine(
            color = color,
            start = Offset(size.width * 0.36f, size.height * 0.63f),
            end = Offset(size.width * 0.64f, size.height * 0.63f),
            strokeWidth = stroke.width,
        )
    }
}

@Composable
fun SignalConsentScopeRow(
    index: String,
    title: String,
    description: String,
    access: String,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val shape = RoundedCornerShape(SignalTheme.shapes.xs)
    val borderColor = colors.outlineVariant.copy(alpha = if (colors.dark) 0.72f else 0.92f)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(colors.surfaceContainerLowest, shape)
            .border(BorderStroke(1.dp, borderColor), shape)
            .padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x2),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(if (colors.dark) colors.primary else colors.bankPrimary, RoundedCornerShape(SignalTheme.shapes.full)),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = index, color = colors.onPrimary, style = typography.statusPill)
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, color = colors.onSurface, style = typography.rowTitle)
            Text(text = description, color = colors.onSurfaceVariant, style = typography.rowMeta)
        }
        Surface(
            color = colors.secondaryContainer,
            shape = RoundedCornerShape(SignalTheme.shapes.full),
        ) {
            Text(
                text = access,
                modifier = Modifier.padding(horizontal = SignalSpacing.x2, vertical = SignalSpacing.x1),
                color = colors.onSecondaryContainer,
                style = typography.statusPill,
            )
        }
    }
}

private fun signalCompactIban(iban: String): String =
    "IBAN ending ${iban.filter { it.isLetterOrDigit() }.takeLast(4)}"

@Composable
private fun SignalIconSurface(
    tone: SignalRowTone,
    content: (@Composable () -> Unit)?,
) {
    val background = toneContainerColor(tone)
    val contentColor = toneContentColor(tone)
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(SignalTheme.shapes.sm))
            .background(background, RoundedCornerShape(SignalTheme.shapes.sm)),
        contentAlignment = Alignment.Center,
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CompositionLocalProvider(LocalContentColor provides contentColor) {
                Box(
                    modifier = Modifier.size(SignalComponentMetrics.rowIconGlyph),
                    contentAlignment = Alignment.Center,
                ) {
                    content?.invoke()
                }
            }
        }
    }
}

@Composable
private fun toneColor(tone: SignalRowTone): Color {
    val colors = SignalTheme.colors
    return when (tone) {
        SignalRowTone.Neutral -> colors.textSecondary
        SignalRowTone.Primary -> if (colors.dark) colors.onSurface else colors.bankPrimary
        SignalRowTone.Accent -> colors.bankAccent
        SignalRowTone.Success -> colors.success
        SignalRowTone.Warning -> colors.warning
        SignalRowTone.Danger -> colors.danger
    }
}

@Composable
private fun toneContainerColor(tone: SignalRowTone): Color {
    val colors = SignalTheme.colors
    return when (tone) {
        SignalRowTone.Neutral -> if (colors.dark) colors.surfaceContainerHigh else colors.primaryContainer.copy(alpha = 0.62f)
        SignalRowTone.Primary -> if (colors.dark) colors.primary else colors.bankPrimary
        SignalRowTone.Accent -> if (colors.dark) colors.tertiary else colors.bankAccent
        SignalRowTone.Success -> colors.success
        SignalRowTone.Warning -> colors.warning
        SignalRowTone.Danger -> colors.danger
    }
}

@Composable
private fun toneContentColor(tone: SignalRowTone): Color {
    val colors = SignalTheme.colors
    return when (tone) {
        SignalRowTone.Neutral -> if (colors.dark) colors.onSurface else colors.bankPrimary
        SignalRowTone.Primary -> colors.onPrimary
        SignalRowTone.Accent -> colors.onTertiary
        SignalRowTone.Success, SignalRowTone.Warning, SignalRowTone.Danger -> colors.surface
    }
}
