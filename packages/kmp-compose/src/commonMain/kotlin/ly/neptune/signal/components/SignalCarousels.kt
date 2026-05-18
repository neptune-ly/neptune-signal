package ly.neptune.signal.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ly.neptune.signal.motion.SignalMotion
import ly.neptune.signal.theme.SignalAuthMetrics
import ly.neptune.signal.theme.SignalComponentMetrics
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

data class SignalAccountCarouselItem(
    val accountName: String,
    val accountType: String,
    val statusLabel: String,
    val balance: String,
    val iban: String,
    val alias: String? = null,
    val balanceLabel: String? = null,
)

data class SignalCardCarouselItem(
    val scheme: String,
    val maskedNumber: String,
    val holderName: String? = null,
    val background: Color,
    val contentColor: Color = Color.White,
)

@Immutable
data class SignalOnboardingSlide(
    val eyebrow: String,
    val title: String,
    val body: String,
    val signalLabel: String,
    val primaryValue: String,
    val supportText: String,
    val icon: SignalIconName = SignalIconName.Shield,
)

@Composable
fun SignalOnboardingCarousel(
    slides: List<SignalOnboardingSlide>,
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    onSelectedIndexChange: (Int) -> Unit = {},
    progressLabel: (current: Int, total: Int) -> String = { current, total -> "$current / $total" },
    compact: Boolean = false,
) {
    if (slides.isEmpty()) return
    val safeIndex = selectedIndex.coerceIn(slides.indices)
    val pagerState = rememberPagerState(initialPage = safeIndex) { slides.size }
    LaunchedEffect(safeIndex, slides.size) {
        if (pagerState.currentPage != safeIndex) {
            pagerState.animateScrollToPage(
                page = safeIndex,
                animationSpec = tween(durationMillis = SignalMotion.OnboardingSnapMs, easing = SignalMotion.StandardEasing),
            )
        }
    }
    LaunchedEffect(pagerState.settledPage) {
        val page = pagerState.settledPage.coerceIn(slides.indices)
        if (page != selectedIndex) onSelectedIndexChange(page)
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .semantics {
                contentDescription = progressLabel(safeIndex + 1, slides.size)
            },
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
    ) {
        val pageHeight = if (compact) {
            SignalAuthMetrics.onboardingCompactHeroHeight + 154.dp
        } else {
            SignalAuthMetrics.onboardingHeroHeight + 176.dp
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(pageHeight),
            pageSpacing = SignalSpacing.x3,
            beyondViewportPageCount = 1,
        ) { pageIndex ->
            SignalOnboardingPage(
                slide = slides[pageIndex],
                compact = compact,
            )
        }
        SignalOnboardingDots(
            count = slides.size,
            selectedIndex = safeIndex,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(),
            progressLabel = progressLabel,
            onSelected = onSelectedIndexChange,
        )
    }
}

@Composable
private fun SignalOnboardingPage(
    slide: SignalOnboardingSlide,
    modifier: Modifier = Modifier,
    compact: Boolean = false,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(if (compact) SignalSpacing.x3 else SignalSpacing.x4),
    ) {
        SignalOnboardingHero(slide = slide, compact = compact)
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        ) {
            Text(
                text = slide.eyebrow,
                color = SignalTheme.colors.bankSecondary,
                style = SignalTheme.typography.statusPill,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = slide.title,
                modifier = Modifier.fillMaxWidth(),
                color = SignalTheme.colors.onSurface,
                style = SignalTheme.typography.headlineSmall,
                textAlign = TextAlign.Start,
                maxLines = if (compact) 2 else 3,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = slide.body,
                modifier = Modifier.fillMaxWidth(),
                color = SignalTheme.colors.onSurfaceVariant,
                style = SignalTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                maxLines = if (compact) 2 else 3,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun SignalOnboardingDots(
    count: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    progressLabel: (current: Int, total: Int) -> String,
    onSelected: (Int) -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(count) { index ->
            val active = index == selectedIndex.coerceIn(0, (count - 1).coerceAtLeast(0))
            val indicatorWidth = animateDpAsState(
                targetValue = if (active) 24.dp else 7.dp,
                animationSpec = tween(durationMillis = SignalMotion.OnboardingIndicatorMs, easing = SignalMotion.StandardEasing),
                label = "signal-onboarding-dot-width",
            )
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(SignalTheme.shapes.full))
                    .clickable { onSelected(index) }
                    .semantics {
                        contentDescription = progressLabel(index + 1, count)
                    },
                contentAlignment = Alignment.Center,
            ) {
                Box(
                    modifier = Modifier
                        .size(width = indicatorWidth.value, height = 7.dp)
                        .background(
                            if (active) {
                                SignalTheme.colors.bankAccent
                            } else {
                                SignalTheme.colors.onSurfaceVariant.copy(alpha = 0.28f)
                            },
                            RoundedCornerShape(SignalTheme.shapes.full),
                        ),
                )
            }
        }
    }
}

