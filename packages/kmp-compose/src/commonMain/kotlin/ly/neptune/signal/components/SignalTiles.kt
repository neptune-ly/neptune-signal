package ly.neptune.signal.components

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

@Composable
fun SignalTile(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    leading: (@Composable () -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val shape = RoundedCornerShape(22.dp)
    val borderColor = colors.outlineVariant.copy(alpha = if (colors.dark) 0.66f else 0.58f)
    val leadingColor = if (colors.dark) colors.onSurface else colors.bankPrimary
    val click = if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier

    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 68.dp)
            .clip(shape)
            .background(colors.surfaceCard.copy(alpha = if (colors.dark) 0.86f else 0.96f), shape)
            .then(click)
            .border(BorderStroke(1.dp, borderColor), shape)
            .padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x2),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leading != null) {
            CompositionLocalProvider(LocalContentColor provides leadingColor) {
                Box(modifier = Modifier.size(36.dp), contentAlignment = Alignment.Center) { leading() }
            }
        }
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x1)) {
            Text(text = title, color = colors.onSurface, style = typography.rowTitle, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = subtitle, color = colors.onSurfaceVariant, style = typography.rowMeta, maxLines = 2, overflow = TextOverflow.Ellipsis)
        }
        trailing?.invoke()
    }
}

@Composable
fun SignalVoucherTile(
    provider: String,
    category: String,
    startingPrice: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    mark: @Composable () -> Unit = {},
) {
    SignalTile(
        title = provider,
        subtitle = "$category · from $startingPrice",
        modifier = modifier,
        onClick = onClick,
        leading = mark,
    )
}

@Composable
fun SignalServiceTile(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: @Composable () -> Unit = {},
) {
    SignalTile(
        title = title,
        subtitle = description,
        modifier = modifier,
        onClick = onClick,
        leading = icon,
    )
}
