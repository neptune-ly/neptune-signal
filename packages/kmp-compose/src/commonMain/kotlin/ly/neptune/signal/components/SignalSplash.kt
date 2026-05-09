package ly.neptune.signal.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import ly.neptune.signal.motion.SignalMotion
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

@Composable
fun SignalSplash(
    modifier: Modifier = Modifier,
    title: String = "Neptune. Signal",
    subtitle: String = "Powered by Neptune. Fintech",
    animated: Boolean = true,
) {
    val transition = rememberInfiniteTransition(label = "SignalSplash")
    val orbitProgress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = SignalMotion.SplashOrbitMs, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "SignalSplashOrbit",
    )
    val pulseProgress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = SignalMotion.SplashPulseMs, easing = SignalMotion.StandardEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "SignalSplashPulse",
    )
    val progress = if (animated) orbitProgress else 0.08f
    val pulse = if (animated) pulseProgress else 0.36f
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
    ) {
        SignalPlanetMark(
            progress = progress,
            pulse = pulse,
            modifier = Modifier.size(172.dp),
        )
        Text(
            text = title,
            color = if (colors.dark) colors.onSurface else colors.bankPrimary,
            style = typography.headlineSmall,
            textAlign = TextAlign.Center,
        )
        Text(
            text = subtitle,
            modifier = Modifier.padding(horizontal = SignalSpacing.x6),
            color = colors.onSurfaceVariant,
            style = typography.bodySmall,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun SignalPlanetMark(
    progress: Float,
    pulse: Float,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    Canvas(modifier = modifier) {
        val center = Offset(size.width / 2f, size.height / 2f)
        val base = size.minDimension
        val planetRadius = base * 0.18f
        val glowRadius = planetRadius * (1.85f + (pulse * 0.22f))
        val orbitStroke = Stroke(width = 1.5.dp.toPx())
        val orbitRadii = listOf(base * 0.28f, base * 0.38f, base * 0.47f)
        val moonColors = listOf(colors.bankAccent, colors.bankSecondary, colors.bankPrimary)

        drawCircle(color = colors.bankAccent.copy(alpha = 0.10f), radius = glowRadius, center = center)
        drawCircle(color = colors.bankAccent, radius = planetRadius, center = center)
        drawCircle(
            color = colors.textInverse.copy(alpha = 0.22f),
            radius = planetRadius * 0.92f,
            center = center,
            style = Stroke(width = 2.dp.toPx()),
        )

        orbitRadii.forEachIndexed { index, radius ->
            drawOval(
                color = colors.bankSecondary.copy(alpha = 0.20f),
                topLeft = Offset(center.x - radius, center.y - (radius * 0.58f)),
                size = androidx.compose.ui.geometry.Size(radius * 2f, radius * 1.16f),
                style = orbitStroke,
            )
            val angle = ((progress * (index + 1.0f)) + (index * 0.23f)) * (PI.toFloat() * 2f)
            val moonCenter = Offset(
                x = center.x + (cos(angle) * radius),
                y = center.y + (sin(angle) * radius * 0.58f),
            )
            drawCircle(
                color = moonColors[index].copy(alpha = if (index == 0) 1f else 0.92f),
                radius = if (index == 0) base * 0.048f else base * 0.034f,
                center = moonCenter,
            )
        }
    }
}
