package ly.neptune.signal.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
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
}

val LocalSignalColors = staticCompositionLocalOf { SignalColors() }
val LocalSignalTypography = staticCompositionLocalOf { SignalTypography() }

@Composable
fun SignalTheme(
    colors: SignalColors = SignalColorDefaults.Neptune,
    typography: SignalTypography = SignalTypography(),
    content: @Composable () -> Unit,
) {
    androidx.compose.runtime.CompositionLocalProvider(
        LocalSignalColors provides colors,
        LocalSignalTypography provides typography,
    ) {
        MaterialTheme(
            colorScheme = lightColorScheme(
                primary = colors.primary,
                onPrimary = colors.onPrimary,
                primaryContainer = colors.primaryContainer,
                onPrimaryContainer = colors.onPrimaryContainer,
                inversePrimary = colors.secondary,
                secondary = colors.secondary,
                onSecondary = colors.onSecondary,
                secondaryContainer = colors.secondaryContainer,
                onSecondaryContainer = colors.onSecondaryContainer,
                tertiary = colors.tertiary,
                onTertiary = colors.onTertiary,
                tertiaryContainer = colors.tertiaryContainer,
                onTertiaryContainer = colors.onTertiaryContainer,
                background = colors.surface,
                onBackground = colors.onSurface,
                surface = colors.surface,
                onSurface = colors.onSurface,
                surfaceVariant = colors.surfaceContainer,
                onSurfaceVariant = colors.onSurfaceVariant,
                surfaceTint = colors.primary,
                inverseSurface = colors.inverseSurface,
                inverseOnSurface = colors.inverseOnSurface,
                error = colors.error,
                onError = colors.onError,
                errorContainer = colors.errorContainer,
                onErrorContainer = colors.onErrorContainer,
                outline = colors.outline,
                outlineVariant = colors.outlineVariant,
            ),
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
