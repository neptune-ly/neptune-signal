package ly.neptune.signal.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ly.neptune.signal.motion.SignalMotion
import ly.neptune.signal.theme.SignalComponentMetrics
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

enum class SignalPrismCardType {
    Debit,
    Credit,
    Prepaid,
    Virtual,
    Business,
    Youth,
}

enum class SignalPrismCardNetwork {
    Mastercard,
    Visa,
    Numo,
    Amex,
    Local,
}

enum class SignalCardScheme {
    Visa,
    Mastercard,
    Numo,
    Amex,
    PrivateLabel,
    Unknown,
}

enum class SignalCardBackgroundStyle {
    Flat,
    SoftGradient,
    PremiumDark,
    FrostedLight,
    BankBrand,
    MinimalCorporate,
    BackendAsset,
}

enum class SignalCardMarkStyle {
    TextPlaceholder,
    BankProvidedAsset,
    SimplifiedScheme,
}

enum class SignalCardTypographyStyle {
    Classic,
    Modern,
    Dense,
}

data class SignalBankPalette(
    val primary: Color = Color(0xFF0B2D4D),
    val secondary: Color = Color(0xFF18C5E8),
    val accent: Color = Color(0xFFFF7A59),
    val surface: Color = Color(0xFF06111E),
    val onSurface: Color = Color.White,
)

data class SignalPrismCardArtworkProfile(
    val scheme: SignalCardScheme,
    val personality: ly.neptune.signal.theme.SignalPrismPersonality,
    val bankPalette: SignalBankPalette = SignalBankPalette(),
    val backgroundStyle: SignalCardBackgroundStyle = SignalCardBackgroundStyle.SoftGradient,
    val markStyle: SignalCardMarkStyle = SignalCardMarkStyle.TextPlaceholder,
    val typographyStyle: SignalCardTypographyStyle = SignalCardTypographyStyle.Modern,
    val schemeMarkAssetUrl: String? = null,
    val bankLogoAssetUrl: String? = null,
)

enum class SignalAccountArtworkStyle {
    NeptunePrism,
    BankBrandFlat,
    BankBrandGradient,
    MinimalCorporate,
    IslamicCalm,
    WalletEnergy,
}

enum class SignalCardPresentationMode {
    Carousel,
    List,
    CompactStack,
}

enum class SignalCardDetailPresentationMode {
    CompactIdentity,
    ExpandedArtwork,
    SecureDataFirst,
    ControlCenter,
}

data class SignalCardPresentationPolicy(
    val defaultMode: SignalCardPresentationMode = SignalCardPresentationMode.List,
    val allowedModes: Set<SignalCardPresentationMode> = setOf(SignalCardPresentationMode.List),
    val allowUserOverride: Boolean = true,
    val detailModeFromCarousel: SignalCardDetailPresentationMode = SignalCardDetailPresentationMode.SecureDataFirst,
    val detailModeFromList: SignalCardDetailPresentationMode = SignalCardDetailPresentationMode.CompactIdentity,
    val showLargeArtworkInDetails: Boolean = false,
)

data class SignalCardClipboardPolicy(
    val allowCopyPan: Boolean = true,
    val allowCopyExpiry: Boolean = true,
    val allowCopyCvv: Boolean = false,
    val allowCopyCardholder: Boolean = true,
    val requireRevealBeforeCopy: Boolean = true,
    val markClipboardSensitive: Boolean = true,
    val clearClipboardAfterSeconds: Int? = null,
)

enum class SignalCardSecureFieldRole {
    Pan,
    Expiry,
    Cvv,
    Cardholder,
    CardReference,
}

enum class SignalPrismCardStatus {
    Active,
    Frozen,
    Blocked,
    Expired,
    PendingActivation,
    VirtualOnly,
    PhysicalShipping,
    LostOrStolen,
}

enum class SignalPrismCardStyle {
    ClassicBank,
    PremiumWallet,
    YouthFintech,
    CorporateDense,
    IslamicCalm,
    MerchantEnergy,
}

data class SignalPrismCardRevealPolicy(
    val allowPanReveal: Boolean = true,
    val allowExpiryReveal: Boolean = true,
    val allowCvvReveal: Boolean = false,
    val requiresAuth: Boolean = true,
    val autoRemaskSeconds: Int = 20,
    val blockScreenshotsWhenRevealed: Boolean = true,
)

data class SignalPrismCardRevealState(
    val revealed: Boolean = false,
    val secondsRemaining: Int? = null,
)

data class SignalPrismCardModel(
    val id: String,
    val label: String,
    val subtitle: String,
    val type: SignalPrismCardType,
    val network: SignalPrismCardNetwork,
    val status: SignalPrismCardStatus,
    val maskedPan: String,
    val fullPan: String? = null,
    val last4: String,
    val holderName: String,
    val expiryMasked: String = "••/••",
    val expiry: String? = null,
    val cvvMasked: String = "•••",
    val cvv: String? = null,
    val balanceOrLimit: String? = null,
    val virtual: Boolean = false,
    val style: SignalPrismCardStyle = SignalPrismCardStyle.PremiumWallet,
    val artworkProfile: SignalPrismCardArtworkProfile? = null,
    val tags: List<String> = emptyList(),
    val revealPolicy: SignalPrismCardRevealPolicy = SignalPrismCardRevealPolicy(),
    val clipboardPolicy: SignalCardClipboardPolicy = SignalCardClipboardPolicy(),
    val cardReference: String? = null,
)

data class SignalPrismCardAction(
    val id: String,
    val label: String,
    val supportingText: String,
    val onClick: () -> Unit,
    val icon: @Composable () -> Unit = {},
    val enabled: Boolean = true,
    val destructive: Boolean = false,
    val deeplink: String? = null,
)

data class SignalPrismCardLimit(
    val id: String,
    val label: String,
    val value: String,
    val detail: String,
    val enabled: Boolean = true,
)

data class SignalPrismCardTransaction(
    val id: String,
    val merchant: String,
    val metadata: String,
    val amount: String,
    val incoming: Boolean = false,
    val status: String? = null,
    val onClick: () -> Unit = {},
)

data class SignalPrismAccountCardModel(
    val id: String,
    val accountName: String,
    val accountType: String,
    val statusLabel: String,
    val balance: String,
    val iban: String,
    val alias: String? = null,
    val balanceLabel: String = "الرصيد المتاح",
    val style: SignalPrismCardStyle = SignalPrismCardStyle.PremiumWallet,
    val artworkStyle: SignalAccountArtworkStyle = SignalAccountArtworkStyle.NeptunePrism,
    val bankPalette: SignalBankPalette = SignalBankPalette(),
)

enum class SignalAccountDetailMode {
    Retail,
    CorporateDense,
    Wallet,
    Savings,
    Salary,
    Minimal,
}

enum class SignalAccountActionCapability {
    Transfer,
    Receive,
    GenerateQr,
    ViewStatement,
    PayBills,
    RequestCard,
    RequestCheque,
    RequestChequeBook,
    DownloadDocuments,
    ViewLimits,
    ViewFees,
    OpenDispute,
}

enum class SignalAccountIdentityFieldRole {
    Alias,
    Iban,
    AccountNumber,
    HolderName,
    Branch,
    Reference,
}

enum class SignalAccountRequestType {
    RequestCard,
    CertifiedCheque,
    ChequeBook,
    StatementCertificate,
    BalanceCertificate,
    Dispute,
    Closure,
}

enum class SignalPrismAccountWorkspaceSection {
    Overview,
    Activity,
    Requests,
    Documents,
}

data class SignalPrismAccountWorkspaceTransitionSpec(
    val durationMillis: Int = 320,
    val preserveAccountColor: Boolean = true,
    val rtlAware: Boolean = true,
    val initialScale: Float = 0.972f,
)

data class SignalAccountClipboardPolicy(
    val allowCopyAlias: Boolean = true,
    val allowCopyIban: Boolean = true,
    val allowCopyAccountNumber: Boolean = true,
    val allowCopyHolderName: Boolean = true,
    val allowCopyReference: Boolean = true,
    val markClipboardSensitive: Boolean = true,
)

data class SignalPrismAccountIdentityField(
    val id: String,
    val label: String,
    val value: String,
    val role: SignalAccountIdentityFieldRole,
    val copyEnabled: Boolean = true,
)

data class SignalPrismBalanceBreakdownItem(
    val id: String,
    val label: String,
    val value: String,
    val detail: String? = null,
    val emphasis: Boolean = false,
)

data class SignalPrismAccountLimitTile(
    val id: String,
    val label: String,
    val value: String,
    val detail: String? = null,
)

data class SignalPrismAccountActivityItem(
    val id: String,
    val title: String,
    val metadata: String,
    val amount: String,
    val incoming: Boolean = false,
    val status: String? = null,
    val onClick: () -> Unit = {},
)

data class SignalPrismAccountRequestItem(
    val id: String,
    val type: SignalAccountRequestType,
    val title: String,
    val description: String,
    val status: String,
    val onClick: () -> Unit = {},
)

data class SignalPrismAccountDocumentItem(
    val id: String,
    val title: String,
    val description: String,
    val available: Boolean = true,
    val onClick: () -> Unit = {},
)

data class SignalPrismLinkedAccountServiceItem(
    val id: String,
    val title: String,
    val description: String,
    val status: String,
    val icon: SignalIconName = SignalIconName.Shield,
    val onClick: () -> Unit = {},
)

@Composable
fun SignalPrismAccountCarousel(
    accounts: List<SignalPrismAccountCardModel>,
    modifier: Modifier = Modifier,
    masked: Boolean = false,
    selectedIndex: Int = 0,
    onSelectedIndexChange: (Int) -> Unit = {},
    onOpenAccount: (Int) -> Unit = {},
    onToggleMasked: (() -> Unit)? = null,
    onCopyIban: ((SignalPrismAccountCardModel) -> Unit)? = null,
) {
    if (accounts.isEmpty()) return
    val safeIndex = selectedIndex.coerceIn(accounts.indices)
    val pagerState = rememberPagerState(initialPage = safeIndex) { accounts.size }
    LaunchedEffect(safeIndex, accounts.size) {
        if (pagerState.currentPage != safeIndex) {
            pagerState.animateScrollToPage(safeIndex, animationSpec = tween(SignalMotion.RouteMs))
        }
    }
    LaunchedEffect(pagerState.settledPage) {
        val page = pagerState.settledPage.coerceIn(accounts.indices)
        if (page != selectedIndex) onSelectedIndexChange(page)
    }
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            pageSpacing = SignalSpacing.x2,
            beyondViewportPageCount = 1,
        ) { page ->
            SignalPrismAccountCard(
                account = accounts[page],
                index = page,
                count = accounts.size,
                masked = masked,
                onOpen = { onOpenAccount(page) },
                onToggleMasked = onToggleMasked,
                onCopyIban = onCopyIban?.let { { it(accounts[page]) } },
            )
        }
    }
}

