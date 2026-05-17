package ly.neptune.signal.motion

enum class SignalPaymentRitualPhase(
    val order: Int,
    val feedback: SignalPaymentFeedback,
    val defaultDurationMs: Int,
) {
    Preparing(0, SignalPaymentFeedback.Submitted, 420),
    Routing(1, SignalPaymentFeedback.Transition, 700),
    Verifying(2, SignalPaymentFeedback.Verified, 820),
    BankConfirmation(3, SignalPaymentFeedback.Transition, 720),
    SettlementConfirmation(4, SignalPaymentFeedback.Transition, 620),
    Completed(5, SignalPaymentFeedback.Completed, 0),
    Pending(3, SignalPaymentFeedback.Pending, 0),
    Failed(3, SignalPaymentFeedback.Failed, 0),
    Timeout(3, SignalPaymentFeedback.Pending, 0),
}

enum class SignalPaymentFeedback {
    Submitted,
    Transition,
    Verified,
    Completed,
    Failed,
    Pending,
}

enum class SignalFeedbackRole {
    Prepare,
    Verify,
    Confirm,
    Success,
    Failure,
    Cancel,
    Pending,
}

enum class SignalFeedbackSoundSource {
    Asset,
    SynthFallback,
}

enum class SignalPaymentExecutionModel {
    Instant,
    Synchronous,
    AsyncCallback,
    Operational,
}

data class SignalFeedbackConfig(
    val soundEnabled: Boolean = true,
    val hapticsEnabled: Boolean = true,
    val reducedFeedback: Boolean = false,
    val useSynthFallback: Boolean = false,
)

data class SignalFeedbackCue(
    val role: SignalFeedbackRole,
    val assetName: String,
    val synthFrequenciesHz: List<Double>,
    val durationMs: Int,
    val amplitude: Float,
)

data class SignalPaymentRitualCopy(
    val preparingTitle: String,
    val preparingMessage: String,
    val routingTitle: String,
    val routingMessage: String,
    val verifyingTitle: String,
    val verifyingMessage: String,
    val bankConfirmationTitle: String,
    val bankConfirmationMessage: String,
    val settlementTitle: String,
    val settlementMessage: String,
    val completedTitle: String,
    val completedMessage: String,
    val failedTitle: String,
    val failedMessage: String,
    val pendingTitle: String,
    val pendingMessage: String,
    val timeoutTitle: String,
    val timeoutMessage: String,
) {
    fun title(phase: SignalPaymentRitualPhase): String = when (phase) {
        SignalPaymentRitualPhase.Preparing -> preparingTitle
        SignalPaymentRitualPhase.Routing -> routingTitle
        SignalPaymentRitualPhase.Verifying -> verifyingTitle
        SignalPaymentRitualPhase.BankConfirmation -> bankConfirmationTitle
        SignalPaymentRitualPhase.SettlementConfirmation -> settlementTitle
        SignalPaymentRitualPhase.Completed -> completedTitle
        SignalPaymentRitualPhase.Failed -> failedTitle
        SignalPaymentRitualPhase.Pending -> pendingTitle
        SignalPaymentRitualPhase.Timeout -> timeoutTitle
    }

    fun message(phase: SignalPaymentRitualPhase): String = when (phase) {
        SignalPaymentRitualPhase.Preparing -> preparingMessage
        SignalPaymentRitualPhase.Routing -> routingMessage
        SignalPaymentRitualPhase.Verifying -> verifyingMessage
        SignalPaymentRitualPhase.BankConfirmation -> bankConfirmationMessage
        SignalPaymentRitualPhase.SettlementConfirmation -> settlementMessage
        SignalPaymentRitualPhase.Completed -> completedMessage
        SignalPaymentRitualPhase.Failed -> failedMessage
        SignalPaymentRitualPhase.Pending -> pendingMessage
        SignalPaymentRitualPhase.Timeout -> timeoutMessage
    }
}

object SignalPaymentRitual {
    val InfrastructurePath = listOf(
        SignalPaymentRitualPhase.Preparing,
        SignalPaymentRitualPhase.Routing,
        SignalPaymentRitualPhase.Verifying,
        SignalPaymentRitualPhase.BankConfirmation,
        SignalPaymentRitualPhase.SettlementConfirmation,
        SignalPaymentRitualPhase.Completed,
    )

    val InstantPath = listOf(
        SignalPaymentRitualPhase.Preparing,
        SignalPaymentRitualPhase.Completed,
    )

