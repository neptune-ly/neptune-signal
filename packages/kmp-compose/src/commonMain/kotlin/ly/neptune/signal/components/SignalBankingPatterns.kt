package ly.neptune.signal.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

data class SignalQuickAction(
    val key: String,
    val label: String,
    val onClick: () -> Unit,
    val icon: @Composable () -> Unit = {},
)

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
            .background(colors.surfaceContainerLow, RoundedCornerShape(shapes.lg))
            .border(BorderStroke(1.dp, colors.outlineVariant), RoundedCornerShape(shapes.lg))
            .padding(SignalSpacing.x3),
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
    ) {
        SignalButton(
            label = primaryLabel,
            onClick = onPrimaryClick,
            modifier = Modifier.fillMaxWidth(),
            leading = primaryIcon,
        )
        SignalShortcutRow(actions = actions)
    }
}

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
            .heightIn(min = 68.dp)
            .background(colors.surfaceContainerLowest, RoundedCornerShape(shapes.md))
            .border(BorderStroke(1.dp, colors.outlineVariant), RoundedCornerShape(shapes.md))
            .clickable(onClick = action.onClick)
            .padding(SignalSpacing.x2),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(modifier = Modifier.size(24.dp), contentAlignment = Alignment.Center) {
            action.icon()
        }
        Text(
            text = action.label,
            color = colors.onSurface,
            style = typography.statusPill,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
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

    Row(
        modifier = modifier
            .then(click)
            .fillMaxWidth()
            .heightIn(min = 62.dp)
            .background(accent.copy(alpha = if (colors.dark) 0.16f else 0.08f), RoundedCornerShape(shapes.md))
            .border(BorderStroke(1.dp, accent.copy(alpha = if (colors.dark) 0.32f else 0.20f)), RoundedCornerShape(shapes.md))
            .padding(SignalSpacing.x3),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(modifier = Modifier.size(32.dp), contentAlignment = Alignment.Center) { leading() }
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
fun SignalAccountCarousel(
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
