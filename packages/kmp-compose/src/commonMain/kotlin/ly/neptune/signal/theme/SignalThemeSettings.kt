package ly.neptune.signal.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp

enum class SignalAppearanceMode {
    System,
    Light,
    Dark,
    Black,
}

fun SignalAppearanceMode.resolve(systemDark: Boolean): SignalColorMode = when (this) {
    SignalAppearanceMode.System -> if (systemDark) SignalColorMode.Dark else SignalColorMode.Light
    SignalAppearanceMode.Light -> SignalColorMode.Light
    SignalAppearanceMode.Dark -> SignalColorMode.Dark
    SignalAppearanceMode.Black -> SignalColorMode.Black
}

fun SignalColorMode.toAppearanceMode(): SignalAppearanceMode = when (this) {
    SignalColorMode.Light -> SignalAppearanceMode.Light
    SignalColorMode.Dark -> SignalAppearanceMode.Dark
    SignalColorMode.Black -> SignalAppearanceMode.Black
}

@Immutable
data class SignalThemeOverrides(
    val primary: Color? = null,
    val secondary: Color? = null,
    val accent: Color? = null,
    val ink: Color? = null,
    val lightSurface: Color? = null,
    val darkSurface: Color? = null,
    val blackSurface: Color? = null,
    val primaryContainer: Color? = null,
    val secondaryContainer: Color? = null,
    val tertiaryContainer: Color? = null,
    val surfaceContainerLowest: Color? = null,
    val surfaceContainerLow: Color? = null,
    val surfaceContainer: Color? = null,
    val surfaceContainerHigh: Color? = null,
    val surfaceContainerHighest: Color? = null,
)

@Immutable
data class SignalThemeSettings(
    val brand: SignalBrand = SignalBrandDefaults.Neptune,
    val appearance: SignalAppearanceMode = SignalAppearanceMode.System,
    val overrides: SignalThemeOverrides = SignalThemeOverrides(),
    val typography: SignalTypography = SignalTypography(),
    val shapes: SignalShapes = SignalShapes(),
    val dimensions: SignalDimensions = SignalDimensions(),
) {
    fun resolvedMode(systemDark: Boolean): SignalColorMode = appearance.resolve(systemDark)

    fun toThemeConfig(systemDark: Boolean): SignalThemeConfig {
        val mode = resolvedMode(systemDark)
        return SignalThemeConfig(
            brand = overrides.applyTo(brand),
            mode = mode,
            colors = overrides.resolveColors(brand, mode),
            typography = typography,
            shapes = shapes,
            dimensions = dimensions,
        )
    }
}

fun SignalBrand.withThemeOverrides(overrides: SignalThemeOverrides): SignalBrand = overrides.applyTo(this)

private fun SignalThemeOverrides.applyTo(brand: SignalBrand): SignalBrand = brand.copy(
    primary = primary ?: brand.primary,
    secondary = secondary ?: brand.secondary,
    accent = accent ?: brand.accent,
    ink = ink ?: brand.ink,
    surface = lightSurface ?: brand.surface,
)

private fun SignalThemeOverrides.resolveColors(
    brand: SignalBrand,
    mode: SignalColorMode,
): SignalColors {
    val resolvedBrand = applyTo(brand)
    val resolvedSurface = when (mode) {
        SignalColorMode.Light -> lightSurface ?: resolvedBrand.surface
        SignalColorMode.Dark -> darkSurface ?: SignalColorDefaults.NeptuneDark.surface
        SignalColorMode.Black -> blackSurface ?: SignalColorDefaults.NeptuneBlack.surface
    }
    return SignalColorDefaults.whiteLabel(
        primary = resolvedBrand.primary,
        secondary = resolvedBrand.secondary,
        accent = resolvedBrand.accent,
        ink = resolvedBrand.ink,
        surface = resolvedSurface,
        primaryContainer = primaryContainer
            ?: defaultPrimaryContainer(mode, resolvedBrand.primary, resolvedSurface),
        secondaryContainer = secondaryContainer
            ?: defaultSecondaryContainer(mode, resolvedBrand.secondary, resolvedSurface),
        tertiaryContainer = tertiaryContainer
            ?: defaultTertiaryContainer(mode, resolvedBrand.accent, resolvedSurface),
        dark = mode == SignalColorMode.Dark,
        black = mode == SignalColorMode.Black,
    ).copy(
        surface = resolvedSurface,
        surfacePaper = resolvedSurface,
        surfaceContainerLowest = surfaceContainerLowest
            ?: defaultSurfaceContainerLowest(mode),
        surfaceContainerLow = surfaceContainerLow
            ?: defaultSurfaceContainerLow(mode),
        surfaceContainer = surfaceContainer
            ?: defaultSurfaceContainer(mode),
        surfaceContainerHigh = surfaceContainerHigh
            ?: defaultSurfaceContainerHigh(mode),
        surfaceContainerHighest = surfaceContainerHighest
            ?: defaultSurfaceContainerHighest(mode),
        surfaceSoft = surfaceContainer ?: defaultSurfaceContainer(mode),
        surfaceCard = surfaceContainerLowest ?: defaultSurfaceContainerLowest(mode),
        borderDefault = defaultBorder(mode),
    )
}

