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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ly.neptune.signal.theme.SignalGradientIntensity
import ly.neptune.signal.theme.SignalMotionIntensity
import ly.neptune.signal.theme.SignalPrismCardStyle
import ly.neptune.signal.theme.SignalPrismNavStyle
import ly.neptune.signal.theme.SignalPrismPersonality
import ly.neptune.signal.theme.SignalSpacing
import ly.neptune.signal.theme.SignalTheme

enum class SignalPrismMood {
    Neptune,
    Ocean,
    Emerald,
    Graphite,
    Midnight,
    Slate,
    Arctic,
    Sand,
    Aurora,
}

enum class SignalPrismHomeLayout {
    DailyCockpit,
    AccountFirst,
    FeedFirst,
    Compact,
}

enum class SignalPrismPaymentActionStyle {
    Wallet,
    Transfer,
    ScanPay,
}

enum class SignalPrismPaymentActionEmphasis {
    Standard,
    Compact,
    Elevated,
}

enum class SignalPrismTypographyPreference {
    Standard,
    Larger,
    Reading,
}

enum class SignalPrismDensityPreference {
    Comfortable,
    Compact,
    Operational,
}

enum class SignalPrismMotionPreference {
    Full,
    Calm,
    Reduced,
}

enum class SignalPrismSoundPreference {
    On,
    Quiet,
    Off,
}

enum class SignalPrismHapticPreference {
    On,
    Off,
}

@Immutable
data class SignalPrismUserPersonalizationPolicy(
    val allowAppearanceMode: Boolean = true,
    val allowMood: Boolean = true,
    val allowHomeLayout: Boolean = true,
    val allowCardPresentationMode: Boolean = true,
    val allowNavStyle: Boolean = false,
    val allowPaymentActionStyle: Boolean = false,
    val allowDensity: Boolean = true,
    val allowTypographyScale: Boolean = true,
    val allowMotionIntensity: Boolean = true,
    val allowSoundFeedback: Boolean = true,
    val allowHaptics: Boolean = true,
    val allowCampaignPersonalization: Boolean = false,
)

@Immutable
data class SignalPrismBankExperiencePolicy(
    val bankId: String,
    val brandName: String,
    val logoUrl: String? = null,
    val palette: SignalBankPalette,
    val defaultPersonality: SignalPrismPersonality,
    val allowedUserMoods: Set<SignalPrismMood>,
    val allowedCardModes: Set<SignalCardPresentationMode>,
    val allowedNavStyles: Set<SignalPrismNavStyle>,
    val defaultHomeLayout: SignalPrismHomeLayout,
    val defaultAppearanceLabel: String,
    val gradientIntensity: SignalGradientIntensity,
    val motionIntensity: SignalMotionIntensity,
    val cardStyle: SignalPrismCardStyle,
    val campaignStyleLabel: String,
    val userPersonalizationPolicy: SignalPrismUserPersonalizationPolicy,
)

@Immutable
data class SignalPrismResolvedExperience(
    val mood: SignalPrismMood,
    val homeLayout: SignalPrismHomeLayout,
    val cardPresentationMode: SignalCardPresentationMode,
    val navStyle: SignalPrismNavStyle,
    val typography: SignalPrismTypographyPreference,
    val density: SignalPrismDensityPreference,
    val motion: SignalPrismMotionPreference,
    val sound: SignalPrismSoundPreference,
    val haptics: SignalPrismHapticPreference,
)

