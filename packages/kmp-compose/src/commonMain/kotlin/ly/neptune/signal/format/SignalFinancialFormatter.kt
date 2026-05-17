package ly.neptune.signal.format

enum class SignalCurrency(
    val code: String,
    val display: String,
    val minorDigits: Int,
    val rtlDisplayAfterAmount: Boolean = true,
) {
    LYD("LYD", "د.ل", 3),
    USD("USD", "USD", 2, rtlDisplayAfterAmount = false),
    EUR("EUR", "EUR", 2, rtlDisplayAfterAmount = false),
}

data class SignalMoney(
    val minorUnits: Long,
    val currency: SignalCurrency,
)

object SignalFinancialFormatter {
    fun money(
        minorUnits: Long,
        currency: SignalCurrency,
        signed: Boolean = false,
    ): String {
        val negative = minorUnits < 0
        val absolute = if (negative) -minorUnits else minorUnits
        val divisor = powerOfTen(currency.minorDigits)
        val whole = absolute / divisor
        val fraction = absolute % divisor
        val sign = when {
            signed && negative -> "-"
            signed -> "+"
            negative -> "-"
            else -> ""
        }
        val number = if (currency.minorDigits == 0) {
            groupThousands(whole)
        } else {
            "${groupThousands(whole)}.${fraction.toString().padStart(currency.minorDigits, '0')}"
        }
        return if (currency.rtlDisplayAfterAmount) {
            "$sign$number ${currency.display}"
        } else {
            "$sign$number ${currency.code}"
        }
    }

    fun compactIbanEnding(iban: String): String =
        iban.filter { it.isLetterOrDigit() }.takeLast(4)

    private fun groupThousands(value: Long): String {
        val raw = value.toString()
        return raw.reversed()
            .chunked(3)
            .joinToString(",")
            .reversed()
    }

    private fun powerOfTen(digits: Int): Long {
        var value = 1L
        repeat(digits) { value *= 10L }
        return value
    }
}
