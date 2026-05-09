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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

enum class SignalConsentKind {
    DataAccess,
    RecurringPayment,
    OneTimeApproval,
}

data class SignalConsentMetric(
    val label: String,
    val value: String,
)

data class SignalConsentTag(
    val label: String,
    val tone: SignalConsentKind = SignalConsentKind.DataAccess,
)

@Composable
fun SignalConsentOverview(
    title: String,
    description: String,
    alias: String,
    defaultAccount: String,
    metrics: List<SignalConsentMetric>,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val shape = RoundedCornerShape(SignalTheme.shapes.lg)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.primaryContainer, shape)
            .border(BorderStroke(1.dp, colors.outlineVariant), shape)
            .padding(SignalSpacing.x4),
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SignalConsentGlyph(kind = SignalConsentKind.DataAccess)
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, color = colors.onPrimaryContainer, style = typography.sectionTitle)
                Text(text = description, color = colors.onSurfaceVariant, style = typography.rowMeta)
            }
        }
        SignalValueLine(
            label = "NPT Alias",
            value = alias,
            format = SignalValueFormat.Alias,
            compactMode = SignalValueCompactMode.Full,
        )
        SignalIdentifierChip(text = defaultAccount)
        if (metrics.isNotEmpty()) {
            Row(horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
                metrics.take(3).forEach { metric ->
                    SignalConsentMetricTile(metric = metric, modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun SignalConsentCard(
    title: String,
    description: String,
    status: String,
    kind: SignalConsentKind,
    modifier: Modifier = Modifier,
    tags: List<SignalConsentTag> = emptyList(),
    onClick: (() -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val shape = RoundedCornerShape(SignalTheme.shapes.md)
    val clickableModifier = if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier

    Row(
        modifier = modifier
            .then(clickableModifier)
            .fillMaxWidth()
            .heightIn(min = 82.dp)
            .background(colors.surfaceContainerLowest, shape)
            .border(BorderStroke(1.dp, colors.outlineVariant), shape)
            .padding(SignalSpacing.x3),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SignalConsentGlyph(kind = kind)
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
                text = description,
                color = colors.onSurfaceVariant,
                style = typography.rowMeta,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            if (tags.isNotEmpty()) {
                Row(horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x1)) {
                    tags.take(2).forEach { tag ->
                        SignalConsentTagPill(tag = tag)
                    }
                }
            }
        }
        SignalConsentTagPill(tag = SignalConsentTag(status, kind))
    }
}

@Composable
private fun SignalConsentMetricTile(
    metric: SignalConsentMetric,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    Surface(
        modifier = modifier,
        color = colors.surfaceContainerLowest,
        shape = RoundedCornerShape(SignalTheme.shapes.sm),
        border = BorderStroke(1.dp, colors.outlineVariant),
    ) {
        Column(
            modifier = Modifier.padding(SignalSpacing.x2),
            verticalArrangement = Arrangement.spacedBy(SignalSpacing.x1),
        ) {
            Text(text = metric.label, color = colors.onSurfaceVariant, style = typography.rowMeta, maxLines = 1)
            Text(text = metric.value, color = colors.bankPrimary, style = typography.sectionTitle, maxLines = 1)
        }
    }
}

@Composable
private fun SignalConsentTagPill(
    tag: SignalConsentTag,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    val background = when (tag.tone) {
        SignalConsentKind.DataAccess -> colors.primaryContainer
        SignalConsentKind.RecurringPayment -> colors.secondaryContainer
        SignalConsentKind.OneTimeApproval -> colors.tertiaryContainer
    }
    val foreground = when (tag.tone) {
        SignalConsentKind.DataAccess -> colors.onPrimaryContainer
        SignalConsentKind.RecurringPayment -> colors.onSecondaryContainer
        SignalConsentKind.OneTimeApproval -> colors.onTertiaryContainer
    }
    Surface(
        modifier = modifier,
        color = background,
        shape = RoundedCornerShape(SignalTheme.shapes.full),
    ) {
        Text(
            text = tag.label,
            modifier = Modifier.padding(horizontal = SignalSpacing.x2, vertical = SignalSpacing.x1),
            color = foreground,
            style = SignalTheme.typography.statusPill,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun SignalConsentGlyph(
    kind: SignalConsentKind,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    val color = when (kind) {
        SignalConsentKind.DataAccess -> colors.bankPrimary
        SignalConsentKind.RecurringPayment -> colors.bankSecondary
        SignalConsentKind.OneTimeApproval -> colors.bankAccent
    }
    val content = when (kind) {
        SignalConsentKind.DataAccess -> colors.textInverse
        SignalConsentKind.RecurringPayment -> colors.onSecondary
        SignalConsentKind.OneTimeApproval -> colors.onTertiary
    }
    Box(
        modifier = modifier
            .size(42.dp)
            .background(color, RoundedCornerShape(SignalTheme.shapes.sm)),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.size(21.dp)) {
            val stroke = Stroke(width = 2.2.dp.toPx())
            val c = content
            when (kind) {
                SignalConsentKind.DataAccess -> {
                    drawCircle(color = c, radius = size.minDimension * 0.34f, style = stroke)
                    drawLine(c, Offset(size.width * 0.68f, size.height * 0.68f), Offset(size.width, size.height), stroke.width)
                }
                SignalConsentKind.RecurringPayment -> {
                    drawArc(c, 40f, 250f, false, style = stroke)
                    drawLine(c, Offset(size.width * 0.72f, size.height * 0.04f), Offset(size.width * 0.94f, size.height * 0.06f), stroke.width)
                    drawLine(c, Offset(size.width * 0.72f, size.height * 0.04f), Offset(size.width * 0.76f, size.height * 0.26f), stroke.width)
                }
                SignalConsentKind.OneTimeApproval -> {
                    drawLine(c, Offset(size.width * 0.18f, size.height * 0.54f), Offset(size.width * 0.42f, size.height * 0.78f), stroke.width)
                    drawLine(c, Offset(size.width * 0.42f, size.height * 0.78f), Offset(size.width * 0.84f, size.height * 0.24f), stroke.width)
                }
            }
        }
    }
}
