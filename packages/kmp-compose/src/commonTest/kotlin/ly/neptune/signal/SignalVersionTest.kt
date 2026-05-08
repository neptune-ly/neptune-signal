package ly.neptune.signal

import kotlin.test.Test
import kotlin.test.assertEquals

class SignalVersionTest {
    @Test
    fun exposesStandardAndSdkVersions() {
        assertEquals("0.1.0", Signal.StandardVersion)
        assertEquals("0.1.0", Signal.SdkVersion)
    }
}

