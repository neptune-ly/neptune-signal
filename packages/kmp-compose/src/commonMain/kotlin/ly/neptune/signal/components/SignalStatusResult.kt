package ly.neptune.signal.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.style.TextOverflow
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
    amount: String? = null,
    recipient: String? = null,
    primaryAction: @Composable () -> Unit,
    secondaryAction: (@Composable () -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val stateColor = when (state) {
        SignalResultState.Waiting -> colors.bankAccent
        SignalResultState.Success -> colors.success
        SignalResultState.Failure -> colors.danger
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.surfaceContainerLowest, RoundedCornerShape(30.dp))
            .border(BorderStroke(1.dp, colors.outlineVariant.copy(alpha = if (colors.dark) 0.18f else 0.28f)), RoundedCornerShape(30.dp))
            .padding(SignalSpacing.x4),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
    ) {
        SignalStatusMark(state = state, color = stateColor)
        Text(text = title, color = if (colors.dark) colors.onSurface else colors.bankPrimary, style = typography.headlineSmall)
        if (amount != null) {
            Text(
                text = amount,
                color = colors.onSurface,
                style = typography.displaySmall.copy(fontFeatureSettings = "tnum"),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        if (recipient != null) {
            Text(
                text = recipient,
                color = colors.onSurfaceVariant,
                style = typography.rowTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Text(text = reference, color = colors.onSurfaceVariant, style = typography.rowMeta)
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(0.dp),
        ) {
            SignalReceiptSurface(details = details)
        }
        primaryAction()
        secondaryAction?.invoke()
    }
}

@Composable
private fun SignalReceiptSurface(details: List<SignalResultDetail>) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.surfaceContainerLow.copy(alpha = if (colors.dark) 0.58f else 0.72f), RoundedCornerShape(22.dp))
            .border(
                BorderStroke(1.dp, colors.outlineVariant.copy(alpha = if (colors.dark) 0.12f else 0.18f)),
                RoundedCornerShape(22.dp),
            )
            .padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x2),
    ) {
        details.forEachIndexed { index, detail ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = detail.label,
                    color = colors.onSurfaceVariant,
                    style = typography.rowMeta,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = detail.value,
                    color = colors.onSurface,
                    style = typography.rowTitle.copy(fontFeatureSettings = "tnum"),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                )
            }
            if (index != details.lastIndex) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(colors.outlineVariant.copy(alpha = if (colors.dark) 0.12f else 0.18f)),
                )
            }
        }
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
            .size(94.dp),
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
                .size(64.dp)
                .background(color.copy(alpha = if (SignalTheme.colors.dark) 0.88f else 0.96f), RoundedCornerShape(22.dp)),
            contentAlignment = Alignment.Center,
        ) {
            when (state) {
                SignalResultState.Waiting -> CircularProgressIndicator(color = Color.White, strokeWidth = 3.dp)
                SignalResultState.Success -> Canvas(Modifier.size(42.dp)) {
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