@Composable
fun SignalPrismAccountCard(
    account: SignalPrismAccountCardModel,
    index: Int,
    count: Int,
    masked: Boolean,
    onOpen: () -> Unit,
    modifier: Modifier = Modifier,
    onToggleMasked: (() -> Unit)? = null,
    onCopyIban: (() -> Unit)? = null,
) {
    val shape = RoundedCornerShape(30.dp)
    val onCard = Color.White
    val quiet = Color.White.copy(alpha = 0.68f)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(SignalComponentMetrics.accountCarouselHeight)
            .clip(shape)
            .background(signalPrismAccountBrush(account), shape)
            .border(1.dp, Color.White.copy(alpha = 0.08f), shape)
            .clickable(onClick = onOpen),
    ) {
        SignalPrismAccountCardArtwork(
            style = account.style,
            artworkStyle = account.artworkStyle,
            bankPalette = account.bankPalette,
            modifier = Modifier.matchParentSize(),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = SignalSpacing.x4, top = SignalSpacing.x4, end = SignalSpacing.x4, bottom = SignalSpacing.x4),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2), verticalAlignment = Alignment.Top) {
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp), horizontalAlignment = Alignment.End) {
                    Text(account.accountName, color = onCard, style = SignalTheme.typography.prismTitleCard, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Text(account.accountType, color = quiet, style = SignalTheme.typography.prismMeta, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                SignalPrismStatusChip(account.statusLabel, contentColor = onCard)
                if (onToggleMasked != null) {
                    SignalPrismMaskControl(masked = masked, onClick = onToggleMasked)
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(6.dp), horizontalAlignment = Alignment.End) {
                Text(account.balanceLabel, color = quiet, style = SignalTheme.typography.prismMeta, maxLines = 1)
                SignalPrismFinancialAmount(
                    value = if (masked) "••••••" else account.balance,
                    contentColor = onCard,
                    decimalColor = onCard.copy(alpha = 0.58f),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color.White.copy(alpha = 0.075f))
                    .clickable(enabled = onCopyIban != null) { onCopyIban?.invoke() }
                    .padding(horizontal = 13.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "IBAN",
                    color = quiet,
                    style = SignalTheme.typography.prismMeta,
                    maxLines = 1,
                )
                Text(
                    text = if (masked) "ينتهي ••••" else "ينتهي ${account.iban.takeLast(4)}",
                    color = onCard.copy(alpha = 0.94f),
                    style = SignalTheme.typography.prismIdentifier,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                )
                if (count > 1) {
                    Text("${index + 1}/$count", color = quiet, style = SignalTheme.typography.prismMeta, maxLines = 1)
                }
            }
        }
        SignalPrismCarouselDots(
            count = count,
            selectedIndex = index,
            modifier = Modifier.align(Alignment.BottomCenter).width(56.dp).padding(bottom = 7.dp),
            onPrimary = true,
        )
    }
}

@Composable
fun SignalPrismAccountDetailHero(
    account: SignalPrismAccountCardModel,
    masked: Boolean,
    modifier: Modifier = Modifier,
    mode: SignalAccountDetailMode = SignalAccountDetailMode.Retail,
    currentBalance: String? = null,
    ledgerBalance: String? = null,
    onToggleMasked: (() -> Unit)? = null,
) {
    val prism = SignalTheme.prism
    val shape = RoundedCornerShape(36.dp)
    val heroHeight = when (mode) {
        SignalAccountDetailMode.CorporateDense,
        SignalAccountDetailMode.Minimal -> 220.dp
        else -> 262.dp
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(heroHeight)
            .clip(shape)
            .background(signalPrismAccountBrush(account), shape)
            .border(1.dp, Color.White.copy(alpha = 0.12f), shape),
    ) {
        SignalPrismAccountCardArtwork(
            style = account.style,
            artworkStyle = account.artworkStyle,
            bankPalette = account.bankPalette,
            modifier = Modifier.matchParentSize(),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(SignalSpacing.x4),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2), verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                    Text(account.accountName, color = Color.White, style = SignalTheme.typography.prismTitleHero, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Text(account.accountType, color = Color.White.copy(alpha = 0.70f), style = SignalTheme.typography.prismMeta, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                SignalPrismStatusChip(account.statusLabel)
                if (onToggleMasked != null) SignalPrismMaskControl(masked = masked, onClick = onToggleMasked)
            }
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(account.balanceLabel, color = Color.White.copy(alpha = 0.70f), style = SignalTheme.typography.prismMeta, maxLines = 1)
                SignalPrismFinancialAmount(
                    value = if (masked) "••••••" else account.balance,
                    contentColor = Color.White,
                    decimalColor = Color.White.copy(alpha = 0.58f),
                )
                Row(horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2), verticalAlignment = Alignment.CenterVertically) {
                    if (currentBalance != null) SignalPrismHeroMetaPill("الحالي", if (masked) "••••" else currentBalance, onPrimary = true)
                    if (ledgerBalance != null) SignalPrismHeroMetaPill("دفتر الأستاذ", if (masked) "••••" else ledgerBalance, onPrimary = true)
                }
            }
        }
    }
}

@Composable
private fun SignalPrismHeroMetaPill(label: String, value: String, onPrimary: Boolean = false) {
    val labelColor = if (onPrimary) Color.White.copy(alpha = 0.70f) else SignalTheme.prism.palette.prismTextSecondary
    val valueColor = if (onPrimary) Color.White.copy(alpha = 0.90f) else SignalTheme.prism.palette.prismTextPrimary
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White.copy(alpha = 0.10f))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp),
    ) {
        Text(label, color = labelColor, style = SignalTheme.typography.prismMeta, maxLines = 1)
        Text(value, color = valueColor, style = SignalTheme.typography.prismIdentifier, maxLines = 1)
    }
}

@Composable
fun SignalPrismAccountIdentityPanel(
    fields: List<SignalPrismAccountIdentityField>,
    onCopyField: (SignalAccountIdentityFieldRole, String, String) -> Unit,
    modifier: Modifier = Modifier,
    copiedRole: SignalAccountIdentityFieldRole? = null,
) {
    val prism = SignalTheme.prism
    val copiedFieldLabel = fields.firstOrNull { it.role == copiedRole }?.label
    SignalPrismPanel(
        modifier = modifier,
        title = "بيانات الاستلام",
        subtitle = copiedFieldLabel?.let { "تم نسخ $it" } ?: "انسخ المعرف المناسب لهذا الحساب",
        icon = SignalIconName.Copy,
        iconTint = if (copiedFieldLabel != null) prism.palette.prismEmerald else prism.palette.prismCyan,
    ) {
        fields.forEachIndexed { index, field ->
            SignalPrismCopyableAccountField(
                field = field,
                copied = copiedRole == field.role,
                onCopy = onCopyField,
            )
            if (index != fields.lastIndex) Spacer(modifier = Modifier.height(2.dp))
        }
    }
}

@Composable
fun SignalPrismCopyableAccountField(
    field: SignalPrismAccountIdentityField,
    copied: Boolean,
    onCopy: (SignalAccountIdentityFieldRole, String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    val textDirection = when (field.role) {
        SignalAccountIdentityFieldRole.Iban,
        SignalAccountIdentityFieldRole.AccountNumber,
        SignalAccountIdentityFieldRole.Alias,
        SignalAccountIdentityFieldRole.Reference -> TextDirection.Ltr
        SignalAccountIdentityFieldRole.HolderName,
        SignalAccountIdentityFieldRole.Branch -> TextDirection.ContentOrLtr
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White.copy(alpha = 0.055f))
            .clickable(enabled = field.copyEnabled) { onCopy(field.role, field.value, field.label) }
            .padding(SignalSpacing.x2),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(field.label, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.prismMeta, maxLines = 1)
            Text(
                field.value,
                color = prism.palette.prismTextPrimary,
                style = SignalTheme.typography.prismIdentifier.copy(textDirection = textDirection),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (copied) Text("تم النسخ", color = prism.palette.prismEmerald, style = SignalTheme.typography.prismStatus, maxLines = 1)
        }
        SignalIcon(if (field.copyEnabled) SignalIconName.Copy else SignalIconName.Lock, tint = if (field.copyEnabled) prism.palette.prismTextSecondary else prism.palette.prismTextMuted, size = 19.dp)
    }
}

