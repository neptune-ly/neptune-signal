package ly.neptune.signal.accessibility

import androidx.compose.runtime.Immutable

enum class SignalTextDirectionHint {
    Natural,
    Ltr,
    Rtl,
}

@Immutable
data class SignalAccessibleLabel(
    val visualText: String,
    val spokenText: String = visualText,
    val directionHint: SignalTextDirectionHint = SignalTextDirectionHint.Natural,
)

object SignalIdentifierRendering {
    private const val Lre = '\u202A'
    private const val Pdf = '\u202C'

    fun ltrIdentifier(value: String): String = "$Lre$value$Pdf"

    fun groupedIdentifier(value: String, groupSize: Int = 4): String {
        if (groupSize <= 0) return ltrIdentifier(value)
        return ltrIdentifier(
            value.filterNot(Char::isWhitespace)
                .chunked(groupSize)
                .joinToString(" "),
        )
    }

    fun maskedPan(last4: String): String = ltrIdentifier("•••• •••• •••• ${last4.takeLast(4)}")
}
