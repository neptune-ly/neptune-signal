package ly.neptune.signal.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalComponentMetrics
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

data class SignalQuickAction(
    val key: String,
    val label: String,
    val onClick: () -> Unit,
    val icon: @Composable () -> Unit = {},
    val subtitle: String? = null,
)

@Composable
fun SignalBankHeader(
    bankName: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    mark: String = "N.",
    brandVisual: (@Composable () -> Unit)? = null,
    actionContentDescription: String = "Notifications",
    onActionClick: (() -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val titleColor = if (colors.dark) colors.onSurface else colors.bankPrimary
    Row(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .heightIn(min = 68.dp)
            .padding(vertical = SignalSpacing.x2),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(SignalTheme.shapes.sm))
                .background(colors.bankSecondary),
            contentAlignment = Alignment.Center,
        ) {
            CompositionLocalProvider(LocalContentColor provides Color.White) {
                if (brandVisual != null) {
                    brandVisual()
                } else {
                    Text(
                        text = mark,
                        color = Color.White,
                        style = SignalTheme.typography.screenTitle,
                        maxLines = 1,
                        overflow = TextOverflow.Clip,
                    )
                }
            }
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = bankName,
                color = titleColor,
                style = SignalTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (subtitle.isNotBlank()) {
                Text(
                    text = subtitle,
                    color = colors.onSurfaceVariant,
                    style = SignalTheme.typography.rowMeta,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        Box(
            modifier = Modifier
                .size(42.dp)
                .clickable(enabled = onActionClick != null) { onActionClick?.invoke() }
                .semantics { contentDescription = actionContentDescription },
            contentAlignment = Alignment.Center,
        ) {
            SignalIcon(
                name = SignalIconName.Bell,
                tint = titleColor,
                size = 24.dp,
            )
        }
    }
}

@Composable
fun SignalCustomerGreeting(
    greeting: String,
    name: String,
    metadata: String,
    modifier: Modifier = Modifier,
    identifier: String? = null,
    trailing: (@Composable () -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val shapes = SignalTheme.shapes
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 64.dp)
            .background(colors.surfaceContainerLow, RoundedCornerShape(shapes.lg))
            .border(BorderStroke(1.dp, colors.outlineVariant), RoundedCornerShape(shapes.lg))
            .padding(SignalSpacing.x2),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SignalCustomerAvatar(name = name)
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = greeting,
                color = colors.onSurfaceVariant,
                style = SignalTheme.typography.rowMeta,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = name,
                color = colors.onSurface,
                style = SignalTheme.typography.screenTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (identifier != null) {
                Text(
                    text = identifier,
                    color = colors.onSurfaceVariant,
                    style = SignalTheme.typography.rowMeta,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                )
            }
            Text(
                text = metadata,
                color = colors.onSurfaceVariant,
                style = SignalTheme.typography.rowMeta,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        if (trailing != null) {
            Box(modifier = Modifier.size(30.dp), contentAlignment = Alignment.Center) {
                trailing()
            }
        }
    }
}

@Composable
fun SignalCustomerStrip(
    greeting: String,
    name: String,
    metadata: String,
    modifier: Modifier = Modifier,
    identifier: String? = null,
    trailing: (@Composable () -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp)
            .padding(horizontal = SignalSpacing.x1),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(1.dp),
        ) {
            Text(
                text = "$greeting $name",
                color = colors.onSurfaceVariant,
                style = SignalTheme.typography.rowTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = listOfNotNull(identifier, metadata.takeIf { it.isNotBlank() }).joinToString(" · "),
                color = colors.onSurfaceVariant,
                style = SignalTheme.typography.rowMeta,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        if (trailing != null) {
            Box(modifier = Modifier.size(32.dp), contentAlignment = Alignment.Center) {
                trailing()
            }
        }
    }
}

@Composable
private fun SignalCustomerAvatar(name: String, size: Dp = 40.dp) {
    val colors = SignalTheme.colors
    Box(
        modifier = Modifier
            .size(size)
            .background(colors.bankPrimary, RoundedCornerShape(SignalTheme.shapes.full)),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = signalInitials(name),
            color = Color.White,
            style = SignalTheme.typography.rowTitle,
            maxLines = 1,
            overflow = TextOverflow.Clip,
        )
    }
}

private fun signalInitials(name: String): String {
    val parts = name.trim().split(Regex("\\s+")).filter { it.isNotBlank() }
    return parts.take(2).mapNotNull { part ->
        val normalized = if (part.startsWith("ال") && part.length > 2) part.drop(2) else part
        normalized.firstOrNull()?.toString()
    }.joinToString("").ifBlank { "N." }
}

@Composable
fun SignalActionDock(
    primaryLabel: String,
    onPrimaryClick: () -> Unit,
    actions: List<SignalQuickAction>,
    modifier: Modifier = Modifier,
    primaryIcon: (@Composable () -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val shapes = SignalTheme.shapes

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.surfaceCard.copy(alpha = if (colors.dark) 0.88f else 0.94f), RoundedCornerShape(28.dp))
            .border(BorderStroke(1.dp, colors.outlineVariant.copy(alpha = 0.72f)), RoundedCornerShape(28.dp))
            .padding(SignalSpacing.x2),
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
    ) {
        SignalCommandButton(
            label = primaryLabel,
            onClick = onPrimaryClick,
            modifier = Modifier.fillMaxWidth(),
            leading = primaryIcon,
        )
        SignalShortcutRow(actions = actions)
    }
}

