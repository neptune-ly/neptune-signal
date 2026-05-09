package ly.neptune.signal

import androidx.compose.ui.graphics.Color
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import ly.neptune.signal.theme.SignalBrand
import ly.neptune.signal.theme.SignalBrandDefaults
import ly.neptune.signal.theme.SignalColorDefaults
import ly.neptune.signal.theme.SignalColorMode
import ly.neptune.signal.theme.fromBrand

class SignalThemeConfigurationTest {
    @Test
    fun createsLightThemeFromBrand() {
        val brand = SignalBrand(
            key = "test-bank",
            label = "Test Bank",
            primary = Color(0xFF981B1E),
            secondary = Color(0xFFC7A15D),
            accent = Color(0xFFD7282F),
        )

        val colors = SignalColorDefaults.fromBrand(brand)

        assertEquals(Color(0xFF981B1E), colors.primary)
        assertEquals(Color(0xFFC7A15D), colors.bankSecondary)
        assertEquals(Color(0xFFD7282F), colors.bankAccent)
        assertEquals(Color.White, colors.textInverse)
        assertEquals(false, colors.dark)
    }

    @Test
    fun computesReadableContentForBrightBankPrimary() {
        val brand = SignalBrand(
            key = "bright-bank",
            label = "Bright Bank",
            primary = Color.White,
            secondary = Color(0xFF00A8AE),
            accent = Color(0xFFEB4E4D),
        )

        val colors = SignalColorDefaults.fromBrand(brand)

        assertEquals(Color(0xFF071C2E), colors.onPrimary)
        assertEquals(Color(0xFF071C2E), colors.textInverse)
    }

    @Test
    fun createsDarkAndBlackThemesFromBrand() {
        val dark = SignalColorDefaults.fromBrand(SignalBrandDefaults.Neptune, SignalColorMode.Dark)
        val black = SignalColorDefaults.fromBrand(SignalBrandDefaults.Neptune, SignalColorMode.Black)

        assertTrue(dark.dark)
        assertTrue(black.dark)
        assertTrue(black.black)
        assertEquals(Color(0xFF000000), black.surface)
    }
}
