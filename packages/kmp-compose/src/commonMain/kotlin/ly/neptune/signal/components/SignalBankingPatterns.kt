package ly.neptune.signal.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.graphicsLayer
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

data class SignalWalletAction(
    val key: String,
    val label: String,
    val supportingText: String,
    val onClick: () -> Unit,
    val icon: @Composable () -> Unit = {},
)

data class SignalFinancialTimelineHomeItem(
    val time: String,
    val title: String,
    val metadata: String,
    val tone: SignalRowTone = SignalRowTone.Neutral,
    val onClick: () -> Unit,
)

data class SignalFinancialStatusMetric(
    val label: String,
    val value: String,
    val detail: String,
    val onClick: () -> Unit,
)

data class SignalHubAction(
    val key: String,
    val title: String,
    val metadata: String,
    val tone: SignalRowTone = SignalRowTone.Neutral,
    val onClick: () -> Unit,
    val icon: @Composable () -> Unit = {},
)

data class SignalPrismAccountAction(
    val id: String,
    val label: String,
    val supportingText: String,
    val onClick: () -> Unit,
    val icon: @Composable () -> Unit = {},
    val suggested: Boolean = false,
    val deeplink: String? = null,
)

data class SignalPrismQuickTransfer(
    val id: String,
    val title: String,
    val subtitle: String,
    val onClick: () -> Unit,
    val sourceAccountId: String? = null,
    val deeplink: String? = null,
)

data class SignalPrismMoneyMovement(
    val id: String,
    val title: String,
    val metadata: String,
    val amount: String,
    val tone: SignalRowTone,
    val onClick: () -> Unit,
    val postedAt: String? = null,
    val deeplink: String? = null,
)

data class SignalPrismCampaign(
    val id: String,
    val eyebrow: String,
    val title: String,
    val message: String,
    val actionLabel: String,
    val onClick: () -> Unit,
    val priority: Int = 0,
    val expiresAt: String? = null,
    val deeplink: String? = null,
    val imageUrl: String? = null,
    val imageDescription: String? = null,
    val gradientStops: List<Color>? = null,
    val media: (@Composable () -> Unit)? = null,
)