@Composable
private fun SignalCommandButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leading: (@Composable () -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val shapes = SignalTheme.shapes
    val layoutDirection = LocalLayoutDirection.current
    val labelAlign = if (layoutDirection == LayoutDirection.Rtl || label.hasSignalRtlScript()) {
        TextAlign.Right
    } else {
        TextAlign.Left
    }
    Row(
        modifier = modifier
            .heightIn(min = 66.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(colors.bankPrimary)
            .clickable(onClick = onClick)
            .padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x2),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leading != null) {
            CompositionLocalProvider(LocalContentColor provides colors.textInverse) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .background(colors.bankSecondary, RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center,
                ) {
                    leading()
                }
            }
        } else {
            Box(modifier = Modifier.size(38.dp))
        }
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            color = colors.textInverse,
            style = SignalTheme.typography.screenTitle,
            textAlign = labelAlign,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Box(modifier = Modifier.size(34.dp), contentAlignment = Alignment.Center) {
            SignalIcon(
                name = SignalIconName.ChevronEnd,
                tint = colors.textInverse,
                size = 22.dp,
            )
        }
    }
}

private fun String.hasSignalRtlScript(): Boolean =
    any { it in '\u0590'..'\u08FF' || it in '\uFB50'..'\uFDFF' || it in '\uFE70'..'\uFEFF' }

