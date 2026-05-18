package ly.neptune.signal.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Immutable

enum class SignalSizeClass {
    Compact,
    TallCompact,
    WideCompact,
}

object SignalMetrics {
    val grid: Dp = 4.dp
    val screen: SignalScreenMetrics = SignalScreenMetrics
    val component: SignalComponentMetrics = SignalComponentMetrics
    val auth: SignalAuthMetrics = SignalAuthMetrics
    val motion: SignalMotionMetrics = SignalMotionMetrics
}

object SignalScreenMetrics {
    val compactHorizontalPadding: Dp = 16.dp
    val wideCompactHorizontalPadding: Dp = 20.dp
    val verticalPadding: Dp = 22.dp
    val contentMaxWidth: Dp = 430.dp
    val cardListGap: Dp = 12.dp
    val sectionGap: Dp = 16.dp
    val compactScrollBreakpoint: Dp = 760.dp
}

object SignalComponentMetrics {
    val touchTarget: Dp = 48.dp
    val standardIcon: Dp = 24.dp
    val rowIconGlyph: Dp = 22.dp
    val bottomNavIcon: Dp = 22.dp
    val smallActionIcon: Dp = 18.dp
    val iconButtonBox: Dp = 48.dp
    val topBarOverlayReserve: Dp = 76.dp
    val textFieldHeight: Dp = 58.dp
    val buttonHeight: Dp = 52.dp
    val bottomNavHeight: Dp = 74.dp
    val bottomNavCenterAction: Dp = 68.dp
    val bottomNavCenterActionPlate: Dp = 40.dp
    val bottomNavCenterSlot: Dp = 76.dp
    val rowMinHeight: Dp = 58.dp
    val transactionRowMinHeight: Dp = 72.dp
    val accountRowMinHeight: Dp = 76.dp
    val accountCarouselHeight: Dp = 232.dp
    val cardFaceListHeight: Dp = 160.dp
    val cardFaceDetailHeight: Dp = 190.dp
}

object SignalAuthMetrics {
    const val contentHeightRatio: Float = 0.64f
    val contentGap: Dp = 10.dp
    val compactContentGap: Dp = 8.dp
    val headerHeight: Dp = 126.dp
    val headerIconBox: Dp = 40.dp
    val headerIconRadius: Dp = 13.dp
    val headerGlyph: Dp = SignalComponentMetrics.rowIconGlyph
    val headerLogoBox: Dp = 78.dp
    val headerLogoRadius: Dp = 24.dp
    val stateRadius: Dp = 20.dp
    val statePadding: Dp = 12.dp
    val stateIconBox: Dp = 38.dp
    val stateIconRadius: Dp = 13.dp
    val stateGlyph: Dp = SignalComponentMetrics.rowIconGlyph
    val stateOrbitSize: Dp = 108.dp
    val stateOrbitRadius: Dp = 52.dp
    val stateOrbitStroke: Dp = 16.dp
    val panelPadding: Dp = 16.dp
    val panelGap: Dp = 12.dp
    val panelRadius: Dp = 20.dp
    val actionHeight: Dp = 52.dp
    val noteIconBox: Dp = 36.dp
    val noteIconRadius: Dp = 12.dp
    val noteGlyph: Dp = SignalComponentMetrics.smallActionIcon
    val onboardingMinHeight: Dp = 580.dp
    val onboardingCompactMinHeight: Dp = 520.dp
    val onboardingHeroHeight: Dp = 238.dp
    val onboardingCompactHeroHeight: Dp = 204.dp
    val onboardingShortDeviceBreakpoint: Dp = 780.dp
}

object SignalMotionMetrics {
    const val standardRouteMillis: Int = 240
    const val sharedElementMillis: Int = 420
    const val resultMillis: Int = 300
    const val pressFastMillis: Int = 80
    const val pressMillis: Int = 120
}

@Immutable
data class SignalAuthAdaptiveState(
    val compactHeight: Boolean,
    val keyboardVisible: Boolean,
    val fillContent: Boolean,
)
