package ly.neptune.signal.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import ly.neptune.signal.theme.SignalComponentMetrics
import ly.neptune.signal.theme.SignalPrismAppearance
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

data class SignalNavItem(
    val key: String,
    val label: String,
    val icon: @Composable () -> Unit,
)

data class SignalNavCenterAction(
    val label: String,
    val onClick: () -> Unit,
    val icon: @Composable () -> Unit,
)

enum class SignalNavSurfaceTone {
    ExpressiveTonal,
    Inverse,
    LowContrast,
}

data class SignalNavBarContract(
    val surfaceTone: SignalNavSurfaceTone = SignalNavSurfaceTone.ExpressiveTonal,
    val elevated: Boolean = true,
)

enum class SignalBottomNavVisualLanguage {
    SignalClassic,
    Prism,
}

@Composable
fun SignalTopBar(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    navigation: (@Composable () -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val titleColor = if (colors.dark) colors.onSurface else colors.bankPrimary

    Box(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .heightIn(min = if (subtitle == null) 64.dp else 68.dp)
            .padding(horizontal = SignalSpacing.x4, vertical = SignalSpacing.x2),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .widthIn(min = SignalComponentMetrics.iconButtonBox),
            contentAlignment = Alignment.CenterStart,
        ) {
            navigation?.invoke()
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 62.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = title,
                color = titleColor,
                style = typography.pageTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    color = colors.textSecondary,
                    style = typography.rowMeta,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .widthIn(min = SignalComponentMetrics.iconButtonBox),
            horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x1),
            verticalAlignment = Alignment.CenterVertically,
            content = actions,
        )
    }
}

enum class SignalPrismTopBarVariant {
    Static,
    Collapsing,
    FloatingCompact,
    ContentFirst,
    Detail,
}

@Composable
fun SignalPrismTopBar(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    variant: SignalPrismTopBarVariant = SignalPrismTopBarVariant.Static,
    navigation: (@Composable () -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    if (variant == SignalPrismTopBarVariant.ContentFirst) return
    val prism = SignalTheme.prism
    val typography = SignalTheme.typography
    val shape = RoundedCornerShape(if (variant == SignalPrismTopBarVariant.FloatingCompact) 26.dp else 0.dp)
    val container = when (prism.appearance) {
        SignalPrismAppearance.Light -> prism.surfaces.prismSurface
        SignalPrismAppearance.Dark -> prism.surfaces.prismBackground
        SignalPrismAppearance.Oled -> prism.surfaces.prismBackground
    }
    val titleColor = prism.palette.prismTextPrimary
    val metaColor = prism.palette.prismTextSecondary
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = container,
        contentColor = titleColor,
        shape = shape,
        shadowElevation = if (variant == SignalPrismTopBarVariant.FloatingCompact) 8.dp else 0.dp,
        tonalElevation = 0.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .heightIn(min = if (subtitle == null) 66.dp else 76.dp)
                .padding(horizontal = SignalSpacing.x4, vertical = SignalSpacing.x2),
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .widthIn(min = SignalComponentMetrics.iconButtonBox),
                contentAlignment = Alignment.CenterStart,
            ) {
                navigation?.invoke()
            }
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 64.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = title,
                    color = titleColor,
                    style = if (variant == SignalPrismTopBarVariant.Detail) typography.prismTitleCard else typography.prismTitleHero,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        color = metaColor,
                        style = typography.prismMeta,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .widthIn(min = SignalComponentMetrics.iconButtonBox),
                horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x1),
                verticalAlignment = Alignment.CenterVertically,
                content = actions,
            )
        }
    }
}

