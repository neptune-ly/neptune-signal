package ly.neptune.signal.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

data class SignalPrismServiceAction(
    val id: String,
    val title: String,
    val subtitle: String,
    val icon: SignalIconName,
    val tone: SignalRowTone = SignalRowTone.Neutral,
    val badge: String? = null,
    val onClick: () -> Unit,
)

data class SignalPrismNotificationItem(
    val id: String,
    val category: String,
    val title: String,
    val body: String,
    val time: String,
    val read: Boolean,
    val priority: SignalRowTone = SignalRowTone.Neutral,
    val amount: String? = null,
    val status: String? = null,
    val onClick: () -> Unit,
)

data class SignalPrismAliasItem(
    val id: String,
    val alias: String,
    val linkedAccount: String,
    val status: String,
    val isDefault: Boolean,
    val onClick: () -> Unit,
    val onCopy: () -> Unit,
)

data class SignalPrismConsentItem(
    val id: String,
    val providerName: String,
    val purpose: String,
    val scopes: List<String>,
    val status: String,
    val expiresIn: String,
    val lastAccess: String,
    val canRevoke: Boolean,
    val onClick: () -> Unit,
    val onRevoke: () -> Unit,
)

data class SignalPrismRecurringPaymentItem(
    val id: String,
    val merchantName: String,
    val amount: String,
    val source: String,
    val frequency: String,
    val nextPayment: String,
    val status: String,
    val onClick: () -> Unit,
)

data class SignalPrismApprovalItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val requestedBy: String,
    val expiresIn: String,
    val status: String,
    val onClick: () -> Unit,
    val onApprove: () -> Unit,
    val onReject: () -> Unit,
)

@Composable
fun SignalPrismActiveSpaceCard(
    userName: String,
    spaceName: String,
    sessionStatus: String,
    primaryAction: String,
    secondaryAction: String,
    onPrimaryAction: () -> Unit,
    onSecondaryAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = prism.surfaces.prismSurfaceRaised.copy(alpha = 0.88f),
        contentColor = prism.palette.prismTextPrimary,
        shape = RoundedCornerShape(30.dp),
        border = BorderStroke(1.dp, prism.overlays.prismBorderSoft),
    ) {
        Column(modifier = Modifier.padding(SignalSpacing.x4), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3)) {
            Row(horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3), verticalAlignment = Alignment.CenterVertically) {
                PrismIconCell(icon = SignalIconName.User, tone = SignalRowTone.Primary)
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(userName, style = SignalTheme.typography.titleLarge, color = prism.palette.prismTextPrimary, maxLines = 1)
                    Text(spaceName, style = SignalTheme.typography.rowMeta, color = prism.palette.prismTextSecondary, maxLines = 1)
                    Text(sessionStatus, style = SignalTheme.typography.prismStatus, color = prism.palette.prismTextMuted, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2), modifier = Modifier.fillMaxWidth()) {
                SignalButton(primaryAction, onClick = onPrimaryAction, modifier = Modifier.weight(1f), variant = SignalButtonVariant.Secondary)
                SignalButton(secondaryAction, onClick = onSecondaryAction, modifier = Modifier.weight(1f), variant = SignalButtonVariant.Secondary)
            }
        }
    }
}

@Composable
fun SignalPrismAttentionStrip(
    title: String,
    subtitle: String,
    actionLabel: String,
    unreadCount: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    Surface(
        modifier = modifier.fillMaxWidth().clickable(onClick = onClick),
        color = prism.palette.prismGold.copy(alpha = if (SignalTheme.colors.dark) 0.12f else 0.18f),
        contentColor = prism.palette.prismTextPrimary,
        shape = RoundedCornerShape(26.dp),
        border = BorderStroke(1.dp, prism.palette.prismGold.copy(alpha = 0.22f)),
    ) {
        Row(modifier = Modifier.padding(SignalSpacing.x3), horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3), verticalAlignment = Alignment.CenterVertically) {
            PrismIconCell(icon = SignalIconName.Bell, tone = SignalRowTone.Warning, compact = true)
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(title, color = prism.palette.prismTextPrimary, style = SignalTheme.typography.rowTitle, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(subtitle, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.rowMeta, maxLines = 2, overflow = TextOverflow.Ellipsis)
            }
            Text(if (unreadCount > 0) unreadCount.toString() else actionLabel, color = prism.palette.prismGold, style = SignalTheme.typography.statusPill, maxLines = 1)
        }
    }
}

