package ly.neptune.signal.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

private fun signalTextStyle(
    fontFamily: FontFamily?,
    fontSize: TextUnit,
    lineHeight: TextUnit,
    fontWeight: FontWeight,
): TextStyle = TextStyle(
    fontFamily = fontFamily,
    fontSize = fontSize,
    lineHeight = lineHeight,
    fontWeight = fontWeight,
)

@Immutable
data class SignalTypography(
    val fontFamily: FontFamily? = null,
    val displayLarge: TextStyle = signalTextStyle(fontFamily, 42.sp, 44.sp, FontWeight.Black),
    val displayMedium: TextStyle = signalTextStyle(fontFamily, 36.sp, 38.sp, FontWeight.Black),
    val displaySmall: TextStyle = signalTextStyle(fontFamily, 32.sp, 34.sp, FontWeight.ExtraBold),
    val headlineLarge: TextStyle = signalTextStyle(fontFamily, 32.sp, 38.sp, FontWeight.ExtraBold),
    val headlineMedium: TextStyle = signalTextStyle(fontFamily, 28.sp, 34.sp, FontWeight.ExtraBold),
    val headlineSmall: TextStyle = signalTextStyle(fontFamily, 24.sp, 30.sp, FontWeight.Bold),
    val titleLarge: TextStyle = signalTextStyle(fontFamily, 19.sp, 26.sp, FontWeight.Bold),
    val titleMedium: TextStyle = signalTextStyle(fontFamily, 16.sp, 23.sp, FontWeight.Bold),
    val titleSmall: TextStyle = signalTextStyle(fontFamily, 14.5.sp, 21.sp, FontWeight.SemiBold),
    val bodyLarge: TextStyle = signalTextStyle(fontFamily, 16.sp, 25.sp, FontWeight.Medium),
    val bodyMedium: TextStyle = signalTextStyle(fontFamily, 14.5.sp, 22.sp, FontWeight.Medium),
    val bodySmall: TextStyle = signalTextStyle(fontFamily, 12.5.sp, 18.sp, FontWeight.Medium),
    val labelLarge: TextStyle = signalTextStyle(fontFamily, 15.5.sp, 21.sp, FontWeight.Bold),
    val labelMedium: TextStyle = signalTextStyle(fontFamily, 13.5.sp, 18.sp, FontWeight.SemiBold),
    val labelSmall: TextStyle = signalTextStyle(fontFamily, 12.sp, 16.sp, FontWeight.SemiBold),
    val balance: TextStyle = signalTextStyle(fontFamily, 34.sp, 39.sp, FontWeight.ExtraBold).copy(fontFeatureSettings = "tnum"),
    val displayBalance: TextStyle = balance,
    val pageTitle: TextStyle = signalTextStyle(fontFamily, 21.sp, 29.sp, FontWeight.ExtraBold),
    val screenTitle: TextStyle = signalTextStyle(fontFamily, 18.sp, 25.sp, FontWeight.Bold),
    val sectionTitle: TextStyle = signalTextStyle(fontFamily, 15.5.sp, 22.sp, FontWeight.Bold),
    val rowTitle: TextStyle = signalTextStyle(fontFamily, 14.5.sp, 21.sp, FontWeight.SemiBold),
    val rowMeta: TextStyle = signalTextStyle(fontFamily, 12.5.sp, 18.sp, FontWeight.Medium),
    val fieldLabel: TextStyle = signalTextStyle(fontFamily, 12.5.sp, 17.sp, FontWeight.SemiBold),
    val fieldValue: TextStyle = signalTextStyle(fontFamily, 16.sp, 23.sp, FontWeight.Bold),
    val button: TextStyle = signalTextStyle(fontFamily, 15.5.sp, 21.sp, FontWeight.Bold),
    val statusPill: TextStyle = signalTextStyle(fontFamily, 11.5.sp, 16.sp, FontWeight.SemiBold),
    val prismDisplayFinancial: TextStyle = signalTextStyle(fontFamily, 33.sp, 39.sp, FontWeight.ExtraBold).copy(fontFeatureSettings = "tnum"),
    val prismTitleHero: TextStyle = signalTextStyle(fontFamily, 23.sp, 31.sp, FontWeight.Bold),
    val prismTitleSection: TextStyle = signalTextStyle(fontFamily, 16.5.sp, 23.sp, FontWeight.Bold),
    val prismTitleCard: TextStyle = signalTextStyle(fontFamily, 18.sp, 25.sp, FontWeight.SemiBold),
    val prismBodyPrimary: TextStyle = signalTextStyle(fontFamily, 15.sp, 23.sp, FontWeight.Medium),
    val prismBodySecondary: TextStyle = signalTextStyle(fontFamily, 13.sp, 20.sp, FontWeight.Medium),
    val prismMeta: TextStyle = signalTextStyle(fontFamily, 11.5.sp, 17.sp, FontWeight.Medium),
    val prismNavLabel: TextStyle = signalTextStyle(fontFamily, 11.5.sp, 16.sp, FontWeight.Medium),
    val prismAmountLarge: TextStyle = signalTextStyle(fontFamily, 30.sp, 36.sp, FontWeight.ExtraBold).copy(fontFeatureSettings = "tnum"),
    val prismAmountCompact: TextStyle = signalTextStyle(fontFamily, 15.sp, 21.sp, FontWeight.SemiBold).copy(fontFeatureSettings = "tnum"),
    val prismIdentifier: TextStyle = signalTextStyle(fontFamily, 13.sp, 19.sp, FontWeight.Medium).copy(
        fontFeatureSettings = "tnum",
        textDirection = TextDirection.Ltr,
    ),
    val prismCardPan: TextStyle = signalTextStyle(fontFamily, 21.sp, 28.sp, FontWeight.Bold).copy(
        fontFeatureSettings = "tnum",
        textDirection = TextDirection.Ltr,
    ),
    val prismCardMeta: TextStyle = signalTextStyle(fontFamily, 12.sp, 17.sp, FontWeight.Medium),
    val prismStatus: TextStyle = signalTextStyle(fontFamily, 11.sp, 15.sp, FontWeight.SemiBold),
)

