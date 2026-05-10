package ly.neptune.signal.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
)

data class SignalCardCarouselItem(
    val scheme: String,
    val maskedNumber: String,
    val holderName: String? = null,
    val background: Color,
    val contentColor: Color = Color.White,
)

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
    val layoutDirection = LocalLayoutDirection.current
    val isRtl = layoutDirection == LayoutDirection.Rtl
    val panelShape = RoundedCornerShape(30.dp)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(182.dp)
            .clip(panelShape)
            .background(colors.bankPrimary, panelShape)
            .clickable(onClick = onOpen)
            .semantics {
                contentDescription = "Open account"
                onClick {
                    onOpen()
                    true
                }
            },
    ) {
        SignalAccountPanelDecor(
            isRtl = isRtl,
            modifier = Modifier.matchParentSize(),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = SignalSpacing.x4, top = SignalSpacing.x4, end = SignalSpacing.x4, bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                            .clip(RoundedCornerShape(14.dp))
                        .padding(vertical = 1.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    Text(
                        text = item.accountName,
                        color = colors.textInverse,
                        style = SignalTheme.typography.pageTitle,
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
                if (onToggleMasked != null) {
                    SignalMaskControl(masked = masked, onClick = onToggleMasked)
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = item.statusLabel,
                    color = colors.textInverse.copy(alpha = 0.72f),
                    style = SignalTheme.typography.rowMeta,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    Text(
                        text = "${index + 1} / $count",
                        color = colors.textInverse.copy(alpha = 0.64f),
                        style = SignalTheme.typography.rowMeta,
                        maxLines = 1,
                        overflow = TextOverflow.Clip,
                    )
                }
            }
            Text(
                text = if (masked) "••••••" else item.balance,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .padding(vertical = 1.dp),
                color = colors.textInverse,
                style = SignalTheme.typography.balance.copy(fontFeatureSettings = "tnum"),
                maxLines = 1,
                overflow = TextOverflow.Clip,
            )
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(14.dp))
                        .heightIn(min = 34.dp)
                        .padding(vertical = 2.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("IBAN", color = colors.textInverse.copy(alpha = 0.58f), style = SignalTheme.typography.rowMeta)
                    Text(
                        text = if (masked) "LY•••• •••• •••• ••••" else groupIban(item.iban),
                        color = colors.textInverse.copy(alpha = 0.88f),
                        style = SignalTheme.typography.labelMedium.copy(fontFeatureSettings = "tnum"),
                        maxLines = 1,
                        overflow = TextOverflow.Clip,
                        softWrap = false,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
        SignalCarouselDots(
            count = count,
            selectedIndex = index,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(58.dp)
                .padding(bottom = 10.dp),
            onPrimary = true,
        )
        SignalAccountPanelAccentRail(
            modifier = Modifier.matchParentSize(),
        )
        if (onCopyIban != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .fillMaxWidth()
                    .height(64.dp)
                    .clickable(onClick = onCopyIban)
                    .semantics { contentDescription = "Copy IBAN" },
            )
        }
    }
}

@Composable
private fun SignalAccountPanelDecor(
    isRtl: Boolean,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    Canvas(modifier = modifier) {
        val orbitPath = Path().apply {
            val centerX = if (isRtl) size.width * 0.22f else size.width * 0.78f
            val centerY = size.height * 0.02f
            addOval(
                androidx.compose.ui.geometry.Rect(
                    left = centerX - size.width * 0.36f,
                    top = centerY - size.height * 0.52f,
                    right = centerX + size.width * 0.36f,
                    bottom = centerY + size.height * 0.88f,
                ),
            )
        }
        drawPath(
            path = orbitPath,
            color = colors.bankSecondary.copy(alpha = 0.36f),
            style = Stroke(width = 2.2.dp.toPx()),
        )
        drawPath(
            path = orbitPath,
            color = Color.White.copy(alpha = 0.10f),
            style = Stroke(width = 8.dp.toPx()),
        )
        drawCircle(
            color = colors.bankSecondary.copy(alpha = 0.18f),
            radius = size.minDimension * 0.36f,
            center = androidx.compose.ui.geometry.Offset(
                x = if (isRtl) size.width * 0.16f else size.width * 0.84f,
                y = size.height * 0.24f,
            ),
        )
        drawCircle(
            color = colors.bankAccent.copy(alpha = 0.90f),
            radius = size.minDimension * 0.065f,
            center = androidx.compose.ui.geometry.Offset(
                x = if (isRtl) size.width * 0.08f else size.width * 0.92f,
                y = size.height * 0.76f,
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
                    .padding(vertical = 26.dp)
                    .width(6.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(SignalTheme.shapes.full))
                .background(colors.bankSecondary.copy(alpha = 0.78f)),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.34f)
                    .clip(RoundedCornerShape(SignalTheme.shapes.full))
                    .background(colors.bankAccent.copy(alpha = 0.64f)),
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