@Composable
fun SignalPrismServiceGroup(
    title: String,
    actions: List<SignalPrismServiceAction>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        PrismServiceSectionHeader(title = title)
        Surface(
            color = SignalTheme.prism.surfaces.prismSurfaceRaised.copy(alpha = 0.72f),
            contentColor = SignalTheme.prism.palette.prismTextPrimary,
            shape = RoundedCornerShape(28.dp),
            border = BorderStroke(1.dp, SignalTheme.prism.overlays.prismBorderSoft),
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(vertical = SignalSpacing.x1)) {
                actions.forEachIndexed { index, action ->
                    SignalPrismServiceRow(action = action)
                    if (index != actions.lastIndex) PrismDivider()
                }
            }
        }
    }
}

@Composable
fun SignalPrismServiceRow(
    action: SignalPrismServiceAction,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    Row(
        modifier = modifier.fillMaxWidth().clickable(onClick = action.onClick).padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x3),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PrismIconCell(icon = action.icon, tone = action.tone, compact = true)
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2), verticalAlignment = Alignment.CenterVertically) {
                Text(action.title, color = prism.palette.prismTextPrimary, style = SignalTheme.typography.rowTitle, maxLines = 1, overflow = TextOverflow.Ellipsis)
                action.badge?.let { Text(it, color = toneColor(action.tone), style = SignalTheme.typography.prismStatus, maxLines = 1) }
            }
            Text(action.subtitle, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.rowMeta, maxLines = 2, overflow = TextOverflow.Ellipsis)
        }
        SignalIcon(SignalIconName.ChevronEnd, tint = prism.palette.prismTextMuted, size = 18.dp)
    }
}

@Composable
fun SignalPrismNotificationCenter(
    categories: List<String>,
    selectedCategory: String,
    notifications: List<SignalPrismNotificationItem>,
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3)) {
        PrismServiceSectionHeader(title = "مركز الإشعارات", actionLabel = "${notifications.count { !it.read }} غير مقروءة")
        SignalPrismNotificationCategoryTabs(categories, selectedCategory, onCategoryClick)
        notifications.forEach { SignalPrismNotificationRow(it) }
    }
}

@Composable
fun SignalPrismNotificationCategoryTabs(
    categories: List<String>,
    selectedCategory: String,
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        categories.forEach { category ->
            val selected = category == selectedCategory
            val prism = SignalTheme.prism
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(if (selected) prism.palette.prismCyan.copy(alpha = 0.18f) else prism.surfaces.prismSurfaceRaised.copy(alpha = 0.64f))
                    .border(BorderStroke(1.dp, if (selected) prism.palette.prismCyan.copy(alpha = 0.32f) else prism.overlays.prismBorderSoft), RoundedCornerShape(999.dp))
                    .clickable { onCategoryClick(category) }
                    .padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x2),
            ) {
                Text(category, color = if (selected) prism.palette.prismCyan else prism.palette.prismTextSecondary, style = SignalTheme.typography.statusPill, maxLines = 1)
            }
        }
    }
}

@Composable
fun SignalPrismNotificationRow(item: SignalPrismNotificationItem, modifier: Modifier = Modifier) {
    val prism = SignalTheme.prism
    Surface(
        modifier = modifier.fillMaxWidth().clickable(onClick = item.onClick),
        color = if (item.read) prism.surfaces.prismSurfaceRaised.copy(alpha = 0.64f) else prism.surfaces.prismSurfaceFloating.copy(alpha = 0.84f),
        contentColor = prism.palette.prismTextPrimary,
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, if (item.read) prism.overlays.prismBorderSoft else toneColor(item.priority).copy(alpha = 0.24f)),
    ) {
        Row(modifier = Modifier.padding(SignalSpacing.x3), horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3), verticalAlignment = Alignment.CenterVertically) {
            PrismIconCell(icon = iconForCategory(item.category), tone = item.priority, compact = true)
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(item.title, color = prism.palette.prismTextPrimary, style = SignalTheme.typography.rowTitle, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(item.body, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.rowMeta, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Text(item.time, color = prism.palette.prismTextMuted, style = SignalTheme.typography.prismStatus, maxLines = 1)
            }
            Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(3.dp)) {
                item.amount?.let { Text(it, color = toneColor(item.priority), style = SignalTheme.typography.rowTitle, maxLines = 1) }
                item.status?.let { Text(it, color = prism.palette.prismTextMuted, style = SignalTheme.typography.prismStatus, maxLines = 1) }
            }
        }
    }
}

