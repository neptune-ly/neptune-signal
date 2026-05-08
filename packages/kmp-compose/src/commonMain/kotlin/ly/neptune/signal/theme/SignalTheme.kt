package ly.neptune.signal.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class SignalTypography(
    val displayBalance: TextStyle = TextStyle(fontSize = 36.sp, lineHeight = 36.sp, fontWeight = FontWeight.Black),
    val pageTitle: TextStyle = TextStyle(fontSize = 20.sp, lineHeight = 23.sp, fontWeight = FontWeight.Black),
    val sectionTitle: TextStyle = TextStyle(fontSize = 15.sp, lineHeight = 18.sp, fontWeight = FontWeight.Black),
    val rowTitle: TextStyle = TextStyle(fontSize = 14.sp, lineHeight = 18.sp, fontWeight = FontWeight.ExtraBold),
    val rowMeta: TextStyle = TextStyle(fontSize = 11.sp, lineHeight = 15.sp, fontWeight = FontWeight.SemiBold),
    val button: TextStyle = TextStyle(fontSize = 15.sp, lineHeight = 18.sp, fontWeight = FontWeight.Black),
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
                primary = colors.bankPrimary,
                secondary = colors.bankSecondary,
                tertiary = colors.bankAccent,
                background = colors.surfacePaper,
                surface = colors.surfaceCard,
                onPrimary = colors.textInverse,
                onSecondary = colors.textInverse,
                onSurface = colors.textPrimary,
            ),
            typography = Typography(
                titleLarge = typography.pageTitle,
                titleMedium = typography.sectionTitle,
                bodyLarge = typography.rowTitle,
                bodyMedium = typography.rowMeta,
                labelLarge = typography.button,
            ),
            content = content,
        )
    }
}