@Composable
fun SignalShortcutRow(
    actions: List<SignalQuickAction>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
    ) {
        actions.take(4).forEach { action ->
            SignalQuickActionButton(
                action = action,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
fun SignalQuickActionButton(
    action: SignalQuickAction,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val shapes = SignalTheme.shapes

    Column(
        modifier = modifier
            .heightIn(min = if (action.subtitle == null) 67.dp else 80.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(colors.surfaceContainerLowest, RoundedCornerShape(22.dp))
            .border(BorderStroke(1.dp, colors.outlineVariant.copy(alpha = 0.82f)), RoundedCornerShape(22.dp))
            .clickable(onClick = action.onClick)
            .padding(horizontal = SignalSpacing.x2, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(modifier = Modifier.size(SignalComponentMetrics.rowIconGlyph), contentAlignment = Alignment.Center) {
            CompositionLocalProvider(LocalContentColor provides colors.bankSecondary) {
                action.icon()
            }
        }
        Spacer(modifier = Modifier.size(width = 1.dp, height = 4.dp))
        Text(
            text = action.label,
            color = colors.onSurface,
            style = typography.rowTitle,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        if (action.subtitle != null) {
            Text(
                text = action.subtitle,
                color = colors.onSurfaceVariant,
                style = typography.statusPill,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun SignalInsightCard(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    tone: SignalRowTone = SignalRowTone.Accent,
    leading: @Composable () -> Unit = {},
    onClick: (() -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val shapes = SignalTheme.shapes
    val accent = signalToneColor(tone)
    val click = if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier
    val background = if (colors.dark) colors.surfaceContainerHigh else colors.surfaceContainerLowest

    Row(
        modifier = modifier
            .then(click)
            .fillMaxWidth()
            .heightIn(min = 67.dp)
            .background(background, RoundedCornerShape(shapes.md))
            .border(BorderStroke(1.dp, colors.outlineVariant), RoundedCornerShape(shapes.md))
            .padding(SignalSpacing.x3),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CompositionLocalProvider(LocalContentColor provides accent) {
            Box(modifier = Modifier.size(32.dp), contentAlignment = Alignment.Center) { leading() }
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, color = colors.onSurface, style = typography.rowTitle, maxLines = 1)
            Text(text = message, color = colors.onSurfaceVariant, style = typography.rowMeta, maxLines = 2)
        }
    }
}

data class SignalAccountUiModel(
    val key: String,
    val name: String,
    val type: String,
    val status: String,
    val iban: String,
    val balance: String,
    val alias: String? = null,
)

@Composable
fun SignalLegacyAccountCarousel(
    accounts: List<SignalAccountUiModel>,
    selectedIndex: Int,
    onSelectedIndexChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    masked: Boolean = false,
    onOpenDetails: (SignalAccountUiModel) -> Unit = {},
    actions: @Composable () -> Unit = {},
) {
    if (accounts.isEmpty()) {
        SignalEmptyState(
            title = "No accounts",
            message = "Accounts appear here when they are available.",
            modifier = modifier,
        )
        return
    }
    val safeIndex = selectedIndex.coerceIn(accounts.indices)
    val account = accounts[safeIndex]
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        Box(modifier = Modifier.clickable { onOpenDetails(account) }) {
            SignalAccountHeader(
                accountName = account.name,
                balance = account.balance,
                iban = account.iban,
                alias = account.alias,
                masked = masked,
                actions = actions,
            )
        }
        SignalCarouselControls(
            count = accounts.size,
            selectedIndex = safeIndex,
            onPrevious = { onSelectedIndexChange(if (safeIndex == 0) accounts.lastIndex else safeIndex - 1) },
            onNext = { onSelectedIndexChange(if (safeIndex == accounts.lastIndex) 0 else safeIndex + 1) },
        )
    }
}

@Composable
fun SignalAccountList(
    accounts: List<SignalAccountUiModel>,
    onAccountClick: (SignalAccountUiModel) -> Unit,
    modifier: Modifier = Modifier,
    masked: Boolean = false,
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        accounts.forEach { account ->
            SignalAccountRow(
                name = account.name,
                accountType = account.type,
                status = account.status,
                iban = account.iban,
                balance = account.balance,
                masked = masked,
                onClick = { onAccountClick(account) },
            )
        }
    }
}

@Composable
fun SignalCarouselControls(
    count: Int,
    selectedIndex: Int,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SignalTextIconButton(label = "Previous", onClick = onPrevious)
        Row(horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x1)) {
            repeat(count) { index ->
                val active = index == selectedIndex
                Box(
                    modifier = Modifier
                        .size(width = if (active) 18.dp else 6.dp, height = 6.dp)
                        .background(
                            if (active) colors.bankAccent else colors.onSurfaceVariant.copy(alpha = 0.32f),
                            RoundedCornerShape(SignalTheme.shapes.full),
                        ),
                )
            }
        }
        SignalTextIconButton(label = "Next", onClick = onNext)
    }
}

@Composable
fun SignalTextIconButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.clickable(onClick = onClick),
        color = SignalTheme.colors.primaryContainer,
        shape = RoundedCornerShape(SignalTheme.shapes.full),
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x2),
            color = SignalTheme.colors.onPrimaryContainer,
            style = SignalTheme.typography.statusPill,
        )
    }
}

data class SignalPaymentCardUiModel(
    val key: String,
    val scheme: String,
    val maskedNumber: String,
    val holderName: String? = null,
    val background: Color,
    val contentColor: Color = Color.White,
    val signal: Color? = null,
)

@Composable
fun SignalCardStack(
    cards: List<SignalPaymentCardUiModel>,
    onCardClick: (SignalPaymentCardUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3)) {
        cards.forEach { card ->
            Box(modifier = Modifier.clickable { onCardClick(card) }) {
                SignalCardFace(
                    scheme = card.scheme,
                    maskedNumber = card.maskedNumber,
                    holderName = card.holderName,
                    background = card.background,
                    contentColor = card.contentColor,
                )
            }
        }
    }
}

@Composable
fun SignalCardDetailStage(
    card: SignalPaymentCardUiModel,
    metrics: List<SignalMetric>,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3)) {
        Surface(
            color = card.background,
            contentColor = card.contentColor,
            shape = RoundedCornerShape(
                bottomStart = SignalTheme.shapes.xl,
                bottomEnd = SignalTheme.shapes.xl,
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SignalSpacing.x5),
                verticalArrangement = Arrangement.spacedBy(SignalSpacing.x4),
            ) {
                SignalCardFace(
                    scheme = card.scheme,
                    maskedNumber = card.maskedNumber,
                    holderName = card.holderName,
                    background = Color.Transparent,
                    contentColor = card.contentColor,
                )
                SignalMetricStrip(metrics = metrics, onPrimary = true, primaryContentColor = card.contentColor)
            }
        }
        content()
    }
}