@Composable
private fun SignalOnboardingHero(
    slide: SignalOnboardingSlide,
    modifier: Modifier = Modifier,
    compact: Boolean = false,
) {
    val colors = SignalTheme.colors
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(if (compact) SignalAuthMetrics.onboardingCompactHeroHeight else SignalAuthMetrics.onboardingHeroHeight),
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            modifier = Modifier
                .padding(horizontal = SignalSpacing.x1)
                .fillMaxWidth(),
            color = if (colors.dark || colors.black) colors.surfaceContainer else colors.surfaceContainerLow,
            contentColor = colors.onSurface,
            shape = RoundedCornerShape(32.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, colors.outlineVariant.copy(alpha = if (colors.dark || colors.black) 0.44f else 0.50f)),
        ) {
            Column(
                modifier = Modifier.padding(horizontal = SignalSpacing.x4, vertical = if (compact) SignalSpacing.x3 else SignalSpacing.x4),
                verticalArrangement = Arrangement.spacedBy(if (compact) SignalSpacing.x2 else SignalSpacing.x3),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Surface(
                        color = if (colors.dark || colors.black) colors.surfaceContainerHigh else colors.primaryContainer,
                        contentColor = colors.bankPrimary,
                        shape = RoundedCornerShape(SignalTheme.shapes.full),
                        border = androidx.compose.foundation.BorderStroke(1.dp, colors.outlineVariant.copy(alpha = 0.42f)),
                    ) {
                        Text(
                            text = slide.signalLabel,
                            modifier = Modifier.padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x2),
                            style = SignalTheme.typography.statusPill,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(15.dp))
                            .background(colors.bankPrimary.copy(alpha = if (colors.dark || colors.black) 0.88f else 1f)),
                        contentAlignment = Alignment.Center,
                    ) {
                        SignalIcon(slide.icon, tint = colors.textInverse, size = SignalComponentMetrics.smallActionIcon)
                    }
                }
                Text(
                    text = slide.primaryValue,
                    color = colors.onSurface,
                    style = SignalTheme.typography.pageTitle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(colors.outlineVariant.copy(alpha = if (colors.dark || colors.black) 0.30f else 0.42f)),
                )
                Text(
                    text = slide.supportText,
                    color = colors.onSurfaceVariant,
                    style = SignalTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
fun SignalAccountCarousel(
    items: List<SignalAccountCarouselItem>,
    modifier: Modifier = Modifier,
    masked: Boolean = false,
    selectedIndex: Int = 0,
    onSelectedIndexChange: (Int) -> Unit = {},
    onOpenAccount: (Int) -> Unit = {},
    onToggleMasked: (() -> Unit)? = null,
    onCopyIban: ((SignalAccountCarouselItem) -> Unit)? = null,
) {
    if (items.isEmpty()) return
    val safeIndex = selectedIndex.coerceIn(items.indices)
    val pagerState = rememberPagerState(initialPage = safeIndex) { items.size }
    LaunchedEffect(safeIndex, items.size) {
        if (pagerState.currentPage != safeIndex) {
            pagerState.animateScrollToPage(safeIndex)
        }
    }
    LaunchedEffect(pagerState.settledPage) {
        val page = pagerState.settledPage.coerceIn(items.indices)
        if (page != selectedIndex) onSelectedIndexChange(page)
    }
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 0.dp),
            pageSpacing = SignalSpacing.x2,
            beyondViewportPageCount = 1,
        ) { pageIndex ->
            val account = items[pageIndex]
            SignalAccountPanel(
                item = account,
                index = pageIndex,
                count = items.size,
                masked = masked,
                onOpen = { onOpenAccount(pageIndex) },
                onToggleMasked = onToggleMasked,
                onCopyIban = onCopyIban?.let { { it(account) } },
            )
        }
    }
}