@Composable
fun SignalPrismBalanceBreakdown(
    items: List<SignalPrismBalanceBreakdownItem>,
    modifier: Modifier = Modifier,
) {
    SignalPrismPanel(
        modifier = modifier,
        title = "تفصيل الرصيد",
        subtitle = "الأرصدة التي تؤثر على المتاح الآن",
        icon = SignalIconName.Accounts,
        iconTint = SignalTheme.prism.palette.prismEmerald,
    ) {
        items.chunked(2).forEach { rowItems ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
                rowItems.forEach { item ->
                    SignalPrismMetricTile(
                        label = item.label,
                        value = item.value,
                        detail = item.detail,
                        emphasis = item.emphasis,
                        modifier = Modifier.weight(1f),
                    )
                }
                if (rowItems.size == 1) Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun SignalPrismAccountPackagePanel(
    packageName: String,
    feeTier: String,
    limits: List<SignalPrismAccountLimitTile>,
    modifier: Modifier = Modifier,
) {
    SignalPrismPanel(
        modifier = modifier,
        title = "باقة الحساب",
        subtitle = "$packageName · $feeTier",
        icon = SignalIconName.Chart,
        iconTint = SignalTheme.prism.palette.prismGold,
    ) {
        limits.chunked(2).forEach { rowItems ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
                rowItems.forEach { item ->
                    SignalPrismMetricTile(
                        label = item.label,
                        value = item.value,
                        detail = item.detail,
                        modifier = Modifier.weight(1f),
                    )
                }
                if (rowItems.size == 1) Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun SignalPrismAccountActivityPreview(
    activities: List<SignalPrismAccountActivityItem>,
    onViewAll: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SignalPrismSectionHeader(title = "آخر حركات الحساب", action = "عرض الكل", onAction = onViewAll, modifier = modifier)
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x1)) {
        activities.take(3).forEach { item ->
            SignalPrismActivityRow(item)
        }
    }
}

@Composable
fun SignalPrismAccountRequestPanel(
    requests: List<SignalPrismAccountRequestItem>,
    modifier: Modifier = Modifier,
) {
    SignalPrismPanel(
        modifier = modifier,
        title = "طلبات الحساب",
        subtitle = "إجراءات مرتبطة بهذا الحساب فقط",
        icon = SignalIconName.Settings,
        iconTint = SignalTheme.prism.palette.prismCyan,
    ) {
        requests.take(4).forEach { item ->
            SignalPrismCompactActionRow(
                title = item.title,
                metadata = "${item.description} · ${item.status}",
                icon = requestIcon(item.type),
                onClick = item.onClick,
            )
        }
    }
}

@Composable
fun SignalPrismAccountDocumentsPanel(
    documents: List<SignalPrismAccountDocumentItem>,
    modifier: Modifier = Modifier,
) {
    SignalPrismPanel(
        modifier = modifier,
        title = "المستندات",
        subtitle = "تحميل أو طلب مستندات الحساب",
        icon = SignalIconName.Copy,
        iconTint = SignalTheme.prism.palette.prismViolet,
    ) {
        documents.take(4).forEach { item ->
            SignalPrismCompactActionRow(
                title = item.title,
                metadata = item.description + if (item.available) "" else " · يتطلب موافقة",
                icon = SignalIconName.Copy,
                onClick = item.onClick,
            )
        }
    }
}

@Composable
fun SignalPrismLinkedAccountServices(
    services: List<SignalPrismLinkedAccountServiceItem>,
    modifier: Modifier = Modifier,
) {
    SignalPrismPanel(
        modifier = modifier,
        title = "الخدمات المرتبطة",
        subtitle = "ما يستخدم هذا الحساب حالياً",
        icon = SignalIconName.Shield,
        iconTint = SignalTheme.prism.palette.prismEmerald,
    ) {
        services.take(3).forEach { item ->
            SignalPrismCompactActionRow(
                title = item.title,
                metadata = "${item.description} · ${item.status}",
                icon = item.icon,
                onClick = item.onClick,
            )
        }
    }
}

@Composable
fun SignalPrismAccountWorkspaceTransition(
    targetKey: Any,
    modifier: Modifier = Modifier,
    spec: SignalPrismAccountWorkspaceTransitionSpec = SignalPrismAccountWorkspaceTransitionSpec(),
    content: @Composable () -> Unit,
) {
    AnimatedContent(
        targetState = targetKey,
        modifier = modifier,
        transitionSpec = {
            (fadeIn(tween(spec.durationMillis, easing = SignalMotion.StandardEasing)) +
                androidx.compose.animation.scaleIn(
                    animationSpec = tween(spec.durationMillis, easing = SignalMotion.StandardEasing),
                    initialScale = spec.initialScale,
                ))
                .togetherWith(
                    fadeOut(tween(spec.durationMillis / 2, easing = SignalMotion.StandardEasing)),
                )
        },
        label = "signal-account-workspace-transition",
    ) {
        content()
    }
}

@Composable
fun SignalPrismAccountWorkspaceStage(
    account: SignalPrismAccountCardModel,
    masked: Boolean,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    availableBalance: String = account.balance,
    currentBalance: String? = null,
    heldBalance: String? = null,
    accountIndexLabel: String? = null,
    onToggleMasked: (() -> Unit)? = null,
) {
    val prism = SignalTheme.prism
    val colors = SignalTheme.colors
    val shape = RoundedCornerShape(bottomStart = 38.dp, bottomEnd = 38.dp)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(signalPrismAccountBrush(account))
            .heightIn(min = 304.dp)
            .padding(start = SignalSpacing.x4, top = SignalSpacing.x4, end = SignalSpacing.x4, bottom = SignalSpacing.x5),
    ) {
        SignalPrismAccountWorkspaceStageArtwork(
            account = account,
            modifier = Modifier.matchParentSize(),
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(SignalSpacing.x4),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SignalIconButton(onClick = onBack) {
                    SignalIcon(SignalIconName.ArrowStart, tint = Color.White, size = 24.dp)
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    Text(
                        text = account.accountName,
                        color = Color.White,
                        style = SignalTheme.typography.prismTitleHero,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.End,
                    )
                    Text(
                        text = account.accountType,
                        color = Color.White.copy(alpha = 0.72f),
                        style = SignalTheme.typography.prismMeta,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.End,
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                Text(
                    text = account.balanceLabel,
                    color = Color.White.copy(alpha = 0.70f),
                    style = SignalTheme.typography.prismMeta,
                    maxLines = 1,
                )
                Text(
                    text = if (masked) "••••••" else availableBalance,
                    color = Color.White,
                    style = SignalTheme.typography.prismAmountLarge.copy(
                        fontFeatureSettings = "tnum",
                        fontWeight = FontWeight.Black,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    textAlign = TextAlign.End,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (onToggleMasked != null) {
                        SignalPrismStageIconChip(
                            label = if (masked) "إظهار" else "إخفاء",
                            onClick = onToggleMasked,
                        ) {
                            SignalIcon(if (masked) SignalIconName.Eye else SignalIconName.EyeOff, size = 18.dp, tint = Color.White)
                        }
                    }
                    SignalPrismAccountStageChip(account.statusLabel)
                    if (accountIndexLabel != null) {
                        SignalPrismAccountStageChip(accountIndexLabel)
                    }
                    if (currentBalance != null) {
                        SignalPrismAccountStageChip("الحالي $currentBalance")
                    }
                    if (heldBalance != null) {
                        SignalPrismAccountStageChip("محجوز $heldBalance")
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .width(92.dp)
                .height(3.dp)
                .clip(RoundedCornerShape(99.dp))
                .background(prism.palette.prismCyan.copy(alpha = if (colors.black) 0.78f else 0.62f)),
        )
    }
}

@Composable
private fun SignalPrismAccountWorkspaceStageArtwork(
    account: SignalPrismAccountCardModel,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    Canvas(modifier = modifier) {
        drawRoundRect(
            color = Color.White.copy(alpha = 0.055f),
            topLeft = Offset(size.width * 0.06f, size.height * 0.19f),
            size = Size(size.width * 0.68f, 2.dp.toPx()),
            cornerRadius = CornerRadius(99f, 99f),
        )
        drawCircle(
            color = account.bankPalette.secondary.copy(alpha = 0.12f),
            radius = size.minDimension * 0.36f,
            center = Offset(size.width * 0.12f, size.height * 1.06f),
        )
    }
}

@Composable
fun SignalPrismAccountStageBalance(
    label: String,
    amount: String,
    modifier: Modifier = Modifier,
    masked: Boolean = false,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(label, color = Color.White.copy(alpha = 0.70f), style = SignalTheme.typography.prismMeta, maxLines = 1)
        Text(
            text = if (masked) "••••••" else amount,
            color = Color.White,
            style = SignalTheme.typography.prismAmountLarge.copy(fontFeatureSettings = "tnum"),
            maxLines = 1,
            overflow = TextOverflow.Clip,
            textAlign = TextAlign.End,
        )
    }
}

@Composable
fun SignalPrismAccountStageChip(
    label: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = label,
        modifier = modifier
            .clip(RoundedCornerShape(99.dp))
            .background(Color.White.copy(alpha = 0.12f))
            .border(1.dp, Color.White.copy(alpha = 0.10f), RoundedCornerShape(99.dp))
            .padding(horizontal = SignalSpacing.x2, vertical = 7.dp),
        color = Color.White.copy(alpha = 0.88f),
        style = SignalTheme.typography.prismStatus,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
private fun SignalPrismStageIconChip(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(99.dp))
            .background(Color.White.copy(alpha = 0.14f))
            .border(1.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(99.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = SignalSpacing.x2, vertical = 7.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon()
        Text(label, color = Color.White.copy(alpha = 0.90f), style = SignalTheme.typography.prismStatus, maxLines = 1)
    }
}

@Composable
fun SignalPrismAccountWorkspaceHero(
    account: SignalPrismAccountCardModel,
    masked: Boolean,
    modifier: Modifier = Modifier,
    availableBalance: String = account.balance,
    currentBalance: String? = null,
    ledgerBalance: String? = null,
    onToggleMasked: (() -> Unit)? = null,
) {
    val prism = SignalTheme.prism
    val shape = RoundedCornerShape(34.dp)
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color.Transparent,
        contentColor = prism.palette.prismTextPrimary,
        shape = shape,
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.12f)),
    ) {
        Box(
            modifier = Modifier
                .background(signalPrismAccountBrush(account), shape)
                .heightIn(min = 204.dp)
                .padding(SignalSpacing.x3),
        ) {
            SignalPrismAccountCardArtwork(
                style = account.style,
                artworkStyle = account.artworkStyle,
                bankPalette = account.bankPalette,
                modifier = Modifier.matchParentSize(),
            )
            Column(verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3)) {
                Row(horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2), verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(account.accountName, style = SignalTheme.typography.prismTitleHero, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(account.accountType, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.prismMeta, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                    SignalPrismStatusChip(account.statusLabel)
                    if (onToggleMasked != null) SignalPrismMaskControl(masked = masked, onClick = onToggleMasked)
                }
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(account.balanceLabel, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.prismMeta, maxLines = 1)
                    SignalPrismFinancialAmount(if (masked) "••••••" else availableBalance)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2), verticalAlignment = Alignment.CenterVertically) {
                    if (currentBalance != null) SignalPrismHeroMetaPill("الحالي", if (masked) "••••" else currentBalance)
                    if (ledgerBalance != null) SignalPrismHeroMetaPill("دفتر الأستاذ", if (masked) "••••" else ledgerBalance)
                }
            }
        }
    }
}

@Composable
fun SignalPrismAccountIdentityStrip(
    fields: List<SignalPrismAccountIdentityField>,
    onCopyField: (SignalAccountIdentityFieldRole, String, String) -> Unit,
    modifier: Modifier = Modifier,
    copiedRole: SignalAccountIdentityFieldRole? = null,
) {
    val prism = SignalTheme.prism
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x1)) {
        Row(horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2), verticalAlignment = Alignment.CenterVertically) {
            Text("بيانات الاستلام", color = prism.palette.prismTextPrimary, style = SignalTheme.typography.prismTitleCard, modifier = Modifier.weight(1f), maxLines = 1)
            val copiedLabel = fields.firstOrNull { it.role == copiedRole }?.label
            if (copiedLabel != null) {
                Text("تم نسخ $copiedLabel", color = prism.palette.prismEmerald, style = SignalTheme.typography.prismStatus, maxLines = 1)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(prism.surfaces.prismSurfaceRaised.copy(alpha = 0.64f))
                .border(1.dp, prism.overlays.prismBorderSoft.copy(alpha = 0.12f), RoundedCornerShape(24.dp))
                .padding(horizontal = SignalSpacing.x2, vertical = SignalSpacing.x1),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            fields.take(3).forEach { field ->
                SignalPrismCopyableAccountIdentifier(
                    field = field,
                    copied = copiedRole == field.role,
                    onCopy = onCopyField,
                )
            }
        }
    }
}

@Composable
fun SignalPrismAccountIdentifierRail(
    fields: List<SignalPrismAccountIdentityField>,
    onCopyField: (SignalAccountIdentityFieldRole, String, String) -> Unit,
    modifier: Modifier = Modifier,
    copiedRole: SignalAccountIdentityFieldRole? = null,
) {
    val prism = SignalTheme.prism
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x1)) {
        Row(horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2), verticalAlignment = Alignment.CenterVertically) {
            Text(
                "بيانات الاستلام",
                color = prism.palette.prismTextPrimary,
                style = SignalTheme.typography.prismTitleCard,
                modifier = Modifier.weight(1f),
                maxLines = 1,
            )
            fields.firstOrNull { it.role == copiedRole }?.let {
                Text("تم نسخ ${it.label}", color = prism.palette.prismEmerald, style = SignalTheme.typography.prismStatus, maxLines = 1)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(prism.surfaces.prismSurfaceRaised.copy(alpha = 0.66f))
                .border(1.dp, prism.overlays.prismBorderSoft.copy(alpha = 0.12f), RoundedCornerShape(24.dp))
                .padding(horizontal = SignalSpacing.x2, vertical = SignalSpacing.x1),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            fields.take(3).forEach { field ->
                SignalPrismCopyableAccountIdentifierChip(
                    field = field,
                    copied = copiedRole == field.role,
                    onCopy = onCopyField,
                )
            }
        }
    }
}

