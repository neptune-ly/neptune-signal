package ly.neptune.signal.prism

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import ly.neptune.signal.components.SignalCardPresentationMode
import ly.neptune.signal.theme.SignalGradientIntensity
import ly.neptune.signal.theme.SignalMotionIntensity
import ly.neptune.signal.theme.SignalPrismAppearance
import ly.neptune.signal.theme.SignalPrismCardStyle
import ly.neptune.signal.theme.SignalPrismNavStyle
import ly.neptune.signal.theme.SignalPrismPersonality

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
data class SignalBankPalette(
    val primary: Color = Color(0xFF0B2D4D),
    val secondary: Color = Color(0xFF18C5E8),
    val accent: Color = Color(0xFFFF7A59),
    val surface: Color = Color(0xFF06111E),
    val onSurface: Color = Color.White,
)

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
    val palette: SignalBankPalette = SignalBankPalette(),
    val defaultPersonality: SignalPrismPersonality = SignalPrismPersonality.PremiumWallet,
    val allowedUserMoods: Set<SignalPrismMood> = setOf(SignalPrismMood.Neptune),
    val allowedCardModes: Set<SignalCardPresentationMode> = setOf(SignalCardPresentationMode.List),
    val allowedNavStyles: Set<SignalPrismNavStyle> = setOf(SignalPrismNavStyle.FloatingDock),
    val defaultHomeLayout: SignalPrismHomeLayout = SignalPrismHomeLayout.DailyCockpit,
    val defaultAppearance: SignalPrismAppearance = SignalPrismAppearance.Dark,
    val gradientIntensity: SignalGradientIntensity = SignalGradientIntensity.Controlled,
    val motionIntensity: SignalMotionIntensity = SignalMotionIntensity.Fluid,
    val cardStyle: SignalPrismCardStyle = SignalPrismCardStyle.GradientHero,
    val userPersonalizationPolicy: SignalPrismUserPersonalizationPolicy = SignalPrismUserPersonalizationPolicy(),
)

@Immutable
data class SignalPrismUserPreference(
    val appearance: SignalPrismAppearance? = null,
    val mood: SignalPrismMood? = null,
    val homeLayout: SignalPrismHomeLayout? = null,
    val cardPresentationMode: SignalCardPresentationMode? = null,
    val navStyle: SignalPrismNavStyle? = null,
    val paymentActionStyle: SignalPrismPaymentActionStyle? = null,
    val paymentActionEmphasis: SignalPrismPaymentActionEmphasis? = null,
    val density: SignalPrismDensityPreference? = null,
    val typography: SignalPrismTypographyPreference? = null,
    val motion: SignalPrismMotionPreference? = null,
    val sound: SignalPrismSoundPreference? = null,
    val haptics: SignalPrismHapticPreference? = null,
)

@Immutable
data class SignalPrismAccessibilityState(
    val forceReducedMotion: Boolean = false,
    val forceReducedFeedback: Boolean = false,
    val highContrastText: Boolean = false,
)

@Immutable
data class SignalPrismComponentPolicy(
    val allowCampaigns: Boolean = true,
    val allowSensitiveReveal: Boolean = true,
    val allowSecureCopy: Boolean = true,
    val requireReadableGradientOverlays: Boolean = true,
)

@Immutable
data class SignalPrismModulePolicy(
    val enabledModules: Set<String> = emptySet(),
    val lockedModules: Set<String> = emptySet(),
)

@Immutable
data class SignalPrismResolvedExperience(
    val appearance: SignalPrismAppearance,
    val mood: SignalPrismMood,
    val homeLayout: SignalPrismHomeLayout,
    val cardPresentationMode: SignalCardPresentationMode,
    val navStyle: SignalPrismNavStyle,
    val paymentActionStyle: SignalPrismPaymentActionStyle,
    val paymentActionEmphasis: SignalPrismPaymentActionEmphasis,
    val typography: SignalPrismTypographyPreference,
    val density: SignalPrismDensityPreference,
    val motion: SignalPrismMotionPreference,
    val sound: SignalPrismSoundPreference,
    val haptics: SignalPrismHapticPreference,
    val componentPolicy: SignalPrismComponentPolicy = SignalPrismComponentPolicy(),
    val modulePolicy: SignalPrismModulePolicy = SignalPrismModulePolicy(),
)

