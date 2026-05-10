package ly.neptune.signal.components

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

data class SignalPaymentCardStackItem(
    val scheme: String,
    val label: String,
    val maskedNumber: String,
    val subtitle: String,
    val status: String,
    val tags: List<String>,
    val background: Color,
    val signal: Color,
    val mark: SignalCardMark,
)

enum class SignalCardMark {
    Mastercard,
    Visa,
    Numo,
}

private val SignalCardMotionEasing = CubicBezierEasing(0.16f, 1f, 0.3f, 1f)

@Composable
fun SignalPaymentCardStack(
    items: List<SignalPaymentCardStackItem>,
    modifier: Modifier = Modifier,
    onCardSelected: (Int) -> Unit = {},
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items.forEachIndexed { index, item ->
            SignalPaymentCard(
                item = item,
                index = index,
                modifier = Modifier.clickable { onCardSelected(index) },
            )
        }
    }
}

@Composable
fun SignalPaymentCard(
    item: SignalPaymentCardStackItem,
    modifier: Modifier = Modifier,
    index: Int = 0,
) {
    val colors = SignalTheme.colors
    var entered by remember { mutableStateOf(false) }
    LaunchedEffect(item.maskedNumber) { entered = true }
    val entryProgress by animateFloatAsState(
        targetValue = if (entered) 1f else 0f,
        animationSpec = tween(
            durationMillis = 240,
            delayMillis = index.coerceAtMost(4) * 24,
            easing = SignalCardMotionEasing,
        ),
        label = "signal-card-stack-entry",
    )
    val progress = entryProgress.coerceIn(0f, 1f)
    val cardShape = RoundedCornerShape(28.dp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(156.dp)
            .graphicsLayer {
                val inverse = 1f - progress
                scaleX = 0.975f + (0.025f * progress)
                scaleY = 0.975f + (0.025f * progress)
                translationY = inverse * (10f + index.coerceAtMost(4) * 3f) * density
                alpha = 0.72f + (0.28f * progress)
                rotationX = inverse * 4f
                cameraDistance = 18f * density
            }
            .clip(cardShape)
            .background(item.background)
            .border(1.dp, Color.White.copy(alpha = 0.18f), cardShape),
    ) {
        SignalCardSignalRail(
            signal = item.signal,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .height(156.dp),
        )
        SignalCardEdgeLines(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .height(156.dp),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SignalSchemeMark(item.mark)
                Text(
                    text = item.label,
                    modifier = Modifier.weight(1f),
                    color = colors.textInverse,
                    style = SignalTheme.typography.screenTitle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.End,
            ) {
                Text(
                    text = item.maskedNumber,
                    color = colors.textInverse,
                    style = SignalTheme.typography.balance.copy(fontFeatureSettings = "tnum"),
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                )
                Text(
                    text = item.subtitle,
                    color = colors.textInverse.copy(alpha = 0.72f),
                    style = SignalTheme.typography.statusPill,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Box(modifier = Modifier.fillMaxWidth()) {
                SignalCardStatusPill(
                    text = item.status,
                    modifier = Modifier.align(Alignment.CenterStart),
                )
            }
        }
    }
}

@Composable
fun SignalCardDetailStage(
    item: SignalPaymentCardStackItem,
    availableBalance: String,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onRefresh: () -> Unit = {},
    firstMetricLabel: String = "اليوم",
    firstMetricValue: String = "-85 د.ل",
    secondMetricLabel: String = "القناة",
    secondMetricValue: String = "POS",
    thirdMetricLabel: String = "المخاطر",
    thirdMetricValue: String = "طبيعي",
) {
    val colors = SignalTheme.colors
    val layoutDirection = LocalLayoutDirection.current
    val isRtl = layoutDirection == LayoutDirection.Rtl
    var entered by remember { mutableStateOf(false) }
    LaunchedEffect(item.maskedNumber) { entered = true }
    val stageProgress by animateFloatAsState(
        targetValue = if (entered) 1f else 0f,
        animationSpec = tween(durationMillis = 340, easing = SignalCardMotionEasing),
        label = "signal-card-detail-entry",
    )
    val progress = stageProgress.coerceIn(0f, 1f)
    val stageShape = RoundedCornerShape(bottomStart = 38.dp, bottomEnd = 38.dp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 402.dp)
            .graphicsLayer {
                val inverse = 1f - progress
                scaleX = 0.945f + (0.055f * progress)
                scaleY = 0.945f + (0.055f * progress)
                translationY = inverse * -14f * density
                alpha = 0.78f + (0.22f * progress)
                rotationX = inverse * 10f
                cameraDistance = 18f * density
            }
            .clip(stageShape)
            .background(item.background),
    ) {
        SignalDetailHalo(
            signal = item.signal,
            isRtl = isRtl,
            modifier = Modifier.align(Alignment.TopEnd),
        )
        SignalDetailCardAccentRail(
            signal = item.signal,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(horizontal = 10.dp, vertical = 118.dp),
        )
        SignalCardEdgeLines(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 76.dp)
                .height(132.dp),
            alpha = 0.7f,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 18.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SignalDetailStageAction(SignalStageActionIcon.Back, onBack)
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        item.label,
                        color = colors.textInverse,
                        style = SignalTheme.typography.rowTitle,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        item.maskedNumber,
                        color = colors.textInverse.copy(alpha = 0.66f),
                        style = SignalTheme.typography.statusPill,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Clip,
                    )
                }
                SignalDetailStageAction(SignalStageActionIcon.Refresh, onRefresh)
            }
            SignalDetailCardVisual(
                item = item,
                availableBalance = availableBalance,
                progress = progress,
                isRtl = isRtl,
                modifier = Modifier.fillMaxWidth(),
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                SignalStageMetric(firstMetricLabel, firstMetricValue, Modifier.weight(1f))
                SignalStageMetric(secondMetricLabel, secondMetricValue, Modifier.weight(1f))
                SignalStageMetric(thirdMetricLabel, thirdMetricValue, Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun SignalDetailCardVisual(
    item: SignalPaymentCardStackItem,
    availableBalance: String,
    progress: Float,
    isRtl: Boolean,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    val shape = RoundedCornerShape(30.dp)
    Box(
        modifier = modifier
            .height(214.dp)
            .graphicsLayer {
                val inverse = 1f - progress
                scaleX = 0.82f + (0.18f * progress)
                scaleY = 0.82f + (0.18f * progress)
                translationY = inverse * 38f * density
                alpha = 0.18f + (0.82f * progress)
                rotationX = inverse * 52f
                rotationY = inverse * (if (isRtl) 30f else -30f)
                cameraDistance = 18f * density
            }
            .clip(shape)
            .background(Color.White.copy(alpha = 0.105f), shape)
            .border(1.dp, Color.White.copy(alpha = 0.18f), shape),
    ) {
        SignalCardEdgeLines(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .height(214.dp),
            alpha = 0.82f,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SignalSchemeMark(item.mark)
                SignalCardStatusPill(item.status)
            }
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(
                    item.maskedNumber,
                    color = colors.textInverse,
                    style = SignalTheme.typography.pageTitle.copy(fontFeatureSettings = "tnum"),
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                )
                Text(
                    item.subtitle,
                    color = colors.textInverse.copy(alpha = 0.72f),
                    style = SignalTheme.typography.statusPill,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Text(
                availableBalance,
                modifier = Modifier
                    .background(Color.White.copy(alpha = 0.14f), RoundedCornerShape(999.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.16f), RoundedCornerShape(999.dp))
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                color = colors.textInverse.copy(alpha = 0.90f),
                style = SignalTheme.typography.rowMeta.copy(fontFeatureSettings = "tnum"),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

private enum class SignalStageActionIcon {
    Back,
    Refresh,
}

@Composable
private fun SignalDetailStageAction(icon: SignalStageActionIcon, onClick: () -> Unit) {
    val layoutDirection = LocalLayoutDirection.current
    val iconColor = SignalTheme.colors.textInverse
    Box(
        modifier = Modifier
            .size(44.dp)
            .clickable(onClick = onClick)
            .padding(10.dp),
        contentAlignment = Alignment.Center,
    ) {
        when (icon) {
            SignalStageActionIcon.Back -> SignalBackIcon(
                pointsRight = layoutDirection == LayoutDirection.Rtl,
                color = iconColor,
            )
            SignalStageActionIcon.Refresh -> SignalRefreshIcon(iconColor)
        }
    }
}

@Composable
private fun SignalStageMetric(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .height(62.dp)
            .background(Color.White.copy(alpha = 0.115f), RoundedCornerShape(18.dp))
            .border(1.dp, Color.White.copy(alpha = 0.14f), RoundedCornerShape(18.dp))
            .padding(horizontal = 8.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            label,
            color = SignalTheme.colors.textInverse.copy(alpha = 0.62f),
            style = SignalTheme.typography.statusPill,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            value,
            color = SignalTheme.colors.textInverse,
            style = SignalTheme.typography.rowTitle.copy(fontFeatureSettings = "tnum"),
            maxLines = 1,
            overflow = TextOverflow.Clip,
        )
    }
}

@Composable
private fun SignalSchemeMark(mark: SignalCardMark, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(30.dp)
            .widthIn(min = 54.dp)
            .background(Color.White.copy(alpha = 0.08f), RoundedCornerShape(12.dp))
            .border(1.dp, Color.White.copy(alpha = 0.10f), RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center,
    ) {
        when (mark) {
            SignalCardMark.Mastercard -> Row(horizontalArrangement = Arrangement.spacedBy((-7).dp)) {
                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .background(Color(0xFFF15A24).copy(alpha = 0.95f), RoundedCornerShape(999.dp)),
                )
                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .background(Color(0xFFFFC04D).copy(alpha = 0.92f), RoundedCornerShape(999.dp)),
                )
            }
            SignalCardMark.Visa -> Text(
                text = "VISA",
                color = SignalTheme.colors.textInverse,
                style = SignalTheme.typography.rowTitle,
                textAlign = TextAlign.Center,
                maxLines = 1,
            )
            SignalCardMark.Numo -> Text(
                text = "NUMO",
                color = SignalTheme.colors.textInverse,
                style = SignalTheme.typography.rowTitle,
                textAlign = TextAlign.Center,
                maxLines = 1,
            )
        }
    }
}

@Composable
private fun SignalCardSignalRail(signal: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(6.dp)
            .background(signal, RoundedCornerShape(topEnd = 999.dp, bottomEnd = 999.dp)),
    )
}

@Composable
private fun SignalDetailCardAccentRail(signal: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(5.dp)
            .height(132.dp)
            .background(signal, RoundedCornerShape(999.dp)),
    )
}

@Composable
private fun SignalCardEdgeLines(modifier: Modifier = Modifier, alpha: Float = 1f) {
    Canvas(
        modifier = modifier
            .width(64.dp),
    ) {
        val x0 = size.width - 24.dp.toPx()
        val gap = 18.dp.toPx()
        val stroke = 1.dp.toPx()
        drawLine(
            color = Color.White.copy(alpha = alpha * 0.18f),
            start = Offset(x0, 0f),
            end = Offset(x0, size.height),
            strokeWidth = stroke,
        )
        drawLine(
            color = Color.White.copy(alpha = alpha * 0.10f),
            start = Offset(x0 + gap, 0f),
            end = Offset(x0 + gap, size.height),
            strokeWidth = stroke,
        )
        drawLine(
            color = Color.White.copy(alpha = alpha * 0.06f),
            start = Offset(x0 + gap * 2f, 0f),
            end = Offset(x0 + gap * 2f, size.height),
            strokeWidth = stroke,
        )
    }
}

@Composable
private fun SignalDetailHalo(signal: Color, isRtl: Boolean, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(210.dp)
            .graphicsLayer {
                translationX = (if (isRtl) -72f else 72f) * density
                translationY = -86f * density
                scaleX = 1.34f
                rotationZ = if (isRtl) 18f else -18f
            }
            .border(1.dp, signal.copy(alpha = 0.42f), RoundedCornerShape(999.dp)),
    )
}