@Composable
fun SignalBottomNav(
    items: List<SignalNavItem>,
    activeKey: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    centerAction: SignalNavCenterAction? = null,
    contract: SignalNavBarContract = SignalNavBarContract(),
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val dimensions = SignalTheme.dimensions
    val navShape = RoundedCornerShape(32.dp)
    val navContainer = when (contract.surfaceTone) {
        SignalNavSurfaceTone.Inverse -> colors.inverseSurface
        SignalNavSurfaceTone.LowContrast -> colors.surfaceContainerLow
        SignalNavSurfaceTone.ExpressiveTonal -> if (colors.dark) colors.surfaceContainer else colors.surfaceContainerLow
    }
    val navOnContainer = when (contract.surfaceTone) {
        SignalNavSurfaceTone.Inverse -> colors.inverseOnSurface
        else -> colors.onSurface
    }
    val centerRing = colors.outlineVariant.copy(alpha = if (colors.dark) 0.24f else 0.30f)
    val navBorder = if (colors.dark) {
        colors.outlineVariant.copy(alpha = 0.12f)
    } else {
        colors.outlineVariant.copy(alpha = 0.18f)
    }
    val activeContainer = colors.primaryContainer
    val activeContent = colors.onPrimaryContainer
    val centerContainer = colors.primary
    val centerContent = colors.onPrimary
    val inactiveContent = when {
        contract.surfaceTone == SignalNavSurfaceTone.Inverse -> navOnContainer.copy(alpha = if (colors.dark) 0.78f else 0.74f)
        else -> colors.onSurfaceVariant
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .heightIn(min = if (centerAction == null) dimensions.navHeight else dimensions.navHeight + 22.dp)
            .background(colors.surface)
            .padding(horizontal = SignalSpacing.x4, vertical = 7.dp),
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(dimensions.navHeight)
                .clip(navShape)
                .background(navContainer)
                .border(1.dp, navBorder, navShape)
                .padding(horizontal = SignalSpacing.x1, vertical = 7.dp),
            horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x1),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items.forEachIndexed { index, item ->
                val active = item.key == activeKey
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .height(45.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(if (active) activeContainer.copy(alpha = if (colors.dark) 0.34f else 0.40f) else Color.Transparent)
                        .semantics {
                            role = Role.Tab
                            selected = active
                        }
                        .clickable { onSelected(item.key) }
                        .padding(horizontal = 2.dp, vertical = 3.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    CompositionLocalProvider(LocalContentColor provides if (active) activeContent else inactiveContent) {
                        Box(
                            modifier = Modifier.size(SignalComponentMetrics.bottomNavIcon),
                            contentAlignment = Alignment.Center,
                        ) {
                            item.icon()
                        }
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = item.label,
                        color = if (active) activeContent else inactiveContent,
                        style = typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                if (centerAction != null && index == (items.lastIndex / 2)) {
                    Spacer(modifier = Modifier.width(SignalComponentMetrics.bottomNavCenterSlot))
                }
            }
        }
        if (centerAction != null) {
            val centerShape = RoundedCornerShape(SignalTheme.shapes.full)
            val interactionSource = remember { MutableInteractionSource() }
            val pressed by interactionSource.collectIsPressedAsState()
            Surface(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-10).dp)
                    .size(SignalComponentMetrics.bottomNavCenterAction)
                    .graphicsLayer {
                        val scale = if (pressed) 0.972f else 1f
                        scaleX = scale
                        scaleY = scale
                    }
                    .shadow(if (colors.dark) 2.dp else 6.dp, centerShape, clip = false)
                    .clip(centerShape)
                    .semantics {
                        role = Role.Button
                        contentDescription = centerAction.label
                    }
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = centerAction.onClick,
                    ),
                color = centerContainer,
                contentColor = centerContent,
                shape = centerShape,
                border = BorderStroke(
                    1.dp,
                    centerRing,
                ),
            ) {
                CompositionLocalProvider(LocalContentColor provides centerContent) {
                    Box(contentAlignment = Alignment.Center) {
                        centerAction.icon()
                    }
                }
            }
        }
    }
}

@Composable
fun SignalPrismFloatingNav(
    items: List<SignalNavItem>,
    activeKey: String,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    centerAction: SignalNavCenterAction? = null,
) {
    val colors = SignalTheme.colors
    val prism = SignalTheme.prism
    val typography = SignalTheme.typography
    val dockShape = RoundedCornerShape(prism.radii.prismRadiusFloatingNav)
    val dockHeight = 72.dp
    val dockContainer = when (prism.appearance) {
        SignalPrismAppearance.Light -> Color(0xFFF3FAFF)
        SignalPrismAppearance.Dark -> prism.palette.prismDeepNavy
        SignalPrismAppearance.Oled -> Color(0xFF03070D)
    }
    val dockBorder = when (prism.appearance) {
        SignalPrismAppearance.Light -> prism.overlays.prismBorderSoft.copy(alpha = 0.08f)
        SignalPrismAppearance.Dark -> prism.overlays.prismBorderLuminous.copy(alpha = 0.05f)
        SignalPrismAppearance.Oled -> prism.overlays.prismBorderSoft.copy(alpha = 0.08f)
    }
    val activeContainer = when (prism.appearance) {
        SignalPrismAppearance.Light -> prism.palette.prismCyan.copy(alpha = 0.18f)
        else -> prism.palette.prismCyan.copy(alpha = 0.20f)
    }
    val activeContent = when (prism.appearance) {
        SignalPrismAppearance.Light -> prism.palette.prismOcean
        else -> Color.White
    }
    val inactiveContent = when (prism.appearance) {
        SignalPrismAppearance.Light -> colors.onSurfaceVariant
        else -> prism.palette.prismTextSecondary.copy(alpha = 0.82f)
    }
    val paymentSlot = if (centerAction == null) 0.dp else 80.dp

    Box(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .heightIn(min = dockHeight + if (centerAction == null) 14.dp else 30.dp)
            .padding(horizontal = 18.dp, vertical = 4.dp),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(dockHeight)
                .shadow(
                    elevation = if (colors.dark) 7.dp else 11.dp,
                    shape = dockShape,
                    clip = false,
                )
                .clip(dockShape)
                .background(dockContainer)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            prism.palette.prismCyan.copy(alpha = if (colors.dark) 0.105f else 0.16f),
                            prism.palette.prismViolet.copy(alpha = if (colors.dark) 0.035f else 0.055f),
                            Color.White.copy(alpha = 0.0f),
                        ),
                    ),
                )
                .border(1.dp, dockBorder, dockShape)
                .padding(horizontal = 8.dp, vertical = 8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                items.forEachIndexed { index, item ->
                    SignalPrismNavItem(
                        item = item,
                        active = item.key == activeKey,
                        activeContainer = activeContainer,
                        activeContent = activeContent,
                        inactiveContent = inactiveContent,
                        onClick = { onSelected(item.key) },
                        modifier = Modifier.weight(1f),
                    )
                    if (centerAction != null && index == (items.lastIndex / 2)) {
                        Spacer(modifier = Modifier.width(paymentSlot))
                    }
                }
            }
        }

        if (centerAction != null) {
            SignalPrismPaymentAction(
                action = centerAction,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 8.dp),
            )
        }
    }
}

