package ly.neptune.signal

import kotlin.test.Test
import kotlin.test.assertEquals
import ly.neptune.signal.accessibility.SignalIdentifierRendering

class SignalIdentifierRenderingTest {
    @Test
    fun wrapsIdentifiersWithLtrEmbedding() {
        assertEquals("\u202ALY81 0240\u202C", SignalIdentifierRendering.ltrIdentifier("LY81 0240"))
    }

    @Test
    fun groupsIdentifiersForRtlScreens() {
        assertEquals(
            "\u202A1234 5678 9012\u202C",
            SignalIdentifierRendering.groupedIdentifier("123456789012"),
        )
    }

    @Test
    fun masksPanWithLastFourOnly() {
        assertEquals("\u202A•••• •••• •••• 8120\u202C", SignalIdentifierRendering.maskedPan("8120"))
    }
}
