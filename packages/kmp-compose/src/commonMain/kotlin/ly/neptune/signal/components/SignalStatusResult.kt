package ly.neptune.signal.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

enum class SignalResultState {
    Waiting,
    Success,
    Failure,
}

data class SignalResultDetail(
    val label: String,
    val value: String,
    val format: SignalValueFormat = SignalValueFormat.Plain,
    val copyable: Boolean = false,
    val shareable: Boolean = false,
    val masked: Boolean = false,
    val onCopy: (() -> Unit)? = null,
    val onShare: (() -> Unit)? = null,
)

@Composable
fun SignalStatusResult(
    state: SignalResultState,
    title: String,
    reference: String,
    details: List<SignalResultDetail>,
    modifier: Modifier = Modifier,
    primaryAction: @Composable () -> Unit,
    secondaryAction: (@Composable () -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val shapes = SignalTheme.shapes
    val stateColor = when (state) {
        SignalResultState.Waiting -> colors.bankAccent
        SignalResultState.Success -> colors.success
        SignalResultState.Failure -> colors.danger
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.surfaceContainerLowest, RoundedCornerShape(shapes.xl))
            .border(BorderStroke(1.dp, colors.outlineVariant), RoundedCornerShape(shapes.xl))
            .padding(SignalSpacing.x5),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x4),
    ) {
        SignalStatusMark(state = state, color = stateColor)
        Text(text = title, color = if (colors.dark) colors.onSurface else colors.bankPrimary, style = typography.headlineMedium)
        Text(text = reference, color = colors.onSurfaceVariant, style = typography.rowTitle)
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        ) {
            details.forEach { detail ->
                SignalValueLine(
                    label = detail.label,
                    value = detail.value,
                    format = detail.format,
                    copyable = detail.copyable,
                    shareable = detail.shareable,
                    masked = detail.masked,
                    onCopy = detail.onCopy,
                    onShare = detail.onShare,
                )
            }
        }
        primaryAction()
        secondaryAction?.invoke()
    }
}

@Composable
fun SignalStatusMark(
    state: SignalResultState,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(124.dp),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(Modifier.fillMaxSize()) {
            drawCircle(color = color.copy(alpha = 0.10f), radius = size.minDimension * 0.48f)
            drawCircle(
                color = color.copy(alpha = 0.22f),
                radius = size.minDimension * 0.38f,
                style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round),
            )
        }
        Box(
            modifier = Modifier
                .size(92.dp)
                .background(color, RoundedCornerShape(SignalTheme.shapes.full)),
            contentAlignment = Alignment.Center,
        ) {
            when (state) {
                SignalResultState.Waiting -> CircularProgressIndicator(color = Color.White, strokeWidth = 3.dp)
                SignalResultState.Success -> Canvas(Modifier.size(48.dp)) {
                    drawLine(
                        color = Color.White,
                        start = androidx.compose.ui.geometry.Offset(size.width * 0.18f, size.height * 0.52f),
                        end = androidx.compose.ui.geometry.Offset(size.width * 0.42f, size.height * 0.76f),
                        strokeWidth = 5.dp.toPx(),
                        cap = StrokeCap.Round,
                    )
                    drawLine(
                        color = Color.White,
                        start = androidx.compose.ui.geometry.Offset(size.width * 0.42f, size.height * 0.76f),
                        end = androidx.compose.ui.geometry.Offset(size.width * 0.84f, size.height * 0.22f),
                        strokeWidth = 5.dp.toPx(),
                        cap = StrokeCap.Round,
                    )
                }
                SignalResultState.Failure -> Text(text = "!", color = Color.White, style = SignalTheme.typography.displayLarge)
            }
        }
    }
}
