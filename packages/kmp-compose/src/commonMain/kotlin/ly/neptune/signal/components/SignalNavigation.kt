package ly.neptune.signal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalRadius
import ly.neptune.signal.theme.SignalSize
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

data class SignalNavItem(
    val key: String,
    val label: String,
    val icon: @Composable () -> Unit,
)

@Composable
fun SignalTopBar(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    navigation: (@Composable () -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography

    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 64.dp)
            .background(colors.surfacePaper)
            .padding(horizontal = SignalSpacing.x4, vertical = SignalSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (navigation != null) {
            navigation()
            Spacer(Modifier.width(SignalSpacing.x2))
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = title, color = colors.bankPrimary, style = typography.pageTitle)
            if (subtitle != null) {
                Text(text = subtitle, color = colors.textSecondary, style = typography.rowMeta)
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x1),
            verticalAlignment = Alignment.CenterVertically,
            content = actions,
        )
    }
}

@Composable
fun SignalBottomNav(
    items: List<SignalNavItem>,
    activeKey: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography

    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = SignalSize.navHeight)
            .background(colors.surfaceCard)
            .padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x2),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items.forEach { item ->
            val active = item.key == activeKey
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(SignalRadius.lg))
                    .background(if (active) colors.surfaceSoft else Color.Transparent)
                    .padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x2),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Box(contentAlignment = Alignment.Center) { item.icon() }
                Text(
                    text = item.label,
                    color = if (active) colors.bankPrimary else colors.textSecondary,
                    style = typography.statusPill,
                )
            }
        }
    }
}

