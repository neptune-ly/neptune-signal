package ly.neptune.signal.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class SignalColors(
    val bankPrimary: Color = Color(0xFF1A335E),
    val bankSecondary: Color = Color(0xFF00A8AE),
    val bankAccent: Color = Color(0xFFEA4E4E),
    val surfacePaper: Color = Color(0xFFF7FAF9),
    val surfaceCard: Color = Color(0xFFFFFFFF),
    val surfaceSoft: Color = Color(0xFFEEF6F6),
    val textPrimary: Color = Color(0xFF071C2E),
    val textSecondary: Color = Color(0xFF60747C),
    val textInverse: Color = Color(0xFFFFFFFF),
    val borderDefault: Color = Color(0xFFD7E5E8),
    val success: Color = Color(0xFF0B8F67),
    val warning: Color = Color(0xFFA96A13),
    val danger: Color = Color(0xFFC33131),
)

object SignalColorDefaults {
    val Neptune = SignalColors()

    fun whiteLabel(
        primary: Color,
        secondary: Color = Color(0xFF00A8AE),
        accent: Color = Color(0xFFEA4E4E),
        ink: Color = Color(0xFF071C2E),
    ) = SignalColors(
        bankPrimary = primary,
        bankSecondary = secondary,
        bankAccent = accent,
        textPrimary = ink,
    )
}

