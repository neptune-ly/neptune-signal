package ly.neptune.signal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ly.neptune.signal.theme.SignalTheme

@Composable
fun SignalAppShell(
    title: String,
    activeRoot: String,
    navItems: List<SignalNavItem>,
    onRootSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    navigation: (@Composable () -> Unit)? = null,
    actions: @Composable androidx.compose.foundation.layout.RowScope.() -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    val colors = SignalTheme.colors

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colors.surface),
    ) {
        SignalTopBar(
            title = title,
            subtitle = subtitle,
            navigation = navigation,
            actions = actions,
        )
        Box(modifier = Modifier.weight(1f)) {
            content(PaddingValues())
        }
        SignalBottomNav(
            items = navItems,
            activeKey = activeRoot,
            onSelected = onRootSelected,
        )
    }
}

@Composable
fun SignalScreen(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(SignalTheme.colors.surface)
            .padding(ly.neptune.signal.theme.SignalSpacing.x4),
    ) {
        content()
    }
}