@Composable
private fun SignalPrismCopyableAccountIdentifierChip(
    field: SignalPrismAccountIdentityField,
    copied: Boolean,
    onCopy: (SignalAccountIdentityFieldRole, String, String) -> Unit,
) {
    val prism = SignalTheme.prism
    val textDirection = when (field.role) {
        SignalAccountIdentityFieldRole.Iban,
        SignalAccountIdentityFieldRole.AccountNumber,
        SignalAccountIdentityFieldRole.Alias,
        SignalAccountIdentityFieldRole.Reference -> TextDirection.Ltr
        SignalAccountIdentityFieldRole.HolderName,
        SignalAccountIdentityFieldRole.Branch -> TextDirection.ContentOrLtr
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(if (copied) prism.palette.prismEmerald.copy(alpha = 0.13f) else Color.Transparent)
            .clickable(enabled = field.copyEnabled) { onCopy(field.role, field.value, field.label) }
            .padding(horizontal = SignalSpacing.x1, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(field.label, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.prismMeta, maxLines = 1)
            Text(
                field.value,
                color = prism.palette.prismTextPrimary,
                style = SignalTheme.typography.prismIdentifier.copy(textDirection = textDirection),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        SignalIcon(if (field.copyEnabled) SignalIconName.Copy else SignalIconName.Lock, tint = if (copied) prism.palette.prismEmerald else prism.palette.prismTextSecondary, size = 18.dp)
    }
}

@Composable
fun SignalPrismCopyableAccountIdentifier(
    field: SignalPrismAccountIdentityField,
    copied: Boolean,
    onCopy: (SignalAccountIdentityFieldRole, String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    val textDirection = when (field.role) {
        SignalAccountIdentityFieldRole.Iban,
        SignalAccountIdentityFieldRole.AccountNumber,
        SignalAccountIdentityFieldRole.Alias,
        SignalAccountIdentityFieldRole.Reference -> TextDirection.Ltr
        SignalAccountIdentityFieldRole.HolderName,
        SignalAccountIdentityFieldRole.Branch -> TextDirection.ContentOrLtr
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(if (copied) prism.palette.prismEmerald.copy(alpha = 0.12f) else Color.Transparent)
            .clickable(enabled = field.copyEnabled) { onCopy(field.role, field.value, field.label) }
            .padding(horizontal = SignalSpacing.x1, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(field.label, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.prismMeta, modifier = Modifier.widthIn(min = 72.dp, max = 104.dp), maxLines = 1)
        Text(
            field.value,
            color = prism.palette.prismTextPrimary,
            style = SignalTheme.typography.prismIdentifier.copy(textDirection = textDirection),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f),
        )
        SignalIcon(if (field.copyEnabled) SignalIconName.Copy else SignalIconName.Lock, tint = if (copied) prism.palette.prismEmerald else prism.palette.prismTextSecondary, size = 18.dp)
    }
}

@Composable
fun SignalPrismAccountPrimaryActions(
    actions: List<SignalPrismAccountAction>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        actions.take(4).forEach { action ->
            SignalPrismWorkspaceActionButton(action, Modifier.weight(if (action.suggested) 1.22f else 1f))
        }
    }
}

@Composable
fun SignalPrismAccountPrimaryActionBar(
    actions: List<SignalPrismAccountAction>,
    modifier: Modifier = Modifier,
) {
    SignalPrismAccountPrimaryActions(actions = actions, modifier = modifier)
}

@Composable
private fun SignalPrismWorkspaceActionButton(action: SignalPrismAccountAction, modifier: Modifier = Modifier) {
    val prism = SignalTheme.prism
    val colors = SignalTheme.colors
    val interaction = remember { MutableInteractionSource() }
    val pressed by interaction.collectIsPressedAsState()
    val scale by animateFloatAsState(if (pressed) 0.985f else 1f, tween(SignalMotion.PressMs), label = "account-action-press")
    val accent = prismWorkspaceActionAccent(action.id, action.suggested)
    Column(
        modifier = modifier
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .height(74.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(
                Brush.linearGradient(
                    listOf(
                        accent.copy(alpha = if (action.suggested) 0.25f else 0.12f),
                        prism.surfaces.prismSurfaceRaised.copy(alpha = if (colors.dark) 0.62f else 0.84f),
                    ),
                ),
            )
            .border(1.dp, accent.copy(alpha = if (action.suggested) 0.28f else 0.14f), RoundedCornerShape(22.dp))
            .clickable(interactionSource = interaction, indication = null, onClick = action.onClick)
            .padding(horizontal = SignalSpacing.x1, vertical = 9.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier.size(30.dp).clip(RoundedCornerShape(12.dp)).background(accent.copy(alpha = if (action.suggested) 0.24f else 0.16f)),
            contentAlignment = Alignment.Center,
        ) {
            CompositionLocalProvider(LocalContentColor provides accent) {
                action.icon()
            }
        }
        Spacer(Modifier.height(4.dp))
        Text(action.label, color = prism.palette.prismTextPrimary, style = SignalTheme.typography.prismStatus, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
private fun prismWorkspaceActionAccent(id: String, prominent: Boolean): Color {
    val prism = SignalTheme.prism
    val key = id.lowercase()
    return when {
        prominent || "transfer" in key -> prism.palette.prismCyan
        "receive" in key || "qr" in key -> prism.palette.prismViolet
        "pay" in key || "bill" in key -> prism.palette.prismCoral
        "statement" in key || "chart" in key -> prism.palette.prismEmerald
        else -> prism.palette.prismGold
    }
}

@Composable
fun SignalPrismWorkspaceSegmentedControl(
    selected: SignalPrismAccountWorkspaceSection,
    onSelected: (SignalPrismAccountWorkspaceSection) -> Unit,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    val sections = listOf(
        SignalPrismAccountWorkspaceSection.Overview,
        SignalPrismAccountWorkspaceSection.Activity,
        SignalPrismAccountWorkspaceSection.Requests,
        SignalPrismAccountWorkspaceSection.Documents,
    )
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(999.dp))
            .background(prism.surfaces.prismSurfaceRaised.copy(alpha = 0.70f))
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        sections.forEach { section ->
            val active = selected == section
            Text(
                text = accountWorkspaceSectionLabel(section),
                color = if (active) prism.palette.prismTextPrimary else prism.palette.prismTextSecondary,
                style = SignalTheme.typography.prismStatus,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(999.dp))
                    .background(if (active) prism.palette.prismCyan.copy(alpha = 0.16f) else Color.Transparent)
                    .clickable { onSelected(section) }
                    .padding(horizontal = 6.dp, vertical = 10.dp),
            )
        }
    }
}

@Composable
fun SignalPrismAccountOverviewPanel(
    balances: List<SignalPrismBalanceBreakdownItem>,
    limits: List<SignalPrismAccountLimitTile>,
    linkedServices: List<SignalPrismLinkedAccountServiceItem>,
    modifier: Modifier = Modifier,
) {
    SignalPrismPanel(
        modifier = modifier,
        title = "نظرة عامة",
        subtitle = "الرصيد، الباقة، والخدمات المهمة فقط",
        icon = SignalIconName.Accounts,
        iconTint = SignalTheme.prism.palette.prismCyan,
    ) {
        SignalPrismBalanceBreakdownCompact(items = balances.take(3))
        SignalPrismAccountPackageSummary(limits = limits.take(2))
        linkedServices.take(2).forEach { item ->
            SignalPrismCompactActionRow(
                title = item.title,
                metadata = "${item.description} · ${item.status}",
                icon = item.icon,
                onClick = item.onClick,
            )
        }
    }
}

@Composable
fun SignalPrismBalanceBreakdownCompact(items: List<SignalPrismBalanceBreakdownItem>, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        items.forEach { item ->
            SignalPrismMetricTile(
                label = item.label,
                value = item.value,
                detail = item.detail,
                emphasis = item.emphasis,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
fun SignalPrismAccountPackageSummary(limits: List<SignalPrismAccountLimitTile>, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        limits.forEach { item ->
            SignalPrismMetricTile(
                label = item.label,
                value = item.value,
                detail = item.detail,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
fun SignalPrismAccountActivitySection(
    activities: List<SignalPrismAccountActivityItem>,
    onViewAll: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        SignalPrismSectionHeader(title = "الحركات", action = "عرض الكل", onAction = onViewAll)
        activities.take(8).forEach { item -> SignalPrismActivityRow(item) }
    }
}

@Composable
fun SignalPrismAccountRequestsSection(
    requests: List<SignalPrismAccountRequestItem>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        SignalPrismSectionHeader(title = "الطلبات")
        requests.take(8).forEach { item -> SignalPrismAccountRequestTile(item) }
    }
}

@Composable
fun SignalPrismAccountRequestTile(item: SignalPrismAccountRequestItem, modifier: Modifier = Modifier) {
    SignalPrismCompactActionRow(
        title = item.title,
        metadata = "${item.description} · ${item.status}",
        icon = requestIcon(item.type),
        onClick = item.onClick,
    )
}

@Composable
fun SignalPrismAccountDocumentsSection(
    documents: List<SignalPrismAccountDocumentItem>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        SignalPrismSectionHeader(title = "المستندات")
        documents.take(8).forEach { item -> SignalPrismDocumentRow(item) }
    }
}

@Composable
fun SignalPrismDocumentRow(item: SignalPrismAccountDocumentItem, modifier: Modifier = Modifier) {
    SignalPrismCompactActionRow(
        title = item.title,
        metadata = item.description + if (item.available) "" else " · يتطلب موافقة",
        icon = SignalIconName.Copy,
        onClick = item.onClick,
    )
}

@Composable
fun SignalPrismAccountLinkedServicesSection(
    services: List<SignalPrismLinkedAccountServiceItem>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        SignalPrismSectionHeader(title = "الخدمات")
        services.take(8).forEach { item ->
            SignalPrismCompactActionRow(
                title = item.title,
                metadata = "${item.description} · ${item.status}",
                icon = item.icon,
                onClick = item.onClick,
            )
        }
    }
}

private fun accountWorkspaceSectionLabel(section: SignalPrismAccountWorkspaceSection): String =
    when (section) {
        SignalPrismAccountWorkspaceSection.Overview -> "نظرة عامة"
        SignalPrismAccountWorkspaceSection.Activity -> "الحركات"
        SignalPrismAccountWorkspaceSection.Requests -> "الطلبات"
        SignalPrismAccountWorkspaceSection.Documents -> "المستندات"
    }

@Composable
fun SignalPrismCardCarousel(
    cards: List<SignalPrismCardModel>,
    selectedIndex: Int,
    onSelectedIndexChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    onCardClick: (Int) -> Unit = {},
) {
    if (cards.isEmpty()) return
    val safeIndex = selectedIndex.coerceIn(cards.indices)
    val pagerState = rememberPagerState(initialPage = safeIndex) { cards.size }
    LaunchedEffect(safeIndex, cards.size) {
        if (pagerState.currentPage != safeIndex) {
            pagerState.animateScrollToPage(safeIndex, animationSpec = tween(SignalMotion.RouteMs))
        }
    }
    LaunchedEffect(pagerState.settledPage) {
        val page = pagerState.settledPage.coerceIn(cards.indices)
        if (page != selectedIndex) onSelectedIndexChange(page)
    }
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = SignalSpacing.x1),
            pageSpacing = SignalSpacing.x3,
            beyondViewportPageCount = 1,
        ) { page ->
            SignalPrismPaymentCard(
                card = cards[page],
                revealState = SignalPrismCardRevealState(revealed = false),
                modifier = Modifier.clickable { onCardClick(page) },
            )
        }
        SignalPrismCarouselDots(
            count = cards.size,
            selectedIndex = safeIndex,
            modifier = Modifier.align(Alignment.CenterHorizontally).width(64.dp),
            onPrimary = false,
        )
    }
}

@Composable
fun SignalCardDisplayPreferenceSelector(
    selectedMode: SignalCardPresentationMode,
    policy: SignalCardPresentationPolicy,
    onModeSelected: (SignalCardPresentationMode) -> Unit,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    val modes = listOf(SignalCardPresentationMode.List)
        .filter { it in policy.allowedModes }
    if (modes.size <= 1) return
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(999.dp))
            .background(prism.surfaces.prismSurfaceRaised.copy(alpha = 0.64f))
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        modes.forEach { mode ->
            val selected = selectedMode == mode
            Text(
                text = cardPresentationModeLabel(mode),
                color = if (selected) prism.palette.prismTextPrimary else prism.palette.prismTextSecondary,
                style = SignalTheme.typography.prismStatus,
                maxLines = 1,
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(999.dp))
                    .background(if (selected) prism.palette.prismCyan.copy(alpha = 0.18f) else Color.Transparent)
                    .clickable(enabled = policy.allowUserOverride) { onModeSelected(mode) }
                    .padding(horizontal = 10.dp, vertical = 9.dp),
            )
        }
    }
}

@Composable
fun SignalPrismCardList(
    cards: List<SignalPrismCardModel>,
    selectedIndex: Int,
    onSelectedIndexChange: (Int) -> Unit,
    onCardClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        cards.forEachIndexed { index, card ->
            SignalPrismCardListItem(
                card = card,
                selected = index == selectedIndex,
                onClick = {
                    onSelectedIndexChange(index)
                    onCardClick(index)
                },
            )
        }
    }
}

