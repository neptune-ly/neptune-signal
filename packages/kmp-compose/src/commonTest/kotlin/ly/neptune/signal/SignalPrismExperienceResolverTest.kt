package ly.neptune.signal

import kotlin.test.Test
import kotlin.test.assertEquals
import ly.neptune.signal.components.SignalCardPresentationMode
import ly.neptune.signal.prism.SignalPrismAccessibilityState
import ly.neptune.signal.prism.SignalPrismBankExperiencePolicy
import ly.neptune.signal.prism.SignalPrismExperienceResolver
import ly.neptune.signal.prism.SignalPrismHapticPreference
import ly.neptune.signal.prism.SignalPrismMood
import ly.neptune.signal.prism.SignalPrismMotionPreference
import ly.neptune.signal.prism.SignalPrismSoundPreference
import ly.neptune.signal.prism.SignalPrismUserPreference
import ly.neptune.signal.prism.SignalPrismUserPersonalizationPolicy
import ly.neptune.signal.theme.SignalPrismNavStyle

class SignalPrismExperienceResolverTest {
    @Test
    fun rejectsUserChoicesOutsideBankPolicy() {
        val policy = SignalPrismBankExperiencePolicy(
            bankId = "classic-bank",
            brandName = "Classic Bank",
            allowedUserMoods = setOf(SignalPrismMood.Graphite),
            allowedCardModes = setOf(SignalCardPresentationMode.List),
            allowedNavStyles = setOf(SignalPrismNavStyle.ClassicDock),
        )

        val resolved = SignalPrismExperienceResolver.resolve(
            bankPolicy = policy,
            userPreference = SignalPrismUserPreference(
                mood = SignalPrismMood.Aurora,
                cardPresentationMode = SignalCardPresentationMode.Carousel,
                navStyle = SignalPrismNavStyle.PaymentForward,
            ),
        )

        assertEquals(SignalPrismMood.Graphite, resolved.mood)
        assertEquals(SignalCardPresentationMode.List, resolved.cardPresentationMode)
        assertEquals(SignalPrismNavStyle.ClassicDock, resolved.navStyle)
    }

    @Test
    fun accessibilityGuardrailOverridesMotionSoundAndHaptics() {
        val resolved = SignalPrismExperienceResolver.resolve(
            bankPolicy = SignalPrismBankExperiencePolicy(
                bankId = "neptune",
                brandName = "Neptune",
            ),
            userPreference = SignalPrismUserPreference(
                motion = SignalPrismMotionPreference.Full,
                sound = SignalPrismSoundPreference.On,
                haptics = SignalPrismHapticPreference.On,
            ),
            accessibility = SignalPrismAccessibilityState(
                forceReducedMotion = true,
                forceReducedFeedback = true,
            ),
        )

        assertEquals(SignalPrismMotionPreference.Reduced, resolved.motion)
        assertEquals(SignalPrismSoundPreference.Off, resolved.sound)
        assertEquals(SignalPrismHapticPreference.Off, resolved.haptics)
    }

    @Test
    fun disabledPersonalizationUsesBankDefaults() {
        val resolved = SignalPrismExperienceResolver.resolve(
            bankPolicy = SignalPrismBankExperiencePolicy(
                bankId = "locked-bank",
                brandName = "Locked Bank",
                allowedUserMoods = setOf(SignalPrismMood.Emerald),
                userPersonalizationPolicy = SignalPrismUserPersonalizationPolicy(
                    allowMood = false,
                    allowCardPresentationMode = false,
                    allowNavStyle = false,
                ),
            ),
            userPreference = SignalPrismUserPreference(
                mood = SignalPrismMood.Aurora,
                cardPresentationMode = SignalCardPresentationMode.List,
                navStyle = SignalPrismNavStyle.DenseOperational,
            ),
        )

        assertEquals(SignalPrismMood.Emerald, resolved.mood)
        assertEquals(SignalCardPresentationMode.List, resolved.cardPresentationMode)
        assertEquals(SignalPrismNavStyle.FloatingDock, resolved.navStyle)
    }
}
