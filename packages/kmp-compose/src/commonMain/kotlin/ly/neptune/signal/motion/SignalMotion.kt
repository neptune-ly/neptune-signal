package ly.neptune.signal.motion

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween

object SignalMotion {
    const val PressMs = 160
    const val RouteMs = 240
    const val OnboardingSnapMs = 240
    const val OnboardingIndicatorMs = 160
    const val AuthHandoffMs = 320
    const val ImeChoreographyMs = 180
    const val ContainerTransformMs = 460
    const val ContainerChromeMs = 280
    const val ContainerContentDelayMs = 270
    const val ResultMs = 320
    const val PaymentRitualRingSweepMs = 1820
    const val PaymentRitualPulseMs = 1260
    const val PaymentRitualRoutePulseMs = 1480
    const val PaymentRitualBreatheMs = 1320
    const val PaymentRitualTerminalScaleMs = 260
    const val SplashOrbitMs = 4200
    const val SplashPulseMs = 1600

    val StandardEasing = CubicBezierEasing(0.2f, 0.8f, 0.2f, 1f)
    val EmphasizedEasing = CubicBezierEasing(0.16f, 1f, 0.3f, 1f)
    val AccountOpenEasing = CubicBezierEasing(0.16f, 1f, 0.3f, 1f)

    fun <T> routeSpec() = tween<T>(durationMillis = RouteMs, easing = StandardEasing)

    fun <T> containerTransformSpec() =
        tween<T>(durationMillis = ContainerTransformMs, easing = AccountOpenEasing)

    fun <T> cardContainerTransformSpec() =
        tween<T>(durationMillis = ContainerTransformMs, easing = AccountOpenEasing)

    fun <T> containerChromeSpec() =
        tween<T>(durationMillis = ContainerChromeMs, easing = StandardEasing)
}