@Composable
private fun SignalAccountPanel(
    item: SignalAccountCarouselItem,
    index: Int,
    count: Int,
    masked: Boolean,
    onOpen: () -> Unit,
    onToggleMasked: (() -> Unit)?,
    onCopyIban: (() -> Unit)?,
) {
    val colors = SignalTheme.colors
    val panelShape = RoundedCornerShape(30.dp)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(SignalComponentMetrics.accountCarouselHeight)
            .clip(panelShape)
            .background(colors.bankPrimary, panelShape),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = SignalSpacing.x4, top = SignalSpacing.x4, end = SignalSpacing.x4, bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(9.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(14.dp))
                        .clickable(onClick = onOpen)
                        .semantics { contentDescription = "Open account" }
                        .padding(vertical = 1.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    Text(
                        text = item.accountName,
                        color = colors.textInverse,
                        style = SignalTheme.typography.screenTitle,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = item.accountType,
                        color = colors.textInverse.copy(alpha = 0.72f),
                        style = SignalTheme.typography.rowMeta,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                SignalAccountStatusPill(label = item.statusLabel)
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    Text(
                        text = "${index + 1}/$count",
                        color = colors.textInverse.copy(alpha = 0.60f),
                        style = SignalTheme.typography.rowMeta,
                        maxLines = 1,
                        overflow = TextOverflow.Clip,
                    )
                }
                if (onToggleMasked != null) {
                    SignalMaskControl(masked = masked, onClick = onToggleMasked)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onOpen)
                    .padding(top = 6.dp, bottom = 2.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                if (item.balanceLabel != null) {
                    Text(
                        text = item.balanceLabel,
                        color = colors.textInverse.copy(alpha = 0.66f),
                        style = SignalTheme.typography.rowMeta,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                SignalAccountBalanceText(
                    value = if (masked) "••••••" else item.balance,
                    masked = masked,
                )
            }
        }
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(start = SignalSpacing.x4, end = SignalSpacing.x4, bottom = 28.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = if (colors.dark) 0.075f else 0.085f), RoundedCornerShape(16.dp))
                    .height(38.dp)
                    .clickable(enabled = onCopyIban != null) { onCopyIban?.invoke() }
                    .padding(horizontal = 12.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.spacedBy(7.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("IBAN", color = colors.textInverse.copy(alpha = 0.64f), style = SignalTheme.typography.rowMeta)
                Text(
                    text = if (masked) "LY•••• •••• •••• •••• •••• ••••" else groupIban(item.iban),
                    color = colors.textInverse.copy(alpha = 0.90f),
                    style = SignalTheme.typography.labelMedium.copy(fontFeatureSettings = "tnum"),
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    softWrap = false,
                    modifier = Modifier
                        .weight(1f)
                        .horizontalScroll(rememberScrollState()),
                )
            }
        }
        SignalCarouselDots(
            count = count,
            selectedIndex = index,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(58.dp)
                .padding(bottom = 8.dp),
            onPrimary = true,
        )
    }
}

