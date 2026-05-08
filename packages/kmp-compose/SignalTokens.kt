package ly.neptune.signal.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Immutable
data class SignalColors(
    val bankPrimary: Color = Color(0xFF07315F),
    val bankSecondary: Color = Color(0xFF00A8AE),
    val bankAccent: Color = Color(0xFFEB4E4D),
    val surfacePaper: Color = Color(0xFFF7FAF9),
    val surfaceCard: Color = Color(0xFFFFFFFF),
    val textPrimary: Color = Color(0xFF071C2E),
    val textSecondary: Color = Color(0xFF60747C),
    val borderDefault: Color = Color(0xFFD7E5E8),
    val success: Color = Color(0xFF0B8F67),
    val warning: Color = Color(0xFFA96A13),
    val danger: Color = Color(0xFFC33131)
)

object SignalSpacing {
    val x1 = 4.dp
    val x2 = 8.dp
    val x3 = 12.dp
    val x4 = 16.dp
    val x5 = 20.dp
    val x6 = 24.dp
    val x8 = 32.dp
    val x10 = 40.dp
}

object SignalRadius {
    val xs = 8.dp
    val sm = 12.dp
    val md = 16.dp
    val lg = 22.dp
    val xl = 28.dp
}

