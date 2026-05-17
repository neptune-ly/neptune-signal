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
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalComponentMetrics
import ly.neptune.signal.theme.SignalMotionMetrics
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
    val cardForeground = cardContentOnBackground(item.background)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(SignalComponentMetrics.cardFaceListHeight)
            .graphicsLayer {
                val inverse = 1f - progress
                scaleX = 0.986f + (0.014f * progress)
                scaleY = 0.986f + (0.014f * progress)
                translationY = inverse * (8f + index.coerceAtMost(4) * 2f) * density
                alpha = 0.78f + (0.22f * progress)
                rotationX = inverse * 2f
                cameraDistance = 20f * density
            }
            .clip(cardShape)
            .background(item.background)
            .border(1.dp, Color.White.copy(alpha = 0.14f), cardShape),
    ) {
        SignalCardModernTexture(signal = item.signal, modifier = Modifier.matchParentSize())
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = item.label,
                    modifier = Modifier.weight(1f),
                    color = cardForeground,
                    style = SignalTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                SignalSchemeMark(item.mark, tint = cardForeground)
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = item.maskedNumber,
                    color = cardForeground,
                    style = SignalTheme.typography.pageTitle.copy(fontFeatureSettings = "tnum"),
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                )
                Text(
                    text = item.subtitle,
                    color = cardForeground.copy(alpha = 0.82f),
                    style = SignalTheme.typography.statusPill,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = item.scheme,
                    color = cardForeground.copy(alpha = 0.70f),
                    style = SignalTheme.typography.statusPill,
                    maxLines = 1,
                )
                SignalCardStatusPill(text = item.status, textColor = cardForeground)
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
        animationSpec = tween(durationMillis = SignalMotionMetrics.sharedElementMillis, easing = SignalCardMotionEasing),
        label = "signal-card-detail-entry",
    )
    val progress = stageProgress.coerceIn(0f, 1f)
    val stageShape = RoundedCornerShape(bottomStart = 42.dp, bottomEnd = 42.dp)
    val stageBackground = if (colors.black) colors.surface else if (colors.dark) colors.surfaceCard else colors.surfaceContainerLow
    val cardForeground = cardContentOnBackground(item.background)
    val stageForeground = colors.onSurface

    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 420.dp)
            .graphicsLayer {
                val inverse = 1f - progress
                scaleX = 0.972f + (0.028f * progress)
                scaleY = 0.972f + (0.028f * progress)
                translationY = inverse * -10f * density
                alpha = 0.84f + (0.16f * progress)
                rotationX = inverse * 4f
                cameraDistance = 20f * density
            }
            .clip(stageShape)
            .background(stageBackground),
    ) {
        SignalCleanStageShape(
            cardColor = item.background,
            signal = item.signal,
            isRtl = isRtl,
            modifier = Modifier.matchParentSize(),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
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
                        color = stageForeground,
                        style = SignalTheme.typography.rowTitle,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        item.maskedNumber,
                        color = colors.onSurfaceVariant,
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
                textColor = cardForeground,
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
    textColor: Color,
    availableBalance: String,
    progress: Float,
    isRtl: Boolean,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    val shape = RoundedCornerShape(30.dp)
    val visualBackground = item.background
    val visualStroke = Color.White
    Box(
        modifier = modifier
            .height(232.dp)
            .graphicsLayer {
                val inverse = 1f - progress
                scaleX = 0.88f + (0.12f * progress)
                scaleY = 0.88f + (0.12f * progress)
                translationY = inverse * 26f * density
                alpha = 0.24f + (0.76f * progress)
                rotationX = inverse * 18f
                rotationY = inverse * (if (isRtl) 10f else -10f)
                cameraDistance = 22f * density
            }
            .clip(shape)
            .background(visualBackground, shape)
            .border(1.dp, visualStroke.copy(alpha = 0.18f), shape),
    ) {
        SignalCardModernTexture(signal = item.signal, modifier = Modifier.matchParentSize(), quiet = true)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
        ) {
            SignalSchemeMark(item.mark)
            SignalCardStatusPill(item.status, textColor = textColor)
        }
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(
                    item.maskedNumber,
                    color = textColor,
                    style = SignalTheme.typography.pageTitle.copy(fontFeatureSettings = "tnum"),
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                )
                Text(
                    item.subtitle,
                    color = textColor.copy(alpha = 0.82f),
                    style = SignalTheme.typography.statusPill,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Text(
                availableBalance,
                modifier = Modifier
                .background(
                    if (textColor.luminance() > 0.48f) Color(0x0F0F172A) else Color.White.copy(alpha = 0.16f),
                    RoundedCornerShape(999.dp),
                )
                .border(
                    1.dp,
                    if (textColor.luminance() > 0.48f) Color(0x1F0F172A) else Color.White.copy(alpha = 0.18f),
                    RoundedCornerShape(999.dp),
                )
                .padding(horizontal = 14.dp, vertical = 7.dp),
                color = textColor.copy(alpha = 0.90f),
                style = SignalTheme.typography.rowMeta.copy(fontFeatureSettings = "tnum"),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun SignalCardModernTexture(
    signal: Color,
    modifier: Modifier = Modifier,
    quiet: Boolean = false,
) {
    Canvas(modifier = modifier) {
        val alpha = if (quiet) 0.055f else 0.07f
        drawCircle(
            color = Color.White.copy(alpha = alpha),
            radius = size.minDimension * 0.34f,
            center = Offset(size.width * 0.04f, size.height * 0.08f),
        )
        drawCircle(
            color = signal.copy(alpha = if (quiet) 0.12f else 0.16f),
            radius = size.minDimension * 0.13f,
            center = Offset(size.width * 0.90f, size.height * 0.82f),
        )
        drawLine(
            color = Color.White.copy(alpha = if (quiet) 0.06f else 0.075f),
            start = Offset(size.width * 0.11f, size.height * 0.20f),
            end = Offset(size.width * 0.42f, size.height * 0.20f),
            strokeWidth = 2.dp.toPx(),
            cap = StrokeCap.Round,
        )
        drawLine(
            color = signal.copy(alpha = if (quiet) 0.16f else 0.22f),
            start = Offset(size.width * 0.12f, size.height * 0.80f),
            end = Offset(size.width * 0.44f, size.height * 0.80f),
            strokeWidth = 2.dp.toPx(),
            cap = StrokeCap.Round,
        )
    }
}

private fun cardContentOnBackground(background: Color): Color {
    return if (background.luminance() > 0.48f) Color(0xFF0F172A) else Color.White
}

private enum class SignalStageActionIcon {
    Back,
    Refresh,
}

@Composable
private fun SignalDetailStageAction(icon: SignalStageActionIcon, onClick: () -> Unit) {
    when (icon) {
        SignalStageActionIcon.Back -> SignalDetailBackAction(onClick = onClick, contentDescription = "رجوع")
        SignalStageActionIcon.Refresh -> {
            val iconColor = SignalTheme.colors.onSurface
            val actionShape = RoundedCornerShape(16.dp)
            Box(
                modifier = Modifier
                    .size(SignalComponentMetrics.touchTarget)
                    .clip(actionShape)
                    .background(SignalTheme.colors.surfaceContainerHigh.copy(alpha = if (SignalTheme.colors.dark) 0.72f else 0.86f))
                    .border(1.dp, SignalTheme.colors.outlineVariant.copy(alpha = 0.18f), actionShape)
                    .clickable(onClick = onClick)
                    .padding(10.dp),
                contentAlignment = Alignment.Center,
            ) {
                SignalRefreshIcon(iconColor)
            }
        }
    }
}

@Composable
private fun SignalStageMetric(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .height(62.dp)
            .background(
                if (SignalTheme.colors.black) Color.White.copy(alpha = 0.075f)
                else if (SignalTheme.colors.dark) Color.White.copy(alpha = 0.09f)
                else SignalTheme.colors.surfaceContainerHigh.copy(alpha = 0.80f),
                RoundedCornerShape(18.dp),
            )
            .border(
                1.dp,
                if (SignalTheme.colors.dark) Color.White.copy(alpha = 0.10f) else SignalTheme.colors.outlineVariant,
                RoundedCornerShape(18.dp),
            )
            .padding(horizontal = 8.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            label,
            color = SignalTheme.colors.onSurfaceVariant,
            style = SignalTheme.typography.statusPill,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            value,
            color = SignalTheme.colors.onSurface,
            style = SignalTheme.typography.rowTitle.copy(fontFeatureSettings = "tnum"),
            maxLines = 1,
            overflow = TextOverflow.Clip,
        )
    }
}

@Composable
private fun SignalSchemeMark(
    mark: SignalCardMark,
    modifier: Modifier = Modifier,
    tint: Color = SignalTheme.colors.textInverse,
) {
    Box(
        modifier = modifier
            .height(28.dp)
            .widthIn(min = 52.dp)
            .background(Color.White.copy(alpha = 0.075f), RoundedCornerShape(11.dp))
            .border(1.dp, Color.White.copy(alpha = 0.095f), RoundedCornerShape(11.dp))
            .padding(horizontal = 7.dp),
        contentAlignment = Alignment.Center,
    ) {
        when (mark) {
            SignalCardMark.Mastercard -> Row(horizontalArrangement = Arrangement.spacedBy((-7).dp)) {
                Box(
                    modifier = Modifier
                        .size(21.dp)
                        .background(Color(0xFFF15A24).copy(alpha = 0.95f), RoundedCornerShape(999.dp)),
                )
                Box(
                    modifier = Modifier
                        .size(21.dp)
                        .background(Color(0xFFFFC04D).copy(alpha = 0.92f), RoundedCornerShape(999.dp)),
                )
            }
            SignalCardMark.Visa -> Text(
                text = "VISA",
                color = tint,
                style = SignalTheme.typography.rowTitle,
                textAlign = TextAlign.Center,
                maxLines = 1,
            )
            SignalCardMark.Numo -> Text(
                text = "NUMO",
                color = tint,
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
private fun SignalCleanStageShape(
    cardColor: Color,
    signal: Color,
    isRtl: Boolean,
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier) {
        val startX = if (isRtl) size.width * 0.18f else size.width * -0.08f
        drawRoundRect(
            color = cardColor.copy(alpha = 0.10f),
            topLeft = Offset(startX, size.height * 0.15f),
            size = androidx.compose.ui.geometry.Size(size.width * 0.92f, size.height * 0.64f),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(34.dp.toPx(), 34.dp.toPx()),
        )
        drawCircle(
            color = signal.copy(alpha = 0.105f),
            radius = size.minDimension * 0.27f,
            center = Offset(if (isRtl) size.width * 0.14f else size.width * 0.86f, size.height * 0.2f),
        )
        drawLine(
            color = signal.copy(alpha = 0.30f),
            start = Offset(if (isRtl) size.width * 0.08f else size.width * 0.92f, size.height * 0.38f),
            end = Offset(if (isRtl) size.width * 0.42f else size.width * 0.58f, size.height * 0.38f),
            strokeWidth = 5.dp.toPx(),
            cap = androidx.compose.ui.graphics.StrokeCap.Round,
        )
    }
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
private fun SignalCardStatusPill(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
) {
    val onChipBackground = if (textColor.luminance() > 0.48f) Color(0x150F172A) else Color.White.copy(alpha = 0.10f)
    val onChipBorder = if (textColor.luminance() > 0.48f) Color(0x260F172A) else Color.White.copy(alpha = 0.12f)
    Text(
        text = text,
        color = textColor,
        style = SignalTheme.typography.statusPill,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
            .background(onChipBackground, RoundedCornerShape(999.dp))
            .border(1.dp, onChipBorder, RoundedCornerShape(999.dp))
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
