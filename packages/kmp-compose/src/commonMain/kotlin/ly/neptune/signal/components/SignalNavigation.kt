package ly.neptune.signal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalComponentMetrics
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
    val titleColor = if (colors.dark) colors.onSurface else colors.bankPrimary

    Box(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .heightIn(min = if (subtitle == null) 64.dp else 68.dp)
            .background(colors.surfacePaper)
            .padding(horizontal = SignalSpacing.x4, vertical = SignalSpacing.x2),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .widthIn(min = SignalComponentMetrics.iconButtonBox),
            contentAlignment = Alignment.CenterStart,
        ) {
            navigation?.invoke()
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 62.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = title,
                color = titleColor,
                style = typography.pageTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    color = colors.textSecondary,
                    style = typography.rowMeta,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .widthIn(min = SignalComponentMetrics.iconButtonBox),
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
    val dimensions = SignalTheme.dimensions
    val navShape = RoundedCornerShape(32.dp)
    val navContainer = if (colors.dark) colors.surfaceContainerHigh else colors.bankPrimary
    val navBorder = if (colors.dark) {
        colors.outlineVariant.copy(alpha = 0.78f)
    } else {
        colors.bankSecondary.copy(alpha = 0.34f)
    }
    val activeContainer = if (colors.dark) {
        colors.primaryContainer.copy(alpha = 0.84f)
    } else {
        colors.surfaceCard.copy(alpha = 0.96f)
    }
    val activeContent = if (colors.dark) colors.onSurface else colors.bankPrimary
    val inactiveContent = if (colors.dark) colors.onSurfaceVariant else colors.textInverse.copy(alpha = 0.74f)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = dimensions.navHeight)
            .background(Color.Transparent)
            .navigationBarsPadding()
            .padding(horizontal = SignalSpacing.x4, vertical = SignalSpacing.x2)
            .clip(navShape)
            .background(navContainer.copy(alpha = if (colors.dark) 0.96f else 0.98f))
            .border(1.dp, navBorder, navShape)
            .padding(horizontal = SignalSpacing.x2, vertical = SignalSpacing.x1),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x1),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items.forEach { item ->
            val active = item.key == activeKey
            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(58.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(if (active) activeContainer else Color.Transparent)
                    .clickable { onSelected(item.key) }
                    .padding(horizontal = SignalSpacing.x1, vertical = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                CompositionLocalProvider(LocalContentColor provides if (active) activeContent else inactiveContent) {
                    Box(
                        modifier = Modifier.size(SignalComponentMetrics.standardIcon),
                        contentAlignment = Alignment.Center,
                    ) {
                        item.icon()
                    }
                }
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = item.label,
                    color = if (active) activeContent else inactiveContent,
                    style = typography.rowMeta.copy(fontWeight = FontWeight.Black),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