data class SignalPrismTimelineEvent(
    val id: String,
    val title: String,
    val metadata: String,
    val status: String,
    val tone: SignalRowTone,
    val onClick: () -> Unit,
    val priority: Int = 0,
    val deeplink: String? = null,
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
        val markModifier = if (brandVisual != null) {
            Modifier.size(44.dp)
        } else {
            Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(SignalTheme.shapes.sm))
                .background(colors.bankSecondary)
        }
        Box(
            modifier = markModifier,
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
fun SignalFinancialHubCard(
    title: String,
    subtitle: String,
    metadata: String,
    badge: String,
    modifier: Modifier = Modifier,
    primaryAction: String,
    onPrimaryAction: () -> Unit,
    secondaryAction: String? = null,
    onSecondaryAction: (() -> Unit)? = null,
    leading: @Composable () -> Unit = {},
) {
    val colors = SignalTheme.colors
    val shape = RoundedCornerShape(30.dp)
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = if (colors.dark) colors.surfaceContainerLow.copy(alpha = 0.72f) else colors.surfaceContainerLow,
        contentColor = colors.onSurface,
        shape = shape,
        border = BorderStroke(1.dp, colors.outlineVariant.copy(alpha = if (colors.dark) 0.16f else 0.22f)),
    ) {
        Column(
            modifier = Modifier.padding(SignalSpacing.x4),
            verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .background(colors.primaryContainer.copy(alpha = if (colors.dark) 0.34f else 0.68f)),
                    contentAlignment = Alignment.Center,
                ) {
                    CompositionLocalProvider(LocalContentColor provides colors.bankPrimary) {
                        Box(modifier = Modifier.size(SignalComponentMetrics.rowIconGlyph), contentAlignment = Alignment.Center) {
                            leading()
                        }
                    }
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    Text(text = badge, color = colors.bankPrimary, style = SignalTheme.typography.statusPill, maxLines = 1)
                    Text(text = title, color = colors.onSurface, style = SignalTheme.typography.pageTitle, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Text(text = subtitle, color = colors.onSurfaceVariant, style = SignalTheme.typography.rowMeta, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
            }
            Text(
                text = metadata,
                color = colors.textSecondary,
                style = SignalTheme.typography.rowMeta,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                HubMiniButton(
                    label = primaryAction,
                    prominent = true,
                    onClick = onPrimaryAction,
                    modifier = Modifier.weight(1f),
                )
                if (secondaryAction != null && onSecondaryAction != null) {
                    HubMiniButton(
                        label = secondaryAction,
                        prominent = false,
                        onClick = onSecondaryAction,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
private fun HubMiniButton(
    label: String,
    prominent: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    Text(
        text = label,
        modifier = modifier
            .heightIn(min = 44.dp)
            .clip(RoundedCornerShape(SignalTheme.shapes.full))
            .background(if (prominent) colors.bankPrimary else colors.surfaceContainerHighest.copy(alpha = if (colors.dark) 0.30f else 0.42f))
            .clickable(onClick = onClick)
            .padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x2),
        color = if (prominent) colors.textInverse else colors.bankPrimary,
        style = SignalTheme.typography.button,
        textAlign = TextAlign.Center,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun SignalHubActionGrid(
    actions: List<SignalHubAction>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
    ) {
        actions.chunked(2).forEach { rowActions ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
            ) {
                rowActions.forEach { action ->
                    SignalHubActionTile(
                        action = action,
                        modifier = Modifier.weight(1f),
                    )
                }
                if (rowActions.size == 1) Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun SignalHubActionTile(
    action: SignalHubAction,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    val tone = signalToneColor(action.tone)
    val shape = RoundedCornerShape(22.dp)
    Column(
        modifier = modifier
            .heightIn(min = 116.dp)
            .clip(shape)
            .background(if (colors.dark) colors.surfaceContainerLow.copy(alpha = 0.54f) else colors.surfaceContainerLow.copy(alpha = 0.72f), shape)
            .border(BorderStroke(1.dp, colors.outlineVariant.copy(alpha = if (colors.dark) 0.14f else 0.22f)), shape)
            .clickable(onClick = action.onClick)
            .padding(SignalSpacing.x3),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(tone.copy(alpha = if (colors.dark) 0.18f else 0.12f)),
            contentAlignment = Alignment.Center,
        ) {
            CompositionLocalProvider(LocalContentColor provides tone) {
                Box(modifier = Modifier.size(SignalComponentMetrics.smallActionIcon), contentAlignment = Alignment.Center) {
                    action.icon()
                }
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(text = action.title, color = colors.onSurface, style = SignalTheme.typography.rowTitle, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = action.metadata, color = colors.onSurfaceVariant, style = SignalTheme.typography.statusPill, maxLines = 2, overflow = TextOverflow.Ellipsis)
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
    val dockShape = RoundedCornerShape(32.dp)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(if (colors.dark) 0.dp else 4.dp, dockShape, clip = false)
            .background(if (colors.dark) colors.surfaceContainer else colors.surfaceCard, dockShape)
            .border(BorderStroke(1.dp, colors.outlineVariant.copy(alpha = if (colors.dark) 0.44f else 0.30f)), dockShape)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
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
    val layoutDirection = LocalLayoutDirection.current
    val labelAlign = if (layoutDirection == LayoutDirection.Rtl || label.hasSignalRtlScript()) {
        TextAlign.Right
    } else {
        TextAlign.Left
    }
    Row(
        modifier = modifier
            .heightIn(min = 60.dp)
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
                        .size(34.dp)
                        .background(colors.bankSecondary, RoundedCornerShape(14.dp)),
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
        Box(modifier = Modifier.size(30.dp), contentAlignment = Alignment.Center) {
            SignalIcon(
                name = SignalIconName.ChevronEnd,
                tint = colors.textInverse,
                size = 20.dp,
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
            .heightIn(min = if (action.subtitle == null) 58.dp else 72.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(
                if (colors.dark) colors.surfaceContainer.copy(alpha = 0.74f) else colors.surfaceContainerLow.copy(alpha = 0.78f),
                RoundedCornerShape(18.dp),
            )
            .border(BorderStroke(1.dp, colors.outlineVariant.copy(alpha = if (colors.dark) 0.34f else 0.38f)), RoundedCornerShape(18.dp))
            .clickable(onClick = action.onClick)
            .padding(horizontal = SignalSpacing.x2, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(modifier = Modifier.size(SignalComponentMetrics.rowIconGlyph), contentAlignment = Alignment.Center) {
            CompositionLocalProvider(LocalContentColor provides colors.bankPrimary) {
                action.icon()
            }
        }
        Spacer(modifier = Modifier.size(width = 1.dp, height = 3.dp))
        Text(
            text = action.label,
            color = colors.onSurface,
            style = typography.labelMedium,
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
fun SignalWalletActionPanel(
    actions: List<SignalWalletAction>,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(
                if (colors.dark) colors.surfaceContainer.copy(alpha = 0.26f) else colors.surfaceContainerLow.copy(alpha = 0.42f),
                RoundedCornerShape(24.dp),
            )
            .padding(SignalSpacing.x2),
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x1),
    ) {
        actions.take(4).chunked(2).forEachIndexed { rowIndex, rowActions ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x1),
            ) {
                rowActions.forEachIndexed { actionIndex, action ->
                    val absoluteIndex = rowIndex * 2 + actionIndex
                    SignalWalletActionButton(
                        action = action,
                        featured = absoluteIndex == 0,
                        modifier = Modifier.weight(1f),
                    )
                }
                if (rowActions.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun SignalPrismAccountActionCluster(
    actions: List<SignalPrismAccountAction>,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    val colors = SignalTheme.colors
    val clusterShape = RoundedCornerShape(28.dp)
    val containerColor = if (colors.dark) {
        prism.surfaces.prismSurfaceFloating.copy(alpha = 0.94f)
    } else {
        prism.surfaces.prismSurfaceRaised.copy(alpha = 0.98f)
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(clusterShape)
            .background(containerColor, clusterShape)
            .border(
                BorderStroke(1.dp, if (colors.dark) prism.tones.secondary.copy(alpha = 0.18f) else prism.tones.secondary.copy(alpha = 0.16f)),
                clusterShape,
            )
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        actions.take(4).chunked(2).forEach { rowActions ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                rowActions.forEachIndexed { index, action ->
                    SignalPrismAccountActionCell(
                        action = action,
                        prominent = action.suggested || (actions.firstOrNull()?.id == action.id && index == 0),
                        modifier = Modifier.weight(1f),
                    )
                }
                if (rowActions.size == 1) Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun SignalPrismAccountActionCell(
    action: SignalPrismAccountAction,
    prominent: Boolean,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    val colors = SignalTheme.colors
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.972f else 1f,
        label = "signal-prism-account-action-press",
    )
    val accent = prismActionAccent(action.id, prominent)
    val shape = RoundedCornerShape(if (prominent) 22.dp else 18.dp)
    val cellContainer = if (prominent) {
        if (colors.dark) prism.tones.paymentContainer else lerp(prism.surfaces.prismSurface, accent, 0.16f)
    } else {
        lerp(
            if (colors.dark) prism.surfaces.prismSurfaceRaised else prism.surfaces.prismSurface,
            accent,
            if (colors.dark) 0.10f else 0.085f,
        )
    }
    Row(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .heightIn(min = if (prominent) 66.dp else 58.dp)
            .clip(shape)
            .background(cellContainer, shape)
            .border(1.dp, accent.copy(alpha = if (prominent) 0.26f else if (colors.dark) 0.15f else 0.18f), shape)
            .clickable(interactionSource = interactionSource, indication = null, onClick = action.onClick)
            .padding(horizontal = SignalSpacing.x2, vertical = SignalSpacing.x2),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(if (prominent) 38.dp else 32.dp)
                .clip(RoundedCornerShape(if (prominent) 15.dp else 13.dp))
                .background(accent.copy(alpha = if (colors.dark) if (prominent) 0.28f else 0.18f else if (prominent) 0.24f else 0.20f)),
            contentAlignment = Alignment.Center,
        ) {
            CompositionLocalProvider(LocalContentColor provides accent) {
                Box(modifier = Modifier.size(if (prominent) 22.dp else 19.dp), contentAlignment = Alignment.Center) {
                    action.icon()
                }
            }
        }
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(1.dp)) {
            Text(
                text = action.label,
                color = prism.palette.prismTextPrimary,
                style = if (prominent) SignalTheme.typography.rowTitle else SignalTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = action.supportingText,
                color = prism.palette.prismTextSecondary.copy(alpha = if (prominent) 0.88f else 0.84f),
                style = SignalTheme.typography.statusPill,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun prismActionAccent(id: String, prominent: Boolean): Color {
    val prism = SignalTheme.prism
    val key = id.lowercase()
    return when {
        prominent || "transfer" in key || "تحويل" in key -> prism.tones.payment
        "qr" in key || "receive" in key || "استلام" in key -> prism.palette.prismViolet
        "bill" in key || "pay" in key || "دفع" in key -> prism.tones.accent
        "statement" in key || "chart" in key || "كشف" in key -> prism.palette.prismEmerald
        else -> prism.palette.prismGold
    }
}

@Composable
fun SignalPrismQuickTransferRow(
    transfer: SignalPrismQuickTransfer,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    val colors = SignalTheme.colors
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.982f else 1f,
        label = "signal-prism-quick-transfer-press",
    )
    val shape = RoundedCornerShape(30.dp)
    val transferContainer = prism.tones.quickTransferContainer
    val transferContent = prism.tones.onQuickTransferContainer
    Row(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(shape)
            .background(transferContainer, shape)
            .border(BorderStroke(1.dp, prism.tones.payment.copy(alpha = 0.22f)), shape)
            .clickable(interactionSource = interactionSource, indication = null, onClick = transfer.onClick)
            .padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x3),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(prism.tones.paymentContainer),
            contentAlignment = Alignment.Center,
        ) {
            SignalIcon(SignalIconName.Transfer, tint = prism.tones.payment, size = 24.dp)
        }
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(
                text = transfer.title,
                color = transferContent,
                style = SignalTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = transfer.subtitle,
                color = transferContent.copy(alpha = 0.74f),
                style = SignalTheme.typography.rowMeta,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun SignalPrismMoneyMovementPreview(
    title: String,
    movements: List<SignalPrismMoneyMovement>,
    modifier: Modifier = Modifier,
    actionLabel: String? = null,
    onActionClick: (() -> Unit)? = null,
) {
    val prism = SignalTheme.prism
    val colors = SignalTheme.colors
    val containerColor = if (colors.dark) prism.surfaces.prismSurfaceMuted.copy(alpha = 0.58f) else prism.surfaces.prismSurfaceRaised
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
    ) {
        SignalPrismSectionHeader(title = title, actionLabel = actionLabel, onActionClick = onActionClick)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(26.dp))
                .background(containerColor)
                .border(1.dp, prism.overlays.prismBorderSoft.copy(alpha = if (colors.dark) 0.08f else 0.22f), RoundedCornerShape(26.dp))
                .padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x2),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            movements.take(2).forEach { movement ->
                SignalPrismMoneyMovementRow(movement)
            }
        }
    }
}

@Composable
private fun SignalPrismMoneyMovementRow(movement: SignalPrismMoneyMovement) {
    val colors = SignalTheme.colors
    val tone = signalToneColor(movement.tone)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .clickable(onClick = movement.onClick)
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(RoundedCornerShape(13.dp))
                .background(tone.copy(alpha = if (colors.dark) 0.16f else 0.10f)),
            contentAlignment = Alignment.Center,
        ) {
            SignalIcon(
                name = if (movement.tone == SignalRowTone.Success) SignalIconName.ArrowStart else SignalIconName.ArrowEnd,
                tint = tone,
                size = 18.dp,
            )
        }
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(1.dp)) {
            Text(text = movement.title, color = colors.onSurface, style = SignalTheme.typography.rowTitle, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = movement.metadata, color = colors.onSurfaceVariant, style = SignalTheme.typography.rowMeta, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
        SignalPrismInlineAmount(
            value = movement.amount,
            color = tone,
        )
    }
}

@Composable
private fun SignalPrismInlineAmount(
    value: String,
    color: Color,
) {
    val parts = value.split(" ").filter { it.isNotBlank() }
    val currencyFirst = parts.firstOrNull()?.any { it.isLetter() } == true
    val currency = if (currencyFirst) parts.firstOrNull().orEmpty() else parts.getOrNull(1).orEmpty()
    val number = if (currencyFirst) parts.getOrNull(1).orEmpty() else parts.firstOrNull().orEmpty()
    if (currency.isBlank() || number.isBlank()) {
        Text(
            text = value,
            color = color,
            style = SignalTheme.typography.rowTitle.copy(fontFeatureSettings = "tnum"),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        return
    }
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = currency,
                color = color,
                style = SignalTheme.typography.rowTitle.copy(fontFeatureSettings = "tnum"),
                maxLines = 1,
            )
            Spacer(Modifier.width(5.dp))
            Text(
                text = number,
                color = color,
                style = SignalTheme.typography.rowTitle.copy(fontFeatureSettings = "tnum"),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun SignalPrismInsightCard(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    actionLabel: String? = null,
    onClick: (() -> Unit)? = null,
) {
    val prism = SignalTheme.prism
    val colors = SignalTheme.colors
    val shape = RoundedCornerShape(28.dp)
    val click = if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier
    Row(
        modifier = modifier
            .then(click)
            .fillMaxWidth()
            .clip(shape)
            .background(if (colors.dark) prism.surfaces.prismSurfaceRaised.copy(alpha = 0.54f) else prism.surfaces.prismSurfaceFloating.copy(alpha = 0.76f), shape)
            .padding(SignalSpacing.x3),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(prism.palette.prismCoral.copy(alpha = if (colors.dark) 0.16f else 0.10f)),
            contentAlignment = Alignment.Center,
        ) {
            SignalIcon(SignalIconName.Chart, tint = prism.palette.prismCoral, size = 22.dp)
        }
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(text = title, color = prism.palette.prismTextPrimary, style = SignalTheme.typography.rowTitle, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = message, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.rowMeta, maxLines = 2, overflow = TextOverflow.Ellipsis)
            if (actionLabel != null) {
                Text(text = actionLabel, color = prism.palette.prismCyan, style = SignalTheme.typography.statusPill, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}

@Composable
fun SignalPrismCampaignCarousel(
    title: String,
    campaigns: List<SignalPrismCampaign>,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    val colors = SignalTheme.colors
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        SignalPrismSectionHeader(title = title)
        BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
            val cardWidth = maxWidth * 0.88f
            Row(
                modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
            ) {
                campaigns.sortedByDescending { it.priority }.forEach { campaign ->
                    val shape = RoundedCornerShape(34.dp)
                    val media = campaign.media
                    Column(
                        modifier = Modifier
                            .width(cardWidth)
                            .heightIn(min = 236.dp)
                            .clip(shape)
                            .background(if (colors.dark) prism.surfaces.prismSurfaceRaised else prism.surfaces.prismSurface, shape)
                            .border(1.dp, prism.overlays.prismBorderSoft.copy(alpha = if (colors.dark) 0.12f else 0.20f), shape)
                            .clickable(onClick = campaign.onClick)
                            .padding(SignalSpacing.x4),
                        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
                    ) {
                        if (media != null) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(126.dp)
                                    .clip(RoundedCornerShape(22.dp))
                                    .semantics { contentDescription = campaign.imageDescription ?: campaign.title },
                            ) {
                                media()
                            }
                        } else {
                            SignalPrismCampaignMedia(
                                imageUrl = campaign.imageUrl,
                                description = campaign.imageDescription ?: campaign.title,
                                modifier = Modifier.fillMaxWidth().height(126.dp),
                            )
                        }
                        Text(text = campaign.eyebrow, color = prism.palette.prismGold, style = SignalTheme.typography.statusPill, maxLines = 1)
                        Text(text = campaign.title, color = prism.palette.prismTextPrimary, style = SignalTheme.typography.titleLarge, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(text = campaign.message, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.rowMeta, maxLines = 2, overflow = TextOverflow.Ellipsis)
                        Text(text = campaign.actionLabel, color = prism.palette.prismCyan, style = SignalTheme.typography.button, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                }
            }
        }
    }
}

@Composable
private fun SignalPrismCampaignMedia(
    imageUrl: String?,
    description: String,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(22.dp))
            .background(Brush.linearGradient(listOf(prism.palette.prismDeepNavy, prism.palette.prismOcean.copy(alpha = 0.76f))))
            .semantics { contentDescription = if (imageUrl != null) "$description · backend image ready" else description },
    ) {
        Canvas(Modifier.matchParentSize()) {
            drawRoundRect(
                color = Color.White.copy(alpha = 0.10f),
                topLeft = Offset(size.width * 0.18f, size.height * 0.46f),
                size = Size(size.width * 0.58f, size.height * 0.22f),
                cornerRadius = CornerRadius(18.dp.toPx(), 18.dp.toPx()),
            )
            drawRoundRect(
                color = Color.White.copy(alpha = 0.16f),
                topLeft = Offset(size.width * 0.34f, size.height * 0.32f),
                size = Size(size.width * 0.28f, size.height * 0.18f),
                cornerRadius = CornerRadius(14.dp.toPx(), 14.dp.toPx()),
            )
            drawCircle(Color.White.copy(alpha = 0.72f), radius = 6.dp.toPx(), center = Offset(size.width * 0.30f, size.height * 0.72f))
            drawCircle(Color.White.copy(alpha = 0.72f), radius = 6.dp.toPx(), center = Offset(size.width * 0.66f, size.height * 0.72f))
            drawLine(prism.palette.prismCyan.copy(alpha = 0.58f), Offset(size.width * 0.08f, size.height * 0.18f), Offset(size.width * 0.92f, size.height * 0.18f), strokeWidth = 2.dp.toPx())
        }
        if (imageUrl != null) {
            Text(
                "صورة من الخلفية",
                color = Color.White.copy(alpha = 0.78f),
                style = SignalTheme.typography.statusPill,
                modifier = Modifier.align(Alignment.TopStart).padding(10.dp),
                maxLines = 1,
            )
        }
    }
}

@Composable
fun SignalPrismFinancialTimelinePreview(
    title: String,
    summary: String,
    events: List<SignalPrismTimelineEvent>,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        SignalPrismSectionHeader(title = title, actionLabel = summary)
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = prism.surfaces.prismSurfaceRaised.copy(alpha = if (SignalTheme.colors.dark) 0.68f else 0.90f),
            contentColor = prism.palette.prismTextPrimary,
            shape = RoundedCornerShape(28.dp),
            border = BorderStroke(1.dp, prism.overlays.prismBorderSoft.copy(alpha = if (SignalTheme.colors.dark) 0.16f else 0.28f)),
        ) {
            Column(
                modifier = Modifier.padding(horizontal = SignalSpacing.x2, vertical = SignalSpacing.x2),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                events.sortedByDescending { it.priority }.take(3).forEachIndexed { index, event ->
                    SignalPrismTimelineEventRow(event = event, emphasized = index == 0)
                }
            }
        }
    }
}

@Composable
private fun SignalPrismTimelineEventRow(
    event: SignalPrismTimelineEvent,
    emphasized: Boolean,
) {
    val prism = SignalTheme.prism
    val colors = SignalTheme.colors
    val tone = signalToneColor(event.tone)
    val shape = RoundedCornerShape(if (emphasized) 24.dp else 18.dp)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .background(if (emphasized) prism.surfaces.prismSurfaceRaised.copy(alpha = if (colors.dark) 0.46f else 0.70f) else Color.Transparent, shape)
            .clickable(onClick = event.onClick)
            .padding(horizontal = if (emphasized) SignalSpacing.x3 else SignalSpacing.x2, vertical = if (emphasized) 12.dp else 9.dp),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(if (emphasized) 36.dp else 30.dp)
                .clip(RoundedCornerShape(if (emphasized) 14.dp else 12.dp))
                .background(tone.copy(alpha = if (emphasized) 0.16f else 0.08f)),
            contentAlignment = Alignment.Center,
        ) {
            SignalIcon(signalTimelineIcon(event.tone), tint = tone, size = if (emphasized) 19.dp else 16.dp)
        }
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(1.dp)) {
            Text(text = event.title, color = prism.palette.prismTextPrimary, style = SignalTheme.typography.rowTitle, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = event.metadata, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.rowMeta, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
        Text(text = event.status, color = if (emphasized) tone else prism.palette.prismTextMuted, style = SignalTheme.typography.statusPill, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
private fun SignalPrismSectionHeader(
    title: String,
    actionLabel: String? = null,
    onActionClick: (() -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(text = title, color = colors.onSurface, style = SignalTheme.typography.sectionTitle, maxLines = 1, overflow = TextOverflow.Ellipsis)
        if (actionLabel != null) {
            Text(
                text = actionLabel,
                modifier = if (onActionClick != null) Modifier.clickable(onClick = onActionClick) else Modifier,
                color = SignalTheme.prism.palette.prismTextSecondary,
                style = SignalTheme.typography.statusPill,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

private fun signalTimelineIcon(tone: SignalRowTone): SignalIconName = when (tone) {
    SignalRowTone.Warning -> SignalIconName.Shield
    SignalRowTone.Success -> SignalIconName.Transfer
    SignalRowTone.Accent,
    SignalRowTone.Primary -> SignalIconName.Chart
    SignalRowTone.Danger -> SignalIconName.ErrorCircle
    SignalRowTone.Neutral -> SignalIconName.Clock
}

@Composable
private fun SignalWalletActionButton(
    action: SignalWalletAction,
    featured: Boolean,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.975f else 1f,
        label = "signal-wallet-action-press",
    )
    val accent = if (featured) colors.primary else colors.onSurfaceVariant
    val actionShape = RoundedCornerShape(if (featured) 20.dp else 17.dp)
    Row(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .heightIn(min = if (featured) 62.dp else 54.dp)
            .clip(actionShape)
            .background(
                if (featured) {
                    colors.primaryContainer.copy(alpha = if (colors.dark) 0.26f else 0.52f)
                } else {
                    Color.Transparent
                },
                actionShape,
            )
            .clickable(interactionSource = interactionSource, indication = null, onClick = action.onClick)
            .padding(horizontal = SignalSpacing.x2, vertical = if (featured) SignalSpacing.x3 else SignalSpacing.x2),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CompositionLocalProvider(LocalContentColor provides accent) {
            Box(
                modifier = Modifier
                    .size(if (featured) 36.dp else 30.dp)
                    .clip(RoundedCornerShape(if (featured) 14.dp else 12.dp))
                    .background(
                        if (featured) colors.primary.copy(alpha = if (colors.dark) 0.18f else 0.10f) else Color.Transparent,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                action.icon()
            }
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(1.dp),
        ) {
            Text(
                text = action.label,
                color = colors.onSurface,
                style = if (featured) SignalTheme.typography.rowTitle else SignalTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = action.supportingText,
                color = colors.onSurfaceVariant,
                style = SignalTheme.typography.statusPill,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun SignalFinancialTimelineHomePreview(
    title: String,
    summary: String,
    items: List<SignalFinancialTimelineHomeItem>,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = title,
                color = colors.onSurface,
                style = typography.sectionTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = summary,
                color = colors.onSurfaceVariant,
                style = typography.statusPill,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items.take(3).forEachIndexed { index, item ->
                SignalTimelineHomeRow(
                    item = item,
                    emphasized = index == 0,
                )
            }
        }
    }
}

@Composable
fun SignalSmartFinancialBand(
    title: String,
    message: String,
    metrics: List<SignalFinancialStatusMetric>,
    modifier: Modifier = Modifier,
    tone: SignalRowTone = SignalRowTone.Primary,
    leading: @Composable () -> Unit = {},
    onClick: (() -> Unit)? = null,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val accent = signalToneColor(tone)
    val bandShape = RoundedCornerShape(26.dp)
    val click = if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier
    Column(
        modifier = modifier
            .then(click)
            .fillMaxWidth()
            .clip(bandShape)
            .background(
                if (colors.dark) colors.surfaceContainerLow.copy(alpha = 0.72f) else colors.surfaceContainer.copy(alpha = 0.80f),
                bandShape,
            )
            .padding(SignalSpacing.x3),
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CompositionLocalProvider(LocalContentColor provides accent) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(accent.copy(alpha = if (colors.dark) 0.18f else 0.10f)),
                    contentAlignment = Alignment.Center,
                ) { leading() }
            }
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(1.dp)) {
                Text(
                    text = title,
                    color = colors.onSurface,
                    style = typography.rowTitle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = message,
                    color = colors.onSurfaceVariant,
                    style = typography.rowMeta,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        ) {
            metrics.take(2).forEach { metric ->
                SignalFinancialStatusMetricCell(
                    metric = metric,
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun SignalFinancialStatusMetricCell(
    metric: SignalFinancialStatusMetric,
    modifier: Modifier = Modifier,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(colors.surfaceContainerLowest.copy(alpha = if (colors.dark) 0.18f else 0.42f), RoundedCornerShape(16.dp))
            .clickable(onClick = metric.onClick)
            .padding(horizontal = SignalSpacing.x2, vertical = SignalSpacing.x2),
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Text(
            text = metric.label,
            color = colors.onSurfaceVariant,
            style = typography.statusPill,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = metric.value,
            color = colors.onSurface,
            style = typography.rowTitle.copy(fontFeatureSettings = "tnum"),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = metric.detail,
            color = colors.onSurfaceVariant,
            style = typography.statusPill,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun SignalTimelineHomeRow(
    item: SignalFinancialTimelineHomeItem,
    emphasized: Boolean,
) {
    val colors = SignalTheme.colors
    val typography = SignalTheme.typography
    val accent = signalToneColor(item.tone)
    val rowShape = RoundedCornerShape(if (emphasized) 22.dp else 16.dp)
    val iconName = when (item.tone) {
        SignalRowTone.Warning -> SignalIconName.Shield
        SignalRowTone.Success -> SignalIconName.Transfer
        SignalRowTone.Accent,
        SignalRowTone.Primary -> SignalIconName.Chart
        SignalRowTone.Danger -> SignalIconName.ErrorCircle
        SignalRowTone.Neutral -> SignalIconName.Clock
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(rowShape)
            .background(
                if (emphasized) {
                    colors.surfaceContainerHigh.copy(alpha = if (colors.dark) 0.62f else 0.70f)
                } else {
                    Color.Transparent
                },
                rowShape,
            )
            .clickable(onClick = item.onClick)
            .padding(horizontal = if (emphasized) SignalSpacing.x3 else SignalSpacing.x2, vertical = if (emphasized) 12.dp else 9.dp),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(if (emphasized) 36.dp else 30.dp)
                .clip(RoundedCornerShape(if (emphasized) 14.dp else 12.dp))
                .background(accent.copy(alpha = if (emphasized) 0.16f else 0.08f)),
            contentAlignment = Alignment.Center,
        ) {
            SignalIcon(iconName, tint = accent, size = if (emphasized) 20.dp else 17.dp)
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(1.dp),
        ) {
            Text(
                text = item.title,
                color = colors.onSurface,
                style = typography.rowTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = item.metadata,
                color = colors.onSurfaceVariant,
                style = typography.rowMeta,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Text(
            text = item.time,
            color = if (emphasized) accent else colors.onSurfaceVariant,
            style = typography.statusPill,
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
    val cardShape = RoundedCornerShape(shapes.lg)
    val background = if (colors.dark) colors.surfaceContainer else colors.surfaceContainerLowest

    Row(
        modifier = modifier
            .then(click)
            .fillMaxWidth()
            .heightIn(min = 66.dp)
            .shadow(if (colors.dark) 0.dp else 1.dp, cardShape, clip = false)
            .background(background, cardShape)
            .border(BorderStroke(1.dp, colors.outlineVariant.copy(alpha = if (colors.dark) 0.34f else 0.28f)), cardShape)
            .padding(SignalSpacing.x3),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CompositionLocalProvider(LocalContentColor provides accent) {
            Box(modifier = Modifier.size(28.dp), contentAlignment = Alignment.Center) { leading() }
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