data class SignalMetric(
    val label: String,
    val value: String,
)

@Composable
fun SignalMetricStrip(
    metrics: List<SignalMetric>,
    modifier: Modifier = Modifier,
    onPrimary: Boolean = false,
    primaryContentColor: Color = SignalTheme.colors.textInverse,
) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        metrics.take(3).forEach { metric ->
            SignalMetricTile(
                metric = metric,
                modifier = Modifier.weight(1f),
                onPrimary = onPrimary,
                primaryContentColor = primaryContentColor,
            )
        }
    }
}

@Composable
fun SignalMetricTile(
    metric: SignalMetric,
    modifier: Modifier = Modifier,
    onPrimary: Boolean = false,
    primaryContentColor: Color = SignalTheme.colors.textInverse,
) {
    val colors = SignalTheme.colors
    val container = if (onPrimary) primaryContentColor.copy(alpha = 0.12f) else colors.surfaceContainerLowest
    val border = if (onPrimary) primaryContentColor.copy(alpha = 0.16f) else colors.outlineVariant
    val labelColor = if (onPrimary) primaryContentColor.copy(alpha = 0.70f) else colors.onSurfaceVariant
    val valueColor = if (onPrimary) primaryContentColor else colors.onSurface

    Column(
        modifier = modifier
            .heightIn(min = 64.dp)
            .background(container, RoundedCornerShape(SignalTheme.shapes.md))
            .border(BorderStroke(1.dp, border), RoundedCornerShape(SignalTheme.shapes.md))
            .padding(SignalSpacing.x3),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = metric.label, color = labelColor, style = SignalTheme.typography.rowMeta, maxLines = 1)
        Text(text = metric.value, color = valueColor, style = SignalTheme.typography.rowTitle, maxLines = 1)
    }
}

@Composable
fun SignalNotificationBanner(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    label: String? = null,
    onClick: (() -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val click = if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier
    Column(
        modifier = modifier
            .then(click)
            .fillMaxWidth()
            .heightIn(min = 156.dp)
            .background(colors.bankPrimary, RoundedCornerShape(SignalTheme.shapes.xl))
            .padding(SignalSpacing.x4),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        if (label != null) {
            SignalIdentifierChip(text = label)
        }
        Column(verticalArrangement = Arrangement.spacedBy(SignalSpacing.x1)) {
            Text(text = title, color = colors.textInverse, style = SignalTheme.typography.titleLarge, maxLines = 2)
            Text(text = message, color = colors.textInverse.copy(alpha = 0.72f), style = SignalTheme.typography.rowMeta, maxLines = 3)
        }
    }
}

@Composable
internal fun signalToneColor(tone: SignalRowTone): Color {
    val colors = SignalTheme.colors
    return when (tone) {
        SignalRowTone.Neutral -> colors.onSurfaceVariant
        SignalRowTone.Primary -> colors.bankPrimary
        SignalRowTone.Accent -> colors.bankAccent
        SignalRowTone.Success -> colors.success
        SignalRowTone.Warning -> colors.warning
        SignalRowTone.Danger -> colors.danger
    }
}