private fun defaultSurfaceContainerLowest(mode: SignalColorMode): Color = when (mode) {
    SignalColorMode.Light -> SignalColors().surfaceContainerLowest
    SignalColorMode.Dark -> SignalColorDefaults.NeptuneDark.surfaceContainerLowest
    SignalColorMode.Black -> SignalColorDefaults.NeptuneBlack.surfaceContainerLowest
}

private fun defaultSurfaceContainerLow(mode: SignalColorMode): Color = when (mode) {
    SignalColorMode.Light -> SignalColors().surfaceContainerLow
    SignalColorMode.Dark -> SignalColorDefaults.NeptuneDark.surfaceContainerLow
    SignalColorMode.Black -> SignalColorDefaults.NeptuneBlack.surfaceContainerLow
}

private fun defaultSurfaceContainer(mode: SignalColorMode): Color = when (mode) {
    SignalColorMode.Light -> SignalColors().surfaceContainer
    SignalColorMode.Dark -> SignalColorDefaults.NeptuneDark.surfaceContainer
    SignalColorMode.Black -> SignalColorDefaults.NeptuneBlack.surfaceContainer
}

private fun defaultSurfaceContainerHigh(mode: SignalColorMode): Color = when (mode) {
    SignalColorMode.Light -> SignalColors().surfaceContainerHigh
    SignalColorMode.Dark -> SignalColorDefaults.NeptuneDark.surfaceContainerHigh
    SignalColorMode.Black -> SignalColorDefaults.NeptuneBlack.surfaceContainerHigh
}

private fun defaultSurfaceContainerHighest(mode: SignalColorMode): Color = when (mode) {
    SignalColorMode.Light -> SignalColors().surfaceContainerHighest
    SignalColorMode.Dark -> SignalColorDefaults.NeptuneDark.surfaceContainerHighest
    SignalColorMode.Black -> SignalColorDefaults.NeptuneBlack.surfaceContainerHighest
}

private fun defaultBorder(mode: SignalColorMode): Color = when (mode) {
    SignalColorMode.Light -> SignalColors().borderDefault
    SignalColorMode.Dark -> SignalColorDefaults.NeptuneDark.borderDefault
    SignalColorMode.Black -> SignalColorDefaults.NeptuneBlack.borderDefault
}

private fun defaultPrimaryContainer(mode: SignalColorMode, primary: Color, surface: Color): Color = when (mode) {
    SignalColorMode.Light -> lerp(surface, primary, 0.10f)
    SignalColorMode.Dark -> lerp(surface, primary, 0.30f)
    SignalColorMode.Black -> lerp(surface, primary, 0.38f)
}

private fun defaultSecondaryContainer(mode: SignalColorMode, secondary: Color, surface: Color): Color = when (mode) {
    SignalColorMode.Light -> lerp(surface, secondary, 0.14f)
    SignalColorMode.Dark -> lerp(surface, secondary, 0.26f)
    SignalColorMode.Black -> lerp(surface, secondary, 0.32f)
}

private fun defaultTertiaryContainer(mode: SignalColorMode, accent: Color, surface: Color): Color = when (mode) {
    SignalColorMode.Light -> lerp(surface, accent, 0.12f)
    SignalColorMode.Dark -> lerp(surface, accent, 0.24f)
    SignalColorMode.Black -> lerp(surface, accent, 0.30f)
}