object SignalPrismExperienceResolver {
    fun resolve(
        bankPolicy: SignalPrismBankExperiencePolicy,
        mood: SignalPrismMood,
        homeLayout: SignalPrismHomeLayout,
        cardMode: SignalCardPresentationMode,
        navStyle: SignalPrismNavStyle,
        typography: SignalPrismTypographyPreference,
        density: SignalPrismDensityPreference,
        motion: SignalPrismMotionPreference,
        sound: SignalPrismSoundPreference,
        haptics: SignalPrismHapticPreference,
    ): SignalPrismResolvedExperience =
        SignalPrismResolvedExperience(
            mood = mood.takeIf { it in bankPolicy.allowedUserMoods } ?: SignalPrismMood.Neptune,
            homeLayout = homeLayout,
            cardPresentationMode = cardMode.takeIf { it in bankPolicy.allowedCardModes } ?: SignalCardPresentationMode.List,
            navStyle = navStyle.takeIf { it in bankPolicy.allowedNavStyles } ?: SignalPrismNavStyle.FloatingDock,
            typography = typography,
            density = density,
            motion = motion,
            sound = sound,
            haptics = haptics,
        )
}

@Composable
fun SignalPrismLivePreview(
    bankName: String,
    moodLabel: String,
    appearanceLabel: String,
    cardModeLabel: String,
    homeLayoutLabel: String,
    primaryColor: Color,
    accentColor: Color,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = prism.surfaces.prismSurfaceRaised.copy(alpha = if (SignalTheme.colors.dark) 0.72f else 0.88f),
        contentColor = prism.palette.prismTextPrimary,
        shape = RoundedCornerShape(32.dp),
        border = BorderStroke(1.dp, prism.overlays.prismBorderSoft.copy(alpha = 0.12f)),
    ) {
        Column(modifier = Modifier.padding(SignalSpacing.x3), verticalArrangement = Arrangement.spacedBy(SignalSpacing.x3)) {
            SignalPrismSectionHeader("معاينة مباشرة", "شاهد كيف ستظهر تجربتك المالية")
            SignalPrismMiniHomePreview(bankName, moodLabel, appearanceLabel, homeLayoutLabel, primaryColor, accentColor)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2)) {
                SignalPrismMiniCardPreview(cardModeLabel, primaryColor, Modifier.weight(1f))
                SignalPrismMiniNavPreview(accentColor, Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun SignalPrismOptionTile(
    title: String,
    subtitle: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    locked: Boolean = false,
    swatch: Color? = null,
    icon: SignalIconName? = null,
    disabledReason: String = "محدد من المصرف",
) {
    val prism = SignalTheme.prism
    val borderColor = when {
        selected -> prism.tones.payment.copy(alpha = 0.50f)
        locked -> prism.overlays.prismBorderSoft.copy(alpha = 0.08f)
        else -> prism.overlays.prismBorderSoft.copy(alpha = 0.14f)
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(
                when {
                    selected -> prism.surfaces.prismSurfaceFloating.copy(alpha = 0.78f)
                    else -> prism.surfaces.prismSurfaceRaised.copy(alpha = if (SignalTheme.colors.dark) 0.50f else 0.78f)
                },
            )
            .border(1.dp, borderColor, RoundedCornerShape(24.dp))
            .clickable(enabled = !locked, onClick = onClick)
            .padding(SignalSpacing.x2),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(16.dp))
                .background((swatch ?: prism.tones.payment).copy(alpha = if (locked) 0.10f else 0.18f)),
            contentAlignment = Alignment.Center,
        ) {
            if (icon != null) SignalIcon(icon, tint = swatch ?: prism.tones.payment, size = 21.dp)
            if (icon == null && swatch != null) Box(Modifier.size(18.dp).clip(RoundedCornerShape(999.dp)).background(swatch))
        }
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(title, color = prism.palette.prismTextPrimary, style = SignalTheme.typography.prismTitleCard, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(if (locked) disabledReason else subtitle, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.prismMeta, maxLines = 2, overflow = TextOverflow.Ellipsis)
        }
        if (locked) SignalIcon(SignalIconName.Lock, tint = prism.palette.prismTextMuted, size = 18.dp)
        if (!locked && selected) SignalIcon(SignalIconName.CheckCircle, tint = prism.palette.prismEmerald, size = 19.dp)
    }
}

