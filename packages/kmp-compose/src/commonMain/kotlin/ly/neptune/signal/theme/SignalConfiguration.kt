package ly.neptune.signal.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class SignalColorMode {
    Light,
    Dark,
    Black,
}

@Immutable
data class SignalBrand(
    val key: String,
    val label: String,
    val primary: Color,
    val secondary: Color = Color(0xFF00A8AE),
    val accent: Color = Color(0xFFEB4E4D),
    val ink: Color = Color(0xFF071C2E),
    val surface: Color = Color(0xFFFAFCFC),
)

object SignalBrandDefaults {
    val Neptune = SignalBrand(
        key = "neptune",
        label = "Neptune.",
        primary = Color(0xFF07315F),
        secondary = Color(0xFF00A8AE),
        accent = Color(0xFFEB4E4D),
    )

    val ATIB = SignalBrand(
        key = "atib",
        label = "ATIB",
        primary = Color(0xFF981B1E),
        secondary = Color(0xFFFFFFFF),
        accent = Color(0xFFD7282F),
        ink = Color(0xFF1C1B1F),
    )

    val Yaqeen = SignalBrand(
        key = "yaqeen",
        label = "Yaqeen",
        primary = Color(0xFF4C3324),
        secondary = Color(0xFFC7A15D),
        accent = Color(0xFF8E6A2F),
        ink = Color(0xFF231A14),
    )

    val Andalus = SignalBrand(
        key = "andalus",
        label = "Andalus",
        primary = Color(0xFF07315F),
        secondary = Color(0xFF00A8AE),
        accent = Color(0xFF3BC1EE),
    )

    val NCB = SignalBrand(
        key = "ncb",
        label = "NCB",
        primary = Color(0xFF0B5A3A),
        secondary = Color(0xFF35B56F),
        accent = Color(0xFFD6B15B),
        ink = Color(0xFF072019),
    )

    val NUB = SignalBrand(
        key = "nub",
        label = "NUB",
        primary = Color(0xFF1E2430),
        secondary = Color(0xFFD5A332),
        accent = Color(0xFFF0C75E),
        ink = Color(0xFF11141A),
    )

    val Samples = listOf(Neptune, ATIB, Yaqeen, Andalus, NCB, NUB)
}

@Immutable
data class SignalShapes(
    val xs: Dp = 8.dp,
    val sm: Dp = 12.dp,
    val md: Dp = 16.dp,
    val lg: Dp = 20.dp,
    val xl: Dp = 28.dp,
    val full: Dp = 999.dp,
)

@Immutable
data class SignalDimensions(
    val touchTarget: Dp = SignalComponentMetrics.touchTarget,
    val iconButton: Dp = SignalComponentMetrics.iconButtonBox,
    val buttonHeight: Dp = SignalComponentMetrics.buttonHeight,
    val navHeight: Dp = SignalComponentMetrics.bottomNavHeight,
    val fieldHeight: Dp = SignalComponentMetrics.textFieldHeight,
    val tileMinHeight: Dp = 78.dp,
    val rowMinHeight: Dp = SignalComponentMetrics.rowMinHeight,
    val accountRowMinHeight: Dp = SignalComponentMetrics.accountRowMinHeight,
    val accountCardMinHeight: Dp = SignalComponentMetrics.accountCarouselHeight,
    val cardFaceMinHeight: Dp = SignalComponentMetrics.cardFaceListHeight,
)

@Immutable
data class SignalThemeConfig(
    val brand: SignalBrand = SignalBrandDefaults.Neptune,
    val mode: SignalColorMode = SignalColorMode.Light,
    val colors: SignalColors? = null,
    val typography: SignalTypography = SignalTypography(),
    val shapes: SignalShapes = SignalShapes(),
    val dimensions: SignalDimensions = SignalDimensions(),
)

fun SignalColorDefaults.fromBrand(
    brand: SignalBrand,
    mode: SignalColorMode = SignalColorMode.Light,
): SignalColors = when (mode) {
    SignalColorMode.Light -> whiteLabel(
        primary = brand.primary,
        secondary = brand.secondary,
        accent = brand.accent,
        ink = brand.ink,
        surface = brand.surface,
    )
    SignalColorMode.Dark -> whiteLabel(
        primary = brand.primary,
        secondary = brand.secondary,
        accent = brand.accent,
        dark = true,
    )
    SignalColorMode.Black -> whiteLabel(
        primary = brand.primary,
        secondary = brand.secondary,
        accent = brand.accent,
        black = true,
    )
}