object SignalTheme {
    val colors: SignalColors
        @Composable get() = LocalSignalColors.current

    val typography: SignalTypography
        @Composable get() = LocalSignalTypography.current

    val shapes: SignalShapes
        @Composable get() = LocalSignalShapes.current

    val dimensions: SignalDimensions
        @Composable get() = LocalSignalDimensions.current

    val config: SignalThemeConfig
        @Composable get() = LocalSignalThemeConfig.current

    val settings: SignalThemeSettings
        @Composable get() = LocalSignalThemeSettings.current

    val mode: SignalColorMode
        @Composable get() = LocalSignalResolvedMode.current

    val prism: SignalPrismTokens
        @Composable get() = LocalSignalPrism.current
}

val LocalSignalColors = staticCompositionLocalOf { SignalColors() }
val LocalSignalTypography = staticCompositionLocalOf { SignalTypography() }
val LocalSignalShapes = staticCompositionLocalOf { SignalShapes() }
val LocalSignalDimensions = staticCompositionLocalOf { SignalDimensions() }
val LocalSignalThemeConfig = staticCompositionLocalOf { SignalThemeConfig() }
val LocalSignalThemeSettings = staticCompositionLocalOf { SignalThemeSettings() }
val LocalSignalResolvedMode = staticCompositionLocalOf { SignalColorMode.Light }
val LocalSignalPrism = staticCompositionLocalOf {
    SignalPrismDefaults.tokens(
        appearance = SignalPrismAppearance.Light,
    )
}

@Composable
fun SignalTheme(
    colors: SignalColors? = null,
    brand: SignalBrand? = null,
    mode: SignalColorMode = SignalColorMode.Light,
    typography: SignalTypography = SignalTypography(),
    shapes: SignalShapes = SignalShapes(),
    dimensions: SignalDimensions = SignalDimensions(),
    dark: Boolean = false,
    black: Boolean = false,
    content: @Composable () -> Unit,
) {
    val resolvedMode = when {
        black -> SignalColorMode.Black
        dark -> SignalColorMode.Dark
        else -> mode
    }
    val resolvedBrand = brand ?: SignalBrandDefaults.Neptune
    val resolvedColors = colors ?: SignalColorDefaults.fromBrand(brand = resolvedBrand, mode = resolvedMode)
    val config = SignalThemeConfig(
        brand = resolvedBrand,
        mode = resolvedMode,
        colors = resolvedColors,
        typography = typography,
        shapes = shapes,
        dimensions = dimensions,
    )
    SignalThemeContent(
        config = config,
        settings = SignalThemeSettings(
            brand = resolvedBrand,
            appearance = resolvedMode.toAppearanceMode(),
            typography = typography,
            shapes = shapes,
            dimensions = dimensions,
        ),
        colors = resolvedColors,
        mode = resolvedMode,
        typography = typography,
        shapes = shapes,
        dimensions = dimensions,
        content = content,
    )
}

@Composable
fun SignalTheme(
    config: SignalThemeConfig,
    content: @Composable () -> Unit,
) {
    val resolvedColors = config.colors ?: SignalColorDefaults.fromBrand(config.brand, config.mode)
    SignalThemeContent(
        config = config.copy(colors = resolvedColors),
        settings = SignalThemeSettings(
            brand = config.brand,
            appearance = config.mode.toAppearanceMode(),
            typography = config.typography,
            shapes = config.shapes,
            dimensions = config.dimensions,
        ),
        colors = resolvedColors,
        mode = config.mode,
        typography = config.typography,
        shapes = config.shapes,
        dimensions = config.dimensions,
        content = content,
    )
}

@Composable
fun SignalTheme(
    settings: SignalThemeSettings,
    systemDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val config = settings.toThemeConfig(systemDark = systemDark)
    SignalThemeContent(
        config = config,
        settings = settings,
        colors = config.colors ?: SignalColorDefaults.fromBrand(config.brand, config.mode),
        mode = config.mode,
        typography = config.typography,
        shapes = config.shapes,
        dimensions = config.dimensions,
        content = content,
    )
}

