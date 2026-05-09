package ly.neptune.signal.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class SignalTypography(
    val displayLarge: TextStyle = TextStyle(fontSize = 44.sp, lineHeight = 44.sp, fontWeight = FontWeight.Black),
    val displayMedium: TextStyle = TextStyle(fontSize = 36.sp, lineHeight = 36.sp, fontWeight = FontWeight.Black),
    val displaySmall: TextStyle = TextStyle(fontSize = 32.sp, lineHeight = 34.sp, fontWeight = FontWeight.ExtraBold),
    val headlineLarge: TextStyle = TextStyle(fontSize = 32.sp, lineHeight = 36.sp, fontWeight = FontWeight.Black),
    val headlineMedium: TextStyle = TextStyle(fontSize = 28.sp, lineHeight = 32.sp, fontWeight = FontWeight.Black),
    val headlineSmall: TextStyle = TextStyle(fontSize = 24.sp, lineHeight = 28.sp, fontWeight = FontWeight.ExtraBold),
    val titleLarge: TextStyle = TextStyle(fontSize = 20.sp, lineHeight = 23.sp, fontWeight = FontWeight.Black),
    val titleMedium: TextStyle = TextStyle(fontSize = 16.sp, lineHeight = 20.sp, fontWeight = FontWeight.ExtraBold),
    val titleSmall: TextStyle = TextStyle(fontSize = 14.sp, lineHeight = 18.sp, fontWeight = FontWeight.ExtraBold),
    val bodyLarge: TextStyle = TextStyle(fontSize = 16.sp, lineHeight = 23.sp, fontWeight = FontWeight.SemiBold),
    val bodyMedium: TextStyle = TextStyle(fontSize = 14.sp, lineHeight = 20.sp, fontWeight = FontWeight.SemiBold),
    val bodySmall: TextStyle = TextStyle(fontSize = 12.sp, lineHeight = 17.sp, fontWeight = FontWeight.SemiBold),
    val labelLarge: TextStyle = TextStyle(fontSize = 15.sp, lineHeight = 18.sp, fontWeight = FontWeight.Black),
    val labelMedium: TextStyle = TextStyle(fontSize = 12.sp, lineHeight = 15.sp, fontWeight = FontWeight.ExtraBold),
    val labelSmall: TextStyle = TextStyle(fontSize = 11.sp, lineHeight = 14.sp, fontWeight = FontWeight.ExtraBold),
    val displayBalance: TextStyle = displayMedium,
    val pageTitle: TextStyle = titleLarge,
    val sectionTitle: TextStyle = TextStyle(fontSize = 15.sp, lineHeight = 18.sp, fontWeight = FontWeight.Black),
    val rowTitle: TextStyle = titleSmall,
    val rowMeta: TextStyle = labelSmall,
    val button: TextStyle = labelLarge,
    val statusPill: TextStyle = TextStyle(fontSize = 10.sp, lineHeight = 12.sp, fontWeight = FontWeight.ExtraBold),
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
}

val LocalSignalColors = staticCompositionLocalOf { SignalColors() }
val LocalSignalTypography = staticCompositionLocalOf { SignalTypography() }
val LocalSignalShapes = staticCompositionLocalOf { SignalShapes() }
val LocalSignalDimensions = staticCompositionLocalOf { SignalDimensions() }

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
    val resolvedColors = colors ?: when {
        brand != null -> SignalColorDefaults.fromBrand(brand = brand, mode = mode)
        black -> SignalColorDefaults.NeptuneBlack
        dark -> SignalColorDefaults.NeptuneDark
        else -> SignalColorDefaults.Neptune
    }
    SignalThemeContent(
        colors = resolvedColors,
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
    SignalThemeContent(
        colors = config.colors ?: SignalColorDefaults.fromBrand(config.brand, config.mode),
        typography = config.typography,
        shapes = config.shapes,
        dimensions = config.dimensions,
        content = content,
    )
}

@Composable
private fun SignalThemeContent(
    colors: SignalColors,
    typography: SignalTypography,
    shapes: SignalShapes,
    dimensions: SignalDimensions,
    content: @Composable () -> Unit,
) {
    val resolvedColors = colors
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