    val SynchronousPath = listOf(
        SignalPaymentRitualPhase.Preparing,
        SignalPaymentRitualPhase.Verifying,
        SignalPaymentRitualPhase.BankConfirmation,
        SignalPaymentRitualPhase.SettlementConfirmation,
        SignalPaymentRitualPhase.Completed,
    )

    val AsyncCallbackPath = listOf(
        SignalPaymentRitualPhase.Preparing,
        SignalPaymentRitualPhase.Routing,
        SignalPaymentRitualPhase.Verifying,
        SignalPaymentRitualPhase.BankConfirmation,
        SignalPaymentRitualPhase.Pending,
        SignalPaymentRitualPhase.Completed,
    )

    val OperationalPath = listOf(
        SignalPaymentRitualPhase.Preparing,
        SignalPaymentRitualPhase.Routing,
        SignalPaymentRitualPhase.Verifying,
        SignalPaymentRitualPhase.BankConfirmation,
        SignalPaymentRitualPhase.SettlementConfirmation,
        SignalPaymentRitualPhase.Pending,
    )

    val ArabicStageLabels = listOf("تجهيز", "مسار", "تحقق", "مصرف", "إيصال")

    fun feedback(phase: SignalPaymentRitualPhase): SignalPaymentFeedback = phase.feedback

    fun feedbackRole(phase: SignalPaymentRitualPhase): SignalFeedbackRole = when (phase) {
        SignalPaymentRitualPhase.Preparing -> SignalFeedbackRole.Prepare
        SignalPaymentRitualPhase.Routing -> SignalFeedbackRole.Confirm
        SignalPaymentRitualPhase.Verifying,
        SignalPaymentRitualPhase.BankConfirmation,
        SignalPaymentRitualPhase.SettlementConfirmation -> SignalFeedbackRole.Verify
        SignalPaymentRitualPhase.Completed -> SignalFeedbackRole.Success
        SignalPaymentRitualPhase.Failed -> SignalFeedbackRole.Failure
        SignalPaymentRitualPhase.Pending,
        SignalPaymentRitualPhase.Timeout -> SignalFeedbackRole.Pending
    }

    fun pathFor(model: SignalPaymentExecutionModel): List<SignalPaymentRitualPhase> = when (model) {
        SignalPaymentExecutionModel.Instant -> InstantPath
        SignalPaymentExecutionModel.Synchronous -> SynchronousPath
        SignalPaymentExecutionModel.AsyncCallback -> AsyncCallbackPath
        SignalPaymentExecutionModel.Operational -> OperationalPath
    }

    fun delayAfter(phase: SignalPaymentRitualPhase, model: SignalPaymentExecutionModel = SignalPaymentExecutionModel.Synchronous): Int =
        when (model) {
            SignalPaymentExecutionModel.Instant -> when (phase) {
                SignalPaymentRitualPhase.Preparing -> 260
                else -> phase.defaultDurationMs
            }
            SignalPaymentExecutionModel.Synchronous -> when (phase) {
                SignalPaymentRitualPhase.Preparing -> 420
                SignalPaymentRitualPhase.Verifying -> 740
                SignalPaymentRitualPhase.BankConfirmation -> 820
                SignalPaymentRitualPhase.SettlementConfirmation -> 560
                else -> phase.defaultDurationMs
            }
            SignalPaymentExecutionModel.AsyncCallback -> when (phase) {
                SignalPaymentRitualPhase.Preparing -> 360
                SignalPaymentRitualPhase.Routing -> 620
                SignalPaymentRitualPhase.Verifying -> 760
                SignalPaymentRitualPhase.BankConfirmation -> 780
                SignalPaymentRitualPhase.Pending -> 960
                else -> phase.defaultDurationMs
            }
            SignalPaymentExecutionModel.Operational -> when (phase) {
                SignalPaymentRitualPhase.Preparing -> 480
                SignalPaymentRitualPhase.Routing -> 780
                SignalPaymentRitualPhase.Verifying -> 880
                SignalPaymentRitualPhase.BankConfirmation -> 940
                SignalPaymentRitualPhase.SettlementConfirmation -> 720
                else -> phase.defaultDurationMs
            }
        }

    fun lifecycleIndex(phase: SignalPaymentRitualPhase): Int = phase.order.coerceIn(0, 4)