@Composable
fun SignalPrismCampaignDetail(
    eyebrow: String,
    title: String,
    message: String,
    ctaLabel: String,
    metadata: String,
    onCtaClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 230.dp)
                .clip(RoundedCornerShape(34.dp))
                .background(Brush.linearGradient(listOf(prism.palette.prismDeepNavy, prism.palette.prismViolet.copy(alpha = 0.78f), prism.palette.prismCoral.copy(alpha = 0.38f))))
                .padding(SignalSpacing.x5),
            contentAlignment = Alignment.BottomStart,
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
                Text(eyebrow, color = prism.palette.prismGold, style = SignalTheme.typography.statusPill)
                Text(title, color = Color.White, style = SignalTheme.typography.titleLarge, maxLines = 2)
                Text(message, color = Color.White.copy(alpha = 0.78f), style = SignalTheme.typography.rowMeta, maxLines = 3)
            }
        }
        Text(metadata, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.rowMeta)
        SignalButton(ctaLabel, onClick = onCtaClick, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun SignalPrismAliasManagementScreen(
    aliases: List<SignalPrismAliasItem>,
    onAddAlias: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3)) {
        PrismServiceSectionHeader(title = "NPT Alias", actionLabel = "إدارة الاستلام")
        aliases.forEach { alias ->
            SignalPrismAliasRow(alias)
        }
        SignalButton("إضافة Alias", onClick = onAddAlias, modifier = Modifier.fillMaxWidth(), variant = SignalButtonVariant.Secondary)
    }
}

@Composable
fun SignalPrismAliasRow(alias: SignalPrismAliasItem, modifier: Modifier = Modifier) {
    SignalPrismDataRow(
        title = alias.alias,
        subtitle = "${alias.linkedAccount} · ${alias.status}",
        icon = SignalIconName.Copy,
        tone = if (alias.isDefault) SignalRowTone.Primary else SignalRowTone.Neutral,
        badge = if (alias.isDefault) "افتراضي" else null,
        onClick = alias.onClick,
        trailingAction = alias.onCopy,
        modifier = modifier,
    )
}

@Composable
fun SignalPrismConsentDashboard(consents: List<SignalPrismConsentItem>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3)) {
        PrismServiceSectionHeader(title = "OpenWave", actionLabel = "${consents.count { it.canRevoke }} نشطة")
        consents.forEach { consent ->
            SignalPrismDataRow(
                title = consent.providerName,
                subtitle = "${consent.purpose} · ${consent.expiresIn} · آخر وصول ${consent.lastAccess}",
                icon = SignalIconName.Shield,
                tone = if (consent.status.contains("ينتظر")) SignalRowTone.Warning else SignalRowTone.Primary,
                badge = consent.status,
                onClick = consent.onClick,
                trailingAction = if (consent.canRevoke) consent.onRevoke else null,
            )
            if (consent.scopes.isNotEmpty()) {
                FlowRow(horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x1), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x1), modifier = Modifier.padding(horizontal = SignalSpacing.x3)) {
                    consent.scopes.forEach { PrismTag(it) }
                }
            }
        }
    }
}

@Composable
fun SignalPrismRecurringPaymentsScreen(items: List<SignalPrismRecurringPaymentItem>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3)) {
        PrismServiceSectionHeader(title = "المدفوعات الدورية", actionLabel = "${items.size} نشطة")
        items.forEach {
            SignalPrismDataRow(
                title = it.merchantName,
                subtitle = "${it.frequency} · ${it.source} · القادم ${it.nextPayment}",
                icon = SignalIconName.Clock,
                tone = if (it.status.contains("فشل")) SignalRowTone.Warning else SignalRowTone.Neutral,
                badge = it.status,
                amount = it.amount,
                onClick = it.onClick,
            )
        }
    }
}

@Composable
fun SignalPrismApprovalCenter(items: List<SignalPrismApprovalItem>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3)) {
        PrismServiceSectionHeader(title = "الموافقات", actionLabel = "${items.count { it.status == "ينتظر" }} بانتظارك")
        items.forEach {
            SignalPrismDataRow(
                title = it.title,
                subtitle = "${it.subtitle} · ${it.requestedBy} · ${it.expiresIn}",
                icon = SignalIconName.CheckCircle,
                tone = if (it.status == "ينتظر") SignalRowTone.Warning else SignalRowTone.Success,
                badge = it.status,
                onClick = it.onClick,
                trailingAction = it.onApprove,
            )
        }
    }
}

