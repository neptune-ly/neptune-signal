package ly.neptune.signal

import kotlin.test.Test
import kotlin.test.assertEquals

class SignalVersionTest {
    @Test
    fun exposesStandardAndSdkVersions() {
        assertEquals("0.1.1", Signal.StandardVersion)
        assertEquals("0.1.1", Signal.SdkVersion)
    }
}