@Composable
private fun SignalThemeContent(
    config: SignalThemeConfig,
    settings: SignalThemeSettings,
    colors: SignalColors,
    mode: SignalColorMode,
    typography: SignalTypography,
    shapes: SignalShapes,
    dimensions: SignalDimensions,
    content: @Composable () -> Unit,
) {
    val prismProfile = config.prismProfile
    val resolvedColors = if (!colors.dark) {
        prismProfile.lightPalette.applyTo(colors, config.brand)
    } else {
        colors
    }
    val materialColors = if (resolvedColors.dark) {
        darkColorScheme(
            primary = resolvedColors.primary,
            onPrimary = resolvedColors.onPrimary,
            primaryContainer = resolvedColors.primaryContainer,
            onPrimaryContainer = resolvedColors.onPrimaryContainer,
            inversePrimary = resolvedColors.secondary,
            secondary = resolvedColors.secondary,
            onSecondary = resolvedColors.onSecondary,
            secondaryContainer = resolvedColors.secondaryContainer,
            onSecondaryContainer = resolvedColors.onSecondaryContainer,
            tertiary = resolvedColors.tertiary,
            onTertiary = resolvedColors.onTertiary,
            tertiaryContainer = resolvedColors.tertiaryContainer,
            onTertiaryContainer = resolvedColors.onTertiaryContainer,
            background = resolvedColors.surface,
            onBackground = resolvedColors.onSurface,
            surface = resolvedColors.surface,
            onSurface = resolvedColors.onSurface,
            surfaceVariant = resolvedColors.surfaceContainer,
            onSurfaceVariant = resolvedColors.onSurfaceVariant,
            surfaceTint = resolvedColors.primary,
            inverseSurface = resolvedColors.inverseSurface,
            inverseOnSurface = resolvedColors.inverseOnSurface,
            error = resolvedColors.error,
            onError = resolvedColors.onError,
            errorContainer = resolvedColors.errorContainer,
            onErrorContainer = resolvedColors.onErrorContainer,
            outline = resolvedColors.outline,
            outlineVariant = resolvedColors.outlineVariant,
        )
    } else {
        lightColorScheme(
            primary = resolvedColors.primary,
            onPrimary = resolvedColors.onPrimary,
            primaryContainer = resolvedColors.primaryContainer,
            onPrimaryContainer = resolvedColors.onPrimaryContainer,
            inversePrimary = resolvedColors.secondary,
            secondary = resolvedColors.secondary,
            onSecondary = resolvedColors.onSecondary,
            secondaryContainer = resolvedColors.secondaryContainer,
            onSecondaryContainer = resolvedColors.onSecondaryContainer,
            tertiary = resolvedColors.tertiary,
            onTertiary = resolvedColors.onTertiary,
            tertiaryContainer = resolvedColors.tertiaryContainer,
            onTertiaryContainer = resolvedColors.onTertiaryContainer,
            background = resolvedColors.surface,
            onBackground = resolvedColors.onSurface,
            surface = resolvedColors.surface,
            onSurface = resolvedColors.onSurface,
            surfaceVariant = resolvedColors.surfaceContainer,
            onSurfaceVariant = resolvedColors.onSurfaceVariant,
            surfaceTint = resolvedColors.primary,
            inverseSurface = resolvedColors.inverseSurface,
            inverseOnSurface = resolvedColors.inverseOnSurface,
            error = resolvedColors.error,
            onError = resolvedColors.onError,
            errorContainer = resolvedColors.errorContainer,
            onErrorContainer = resolvedColors.onErrorContainer,
            outline = resolvedColors.outline,
            outlineVariant = resolvedColors.outlineVariant,
        )
    }
    androidx.compose.runtime.CompositionLocalProvider(
        LocalSignalColors provides resolvedColors,
        LocalSignalTypography provides typography,
        LocalSignalShapes provides shapes,
        LocalSignalDimensions provides dimensions,
        LocalSignalThemeConfig provides config,
        LocalSignalThemeSettings provides settings,
        LocalSignalResolvedMode provides mode,
        LocalSignalPrism provides (config.prismTokens ?: SignalPrismDefaults.tokens(
            brand = config.brand,
            appearance = SignalPrismDefaults.appearanceFor(mode),
            profile = prismProfile,
        )),
    ) {
        MaterialTheme(
            colorScheme = materialColors,
            typography = Typography(
                displayLarge = typography.displayLarge,
                displayMedium = typography.displayMedium,
                displaySmall = typography.displaySmall,
                headlineLarge = typography.headlineLarge,
                headlineMedium = typography.headlineMedium,
                headlineSmall = typography.headlineSmall,
                titleLarge = typography.titleLarge,
                titleMedium = typography.titleMedium,
                titleSmall = typography.titleSmall,
                bodyLarge = typography.bodyLarge,
                bodyMedium = typography.bodyMedium,
                bodySmall = typography.bodySmall,
                labelLarge = typography.labelLarge,
                labelMedium = typography.labelMedium,
                labelSmall = typography.labelSmall,
            ),
            content = content,
        )
    }
}