@Composable
private fun SignalPrismNavItem(
    item: SignalNavItem,
    active: Boolean,
    activeContainer: Color,
    activeContent: Color,
    inactiveContent: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val typography = SignalTheme.typography
    Column(
        modifier = modifier
            .height(52.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(if (active) activeContainer else Color.Transparent)
            .semantics {
                role = Role.Tab
                selected = active
            }
            .clickable(onClick = onClick)
            .padding(horizontal = 2.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CompositionLocalProvider(LocalContentColor provides if (active) activeContent else inactiveContent) {
            Box(
                modifier = Modifier.size(23.dp),
                contentAlignment = Alignment.Center,
            ) {
                item.icon()
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = item.label,
            color = if (active) activeContent else inactiveContent,
            style = typography.prismNavLabel.copy(fontWeight = if (active) FontWeight.Bold else FontWeight.SemiBold),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun SignalPrismPaymentAction(
    action: SignalNavCenterAction,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val actionShape = RoundedCornerShape(SignalTheme.shapes.full)
    val gradient = Brush.linearGradient(
        colors = listOf(
            prism.palette.prismDeepNavy,
            prism.palette.prismCyan.copy(alpha = 0.96f),
            prism.palette.prismViolet.copy(alpha = 0.86f),
            prism.palette.prismCoral.copy(alpha = 0.68f),
        ),
    )

    Box(
        modifier = modifier
            .size(70.dp)
            .graphicsLayer {
                val scale = if (pressed) 0.972f else 1f
                scaleX = scale
                scaleY = scale
            }
            .shadow(6.dp, actionShape, clip = false)
            .clip(actionShape)
            .background(gradient)
            .border(1.dp, Color.White.copy(alpha = if (pressed) 0.18f else 0.10f), actionShape)
            .semantics {
                role = Role.Button
                contentDescription = action.label
            }
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = action.onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(actionShape)
                .background(Color.White.copy(alpha = if (pressed) 0.08f else 0.032f)),
            contentAlignment = Alignment.Center,
        ) {
            CompositionLocalProvider(LocalContentColor provides Color.White) {
                SignalPrismPaymentIcon(size = 30.dp)
            }
        }
    }
}

@Composable
fun SignalPrismPaymentIcon(
    modifier: Modifier = Modifier,
    size: Dp = 30.dp,
    color: Color = LocalContentColor.current,
) {
    Canvas(modifier = modifier.size(size)) {
        val strokeWidth = this.size.minDimension * 0.085f
        val stroke = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        val w = this.size.width
        val h = this.size.height
        val walletTop = h * 0.30f
        val walletHeight = h * 0.43f
        drawRoundRect(
            color = color,
            topLeft = Offset(w * 0.13f, walletTop),
            size = Size(w * 0.64f, walletHeight),
            cornerRadius = CornerRadius(w * 0.12f, w * 0.12f),
            style = stroke,
        )
        drawLine(
            color = color,
            start = Offset(w * 0.62f, h * 0.29f),
            end = Offset(w * 0.82f, h * 0.29f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = color,
            start = Offset(w * 0.76f, h * 0.20f),
            end = Offset(w * 0.87f, h * 0.29f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawLine(
            color = color,
            start = Offset(w * 0.76f, h * 0.38f),
            end = Offset(w * 0.87f, h * 0.29f),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
        drawCircle(
            color = color,
            radius = w * 0.035f,
            center = Offset(w * 0.62f, h * 0.52f),
        )
    }
}
