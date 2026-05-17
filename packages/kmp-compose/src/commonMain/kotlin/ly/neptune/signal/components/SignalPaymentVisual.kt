package ly.neptune.signal.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ly.neptune.signal.motion.SignalMotion
import ly.neptune.signal.motion.SignalPaymentRitual
import ly.neptune.signal.motion.SignalPaymentRitualCopy
import ly.neptune.signal.motion.SignalPaymentRitualPhase
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

interface SignalPaymentVisual {
    val phase: SignalPaymentRitualPhase
    val copy: SignalPaymentRitualCopy
}

data class SignalPaymentVisualState(
    override val phase: SignalPaymentRitualPhase,
    override val copy: SignalPaymentRitualCopy = SignalPaymentRitual.ArabicLyPayCopy,
) : SignalPaymentVisual

@Composable
fun SignalPaymentRitualVisual(
    state: SignalPaymentVisual,
    modifier: Modifier = Modifier,
    showLifecycle: Boolean = false,
) {
    val colors = SignalTheme.colors
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x4),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SignalPaymentOrb(phase = state.phase)
        if (showLifecycle) {
            SignalPaymentLifecycleRail(phase = state.phase)
        }
        Text(
            text = state.copy.title(state.phase),
            color = colors.onSurface,
            style = SignalTheme.typography.pageTitle,
            textAlign = TextAlign.Center,
        )
        Text(
            text = state.copy.message(state.phase),
            color = colors.textSecondary,
            style = SignalTheme.typography.rowMeta,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun SignalPaymentOrb(phase: SignalPaymentRitualPhase) {
    val colors = SignalTheme.colors
    val infinite = rememberInfiniteTransition(label = "signal-payment-orb")
    val sweep by infinite.animateFloat(
        initialValue = 38f,
        targetValue = 282f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = SignalMotion.PaymentRitualRingSweepMs, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "signal-payment-orb-sweep",
    )
    val pulse by infinite.animateFloat(
        initialValue = 0.965f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = SignalMotion.PaymentRitualBreatheMs),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "signal-payment-orb-pulse",
    )
    val terminalScale by animateFloatAsState(
        targetValue = if (phase == SignalPaymentRitualPhase.Completed) 1.04f else 1f,
        animationSpec = tween(SignalMotion.PaymentRitualTerminalScaleMs),
        label = "signal-payment-orb-terminal",
    )
    val stateColor = signalPaymentStateColor(phase)
    val stateIcon = when (phase) {
        SignalPaymentRitualPhase.Preparing,
        SignalPaymentRitualPhase.Routing -> SignalIconName.Transfer
        SignalPaymentRitualPhase.Verifying,
        SignalPaymentRitualPhase.BankConfirmation,
        SignalPaymentRitualPhase.SettlementConfirmation -> SignalIconName.Shield
        SignalPaymentRitualPhase.Completed -> SignalIconName.CheckCircle
        SignalPaymentRitualPhase.Failed -> SignalIconName.ErrorCircle
        SignalPaymentRitualPhase.Pending,
        SignalPaymentRitualPhase.Timeout -> SignalIconName.Clock
    }

    Box(
        modifier = Modifier
            .size(112.dp)
            .graphicsLayer {
                val activePulse = if (phase == SignalPaymentRitualPhase.Completed || phase == SignalPaymentRitualPhase.Failed) 1f else pulse
                scaleX = terminalScale * activePulse
                scaleY = terminalScale * activePulse
            },
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.size(112.dp)) {
            val stroke = 5.dp.toPx()
            val inset = stroke / 2f + 7.dp.toPx()
            val ringSize = size.width - inset * 2f
            val activeSweep = when (phase) {
                SignalPaymentRitualPhase.Completed -> 360f
                SignalPaymentRitualPhase.Failed,
                SignalPaymentRitualPhase.Pending,
                SignalPaymentRitualPhase.Timeout -> 246f
                else -> sweep
            }
            drawCircle(
                color = if (colors.dark) colors.surfaceContainerHigh.copy(alpha = 0.78f) else colors.surfaceContainerLow,
                radius = size.minDimension / 2f - 8.dp.toPx(),
                style = Fill,
            )
            drawArc(
                color = colors.outlineVariant.copy(alpha = if (colors.dark) 0.18f else 0.28f),
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = Offset(inset, inset),
                size = Size(ringSize, ringSize),
                style = Stroke(width = stroke, cap = StrokeCap.Round),
            )
            drawArc(
                color = stateColor.copy(alpha = if (phase == SignalPaymentRitualPhase.Completed) 0.96f else 0.74f),
                startAngle = -92f,
                sweepAngle = activeSweep,
                useCenter = false,
                topLeft = Offset(inset, inset),
                size = Size(ringSize, ringSize),
                style = Stroke(width = stroke, cap = StrokeCap.Round),
            )
        }
        Box(
            modifier = Modifier
                .size(70.dp)
                .clip(RoundedCornerShape(26.dp))
                .background(stateColor.copy(alpha = if (colors.dark) 0.20f else 0.12f)),
            contentAlignment = Alignment.Center,
        ) {
            SignalIcon(stateIcon, tint = stateColor, size = 34.dp, strokeWidth = 2.55.dp)
        }
    }
}

@Composable
private fun SignalPaymentLifecycleRail(phase: SignalPaymentRitualPhase) {
    val colors = SignalTheme.colors
    val activeIndex = SignalPaymentRitual.lifecycleIndex(phase)
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
        SignalPaymentRitual.ArabicStageLabels.forEachIndexed { index, label ->
            val active = index == activeIndex
            val reached = index <= activeIndex
            Box(
                modifier = Modifier
                    .widthIn(min = if (active) 46.dp else 34.dp)
                    .clip(RoundedCornerShape(SignalTheme.shapes.full))
                    .background(
                        when {
                            active -> colors.bankPrimary.copy(alpha = if (colors.dark) 0.22f else 0.12f)
                            reached -> colors.bankPrimary.copy(alpha = if (colors.dark) 0.12f else 0.07f)
                            else -> colors.surfaceContainerLow.copy(alpha = if (colors.dark) 0.40f else 0.70f)
                        },
                    )
                    .padding(horizontal = 9.dp, vertical = 5.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = label,
                    color = if (reached) colors.bankPrimary else colors.textSecondary.copy(alpha = 0.70f),
                    style = SignalTheme.typography.statusPill,
                    maxLines = 1,
                )
            }
        }
    }
}

@Composable
private fun signalPaymentStateColor(phase: SignalPaymentRitualPhase): Color {
    val colors = SignalTheme.colors
    return when (phase) {
        SignalPaymentRitualPhase.Completed -> colors.success
        SignalPaymentRitualPhase.Failed -> colors.danger
        SignalPaymentRitualPhase.Pending,
        SignalPaymentRitualPhase.Timeout -> colors.warning
        else -> colors.bankPrimary
    }
}
