package ly.neptune.signal.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

@Composable
fun SignalListGroup(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = SignalTheme.colors
    val shapes = SignalTheme.shapes
    val shape = RoundedCornerShape(22.dp)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.surfaceCard.copy(alpha = if (colors.dark) 0.82f else 0.90f), shape)
            .border(BorderStroke(1.dp, colors.outlineVariant.copy(alpha = if (colors.dark) 0.42f else 0.40f)), shape)
            .padding(SignalSpacing.x1),
        content = content,
    )
}

@Composable
fun SignalListDivider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier.padding(horizontal = SignalSpacing.x3),
        color = SignalTheme.colors.outlineVariant.copy(alpha = 0.52f),
    )
}

@Composable
fun SignalListItem(
    title: String,
    metadata: String? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
    leading: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val shapes = SignalTheme.shapes
    val clickModifier = if (onClick != null) Modifier.clickable(enabled = enabled, onClick = onClick) else Modifier

    Row(
        modifier = modifier
            .then(clickModifier)
            .fillMaxWidth()
            .heightIn(min = SignalTheme.dimensions.rowMinHeight)
            .clip(RoundedCornerShape(shapes.md))
            .background(if (onClick != null) colors.surfaceContainerLowest.copy(alpha = if (colors.dark) 0.34f else 0.54f) else Color.Transparent)
            .padding(SignalSpacing.x3),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leading != null) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(shapes.sm))
                    .background(colors.surfaceContainer, RoundedCornerShape(shapes.sm)),
                contentAlignment = Alignment.Center,
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CompositionLocalProvider(LocalContentColor provides colors.onSurfaceVariant) {
                        Box(modifier = Modifier.size(20.dp), contentAlignment = Alignment.Center) {
                            leading()
                        }
                    }
                }
            }
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
            if (metadata != null) {
                Text(
                    text = metadata,
                    color = colors.onSurfaceVariant,
                    style = typography.rowMeta,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        trailing?.invoke()
    }
}

@Composable
fun SignalTransactionRow(
    title: String,
    metadata: String,
    amount: String,
    modifier: Modifier = Modifier,
    incoming: Boolean = false,
    onClick: (() -> Unit)? = null,
    leading: (@Composable () -> Unit)? = { SignalDefaultTransactionGlyph(incoming) },
) {
    val colors = SignalTheme.colors
    SignalListItem(
        title = title,
        metadata = metadata,
        modifier = modifier,
        onClick = onClick,
        leading = leading,
        trailing = {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Text(
                    text = amount,
                    modifier = Modifier.widthIn(min = 88.dp),
                    color = if (incoming) colors.success else colors.error,
                style = SignalTheme.typography.screenTitle.copy(fontFeatureSettings = "tnum"),
                    textAlign = TextAlign.End,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                )
            }
        },
    )
}

@Composable
private fun SignalDefaultTransactionGlyph(incoming: Boolean) {
    val colors = SignalTheme.colors
    androidx.compose.foundation.Canvas(modifier = Modifier.size(18.dp)) {
        val color = if (incoming) colors.success else colors.onSurfaceVariant
        val stroke = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.4.dp.toPx(), cap = androidx.compose.ui.graphics.StrokeCap.Round)
        drawLine(
            color = color,
            start = androidx.compose.ui.geometry.Offset(size.width * 0.22f, size.height * if (incoming) 0.72f else 0.28f),
            end = androidx.compose.ui.geometry.Offset(size.width * 0.78f, size.height * if (incoming) 0.18f else 0.82f),
            strokeWidth = stroke.width,
            cap = androidx.compose.ui.graphics.StrokeCap.Round,
        )
        drawLine(
            color = color,
            start = androidx.compose.ui.geometry.Offset(size.width * 0.78f, size.height * if (incoming) 0.18f else 0.82f),
            end = androidx.compose.ui.geometry.Offset(size.width * 0.76f, size.height * if (incoming) 0.48f else 0.52f),
            strokeWidth = stroke.width,
            cap = androidx.compose.ui.graphics.StrokeCap.Round,
        )
        drawLine(
            color = color,
            start = androidx.compose.ui.geometry.Offset(size.width * 0.78f, size.height * if (incoming) 0.18f else 0.82f),
            end = androidx.compose.ui.geometry.Offset(size.width * 0.48f, size.height * if (incoming) 0.20f else 0.80f),
            strokeWidth = stroke.width,
            cap = androidx.compose.ui.graphics.StrokeCap.Round,
        )
    }
}