@Composable
fun SignalPrismVisualChoiceTile(
    title: String,
    subtitle: String,
    selected: Boolean,
    onClick: () -> Unit,
    icon: SignalIconName,
    colors: List<Color>,
    modifier: Modifier = Modifier,
    locked: Boolean = false,
    disabledReason: String = "محدد من المصرف",
) {
    val prism = SignalTheme.prism
    val shape = RoundedCornerShape(28.dp)
    val tileColors = colors.ifEmpty { listOf(prism.palette.prismDeepNavy, prism.palette.prismCyan) }
    val swatchColor = tileColors.lastOrNull() ?: prism.tones.payment
    val surfaceAlpha = when {
        selected -> if (SignalTheme.colors.dark) 0.86f else 0.96f
        else -> if (SignalTheme.colors.dark) 0.58f else 0.88f
    }
    Column(
        modifier = modifier
            .clip(shape)
            .background(prism.surfaces.prismSurfaceRaised.copy(alpha = surfaceAlpha))
            .border(
                width = 1.dp,
                color = when {
                    locked -> prism.overlays.prismBorderSoft.copy(alpha = 0.08f)
                    selected -> tileColors.last().copy(alpha = 0.58f)
                    else -> prism.overlays.prismBorderSoft.copy(alpha = 0.13f)
                },
                shape = shape,
            )
            .clickable(enabled = !locked, onClick = onClick)
            .padding(SignalSpacing.x2),
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(74.dp)
                .clip(RoundedCornerShape(22.dp))
                .background(swatchColor.copy(alpha = if (SignalTheme.colors.dark) 0.20f else 0.14f)),
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp)
                    .size(34.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(swatchColor.copy(alpha = 0.20f)),
                contentAlignment = Alignment.Center,
            ) {
                SignalIcon(icon, tint = swatchColor, size = 19.dp)
            }
            if (selected) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                        .size(26.dp)
                        .clip(RoundedCornerShape(999.dp))
                        .background(Color.White.copy(alpha = 0.20f)),
                    contentAlignment = Alignment.Center,
                ) {
                    SignalIcon(SignalIconName.CheckCircle, tint = Color.White, size = 16.dp)
                }
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            Text(title, color = prism.palette.prismTextPrimary, style = SignalTheme.typography.prismTitleCard, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(if (locked) disabledReason else subtitle, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.prismMeta, maxLines = 2, overflow = TextOverflow.Ellipsis)
        }
        if (locked) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                SignalIcon(SignalIconName.Lock, tint = prism.palette.prismTextMuted, size = 15.dp)
                Text(disabledReason, color = prism.palette.prismTextMuted, style = SignalTheme.typography.prismStatus, maxLines = 1)
            }
        }
    }
}

@Composable
fun SignalPrismVisualChoiceRow(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
    ) {
        content()
    }
}

@Composable
fun SignalPrismOptionGrid(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
    ) {
        content()
    }
}

