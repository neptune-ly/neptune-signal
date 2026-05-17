package ly.neptune.signal.feedback

import androidx.compose.runtime.Immutable

enum class SignalFeedbackRole {
    Prepare,
    Verify,
    Pending,
    Success,
    Failure,
    Cancel,
    Copy,
    Reveal,
    Navigation,
}

enum class SignalHapticRole {
    LightPress,
    Confirm,
    Reject,
    Reveal,
    Copy,
    PaymentStage,
}

@Immutable
data class SignalFeedbackRequest(
    val role: SignalFeedbackRole,
    val reduced: Boolean = false,
    val assetFirst: Boolean = true,
)

@Immutable
data class SignalClipboardPayload(
    val label: String,
    val value: String,
    val sensitive: Boolean = false,
    val clearAfterSeconds: Int? = null,
)

interface SignalFeedbackAdapter {
    fun play(request: SignalFeedbackRequest)
}

interface SignalSoundAdapter {
    fun play(role: SignalFeedbackRole, assetFirst: Boolean = true, reduced: Boolean = false)
}

interface SignalHapticAdapter {
    fun perform(role: SignalHapticRole)
}

interface SignalClipboardAdapter {
    fun copy(payload: SignalClipboardPayload): Boolean
}

interface SignalSecureClipboardAdapter : SignalClipboardAdapter

interface SignalSystemBarsAdapter {
    fun setAppearance(darkIcons: Boolean, transparent: Boolean = false)
}

object SignalNoopFeedbackAdapter : SignalFeedbackAdapter {
    override fun play(request: SignalFeedbackRequest) = Unit
}

object SignalNoopSoundAdapter : SignalSoundAdapter {
    override fun play(role: SignalFeedbackRole, assetFirst: Boolean, reduced: Boolean) = Unit
}

object SignalNoopHapticAdapter : SignalHapticAdapter {
    override fun perform(role: SignalHapticRole) = Unit
}

object SignalNoopClipboardAdapter : SignalSecureClipboardAdapter {
    override fun copy(payload: SignalClipboardPayload): Boolean = false
}

object SignalNoopSystemBarsAdapter : SignalSystemBarsAdapter {
    override fun setAppearance(darkIcons: Boolean, transparent: Boolean) = Unit
}