@Composable
fun SignalPrismCardListItem(
    card: SignalPrismCardModel,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    val accent = cardAccent(card.artworkProfile ?: card.network.toArtworkProfile(card.style))
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(prism.surfaces.prismSurfaceRaised.copy(alpha = if (selected) 0.72f else 0.46f))
            .border(1.dp, if (selected) accent.copy(alpha = 0.22f) else prism.overlays.prismBorderSoft.copy(alpha = 0.08f), RoundedCornerShape(24.dp))
            .clickable(onClick = onClick)
            .padding(SignalSpacing.x2),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SignalPrismMiniCardArtwork(card = card)
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(7.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(card.label, color = prism.palette.prismTextPrimary, style = SignalTheme.typography.prismTitleCard, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f))
                SignalPrismStatusChip(cardStatusLabel(card.status), contentColor = cardStatusTone(card.status))
            }
            Text(
                text = "${card.network.schemeLabel()} · ${card.maskedPan} · ${if (card.virtual) "افتراضية" else "فعلية"}",
                color = prism.palette.prismTextSecondary,
                style = SignalTheme.typography.prismCardMeta,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            card.balanceOrLimit?.let {
                Text(it, color = prism.palette.prismTextPrimary, style = SignalTheme.typography.prismAmountCompact, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
        }
        SignalIcon(SignalIconName.ChevronStart, tint = prism.palette.prismTextSecondary, size = 22.dp)
    }
}

@Composable
fun SignalPrismMiniCardArtwork(
    card: SignalPrismCardModel,
    modifier: Modifier = Modifier,
) {
    val profile = card.artworkProfile ?: card.network.toArtworkProfile(card.style)
    val contentColor = cardContentColor(profile)
    Box(
        modifier = modifier
            .size(width = 76.dp, height = 52.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(signalPrismCardBrush(profile))
            .border(1.dp, contentColor.copy(alpha = 0.12f), RoundedCornerShape(14.dp)),
    ) {
        SignalPrismCardArtwork(profile = profile, modifier = Modifier.matchParentSize())
        Text(
            text = card.network.schemeLabel(),
            color = contentColor,
            style = SignalTheme.typography.prismStatus,
            modifier = Modifier.align(Alignment.TopStart).padding(8.dp),
            maxLines = 1,
        )
        Text(
            text = card.last4,
            color = contentColor.copy(alpha = 0.82f),
            style = SignalTheme.typography.prismIdentifier,
            modifier = Modifier.align(Alignment.BottomEnd).padding(8.dp),
            maxLines = 1,
        )
    }
}

@Composable
fun SignalPrismPaymentCard(
    card: SignalPrismCardModel,
    revealState: SignalPrismCardRevealState,
    modifier: Modifier = Modifier,
    large: Boolean = false,
) {
    val shape = RoundedCornerShape(if (large) 34.dp else 30.dp)
    val height = if (large) 244.dp else 214.dp
    val dimmed = card.status == SignalPrismCardStatus.Frozen || card.status == SignalPrismCardStatus.Blocked
    val profile = card.artworkProfile ?: card.network.toArtworkProfile(card.style)
    val contentColor = cardContentColor(profile)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .graphicsLayer { alpha = if (dimmed) 0.78f else 1f }
            .clip(shape)
            .background(signalPrismCardBrush(profile), shape)
            .border(1.dp, contentColor.copy(alpha = 0.12f), shape),
    ) {
        SignalPrismCardArtwork(profile = profile, modifier = Modifier.matchParentSize())
        Column(
            modifier = Modifier.fillMaxSize().padding(if (large) 22.dp else 18.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Column(verticalArrangement = Arrangement.spacedBy(2.dp), modifier = Modifier.weight(1f)) {
                    Text(card.label, color = contentColor, style = SignalTheme.typography.prismTitleCard, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Text(card.subtitle, color = contentColor.copy(alpha = 0.70f), style = SignalTheme.typography.prismCardMeta, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
                SignalPrismCardNetworkMark(card.network, color = contentColor)
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                SignalPrismCardChip(tint = contentColor)
                Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    card.balanceOrLimit?.let {
                        Text(it, color = contentColor, style = SignalTheme.typography.prismAmountCompact, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                    if (card.virtual) SignalPrismStatusChip("افتراضية", contentColor = contentColor) else SignalPrismContactlessMark(tint = contentColor)
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = SignalPrismSensitiveCardField(
                        masked = card.maskedPan,
                        revealed = "4242 8120 2481 ${card.last4}",
                        revealState = revealState,
                        allowed = card.revealPolicy.allowPanReveal,
                    ),
                    color = contentColor,
                    style = SignalTheme.typography.prismCardPan,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                )
                Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        Text(card.holderName, color = contentColor.copy(alpha = 0.76f), style = SignalTheme.typography.prismCardMeta, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text("EXP ${card.expiry ?: card.expiryMasked}", color = contentColor.copy(alpha = 0.62f), style = SignalTheme.typography.prismCardMeta, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                    Text(cardStatusLabel(card.status), color = contentColor, style = SignalTheme.typography.prismStatus, maxLines = 1, modifier = Modifier.background(contentColor.copy(alpha = 0.13f), RoundedCornerShape(999.dp)).padding(horizontal = 10.dp, vertical = 5.dp))
                }
            }
        }
    }
}

@Composable
fun SignalPrismCardActionCluster(
    actions: List<SignalPrismCardAction>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(6.dp)) {
        actions.take(4).chunked(2).forEach { row ->
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                row.forEach { action ->
                    SignalPrismCardActionCell(action = action, modifier = Modifier.weight(1f))
                }
                if (row.size == 1) Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun SignalPrismCardActionCell(action: SignalPrismCardAction, modifier: Modifier = Modifier) {
    val prism = SignalTheme.prism
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(if (pressed) 0.974f else 1f, label = "signal-prism-card-action")
    val tone = when {
        action.destructive -> SignalTheme.colors.error
        action.id == "freeze" -> prism.palette.prismGold
        else -> prism.palette.prismCyan
    }
    Row(
        modifier = modifier
            .graphicsLayer { scaleX = scale; scaleY = scale }
            .heightIn(min = 62.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(prism.surfaces.prismSurfaceRaised.copy(alpha = 0.52f))
            .clickable(enabled = action.enabled, interactionSource = interactionSource, indication = null, onClick = action.onClick)
            .padding(SignalSpacing.x2),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(modifier = Modifier.size(34.dp).clip(RoundedCornerShape(14.dp)).background(tone.copy(alpha = 0.16f)), contentAlignment = Alignment.Center) {
            CompositionLocalProvider(LocalContentColor provides tone) {
                Box(modifier = Modifier.size(19.dp), contentAlignment = Alignment.Center) { action.icon() }
            }
        }
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(1.dp)) {
            Text(action.label, color = prism.palette.prismTextPrimary, style = SignalTheme.typography.labelMedium, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(action.supportingText, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.statusPill, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
fun SignalPrismCardStatusPanel(card: SignalPrismCardModel, modifier: Modifier = Modifier) {
    val prism = SignalTheme.prism
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = prism.surfaces.prismSurfaceRaised.copy(alpha = 0.50f),
        contentColor = prism.palette.prismTextPrimary,
        shape = RoundedCornerShape(26.dp),
        border = BorderStroke(1.dp, prism.overlays.prismBorderSoft.copy(alpha = 0.10f)),
    ) {
        Row(modifier = Modifier.padding(SignalSpacing.x3), horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(16.dp)).background(cardStatusTone(card.status).copy(alpha = 0.16f)), contentAlignment = Alignment.Center) {
                SignalIcon(if (card.status == SignalPrismCardStatus.Frozen) SignalIconName.Lock else SignalIconName.Shield, tint = cardStatusTone(card.status), size = 21.dp)
            }
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(cardStatusLabel(card.status), style = SignalTheme.typography.rowTitle, maxLines = 1)
                Text(card.tags.joinToString(" · ").ifBlank { card.type.name }, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.rowMeta, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
            card.balanceOrLimit?.let {
                Text(it, color = prism.palette.prismTextPrimary, style = SignalTheme.typography.rowTitle.copy(fontFeatureSettings = "tnum"), maxLines = 1)
            }
        }
    }
}

@Composable
fun SignalPrismCardDetailHeader(
    card: SignalPrismCardModel,
    mode: SignalCardDetailPresentationMode,
    modifier: Modifier = Modifier,
    onOpenArtwork: (() -> Unit)? = null,
) {
    when (mode) {
        SignalCardDetailPresentationMode.ExpandedArtwork -> SignalPrismPaymentCard(
            card = card,
            revealState = SignalPrismCardRevealState(revealed = false),
            large = true,
            modifier = modifier.padding(horizontal = SignalSpacing.x1),
        )
        SignalCardDetailPresentationMode.SecureDataFirst -> Unit
        SignalCardDetailPresentationMode.CompactIdentity,
        SignalCardDetailPresentationMode.ControlCenter -> SignalPrismCompactCardIdentityHeader(
            card = card,
            receiptLike = mode == SignalCardDetailPresentationMode.ControlCenter,
            onOpenArtwork = onOpenArtwork,
            modifier = modifier,
        )
    }
}

@Composable
private fun SignalPrismCompactCardIdentityHeader(
    card: SignalPrismCardModel,
    receiptLike: Boolean,
    onOpenArtwork: (() -> Unit)?,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = prism.surfaces.prismSurfaceRaised.copy(alpha = if (receiptLike) 0.58f else 0.66f),
        contentColor = prism.palette.prismTextPrimary,
        shape = RoundedCornerShape(28.dp),
        border = BorderStroke(1.dp, prism.overlays.prismBorderSoft.copy(alpha = 0.10f)),
    ) {
        Row(
            modifier = Modifier.padding(SignalSpacing.x3),
            horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SignalPrismMiniCardArtwork(
                card = card,
                modifier = Modifier.clickable(enabled = onOpenArtwork != null) { onOpenArtwork?.invoke() },
            )
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(card.label, style = SignalTheme.typography.prismTitleCard, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f))
                    SignalPrismStatusChip(cardStatusLabel(card.status), contentColor = cardStatusTone(card.status))
                }
                Text("${card.network.schemeLabel()} · ${card.maskedPan}", color = prism.palette.prismTextSecondary, style = SignalTheme.typography.prismCardMeta, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(if (card.virtual) "بطاقة افتراضية" else "بطاقة فعلية", color = prism.palette.prismTextMuted, style = SignalTheme.typography.prismMeta, maxLines = 1)
            }
        }
    }
}

@Composable
fun SignalPrismCardLimitSummary(limits: List<SignalPrismCardLimit>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        Text("الحدود والتحكم", color = SignalTheme.colors.onSurface, style = SignalTheme.typography.sectionTitle)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
            limits.take(2).forEach { limit ->
                Column(
                    modifier = Modifier.weight(1f).clip(RoundedCornerShape(22.dp)).background(SignalTheme.prism.surfaces.prismSurfaceRaised.copy(alpha = 0.48f)).padding(SignalSpacing.x3),
                    verticalArrangement = Arrangement.spacedBy(3.dp),
                ) {
                    Text(limit.label, color = SignalTheme.prism.palette.prismTextSecondary, style = SignalTheme.typography.statusPill, maxLines = 1)
                    Text(limit.value, color = SignalTheme.prism.palette.prismTextPrimary, style = SignalTheme.typography.rowTitle.copy(fontFeatureSettings = "tnum"), maxLines = 1)
                    Text(limit.detail, color = SignalTheme.prism.palette.prismTextMuted, style = SignalTheme.typography.statusPill, maxLines = 1)
                }
            }
        }
    }
}

@Composable
fun SignalPrismCardTransactionsPreview(
    transactions: List<SignalPrismCardTransaction>,
    modifier: Modifier = Modifier,
    onViewAll: (() -> Unit)? = null,
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
            Text("آخر حركات البطاقة", color = SignalTheme.colors.onSurface, style = SignalTheme.typography.sectionTitle)
            if (onViewAll != null) Text("عرض الكل", color = SignalTheme.prism.palette.prismTextSecondary, style = SignalTheme.typography.statusPill, modifier = Modifier.clickable(onClick = onViewAll))
        }
        Column(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(26.dp)).background(SignalTheme.prism.surfaces.prismSurfaceRaised.copy(alpha = 0.46f)).padding(horizontal = SignalSpacing.x3, vertical = SignalSpacing.x2)) {
            transactions.take(3).forEach { tx ->
                Row(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).clickable(onClick = tx.onClick).padding(vertical = 10.dp), horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(34.dp).clip(RoundedCornerShape(13.dp)).background(if (tx.incoming) Color(0xFF22C98B).copy(alpha = 0.16f) else SignalTheme.prism.palette.prismCoral.copy(alpha = 0.14f)), contentAlignment = Alignment.Center) {
                        SignalIcon(if (tx.incoming) SignalIconName.ArrowStart else SignalIconName.Cards, tint = if (tx.incoming) Color(0xFF22C98B) else SignalTheme.prism.palette.prismCoral, size = 18.dp)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(tx.merchant, color = SignalTheme.prism.palette.prismTextPrimary, style = SignalTheme.typography.rowTitle, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(tx.metadata, color = SignalTheme.prism.palette.prismTextSecondary, style = SignalTheme.typography.rowMeta, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    }
                    Text(tx.amount, color = if (tx.incoming) Color(0xFF22C98B) else SignalTheme.prism.palette.prismTextPrimary, style = SignalTheme.typography.rowTitle.copy(fontFeatureSettings = "tnum"), maxLines = 1)
                }
            }
        }
    }
}

@Composable
fun SignalPrismCardRevealSheet(
    card: SignalPrismCardModel,
    revealState: SignalPrismCardRevealState,
    onToggleReveal: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = prism.surfaces.prismSurfaceRaised.copy(alpha = 0.54f),
        contentColor = prism.palette.prismTextPrimary,
        shape = RoundedCornerShape(26.dp),
        border = BorderStroke(1.dp, prism.overlays.prismBorderSoft.copy(alpha = 0.10f)),
    ) {
        Column(modifier = Modifier.padding(SignalSpacing.x3), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3)) {
            Row(horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3), verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(16.dp)).background(prism.palette.prismCyan.copy(alpha = 0.14f)), contentAlignment = Alignment.Center) {
                    SignalIcon(SignalIconName.Lock, tint = prism.palette.prismCyan, size = 21.dp)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(if (revealState.revealed) "بيانات البطاقة ظاهرة" else "بيانات البطاقة مخفية", style = SignalTheme.typography.rowTitle, maxLines = 1)
                    Text("سيتم إخفاء البيانات تلقائياً بعد لحظات", color = prism.palette.prismTextSecondary, style = SignalTheme.typography.rowMeta, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
                SignalPrismSensitiveFieldLabel("PAN", SignalPrismSensitiveCardField(card.maskedPan, "4242 8120 2481 ${card.last4}", revealState, card.revealPolicy.allowPanReveal), Modifier.weight(1f))
                SignalPrismSensitiveFieldLabel("CVV", SignalPrismSensitiveCardField(card.cvvMasked, card.cvv ?: card.cvvMasked, revealState, card.revealPolicy.allowCvvReveal), Modifier.weight(1f))
            }
            Text(
                text = if (revealState.revealed) "إخفاء البيانات" else "إظهار بيانات البطاقة",
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(999.dp)).background(prism.palette.prismCyan.copy(alpha = 0.16f)).clickable(onClick = onToggleReveal).padding(vertical = 12.dp),
                color = prism.palette.prismTextPrimary,
                style = SignalTheme.typography.button,
                maxLines = 1,
            )
        }
    }
}