@Composable
private fun SignalAccountBalanceText(
    value: String,
    masked: Boolean,
) {
    val colors = SignalTheme.colors
    val baseStyle = SignalTheme.typography.displaySmall.copy(fontFeatureSettings = "tnum")
    if (masked) {
        Text(
            text = value,
            color = colors.textInverse,
            style = baseStyle,
            maxLines = 1,
            overflow = TextOverflow.Clip,
        )
        return
    }
    val parts = value.split(" ").filter { it.isNotBlank() }
    val currencyFirst = parts.firstOrNull()?.any { it.isLetter() } == true
    val currency = if (currencyFirst) parts.firstOrNull().orEmpty() else parts.getOrNull(1).orEmpty()
    val number = if (currencyFirst) parts.getOrNull(1).orEmpty() else parts.firstOrNull().orEmpty()
    val whole = number.substringBefore(".")
    val decimal = number.substringAfter(".", missingDelimiterValue = "")
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom,
        ) {
            if (currency.isNotEmpty()) {
                Text(
                    text = currency,
                    color = colors.textInverse.copy(alpha = 0.78f),
                    style = baseStyle.copy(fontSize = 22.sp, fontWeight = FontWeight.Bold),
                    maxLines = 1,
                )
                androidx.compose.foundation.layout.Spacer(Modifier.width(8.dp))
            }
            Text(
                text = buildAnnotatedString {
                    append(whole)
                    if (decimal.isNotEmpty()) {
                        withStyle(
                            SpanStyle(
                                color = colors.textInverse.copy(alpha = 0.70f),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.ExtraBold,
                            ),
                        ) {
                            append(".$decimal")
                        }
                    }
                },
                color = colors.textInverse,
                style = baseStyle,
                maxLines = 1,
                overflow = TextOverflow.Clip,
            )
        }
    }
}

@Composable
private fun SignalAccountStatusPill(label: String) {
    val colors = SignalTheme.colors
    Text(
        text = label,
        modifier = Modifier
            .clip(RoundedCornerShape(SignalTheme.shapes.full))
            .background(Color.White.copy(alpha = if (colors.dark) 0.10f else 0.12f))
            .padding(horizontal = SignalSpacing.x2, vertical = 3.dp),
        color = colors.textInverse.copy(alpha = 0.82f),
        style = SignalTheme.typography.statusPill,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
private fun SignalAccountPanelDecor(
    isRtl: Boolean,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    Canvas(modifier = modifier) {
        val orbitPath = Path().apply {
            val centerX = if (isRtl) size.width * 0.12f else size.width * 0.88f
            val centerY = size.height * 0.06f
            addOval(
                androidx.compose.ui.geometry.Rect(
                    left = centerX - size.width * 0.30f,
                    top = centerY - size.height * 0.48f,
                    right = centerX + size.width * 0.30f,
                    bottom = centerY + size.height * 0.72f,
                ),
            )
        }
        drawPath(
            path = orbitPath,
            color = colors.bankSecondary.copy(alpha = 0.08f),
            style = Stroke(width = 2.dp.toPx()),
        )
        drawCircle(
            color = colors.bankSecondary.copy(alpha = 0.035f),
            radius = size.minDimension * 0.28f,
            center = androidx.compose.ui.geometry.Offset(
                x = if (isRtl) size.width * 0.08f else size.width * 0.92f,
                y = size.height * 0.22f,
            ),
        )
        drawCircle(
            color = colors.bankAccent.copy(alpha = 0.28f),
            radius = size.minDimension * 0.048f,
            center = androidx.compose.ui.geometry.Offset(
                x = if (isRtl) size.width * 0.10f else size.width * 0.90f,
                y = size.height * 0.78f,
            ),
        )
    }
}

@Composable
private fun SignalAccountPanelAccentRail(
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                    .padding(vertical = 34.dp)
                    .width(4.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(SignalTheme.shapes.full))
                .background(colors.bankSecondary.copy(alpha = 0.46f)),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.30f)
                    .clip(RoundedCornerShape(SignalTheme.shapes.full))
                    .background(colors.bankAccent.copy(alpha = 0.52f)),
            )
        }
    }
}

