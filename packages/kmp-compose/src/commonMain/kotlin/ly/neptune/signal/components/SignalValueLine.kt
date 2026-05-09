package ly.neptune.signal.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

enum class SignalValueFormat {
    Plain,
    Amount,
    Iban,
    Alias,
    Reference,
    Secret,
}

enum class SignalValueCompactMode {
    Full,
    SemanticCompact,
}

enum class SignalValueTone {
    Surface,
    OnPrimary,
}

@Composable
fun SignalValueLine(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    format: SignalValueFormat = SignalValueFormat.Plain,
    copyable: Boolean = false,
    shareable: Boolean = false,
    masked: Boolean = false,
    compactMode: SignalValueCompactMode = SignalValueCompactMode.Full,
    tone: SignalValueTone = SignalValueTone.Surface,
    onCopy: (() -> Unit)? = null,
    onShare: (() -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val shapes = SignalTheme.shapes
    val dimensions = SignalTheme.dimensions
    val valueText = signalDisplayValue(label, value, format, masked, compactMode)
    val valueStyle = when (format) {
        SignalValueFormat.Iban,
        SignalValueFormat.Reference,
        SignalValueFormat.Secret -> typography.rowTitle.copy(fontFamily = FontFamily.Monospace)
        SignalValueFormat.Amount -> typography.rowTitle.copy(fontFeatureSettings = "tnum")
        SignalValueFormat.Plain,
        SignalValueFormat.Alias -> typography.rowTitle
    }
    val containerColor = when (tone) {
        SignalValueTone.Surface -> colors.surfaceContainerLowest
        SignalValueTone.OnPrimary -> colors.textInverse.copy(alpha = 0.10f)
    }
    val borderColor = when (tone) {
        SignalValueTone.Surface -> colors.outlineVariant
        SignalValueTone.OnPrimary -> colors.textInverse.copy(alpha = 0.14f)
    }
    val labelColor = when (tone) {
        SignalValueTone.Surface -> colors.onSurfaceVariant
        SignalValueTone.OnPrimary -> colors.textInverse.copy(alpha = 0.62f)
    }
    val valueColor = when (tone) {
        SignalValueTone.Surface -> colors.onSurface
        SignalValueTone.OnPrimary -> colors.textInverse
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(containerColor, RoundedCornerShape(shapes.md))
            .border(BorderStroke(1.dp, borderColor), RoundedCornerShape(shapes.md))
            .padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x2),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(SignalSpacing.x1),
        ) {
            Text(text = label, color = labelColor, style = typography.rowMeta)
            Text(
                text = valueText,
                color = valueColor,
                style = valueStyle,
                maxLines = if (compactMode == SignalValueCompactMode.SemanticCompact) 1 else Int.MAX_VALUE,
                overflow = TextOverflow.Clip,
                softWrap = true,
            )
        }
        if (copyable || onCopy != null) {
            IconButton(
                onClick = { onCopy?.invoke() },
                enabled = onCopy != null,
                modifier = Modifier
                    .size(dimensions.touchTarget)
                    .semantics { contentDescription = "Copy $label" },
            ) {
                SignalCopyGlyph(color = valueColor)
            }
        }
        if (shareable || onShare != null) {
            IconButton(
                onClick = { onShare?.invoke() },
                enabled = onShare != null,
                modifier = Modifier
                    .size(dimensions.touchTarget)
                    .semantics { contentDescription = "Share $label" },
            ) {
                SignalShareGlyph(color = valueColor)
            }
        }
    }
}

@Composable
fun SignalCopyGlyph(
    modifier: Modifier = Modifier,
    color: Color? = null,
) {
    val resolvedColor = color ?: SignalTheme.colors.bankPrimary
    Canvas(modifier = modifier.size(18.dp)) {
        val stroke = Stroke(width = 2.dp.toPx())
        val radius = CornerRadius(3.dp.toPx(), 3.dp.toPx())
        drawRoundRect(
            color = resolvedColor,
            topLeft = Offset(size.width * 0.34f, size.height * 0.28f),
            size = Size(size.width * 0.5f, size.height * 0.56f),
            cornerRadius = radius,
            style = stroke,
        )
        drawRoundRect(
            color = resolvedColor,
            topLeft = Offset(size.width * 0.16f, size.height * 0.12f),
            size = Size(size.width * 0.5f, size.height * 0.56f),
            cornerRadius = radius,
            style = stroke,
        )
    }
}

@Composable
fun SignalShareGlyph(
    modifier: Modifier = Modifier,
    color: Color? = null,
) {
    val resolvedColor = color ?: SignalTheme.colors.bankPrimary
    Canvas(modifier = modifier.size(18.dp)) {
        val stroke = Stroke(width = 2.dp.toPx())
        val start = Offset(size.width * 0.2f, size.height * 0.62f)
        val middle = Offset(size.width * 0.58f, size.height * 0.78f)
        val end = Offset(size.width * 0.74f, size.height * 0.26f)
        drawLine(color = resolvedColor, start = start, end = middle, strokeWidth = stroke.width)
        drawLine(color = resolvedColor, start = middle, end = end, strokeWidth = stroke.width)
        drawCircle(color = resolvedColor, radius = 2.6.dp.toPx(), center = start, style = stroke)
        drawCircle(color = resolvedColor, radius = 2.6.dp.toPx(), center = middle, style = stroke)
        drawCircle(color = resolvedColor, radius = 2.6.dp.toPx(), center = end, style = stroke)
    }
}

internal fun signalDisplayValue(
    label: String,
    value: String,
    format: SignalValueFormat,
    masked: Boolean,
    compactMode: SignalValueCompactMode,
): String {
    if (masked) return signalMaskedValue(format)
    if (compactMode == SignalValueCompactMode.SemanticCompact) {
        return when (format) {
            SignalValueFormat.Iban -> "$label ending ${value.filter { it.isLetterOrDigit() }.takeLast(4)}"
            SignalValueFormat.Reference -> "$label ending ${value.filter { it.isLetterOrDigit() }.takeLast(4)}"
            SignalValueFormat.Secret -> "$label ending ${value.filter { it.isLetterOrDigit() }.takeLast(4)}"
            else -> value
        }
    }
    return when (format) {
        SignalValueFormat.Iban,
        SignalValueFormat.Secret -> value.filter { it.isLetterOrDigit() }.chunked(4).joinToString(" ")
        SignalValueFormat.Plain,
        SignalValueFormat.Amount,
        SignalValueFormat.Alias,
        SignalValueFormat.Reference -> value
    }
}

private fun signalMaskedValue(format: SignalValueFormat): String = when (format) {
    SignalValueFormat.Amount -> "••••••"
    SignalValueFormat.Iban -> "LY•••• •••• •••• ••••"
    SignalValueFormat.Secret -> "•••• •••• ••••"
    SignalValueFormat.Alias -> "••••••"
    SignalValueFormat.Plain,
    SignalValueFormat.Reference -> "••••••"
}