    fun progressDotIndex(phase: SignalPaymentRitualPhase): Int = when (phase) {
        SignalPaymentRitualPhase.Preparing -> 0
        SignalPaymentRitualPhase.Routing -> 1
        SignalPaymentRitualPhase.Verifying -> 2
        SignalPaymentRitualPhase.BankConfirmation,
        SignalPaymentRitualPhase.SettlementConfirmation,
        SignalPaymentRitualPhase.Completed,
        SignalPaymentRitualPhase.Failed,
        SignalPaymentRitualPhase.Pending,
        SignalPaymentRitualPhase.Timeout -> 3
    }

    val DefaultFeedbackCues = mapOf(
        SignalFeedbackRole.Prepare to SignalFeedbackCue(
            role = SignalFeedbackRole.Prepare,
            assetName = "signal_feedback_prepare",
            synthFrequenciesHz = listOf(392.0),
            durationMs = 72,
            amplitude = 0.035f,
        ),
        SignalFeedbackRole.Verify to SignalFeedbackCue(
            role = SignalFeedbackRole.Verify,
            assetName = "signal_feedback_verify",
            synthFrequenciesHz = listOf(523.25, 659.25),
            durationMs = 118,
            amplitude = 0.045f,
        ),
        SignalFeedbackRole.Confirm to SignalFeedbackCue(
            role = SignalFeedbackRole.Confirm,
            assetName = "signal_feedback_confirm",
            synthFrequenciesHz = listOf(440.0, 554.37),
            durationMs = 92,
            amplitude = 0.04f,
        ),
        SignalFeedbackRole.Success to SignalFeedbackCue(
            role = SignalFeedbackRole.Success,
            assetName = "signal_feedback_success",
            synthFrequenciesHz = listOf(493.88, 659.25, 880.0),
            durationMs = 252,
            amplitude = 0.05f,
        ),
        SignalFeedbackRole.Failure to SignalFeedbackCue(
            role = SignalFeedbackRole.Failure,
            assetName = "signal_feedback_failure",
            synthFrequenciesHz = listOf(392.0, 329.63),
            durationMs = 220,
            amplitude = 0.045f,
        ),
        SignalFeedbackRole.Cancel to SignalFeedbackCue(
            role = SignalFeedbackRole.Cancel,
            assetName = "signal_feedback_cancel",
            synthFrequenciesHz = listOf(554.37, 440.0),
            durationMs = 150,
            amplitude = 0.04f,
        ),
        SignalFeedbackRole.Pending to SignalFeedbackCue(
            role = SignalFeedbackRole.Pending,
            assetName = "signal_feedback_pending",
            synthFrequenciesHz = listOf(392.0, 493.88),
            durationMs = 142,
            amplitude = 0.04f,
        ),
    )

    val ArabicLyPayCopy = SignalPaymentRitualCopy(
        preparingTitle = "نجهز طلب التحويل",
        preparingMessage = "نقفل تفاصيل العملية قبل إرسالها. لا يتم الخصم في هذه اللحظة.",
        routingTitle = "نمرر العملية عبر المسار المناسب",
        routingMessage = "نختار مسار LyPay المناسب حسب المستلم والحساب.",
        verifyingTitle = "نتحقق من المستلم والمبلغ",
        verifyingMessage = "نراجع الهوية، schema، والمبلغ قبل انتظار الرد النهائي.",
        bankConfirmationTitle = "ننتظر تأكيد المصرف",
        bankConfirmationMessage = "ننتظر رد المصرف. هذه خطوة طبيعية في الدفع الفوري.",
        settlementTitle = "نثبت المرجع والإيصال",
        settlementMessage = "نثبت المرجع ونجهز ظهور العملية في الخط المالي.",
        completedTitle = "تمت العملية",
        completedMessage = "تم تحويل 250.000 د.ل إلى mohamed@andalus وسيظهر في الخط المالي.",
        failedTitle = "لم تكتمل العملية",
        failedMessage = "لم يتم خصم المبلغ. يمكنك إعادة المحاولة أو فتح تفاصيل الحالة.",
        pendingTitle = "قيد المراجعة",
        pendingMessage = "الطلب محفوظ، وسنرسل إشعاراً عند اكتمال المراجعة.",
        timeoutTitle = "انتهت المهلة",
        timeoutMessage = "لم يصل رد المصرف في الوقت المناسب. تحقق من الحالة قبل إعادة الإرسال.",
    )
}
