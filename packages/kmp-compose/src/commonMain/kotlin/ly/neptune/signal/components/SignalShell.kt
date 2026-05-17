package ly.neptune.signal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalComponentMetrics
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

@Composable
fun SignalAppShell(
    title: String,
    activeRoot: String,
    navItems: List<SignalNavItem>,
    onRootSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    showTopBar: Boolean = true,
    showBottomNav: Boolean = true,
    centerNavAction: SignalNavCenterAction? = null,
    bottomNavVisualLanguage: SignalBottomNavVisualLanguage = SignalBottomNavVisualLanguage.SignalClassic,
    navigation: (@Composable () -> Unit)? = null,
    topBarNavigation: SignalTopBarNavigation? = null,
    onNavigate: () -> Unit = {},
    actions: @Composable androidx.compose.foundation.layout.RowScope.() -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    val colors = SignalTheme.colors

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colors.surface),
    ) {
        content(
            PaddingValues(
                top = if (showTopBar) SignalComponentMetrics.topBarOverlayReserve else 0.dp,
                bottom = if (showBottomNav) {
                    SignalComponentMetrics.bottomNavHeight + SignalSpacing.x8 + if (centerNavAction != null) SignalSpacing.x4 else 0.dp
                } else {
                    SignalSpacing.x4
                },
            ),
        )
        if (showTopBar) {
            Box(
                modifier = Modifier.align(Alignment.TopCenter),
            ) {
                val resolvedTopBarNavigation = topBarNavigation ?: if (navigation == null) {
                    SignalTopBarNavigation.None
                } else {
                    SignalTopBarNavigation.Back
                }
                if (bottomNavVisualLanguage == SignalBottomNavVisualLanguage.Prism) {
                    SignalPrismTopBar(
                        title = title,
                        subtitle = subtitle,
                        variant = SignalPrismTopBarVariant.Static,
                        navigation = navigation ?: when (resolvedTopBarNavigation) {
                            SignalTopBarNavigation.None -> null
                            SignalTopBarNavigation.Back -> {
                                {
                                    SignalIconButton(onClick = onNavigate) {
                                        SignalIcon(SignalIconName.ArrowStart, size = 24.dp)
                                    }
                                }
                            }
                            SignalTopBarNavigation.Close -> {
                                {
                                    SignalIconButton(onClick = onNavigate) {
                                        SignalIcon(SignalIconName.ArrowStart, size = 24.dp)
                                    }
                                }
                            }
                        },
                        actions = actions,
                    )
                } else {
                    SignalContractTopBar(
                        contract = SignalTopBarContract(
                            title = title,
                            subtitle = subtitle,
                            navigation = topBarNavigation ?: if (navigation == null) SignalTopBarNavigation.None else SignalTopBarNavigation.Back,
                            type = SignalTopBarType.CenterAligned,
                        ),
                        onNavigate = onNavigate,
                        navigationOverride = navigation,
                        actions = actions,
                    )
                }
            }
        }
        if (showBottomNav) {
            when (bottomNavVisualLanguage) {
                SignalBottomNavVisualLanguage.SignalClassic -> SignalBottomNav(
                    items = navItems,
                    activeKey = activeRoot,
                    onSelected = onRootSelected,
                    centerAction = centerNavAction,
                    modifier = Modifier.align(Alignment.BottomCenter),
                )
                SignalBottomNavVisualLanguage.Prism -> SignalPrismFloatingNav(
                    items = navItems,
                    activeKey = activeRoot,
                    onSelected = onRootSelected,
                    centerAction = centerNavAction,
                    modifier = Modifier.align(Alignment.BottomCenter),
                )
            }
        }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignalRefreshContainer(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    val state = rememberPullToRefreshState()
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = modifier,
        state = state,
        contentAlignment = Alignment.TopCenter,
        indicator = {
            PullToRefreshDefaults.Indicator(
                modifier = Modifier.align(Alignment.TopCenter),
                isRefreshing = isRefreshing,
                containerColor = SignalTheme.colors.surfaceCard,
                color = SignalTheme.colors.bankSecondary,
                state = state,
            )
        },
    ) {
        content()
    }
}