@Composable
private fun SignalBackIcon(pointsRight: Boolean, color: Color) {
    Canvas(modifier = Modifier.size(18.dp)) {
        val strokeWidth = 2.2.dp.toPx()
        val headX = if (pointsRight) size.width * 0.66f else size.width * 0.34f
        val tailX = if (pointsRight) size.width * 0.34f else size.width * 0.66f
        val topY = size.height * 0.24f
        val middleY = size.height * 0.5f
        val bottomY = size.height * 0.76f
        drawLine(
            color = color,
            start = Offset(tailX, topY),
            end = Offset(headX, middleY),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = color,
            start = Offset(headX, middleY),
            end = Offset(tailX, bottomY),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}

@Composable
private fun SignalRefreshIcon(color: Color) {
    Canvas(modifier = Modifier.size(19.dp)) {
        val strokeWidth = 2.dp.toPx()
        val inset = strokeWidth * 1.2f
        drawArc(
            color = color,
            startAngle = 32f,
            sweepAngle = 286f,
            useCenter = false,
            topLeft = Offset(inset, inset),
            size = Size(size.width - inset * 2f, size.height - inset * 2f),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
        )
        val tip = Offset(size.width * 0.76f, size.height * 0.2f)
        drawLine(
            color = color,
            start = tip,
            end = Offset(size.width * 0.76f, size.height * 0.42f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = color,
            start = tip,
            end = Offset(size.width * 0.56f, size.height * 0.24f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}

@Composable
private fun SignalCardStatusPill(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = SignalTheme.colors.textInverse,
        style = SignalTheme.typography.statusPill,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
            .background(Color.White.copy(alpha = 0.08f), RoundedCornerShape(999.dp))
            .border(1.dp, Color.White.copy(alpha = 0.10f), RoundedCornerShape(999.dp))
            .padding(horizontal = SignalSpacing.x2, vertical = 5.dp),
    )
}

@Composable
private fun SignalCardTag(text: String) {
    Text(
        text = text,
        color = SignalTheme.colors.textInverse.copy(alpha = 0.82f),
        style = SignalTheme.typography.statusPill,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .background(Color.Transparent, RoundedCornerShape(999.dp))
            .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(999.dp))
            .padding(horizontal = 8.dp, vertical = 5.dp),
    )
}