@Composable
fun SignalPrismCardSecureDataPanel(
    card: SignalPrismCardModel,
    revealState: SignalPrismCardRevealState,
    onToggleReveal: () -> Unit,
    onCopyField: (SignalCardSecureFieldRole, String, String) -> Unit,
    modifier: Modifier = Modifier,
    copiedField: SignalCardSecureFieldRole? = null,
) {
    val prism = SignalTheme.prism
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = prism.surfaces.prismSurfaceRaised.copy(alpha = 0.58f),
        contentColor = prism.palette.prismTextPrimary,
        shape = RoundedCornerShape(28.dp),
        border = BorderStroke(1.dp, prism.overlays.prismBorderSoft.copy(alpha = 0.10f)),
    ) {
        Column(modifier = Modifier.padding(SignalSpacing.x3), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
            Row(horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3), verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(40.dp).clip(RoundedCornerShape(16.dp)).background(prism.palette.prismCyan.copy(alpha = 0.14f)), contentAlignment = Alignment.Center) {
                    SignalIcon(if (revealState.revealed) SignalIconName.Eye else SignalIconName.Lock, tint = prism.palette.prismCyan, size = 21.dp)
                }
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text("بيانات البطاقة", style = SignalTheme.typography.prismTitleCard, maxLines = 1)
                    val note = if (revealState.revealed) {
                        "تُخفى البيانات بعد ${revealState.secondsRemaining ?: card.revealPolicy.autoRemaskSeconds} ثانية"
                    } else {
                        "مخفية افتراضياً ولا تُنسخ إلا حسب سياسة المصرف"
                    }
                    Text(note, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.prismMeta, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
            }
            SignalPrismCopyableSecureField(
                label = "رقم البطاقة",
                role = SignalCardSecureFieldRole.Pan,
                maskedValue = card.maskedPan,
                revealedValue = card.fullPan ?: "4242 8120 2481 ${card.last4}",
                revealState = revealState,
                allowedToReveal = card.revealPolicy.allowPanReveal,
                copyEnabled = card.clipboardPolicy.allowCopyPan && (!card.clipboardPolicy.requireRevealBeforeCopy || revealState.revealed),
                copied = copiedField == SignalCardSecureFieldRole.Pan,
                onCopy = onCopyField,
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
                SignalPrismCopyableSecureField(
                    label = "تاريخ الانتهاء",
                    role = SignalCardSecureFieldRole.Expiry,
                    maskedValue = card.expiryMasked,
                    revealedValue = card.expiry ?: card.expiryMasked,
                    revealState = revealState,
                    allowedToReveal = card.revealPolicy.allowExpiryReveal,
                    copyEnabled = card.clipboardPolicy.allowCopyExpiry && (!card.clipboardPolicy.requireRevealBeforeCopy || revealState.revealed),
                    copied = copiedField == SignalCardSecureFieldRole.Expiry,
                    onCopy = onCopyField,
                    modifier = Modifier.weight(1f),
                )
                SignalPrismCopyableSecureField(
                    label = "CVV",
                    role = SignalCardSecureFieldRole.Cvv,
                    maskedValue = card.cvvMasked,
                    revealedValue = card.cvv ?: card.cvvMasked,
                    revealState = revealState,
                    allowedToReveal = card.revealPolicy.allowCvvReveal,
                    copyEnabled = card.clipboardPolicy.allowCopyCvv && revealState.revealed,
                    copied = copiedField == SignalCardSecureFieldRole.Cvv,
                    onCopy = onCopyField,
                    modifier = Modifier.weight(1f),
                )
            }
            SignalPrismCopyableSecureField(
                label = "اسم حامل البطاقة",
                role = SignalCardSecureFieldRole.Cardholder,
                maskedValue = card.holderName,
                revealedValue = card.holderName,
                revealState = SignalPrismCardRevealState(revealed = true),
                allowedToReveal = true,
                copyEnabled = card.clipboardPolicy.allowCopyCardholder,
                copied = copiedField == SignalCardSecureFieldRole.Cardholder,
                onCopy = onCopyField,
            )
            card.cardReference?.let { reference ->
                SignalPrismCopyableSecureField(
                    label = "مرجع البطاقة",
                    role = SignalCardSecureFieldRole.CardReference,
                    maskedValue = reference,
                    revealedValue = reference,
                    revealState = SignalPrismCardRevealState(revealed = true),
                    allowedToReveal = true,
                    copyEnabled = true,
                    copied = copiedField == SignalCardSecureFieldRole.CardReference,
                    onCopy = onCopyField,
                )
            }
            Text(
                text = if (revealState.revealed) "إخفاء البيانات" else "إظهار بيانات البطاقة",
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(999.dp)).background(prism.palette.prismCyan.copy(alpha = 0.16f)).clickable(onClick = onToggleReveal).padding(vertical = 12.dp),
                color = prism.palette.prismTextPrimary,
                style = SignalTheme.typography.button,
                maxLines = 1,
            )
        }
    }
}

@Composable
fun SignalPrismCopyableSecureField(
    label: String,
    role: SignalCardSecureFieldRole,
    maskedValue: String,
    revealedValue: String,
    revealState: SignalPrismCardRevealState,
    allowedToReveal: Boolean,
    copyEnabled: Boolean,
    copied: Boolean,
    onCopy: (SignalCardSecureFieldRole, String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    val value = SignalPrismSensitiveCardField(maskedValue, revealedValue, revealState, allowedToReveal)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White.copy(alpha = 0.055f))
            .clickable(enabled = copyEnabled) { onCopy(role, value, label) }
            .padding(SignalSpacing.x2),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(label, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.prismMeta, maxLines = 1)
            Text(
                value,
                color = prism.palette.prismTextPrimary,
                style = SignalTheme.typography.prismIdentifier.copy(
                    textDirection = if (role == SignalCardSecureFieldRole.Cardholder) TextDirection.ContentOrLtr else TextDirection.Ltr,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (copied) {
                Text("تم النسخ", color = prism.palette.prismEmerald, style = SignalTheme.typography.prismStatus, maxLines = 1)
            }
        }
        if (copyEnabled) {
            SignalIcon(SignalIconName.Copy, tint = prism.palette.prismTextSecondary, size = 19.dp)
        } else {
            SignalIcon(SignalIconName.Lock, tint = prism.palette.prismTextMuted, size = 18.dp)
        }
    }
}

@Composable
private fun SignalPrismSensitiveFieldLabel(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.clip(RoundedCornerShape(18.dp)).background(Color.White.copy(alpha = 0.06f)).padding(SignalSpacing.x2), verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(label, color = SignalTheme.prism.palette.prismTextSecondary, style = SignalTheme.typography.statusPill, maxLines = 1)
        Text(
            value,
            color = SignalTheme.prism.palette.prismTextPrimary,
            style = SignalTheme.typography.labelMedium.copy(
                fontFeatureSettings = "tnum",
                textDirection = TextDirection.Ltr,
            ),
            maxLines = 1,
            overflow = TextOverflow.Clip,
        )
    }
}

fun SignalPrismSensitiveCardField(
    masked: String,
    revealed: String,
    revealState: SignalPrismCardRevealState,
    allowed: Boolean,
): String = if (revealState.revealed && allowed) revealed else masked

@Composable
private fun SignalPrismFinancialAmount(
    value: String,
    contentColor: Color = SignalTheme.prism.palette.prismTextPrimary,
    decimalColor: Color = SignalTheme.prism.palette.prismTextPrimary.copy(alpha = 0.62f),
) {
    if (value.startsWith("•")) {
        Text(value, color = contentColor, style = SignalTheme.typography.prismAmountLarge)
        return
    }
    val parts = value.split(" ", limit = 2)
    val number = parts.firstOrNull().orEmpty()
    val currency = parts.getOrNull(1).orEmpty()
    val whole = number.substringBefore(".")
    val decimal = number.substringAfter(".", missingDelimiterValue = "")
    Text(
        buildAnnotatedString {
            append(whole)
            if (decimal.isNotEmpty()) {
                withStyle(SpanStyle(color = decimalColor, fontSize = 19.sp, fontWeight = FontWeight.ExtraBold)) {
                    append(".$decimal")
                }
            }
            if (currency.isNotBlank()) append(" $currency")
        },
        color = contentColor,
        style = SignalTheme.typography.prismAmountLarge.copy(fontFeatureSettings = "tnum", textDirection = TextDirection.Ltr),
        maxLines = 1,
        overflow = TextOverflow.Clip,
        textAlign = TextAlign.End,
    )
}

