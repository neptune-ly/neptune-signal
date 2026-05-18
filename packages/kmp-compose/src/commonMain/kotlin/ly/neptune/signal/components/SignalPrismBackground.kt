package ly.neptune.signal.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import ly.neptune.signal.theme.SignalPrismAppearance
import ly.neptune.signal.theme.SignalTheme

@Composable
fun SignalPrismBackground(
    modifier: Modifier = Modifier,
    intensity: Float = 1f,
    content: @Composable BoxScope.() -> Unit,
) {
    val prism = SignalTheme.prism
    val controlledIntensity = intensity.coerceIn(0f, 1f)
    val backgroundBrush = when (prism.appearance) {
        SignalPrismAppearance.Light -> Brush.verticalGradient(
            colors = listOf(
                prism.surfaces.prismBackground,
                prism.surfaces.prismSurface.copy(alpha = 0.98f),
                prism.surfaces.prismBackground,
            ),
        )
        SignalPrismAppearance.Dark,
        SignalPrismAppearance.Oled -> Brush.verticalGradient(
            colors = listOf(
                prism.surfaces.prismBackground,
                prism.surfaces.prismBackground,
            ),
        )
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundBrush, alpha = controlledIntensity.coerceAtLeast(0.96f)),
    ) {
        content()
    }
}