@Composable
private fun SignalSwipeEdgeSignal(
    dragOffset: Float,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    val progress = (kotlin.math.abs(dragOffset) / 58f).coerceIn(0f, 1f)
    if (progress <= 0.02f) return
    Canvas(modifier = modifier) {
        val atEnd = dragOffset < 0f
        val edgeWidth = size.width * (0.10f + progress * 0.08f)
        val edgeX = if (atEnd) size.width - edgeWidth else 0f
        val railX = if (atEnd) size.width - 12.dp.toPx() else 12.dp.toPx()
        val height = size.height * (0.32f + progress * 0.34f)
        drawRoundRect(
            color = colors.bankSecondary.copy(alpha = 0.06f + progress * 0.10f),
            topLeft = androidx.compose.ui.geometry.Offset(edgeX, 0f),
            size = androidx.compose.ui.geometry.Size(edgeWidth, size.height),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(26.dp.toPx(), 26.dp.toPx()),
        )
        drawRoundRect(
            color = colors.bankSecondary.copy(alpha = 0.34f + progress * 0.46f),
            topLeft = androidx.compose.ui.geometry.Offset(
                x = railX - 2.5.dp.toPx(),
                y = (size.height - height) / 2f,
            ),
            size = androidx.compose.ui.geometry.Size(5.dp.toPx(), height),
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(99f, 99f),
        )
        drawCircle(
            color = colors.textInverse.copy(alpha = 0.18f + progress * 0.18f),
            radius = 4.dp.toPx() + progress * 2.dp.toPx(),
            center = androidx.compose.ui.geometry.Offset(
                x = if (atEnd) size.width - 32.dp.toPx() else 32.dp.toPx(),
                y = size.height * 0.50f,
            ),
        )
    }
}

@Composable
private fun SignalMaskControl(masked: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(37.dp)
            .clickable(onClick = onClick)
            .semantics { contentDescription = if (masked) "Show account data" else "Hide account data" },
        contentAlignment = Alignment.Center,
    ) {
        SignalIcon(
            name = if (masked) SignalIconName.EyeOff else SignalIconName.Eye,
            tint = SignalTheme.colors.textInverse.copy(alpha = 0.94f),
            size = SignalComponentMetrics.smallActionIcon,
        )
    }
}

@Composable
fun SignalCardCarousel(
    items: List<SignalCardCarouselItem>,
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    onCardSelected: (Int) -> Unit = {},
    itemPeek: Dp = 44.dp,
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        SignalPeekCarousel(
            itemCount = items.size,
            itemPeek = itemPeek,
        ) { index, itemModifier ->
            val card = items[index]
            Box(
                modifier = itemModifier.clickable { onCardSelected(index) },
            ) {
                SignalCardFace(
                    scheme = card.scheme,
                    maskedNumber = card.maskedNumber,
                    holderName = card.holderName,
                    background = card.background,
                    contentColor = card.contentColor,
                )
            }
        }
        SignalCarouselDots(count = items.size, selectedIndex = selectedIndex)
    }
}

@Composable
private fun SignalCarouselDots(count: Int, selectedIndex: Int, modifier: Modifier = Modifier, onPrimary: Boolean = false) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(count) { index ->
            val active = index == selectedIndex.coerceIn(0, (count - 1).coerceAtLeast(0))
            Box(
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .size(width = if (active) 18.dp else 6.dp, height = 6.dp)
                    .background(
                        when {
                            active -> SignalTheme.colors.bankAccent
                            onPrimary -> SignalTheme.colors.textInverse.copy(alpha = 0.30f)
                            else -> SignalTheme.colors.onSurfaceVariant.copy(alpha = 0.28f)
                        },
                        androidx.compose.foundation.shape.RoundedCornerShape(SignalTheme.shapes.full),
                    ),
            )
        }
    }
}

@Composable
private fun SignalPeekCarousel(
    itemCount: Int,
    modifier: Modifier = Modifier,
    itemPeek: Dp,
    content: @Composable (index: Int, itemModifier: Modifier) -> Unit,
) {
    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val itemWidth = (maxWidth - itemPeek).coerceAtLeast(280.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
        ) {
            repeat(itemCount) { index ->
                content(index, Modifier.width(itemWidth))
            }
        }
    }
}

private fun groupIban(value: String): String =
    compactGroupedCriticalId(value)

private fun compactGroupedCriticalId(value: String): String {
    val clean = value.filter { it.isLetterOrDigit() }
    if (clean.isEmpty()) return value
    val groups = clean.chunked(4)
    if (groups.size <= 5) return groups.joinToString(" ")
    return groups.take(5).joinToString(" ") + " " + groups.drop(5).joinToString("")
}