@Composable
private fun SignalPrismStatusChip(label: String, contentColor: Color = Color.White) {
    Text(
        label,
        color = contentColor,
        style = SignalTheme.typography.prismStatus,
        modifier = Modifier.background(contentColor.copy(alpha = 0.14f), RoundedCornerShape(999.dp)).padding(horizontal = 10.dp, vertical = 5.dp),
        maxLines = 1,
    )
}

@Composable
fun SignalPrismCardArtwork(profile: SignalPrismCardArtworkProfile, modifier: Modifier = Modifier) {
    val accent = cardAccent(profile)
    val content = cardContentColor(profile)
    val structured = profile.backgroundStyle == SignalCardBackgroundStyle.MinimalCorporate || profile.scheme == SignalCardScheme.Amex
    Canvas(modifier = modifier) {
        drawLine(content.copy(alpha = 0.10f), Offset(size.width * 0.10f, size.height * 0.78f), Offset(size.width * 0.44f, size.height * 0.78f), strokeWidth = 2.dp.toPx(), cap = StrokeCap.Round)
        drawLine(accent.copy(alpha = 0.24f), Offset(size.width * 0.62f, size.height * 0.18f), Offset(size.width * 0.90f, size.height * 0.18f), strokeWidth = 2.dp.toPx(), cap = StrokeCap.Round)
        if (structured) {
            repeat(4) { index ->
                val y = size.height * (0.18f + index * 0.16f)
                drawLine(content.copy(alpha = 0.045f), Offset(size.width * 0.08f, y), Offset(size.width * 0.92f, y), strokeWidth = 1.dp.toPx())
            }
        } else if (profile.backgroundStyle == SignalCardBackgroundStyle.SoftGradient) {
            drawCircle(content.copy(alpha = 0.055f), radius = size.minDimension * 0.40f, center = Offset(size.width * 0.02f, size.height * 0.02f))
            drawCircle(accent.copy(alpha = 0.14f), radius = size.minDimension * 0.22f, center = Offset(size.width * 0.88f, size.height * 0.12f))
        }
    }
}

@Composable
fun SignalPrismAccountCardArtwork(
    style: SignalPrismCardStyle,
    modifier: Modifier = Modifier,
    artworkStyle: SignalAccountArtworkStyle = SignalAccountArtworkStyle.NeptunePrism,
    bankPalette: SignalBankPalette = SignalBankPalette(),
) {
    val accent = when (artworkStyle) {
        SignalAccountArtworkStyle.BankBrandFlat,
        SignalAccountArtworkStyle.BankBrandGradient -> bankPalette.secondary
        SignalAccountArtworkStyle.IslamicCalm -> SignalTheme.prism.palette.prismEmerald
        SignalAccountArtworkStyle.WalletEnergy -> SignalTheme.prism.palette.prismCyan
        SignalAccountArtworkStyle.MinimalCorporate -> SignalTheme.prism.palette.prismGold
        SignalAccountArtworkStyle.NeptunePrism -> when (style) {
            SignalPrismCardStyle.IslamicCalm -> SignalTheme.prism.palette.prismEmerald
            SignalPrismCardStyle.CorporateDense -> SignalTheme.prism.palette.prismGold
            else -> SignalTheme.prism.palette.prismCyan
        }
    }
    Canvas(modifier = modifier) {
        drawLine(Color.White.copy(alpha = 0.052f), Offset(size.width * 0.10f, size.height * 0.80f), Offset(size.width * 0.38f, size.height * 0.80f), strokeWidth = 2.dp.toPx(), cap = StrokeCap.Round)
        drawLine(accent.copy(alpha = 0.11f), Offset(size.width * 0.64f, size.height * 0.18f), Offset(size.width * 0.88f, size.height * 0.18f), strokeWidth = 2.dp.toPx(), cap = StrokeCap.Round)
        if (artworkStyle == SignalAccountArtworkStyle.NeptunePrism || artworkStyle == SignalAccountArtworkStyle.WalletEnergy) {
            drawCircle(Color.White.copy(alpha = 0.016f), radius = size.minDimension * 0.32f, center = Offset(size.width * 0.02f, size.height * 0.06f))
        }
    }
}

@Composable
fun SignalPrismCardChip(modifier: Modifier = Modifier, tint: Color = Color.White) {
    Canvas(modifier = modifier.size(38.dp, 28.dp)) {
        drawRoundRect(tint.copy(alpha = 0.74f), Offset.Zero, Size(size.width, size.height), CornerRadius(7.dp.toPx(), 7.dp.toPx()))
        drawLine(Color(0xFF0A2545).copy(alpha = 0.55f), Offset(size.width * 0.18f, size.height * 0.50f), Offset(size.width * 0.82f, size.height * 0.50f), strokeWidth = 1.3.dp.toPx())
        drawLine(Color(0xFF0A2545).copy(alpha = 0.45f), Offset(size.width * 0.42f, size.height * 0.14f), Offset(size.width * 0.42f, size.height * 0.86f), strokeWidth = 1.1.dp.toPx())
    }
}

@Composable
fun SignalPrismContactlessMark(modifier: Modifier = Modifier, tint: Color = Color.White) {
    Canvas(modifier = modifier.size(30.dp)) {
        val stroke = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
        drawArc(tint.copy(alpha = 0.76f), 300f, 120f, false, Offset(size.width * 0.10f, size.height * 0.18f), Size(size.width * 0.38f, size.height * 0.64f), style = stroke)
        drawArc(tint.copy(alpha = 0.58f), 300f, 120f, false, Offset(size.width * 0.25f, size.height * 0.08f), Size(size.width * 0.48f, size.height * 0.84f), style = stroke)
    }
}

@Composable
private fun SignalPrismPanel(
    title: String,
    subtitle: String,
    icon: SignalIconName,
    iconTint: Color,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val prism = SignalTheme.prism
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = prism.surfaces.prismSurfaceRaised.copy(alpha = if (SignalTheme.colors.dark) 0.64f else 0.78f),
        contentColor = prism.palette.prismTextPrimary,
        shape = RoundedCornerShape(28.dp),
        border = BorderStroke(1.dp, prism.overlays.prismBorderSoft.copy(alpha = if (SignalTheme.colors.dark) 0.10f else 0.14f)),
    ) {
        Column(modifier = Modifier.padding(SignalSpacing.x3), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
            Row(horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x3), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.size(40.dp).clip(RoundedCornerShape(16.dp)).background(iconTint.copy(alpha = 0.13f)),
                    contentAlignment = Alignment.Center,
                ) {
                    SignalIcon(icon, tint = iconTint, size = 21.dp)
                }
                Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(title, style = SignalTheme.typography.prismTitleCard, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    Text(subtitle, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.prismMeta, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
            }
            content()
        }
    }
}

@Composable
private fun SignalPrismMetricTile(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    detail: String? = null,
    emphasis: Boolean = false,
) {
    val prism = SignalTheme.prism
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (emphasis) prism.palette.prismCyan.copy(alpha = 0.10f) else Color.White.copy(alpha = 0.055f))
            .padding(SignalSpacing.x2),
        verticalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        Text(label, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.prismMeta, maxLines = 1)
        Text(value, color = prism.palette.prismTextPrimary, style = SignalTheme.typography.prismAmountCompact, maxLines = 1, overflow = TextOverflow.Ellipsis)
        if (detail != null) Text(detail, color = prism.palette.prismTextMuted, style = SignalTheme.typography.prismStatus, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
private fun SignalPrismSectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    action: String? = null,
    onAction: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(top = SignalSpacing.x1),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(title, color = SignalTheme.prism.palette.prismTextPrimary, style = SignalTheme.typography.prismTitleSection, modifier = Modifier.weight(1f), maxLines = 1)
        if (action != null && onAction != null) {
            Text(
                action,
                color = SignalTheme.prism.palette.prismCyan,
                style = SignalTheme.typography.prismStatus,
                modifier = Modifier.clip(RoundedCornerShape(999.dp)).clickable(onClick = onAction).padding(horizontal = 10.dp, vertical = 6.dp),
                maxLines = 1,
            )
        }
    }
}

@Composable
private fun SignalPrismActivityRow(item: SignalPrismAccountActivityItem) {
    val prism = SignalTheme.prism
    val tone = if (item.incoming) prism.palette.prismEmerald else prism.palette.prismTextPrimary
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(prism.surfaces.prismSurfaceRaised.copy(alpha = if (SignalTheme.colors.dark) 0.54f else 0.74f))
            .clickable(onClick = item.onClick)
            .padding(SignalSpacing.x2),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(modifier = Modifier.size(38.dp).clip(RoundedCornerShape(15.dp)).background(tone.copy(alpha = 0.12f)), contentAlignment = Alignment.Center) {
            SignalIcon(if (item.incoming) SignalIconName.Transfer else SignalIconName.Cards, tint = tone, size = 20.dp)
        }
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(item.title, color = prism.palette.prismTextPrimary, style = SignalTheme.typography.rowTitle, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(item.metadata, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.prismMeta, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
        Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(item.amount, color = tone, style = SignalTheme.typography.prismAmountCompact, maxLines = 1)
            item.status?.let { Text(it, color = prism.palette.prismTextMuted, style = SignalTheme.typography.prismStatus, maxLines = 1) }
        }
    }
}

