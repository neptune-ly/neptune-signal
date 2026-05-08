package ly.neptune.signal.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalRadius
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
    val stateColor = when (state) {
        SignalResultState.Waiting -> colors.bankAccent
        SignalResultState.Success -> colors.success
        SignalResultState.Failure -> colors.danger
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.surfaceCard, RoundedCornerShape(SignalRadius.xl))
            .border(BorderStroke(1.dp, colors.borderDefault), RoundedCornerShape(SignalRadius.xl))
            .padding(SignalSpacing.x5),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x4),
    ) {
        SignalStatusMark(state = state, color = stateColor)
        Text(text = title, color = colors.bankPrimary, style = typography.pageTitle)
        Text(text = reference, color = colors.textSecondary, style = typography.rowTitle)
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
            .size(112.dp)
            .background(color, RoundedCornerShape(999.dp)),
        contentAlignment = Alignment.Center,
    ) {
        when (state) {
            SignalResultState.Waiting -> CircularProgressIndicator(color = Color.White, strokeWidth = 3.dp)
            SignalResultState.Success -> Text(text = "OK", color = Color.White, style = SignalTheme.typography.pageTitle)
            SignalResultState.Failure -> Text(text = "!", color = Color.White, style = SignalTheme.typography.displayBalance)
        }
    }
}