@Composable
fun SignalPrismLinkedSpaceRow(title: String, subtitle: String, mark: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    SignalPrismDataRow(title = title, subtitle = subtitle, icon = SignalIconName.Services, tone = SignalRowTone.Neutral, badge = mark, onClick = onClick, modifier = modifier)
}

@Composable
private fun SignalPrismDataRow(
    title: String,
    subtitle: String,
    icon: SignalIconName,
    tone: SignalRowTone,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    badge: String? = null,
    amount: String? = null,
    trailingAction: (() -> Unit)? = null,
) {
    val prism = SignalTheme.prism
    Surface(
        modifier = modifier.fillMaxWidth().clickable(onClick = onClick),
        color = prism.surfaces.prismSurfaceRaised.copy(alpha = 0.72f),
        contentColor = prism.palette.prismTextPrimary,
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, prism.overlays.prismBorderSoft),
    ) {
        Row(modifier = Modifier.padding(SignalSpacing.x3), horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3), verticalAlignment = Alignment.CenterVertically) {
            PrismIconCell(icon = icon, tone = tone, compact = true)
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(title, color = prism.palette.prismTextPrimary, style = SignalTheme.typography.rowTitle, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(subtitle, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.rowMeta, maxLines = 2, overflow = TextOverflow.Ellipsis)
            }
            Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(3.dp)) {
                amount?.let { Text(it, color = toneColor(tone), style = SignalTheme.typography.rowTitle, maxLines = 1) }
                badge?.let { Text(it, color = toneColor(tone), style = SignalTheme.typography.prismStatus, maxLines = 1) }
                if (trailingAction != null) {
                    Box(modifier = Modifier.clip(RoundedCornerShape(999.dp)).clickable(onClick = trailingAction).padding(8.dp)) {
                        SignalIcon(SignalIconName.Copy, tint = prism.palette.prismCyan, size = 18.dp)
                    }
                }
            }
        }
    }
}

@Composable
private fun PrismIconCell(icon: SignalIconName, tone: SignalRowTone, compact: Boolean = false) {
    val size = if (compact) 42.dp else 52.dp
    Box(
        modifier = Modifier.size(size).clip(RoundedCornerShape(if (compact) 16.dp else 19.dp)).background(toneColor(tone).copy(alpha = 0.16f)),
        contentAlignment = Alignment.Center,
    ) {
        SignalIcon(icon, tint = toneColor(tone), size = if (compact) 20.dp else 24.dp)
    }
}

@Composable
private fun PrismDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = SignalSpacing.x3)
            .heightIn(min = 1.dp)
            .background(SignalTheme.prism.overlays.prismBorderSoft),
    )
}

@Composable
private fun PrismTag(text: String) {
    val prism = SignalTheme.prism
    Box(
        modifier = Modifier.clip(RoundedCornerShape(999.dp)).background(prism.surfaces.prismSurfaceMuted.copy(alpha = 0.72f)).padding(horizontal = SignalSpacing.x2, vertical = 6.dp),
    ) {
        Text(text, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.prismStatus, maxLines = 1)
    }
}

@Composable
private fun PrismServiceSectionHeader(title: String, actionLabel: String? = null) {
    val prism = SignalTheme.prism
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(title, color = prism.palette.prismTextPrimary, style = SignalTheme.typography.titleMedium, maxLines = 1, overflow = TextOverflow.Ellipsis)
        actionLabel?.let {
            Text(it, color = prism.palette.prismTextMuted, style = SignalTheme.typography.prismStatus, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
private fun toneColor(tone: SignalRowTone): Color {
    val prism = SignalTheme.prism
    val colors = SignalTheme.colors
    return when (tone) {
        SignalRowTone.Primary -> prism.palette.prismCyan
        SignalRowTone.Accent -> prism.palette.prismCoral
        SignalRowTone.Success -> prism.palette.prismEmerald
        SignalRowTone.Warning -> prism.palette.prismGold
        SignalRowTone.Danger -> colors.danger
        SignalRowTone.Neutral -> prism.palette.prismTextMuted
    }
}

private fun iconForCategory(category: String): SignalIconName = when {
    category.contains("حركات") -> SignalIconName.Transfer
    category.contains("موافق") -> SignalIconName.CheckCircle
    category.contains("أمان") -> SignalIconName.Shield
    category.contains("عروض") -> SignalIconName.Voucher
    category.contains("عملات") -> SignalIconName.Chart
    category.contains("خدمات") -> SignalIconName.Services
    else -> SignalIconName.Bell
}