@Composable
private fun SignalPrismCompactActionRow(
    title: String,
    metadata: String,
    icon: SignalIconName,
    onClick: () -> Unit,
) {
    val prism = SignalTheme.prism
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White.copy(alpha = 0.052f))
            .clickable(onClick = onClick)
            .padding(SignalSpacing.x2),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(modifier = Modifier.size(36.dp).clip(RoundedCornerShape(14.dp)).background(prism.palette.prismCyan.copy(alpha = 0.10f)), contentAlignment = Alignment.Center) {
            SignalIcon(icon, tint = prism.palette.prismCyan, size = 19.dp)
        }
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(title, color = prism.palette.prismTextPrimary, style = SignalTheme.typography.rowTitle, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(metadata, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.prismMeta, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Composable
fun SignalPrismCardNetworkMark(network: SignalPrismCardNetwork, modifier: Modifier = Modifier, color: Color = Color.White) {
    when (network) {
        SignalPrismCardNetwork.Mastercard -> Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy((-8).dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(Modifier.size(24.dp).clip(RoundedCornerShape(999.dp)).background(Color(0xFFFF5F45).copy(alpha = 0.94f)))
            Box(Modifier.size(24.dp).clip(RoundedCornerShape(999.dp)).background(Color(0xFFFFB23C).copy(alpha = 0.90f)))
        }
        SignalPrismCardNetwork.Visa -> Text(
            "VISA",
            modifier = modifier,
            color = Color(0xFFFFFFFF),
            style = SignalTheme.typography.prismTitleCard.copy(fontWeight = FontWeight.Black, letterSpacing = 0.7.sp),
            maxLines = 1,
        )
        SignalPrismCardNetwork.Numo -> Text(
            "NUMO",
            modifier = modifier.clip(RoundedCornerShape(999.dp)).background(color.copy(alpha = 0.14f)).padding(horizontal = 9.dp, vertical = 4.dp),
            color = color,
            style = SignalTheme.typography.prismStatus,
            maxLines = 1,
        )
        SignalPrismCardNetwork.Amex -> Box(
            modifier = modifier.clip(RoundedCornerShape(7.dp)).background(Color(0xFFFFFFFF).copy(alpha = 0.92f)).padding(horizontal = 8.dp, vertical = 5.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text("AMEX", color = Color(0xFF185A8D), style = SignalTheme.typography.prismStatus.copy(fontWeight = FontWeight.Black), maxLines = 1)
        }
        SignalPrismCardNetwork.Local -> Text(
            "NPT",
            modifier = modifier,
            color = color,
            style = SignalTheme.typography.prismTitleCard.copy(fontWeight = FontWeight.Bold),
            maxLines = 1,
        )
    }
}

@Composable
private fun signalPrismCardGradient(style: SignalPrismCardStyle): Brush {
    val prism = SignalTheme.prism
    return Brush.linearGradient(
        when (style) {
            SignalPrismCardStyle.ClassicBank -> listOf(prism.palette.prismDeepNavy, prism.palette.prismNight)
            SignalPrismCardStyle.PremiumWallet -> listOf(prism.palette.prismDeepNavy, prism.palette.prismOcean, prism.palette.prismViolet.copy(alpha = 0.88f))
            SignalPrismCardStyle.YouthFintech -> listOf(prism.palette.prismOcean, prism.palette.prismViolet, prism.palette.prismCoral.copy(alpha = 0.86f))
            SignalPrismCardStyle.CorporateDense -> listOf(prism.palette.prismInk, prism.palette.prismDeepNavy)
            SignalPrismCardStyle.IslamicCalm -> listOf(prism.palette.prismDeepNavy, prism.palette.prismEmerald.copy(alpha = 0.74f))
            SignalPrismCardStyle.MerchantEnergy -> listOf(prism.palette.prismOcean, prism.palette.prismCyan.copy(alpha = 0.72f))
        },
    )
}

@Composable
private fun signalPrismCardBrush(profile: SignalPrismCardArtworkProfile): Brush {
    val prism = SignalTheme.prism
    val palette = profile.bankPalette
    return when (profile.backgroundStyle) {
        SignalCardBackgroundStyle.BackendAsset -> Brush.linearGradient(listOf(palette.primary, palette.secondary.copy(alpha = 0.82f)))
        SignalCardBackgroundStyle.Flat -> Brush.linearGradient(listOf(palette.primary, palette.primary))
        SignalCardBackgroundStyle.BankBrand -> Brush.linearGradient(listOf(palette.primary, palette.secondary.copy(alpha = 0.88f)))
        SignalCardBackgroundStyle.FrostedLight -> Brush.linearGradient(listOf(Color(0xFFEFF5FA), Color(0xFFDCE8F2)))
        SignalCardBackgroundStyle.MinimalCorporate -> Brush.linearGradient(listOf(Color(0xFF202733), Color(0xFF121820)))
        SignalCardBackgroundStyle.PremiumDark -> Brush.linearGradient(listOf(prism.palette.prismInk, prism.palette.prismDeepNavy))
        SignalCardBackgroundStyle.SoftGradient -> when (profile.scheme) {
            SignalCardScheme.Visa -> Brush.linearGradient(listOf(Color(0xFF0B4F9E), Color(0xFF123E77)))
            SignalCardScheme.Mastercard -> Brush.linearGradient(listOf(Color(0xFF171B22), Color(0xFF30343C)))
            SignalCardScheme.Numo -> Brush.linearGradient(listOf(palette.primary, palette.secondary.copy(alpha = 0.78f)))
            SignalCardScheme.Amex -> Brush.linearGradient(listOf(Color(0xFF1B5F8C), Color(0xFF0A3555)))
            SignalCardScheme.PrivateLabel -> Brush.linearGradient(listOf(palette.primary, palette.accent.copy(alpha = 0.74f)))
            SignalCardScheme.Unknown -> signalPrismCardGradient(SignalPrismCardStyle.ClassicBank)
        }
    }
}

@Composable
private fun signalPrismAccountBrush(account: SignalPrismAccountCardModel): Brush {
    val prism = SignalTheme.prism
    return when (account.artworkStyle) {
        SignalAccountArtworkStyle.BankBrandFlat -> Brush.linearGradient(listOf(account.bankPalette.primary, account.bankPalette.primary))
        SignalAccountArtworkStyle.BankBrandGradient -> Brush.linearGradient(
            listOf(
                account.bankPalette.primary,
                account.bankPalette.secondary.copy(alpha = 0.50f),
                account.bankPalette.accent.copy(alpha = 0.22f),
            ),
        )
        SignalAccountArtworkStyle.MinimalCorporate -> Brush.linearGradient(listOf(prism.palette.prismInk, prism.palette.prismDeepNavy))
        SignalAccountArtworkStyle.IslamicCalm -> Brush.linearGradient(listOf(prism.palette.prismDeepNavy, prism.palette.prismEmerald.copy(alpha = 0.58f)))
        SignalAccountArtworkStyle.WalletEnergy -> Brush.linearGradient(listOf(prism.palette.prismDeepNavy, prism.palette.prismOcean, prism.palette.prismCyan.copy(alpha = 0.34f)))
        SignalAccountArtworkStyle.NeptunePrism -> signalPrismCardGradient(account.style)
    }
}

@Composable
private fun SignalPrismCardNetwork.toArtworkProfile(style: SignalPrismCardStyle): SignalPrismCardArtworkProfile {
    val personality = when (style) {
        SignalPrismCardStyle.ClassicBank -> ly.neptune.signal.theme.SignalPrismPersonality.ClassicBank
        SignalPrismCardStyle.PremiumWallet -> ly.neptune.signal.theme.SignalPrismPersonality.PremiumWallet
        SignalPrismCardStyle.YouthFintech -> ly.neptune.signal.theme.SignalPrismPersonality.YouthFintech
        SignalPrismCardStyle.CorporateDense -> ly.neptune.signal.theme.SignalPrismPersonality.CorporateDense
        SignalPrismCardStyle.IslamicCalm -> ly.neptune.signal.theme.SignalPrismPersonality.IslamicCalm
        SignalPrismCardStyle.MerchantEnergy -> ly.neptune.signal.theme.SignalPrismPersonality.MerchantEnergy
    }
    return when (this) {
        SignalPrismCardNetwork.Visa -> SignalPrismCardArtworkProfile(SignalCardScheme.Visa, personality, backgroundStyle = SignalCardBackgroundStyle.SoftGradient, markStyle = SignalCardMarkStyle.SimplifiedScheme)
        SignalPrismCardNetwork.Mastercard -> SignalPrismCardArtworkProfile(SignalCardScheme.Mastercard, personality, backgroundStyle = SignalCardBackgroundStyle.PremiumDark)
        SignalPrismCardNetwork.Numo -> SignalPrismCardArtworkProfile(SignalCardScheme.Numo, personality, backgroundStyle = SignalCardBackgroundStyle.BankBrand)
        SignalPrismCardNetwork.Amex -> SignalPrismCardArtworkProfile(SignalCardScheme.Amex, personality, backgroundStyle = SignalCardBackgroundStyle.SoftGradient, markStyle = SignalCardMarkStyle.SimplifiedScheme)
        SignalPrismCardNetwork.Local -> SignalPrismCardArtworkProfile(SignalCardScheme.PrivateLabel, personality, backgroundStyle = SignalCardBackgroundStyle.BankBrand)
    }
}

@Composable
private fun cardContentColor(profile: SignalPrismCardArtworkProfile): Color = when (profile.backgroundStyle) {
    SignalCardBackgroundStyle.FrostedLight -> Color.White
    SignalCardBackgroundStyle.BackendAsset -> profile.bankPalette.onSurface
    SignalCardBackgroundStyle.SoftGradient -> Color.White
    else -> Color.White
}

@Composable
private fun cardAccent(profile: SignalPrismCardArtworkProfile): Color = when (profile.scheme) {
    SignalCardScheme.Visa -> Color(0xFF226ED8)
    SignalCardScheme.Mastercard -> Color(0xFFFF8A3D)
    SignalCardScheme.Numo -> profile.bankPalette.secondary
    SignalCardScheme.Amex -> Color(0xFF2F6F91)
    SignalCardScheme.PrivateLabel -> profile.bankPalette.accent
    SignalCardScheme.Unknown -> SignalTheme.prism.palette.prismCyan
}

private fun requestIcon(type: SignalAccountRequestType): SignalIconName = when (type) {
    SignalAccountRequestType.RequestCard -> SignalIconName.Cards
    SignalAccountRequestType.CertifiedCheque,
    SignalAccountRequestType.ChequeBook,
    SignalAccountRequestType.StatementCertificate,
    SignalAccountRequestType.BalanceCertificate -> SignalIconName.Copy
    SignalAccountRequestType.Dispute,
    SignalAccountRequestType.Closure -> SignalIconName.Support
}

@Composable
private fun cardStatusTone(status: SignalPrismCardStatus): Color = when (status) {
    SignalPrismCardStatus.Active,
    SignalPrismCardStatus.VirtualOnly -> SignalTheme.prism.palette.prismEmerald
    SignalPrismCardStatus.Frozen,
    SignalPrismCardStatus.PendingActivation,
    SignalPrismCardStatus.PhysicalShipping -> SignalTheme.prism.palette.prismGold
    SignalPrismCardStatus.Blocked,
    SignalPrismCardStatus.Expired,
    SignalPrismCardStatus.LostOrStolen -> SignalTheme.prism.palette.prismCoral
}

private fun cardStatusLabel(status: SignalPrismCardStatus): String = when (status) {
    SignalPrismCardStatus.Active -> "نشطة"
    SignalPrismCardStatus.Frozen -> "مجمّدة"
    SignalPrismCardStatus.Blocked -> "موقوفة"
    SignalPrismCardStatus.Expired -> "منتهية"
    SignalPrismCardStatus.PendingActivation -> "بانتظار التفعيل"
    SignalPrismCardStatus.VirtualOnly -> "افتراضية"
    SignalPrismCardStatus.PhysicalShipping -> "قيد الشحن"
    SignalPrismCardStatus.LostOrStolen -> "مفقودة"
}

private fun SignalPrismCardNetwork.schemeLabel(): String = when (this) {
    SignalPrismCardNetwork.Mastercard -> "Mastercard"
    SignalPrismCardNetwork.Visa -> "VISA"
    SignalPrismCardNetwork.Numo -> "NUMO"
    SignalPrismCardNetwork.Amex -> "AMEX"
    SignalPrismCardNetwork.Local -> "NPT"
}

private fun cardPresentationModeLabel(mode: SignalCardPresentationMode): String = when (mode) {
    SignalCardPresentationMode.Carousel -> "بطاقات قابلة للسحب"
    SignalCardPresentationMode.List -> "قائمة واضحة"
    SignalCardPresentationMode.CompactStack -> "عرض مضغوط"
}

@Composable
private fun SignalPrismMaskControl(masked: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(42.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White.copy(alpha = 0.12f))
            .semantics {
                role = Role.Button
                contentDescription = if (masked) "Show balance" else "Hide balance"
            }
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        SignalIcon(
            name = if (masked) SignalIconName.EyeOff else SignalIconName.Eye,
            tint = Color.White,
            size = 21.dp,
        )
    }
}

@Composable
private fun SignalPrismCarouselDots(
    count: Int,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    onPrimary: Boolean = false,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(count) { index ->
            val active = index == selectedIndex.coerceIn(0, (count - 1).coerceAtLeast(0))
            Box(
                modifier = Modifier
                    .padding(horizontal = 3.dp)
                    .size(width = if (active) 24.dp else 7.dp, height = 7.dp)
                    .clip(RoundedCornerShape(999.dp))
                    .background(
                        if (active) {
                            SignalTheme.prism.palette.prismCyan
                        } else if (onPrimary) {
                            Color.White.copy(alpha = 0.30f)
                        } else {
                            SignalTheme.colors.onSurfaceVariant.copy(alpha = 0.28f)
                        },
                    ),
            )
        }
    }
}
