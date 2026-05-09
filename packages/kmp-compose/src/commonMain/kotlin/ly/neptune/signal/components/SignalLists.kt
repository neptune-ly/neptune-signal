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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
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
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.surfaceContainerLow, RoundedCornerShape(shapes.lg))
            .border(BorderStroke(1.dp, colors.outlineVariant), RoundedCornerShape(shapes.lg))
            .padding(SignalSpacing.x1),
        content = content,
    )
}

@Composable
fun SignalListDivider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier.padding(horizontal = SignalSpacing.x3),
        color = SignalTheme.colors.outlineVariant,
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
            .background(Color.Transparent)
            .padding(SignalSpacing.x3),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leading != null) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(colors.primaryContainer, RoundedCornerShape(shapes.sm)),
                contentAlignment = Alignment.Center,
            ) {
                leading()
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
    leading: (@Composable () -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    SignalListItem(
        title = title,
        metadata = metadata,
        modifier = modifier,
        onClick = onClick,
        leading = leading,
        trailing = {
            Text(
                text = amount,
                color = if (incoming) colors.success else colors.error,
                style = SignalTheme.typography.rowTitle,
                maxLines = 1,
            )
        },
    )
}