object SignalPrismExperienceResolver {
    fun resolve(
        bankPolicy: SignalPrismBankExperiencePolicy,
        userPreference: SignalPrismUserPreference = SignalPrismUserPreference(),
        accessibility: SignalPrismAccessibilityState = SignalPrismAccessibilityState(),
        componentPolicy: SignalPrismComponentPolicy = SignalPrismComponentPolicy(),
        modulePolicy: SignalPrismModulePolicy = SignalPrismModulePolicy(),
    ): SignalPrismResolvedExperience {
        val personalization = bankPolicy.userPersonalizationPolicy
        val motion = when {
            accessibility.forceReducedMotion -> SignalPrismMotionPreference.Reduced
            personalization.allowMotionIntensity -> userPreference.motion ?: bankPolicy.motionIntensity.toPreference()
            else -> bankPolicy.motionIntensity.toPreference()
        }
        val sound = when {
            accessibility.forceReducedFeedback -> SignalPrismSoundPreference.Off
            personalization.allowSoundFeedback -> userPreference.sound ?: SignalPrismSoundPreference.On
            else -> SignalPrismSoundPreference.Off
        }
        val haptics = when {
            accessibility.forceReducedFeedback -> SignalPrismHapticPreference.Off
            personalization.allowHaptics -> userPreference.haptics ?: SignalPrismHapticPreference.On
            else -> SignalPrismHapticPreference.Off
        }

        return SignalPrismResolvedExperience(
            appearance = userPreference.appearance
                .takeIf { personalization.allowAppearanceMode }
                ?: bankPolicy.defaultAppearance,
            mood = userPreference.mood
                .takeIf { personalization.allowMood && it in bankPolicy.allowedUserMoods }
                ?: bankPolicy.allowedUserMoods.firstOrNull()
                ?: SignalPrismMood.Neptune,
            homeLayout = userPreference.homeLayout
                .takeIf { personalization.allowHomeLayout }
                ?: bankPolicy.defaultHomeLayout,
            cardPresentationMode = userPreference.cardPresentationMode
                .takeIf { personalization.allowCardPresentationMode && it in bankPolicy.allowedCardModes }
                ?: bankPolicy.allowedCardModes.firstOrNull()
                ?: SignalCardPresentationMode.List,
            navStyle = userPreference.navStyle
                .takeIf { personalization.allowNavStyle && it in bankPolicy.allowedNavStyles }
                ?: bankPolicy.allowedNavStyles.firstOrNull()
                ?: SignalPrismNavStyle.FloatingDock,
            paymentActionStyle = userPreference.paymentActionStyle
                .takeIf { personalization.allowPaymentActionStyle }
                ?: SignalPrismPaymentActionStyle.Wallet,
            paymentActionEmphasis = userPreference.paymentActionEmphasis
                .takeIf { personalization.allowPaymentActionStyle }
                ?: SignalPrismPaymentActionEmphasis.Standard,
            typography = userPreference.typography
                .takeIf { personalization.allowTypographyScale }
                ?: SignalPrismTypographyPreference.Standard,
            density = userPreference.density
                .takeIf { personalization.allowDensity }
                ?: SignalPrismDensityPreference.Comfortable,
            motion = motion,
            sound = sound,
            haptics = haptics,
            componentPolicy = componentPolicy,
            modulePolicy = modulePolicy,
        )
    }

    private fun SignalMotionIntensity.toPreference(): SignalPrismMotionPreference = when (this) {
        SignalMotionIntensity.Reduced -> SignalPrismMotionPreference.Reduced
        SignalMotionIntensity.Calm -> SignalPrismMotionPreference.Calm
        SignalMotionIntensity.Fluid,
        SignalMotionIntensity.Expressive,
        -> SignalPrismMotionPreference.Full
    }
}
