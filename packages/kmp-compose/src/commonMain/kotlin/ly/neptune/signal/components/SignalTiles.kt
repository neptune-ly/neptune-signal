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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalRadius
import ly.neptune.signal.theme.SignalSize
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
    val shape = RoundedCornerShape(SignalRadius.lg)
    val click = if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier

    Row(
        modifier = modifier
            .then(click)
            .fillMaxWidth()
            .heightIn(min = SignalSize.tileMinHeight)
            .background(colors.surfaceCard, shape)
            .border(BorderStroke(1.dp, colors.borderDefault), shape)
            .padding(SignalSpacing.x4),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leading != null) Box(contentAlignment = Alignment.Center) { leading() }
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x1)) {
            Text(text = title, color = colors.textPrimary, style = typography.rowTitle, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = subtitle, color = colors.textSecondary, style = typography.rowMeta, maxLines = 2, overflow = TextOverflow.Ellipsis)
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