@Composable
fun SignalPrismSegmentedSelector(
    options: List<String>,
    selectedIndex: Int,
    onSelectedIndexChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val prism = SignalTheme.prism
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(999.dp))
            .background(prism.surfaces.prismSurfaceRaised.copy(alpha = 0.68f))
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        options.forEachIndexed { index, option ->
            Text(
                option,
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(999.dp))
                    .background(if (selectedIndex == index) prism.tones.paymentContainer else Color.Transparent)
                    .clickable { onSelectedIndexChange(index) }
                    .padding(horizontal = 8.dp, vertical = 9.dp),
                color = if (selectedIndex == index) prism.tones.onPaymentContainer else prism.palette.prismTextSecondary,
                style = SignalTheme.typography.prismStatus,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun SignalPrismMiniHomePreview(
    bankName: String,
    moodLabel: String,
    appearanceLabel: String,
    homeLayoutLabel: String,
    primaryColor: Color,
    accentColor: Color,
) {
    val prism = SignalTheme.prism
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(26.dp))
            .background(prism.surfaces.prismSurface.copy(alpha = if (SignalTheme.colors.dark) 0.70f else 0.86f))
            .padding(SignalSpacing.x2),
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x2),
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
                Text(bankName, color = prism.palette.prismTextPrimary, style = SignalTheme.typography.prismTitleCard, maxLines = 1)
                Text("$moodLabel · $appearanceLabel", color = prism.palette.prismTextSecondary, style = SignalTheme.typography.prismMeta, maxLines = 1)
            }
            Text(
                homeLayoutLabel,
                color = accentColor,
                style = SignalTheme.typography.prismStatus,
                modifier = Modifier
                    .clip(RoundedCornerShape(999.dp))
                    .background(accentColor.copy(alpha = 0.14f))
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                maxLines = 1,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(92.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(primaryColor),
        ) {
            Column(modifier = Modifier.align(Alignment.CenterEnd).padding(SignalSpacing.x2), horizontalAlignment = Alignment.End) {
                Text("الرصيد المتاح", color = Color.White.copy(alpha = 0.74f), style = SignalTheme.typography.prismMeta)
                Text("1,000,000.000 د.ل", color = Color.White, style = SignalTheme.typography.prismAmountCompact)
            }
        }
    }
}

@Composable
private fun SignalPrismMiniCardPreview(cardModeLabel: String, primaryColor: Color, modifier: Modifier = Modifier) {
    val prism = SignalTheme.prism
    Column(
        modifier = modifier.clip(RoundedCornerShape(22.dp)).background(prism.surfaces.prismSurface.copy(alpha = 0.66f)).padding(SignalSpacing.x2),
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x1),
    ) {
        Text("البطاقات", color = prism.palette.prismTextPrimary, style = SignalTheme.typography.prismStatus)
        Box(Modifier.fillMaxWidth().height(54.dp).clip(RoundedCornerShape(16.dp)).background(primaryColor.copy(alpha = 0.82f)))
        Text(cardModeLabel, color = prism.palette.prismTextSecondary, style = SignalTheme.typography.prismMeta, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}

@Composable
private fun SignalPrismMiniNavPreview(accentColor: Color, modifier: Modifier = Modifier) {
    val prism = SignalTheme.prism
    Column(
        modifier = modifier.clip(RoundedCornerShape(22.dp)).background(prism.surfaces.prismSurface.copy(alpha = 0.66f)).padding(SignalSpacing.x2),
        verticalArrangement = Arrangement.spacedBy(SignalSpacing.x1),
    ) {
        Text("التنقل", color = prism.palette.prismTextPrimary, style = SignalTheme.typography.prismStatus)
        Row(
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(999.dp)).background(prism.surfaces.prismSurfaceFloating).padding(7.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(2) { Box(Modifier.size(12.dp).clip(RoundedCornerShape(999.dp)).background(prism.palette.prismTextMuted.copy(alpha = 0.44f))) }
            Box(Modifier.size(32.dp).clip(RoundedCornerShape(999.dp)).background(accentColor))
            repeat(2) { Box(Modifier.size(12.dp).clip(RoundedCornerShape(999.dp)).background(prism.palette.prismTextMuted.copy(alpha = 0.44f))) }
        }
        Text("Prism Floating Dock", color = prism.palette.prismTextSecondary, style = SignalTheme.typography.prismMeta, maxLines = 1)
    }
}

@Composable
private fun SignalPrismSectionHeader(title: String, subtitle: String) {
    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        Text(title, color = SignalTheme.prism.palette.prismTextPrimary, style = SignalTheme.typography.prismTitleSection, maxLines = 1)
        Text(subtitle, color = SignalTheme.prism.palette.prismTextSecondary, style = SignalTheme.typography.prismMeta, maxLines = 2, overflow = TextOverflow.Ellipsis)
    }
}
