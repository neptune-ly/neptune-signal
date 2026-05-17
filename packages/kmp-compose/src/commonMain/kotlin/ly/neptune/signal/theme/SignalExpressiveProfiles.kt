package ly.neptune.signal.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp

@Immutable
data class SignalExpressiveProfile(
    val key: String,
    val label: String,
    val description: String,
    val anchor: Color,
    val accentBias: Color,
    val surfaceTint: Float,
    val navTint: Float,
    val cardTint: Float,
    val darkTint: Float,
    val primaryTone: Color? = null,
    val secondaryTone: Color? = null,
    val accentTone: Color? = null,
) {
    fun overridesFor(
        brand: SignalBrand,
        appearance: SignalAppearanceMode,
    ): SignalThemeOverrides {
        val baseLight = brand.surface
        val lightSurface = lerp(baseLight, anchor, surfaceTint)
        val lightLow = lerp(lightSurface, anchor, cardTint * 0.68f)
        val lightContainer = lerp(lightSurface, anchor, cardTint)
        val lightHigh = lerp(lightSurface, anchor, navTint)
        val darkBase = when (appearance) {
            SignalAppearanceMode.Black -> Color(0xFF000000)
            else -> Color(0xFF080B0E)
        }
        val darkSurface = lerp(darkBase, anchor, darkTint * 0.34f)
        val darkLow = lerp(darkSurface, anchor, (cardTint + 0.02f).coerceAtMost(0.11f))
        val darkContainer = lerp(darkSurface, anchor, (navTint + 0.02f).coerceAtMost(0.13f))
        return SignalThemeOverrides(
            primary = primaryTone,
            secondary = secondaryTone,
            accent = accentTone,
            primaryContainer = lerp(lightSurface, brand.primary, 0.11f),
            secondaryContainer = lerp(lightSurface, secondaryTone ?: brand.secondary, 0.16f),
            tertiaryContainer = lerp(lightSurface, accentTone ?: accentBias, 0.14f),
            lightSurface = lightSurface,
            darkSurface = darkSurface,
            blackSurface = if (appearance == SignalAppearanceMode.Black) Color(0xFF000000) else darkSurface,
            surfaceContainerLowest = when (appearance) {
                SignalAppearanceMode.Dark, SignalAppearanceMode.Black -> darkBase
                else -> lightSurface
            },
            surfaceContainerLow = when (appearance) {
                SignalAppearanceMode.Dark, SignalAppearanceMode.Black -> darkLow
                else -> lightLow
            },
            surfaceContainer = when (appearance) {
                SignalAppearanceMode.Dark, SignalAppearanceMode.Black -> darkContainer
                else -> lightContainer
            },
            surfaceContainerHigh = when (appearance) {
                SignalAppearanceMode.Dark, SignalAppearanceMode.Black -> lerp(darkContainer, anchor, 0.035f)
                else -> lightHigh
            },
            surfaceContainerHighest = when (appearance) {
                SignalAppearanceMode.Dark, SignalAppearanceMode.Black -> lerp(darkContainer, anchor, 0.065f)
                else -> lerp(lightSurface, anchor, (navTint + 0.08f).coerceAtMost(0.24f))
            },
        )
    }
}

object SignalExpressiveProfiles {
    val Neptune = SignalExpressiveProfile(
        key = "neptune",
        label = "Neptune",
        description = "Deep navy with cyan, violet, and coral fintech accents",
        anchor = Color(0xFF082B5A),
        accentBias = Color(0xFFFF6A6A),
        surfaceTint = 0.018f,
        navTint = 0.10f,
        cardTint = 0.060f,
        darkTint = 0.13f,
        primaryTone = Color(0xFF082B5A),
        secondaryTone = Color(0xFF29D4FF),
        accentTone = Color(0xFFFF6A6A),
    )

    val Ocean = SignalExpressiveProfile("ocean", "Ocean", "Deep blue with a soft payment tone", Color(0xFF065C73), Color(0xFF00A8AE), 0.025f, 0.10f, 0.06f, 0.13f, secondaryTone = Color(0xFF37D5FF), accentTone = Color(0xFF00A8AE))
    val Graphite = SignalExpressiveProfile("graphite", "Graphite", "Neutral, quiet, operational", Color(0xFF303942), Color(0xFF6C7A86), 0.018f, 0.075f, 0.04f, 0.08f, secondaryTone = Color(0xFF9CA3AF), accentTone = Color(0xFF6C7A86))
    val Midnight = SignalExpressiveProfile("midnight", "Midnight", "Darker wallet rhythm", Color(0xFF101A2D), Color(0xFF3BC1EE), 0.018f, 0.08f, 0.045f, 0.16f, secondaryTone = Color(0xFF7C7CFF), accentTone = Color(0xFF3BC1EE))
    val Slate = SignalExpressiveProfile("slate", "Slate", "Balanced blue-gray surfaces", Color(0xFF3D5866), Color(0xFF00A8AE), 0.020f, 0.09f, 0.055f, 0.11f, secondaryTone = Color(0xFF94A3B8), accentTone = Color(0xFF00A8AE))
    val Emerald = SignalExpressiveProfile("emerald", "Emerald", "Calm green financial confidence", Color(0xFF0B5A3A), Color(0xFF35B56F), 0.020f, 0.095f, 0.055f, 0.11f, secondaryTone = Color(0xFF35B56F), accentTone = Color(0xFF4FE0A5))
    val Arctic = SignalExpressiveProfile("arctic", "Arctic", "Bright, clean, cold surfaces", Color(0xFF3BC1EE), Color(0xFF00A8AE), 0.018f, 0.075f, 0.045f, 0.09f, secondaryTone = Color(0xFF6BA8FF), accentTone = Color(0xFF00A8AE))
    val Sand = SignalExpressiveProfile("sand", "Sand", "Warm but restrained banking tone", Color(0xFFC7A15D), Color(0xFF8E6A2F), 0.020f, 0.085f, 0.05f, 0.08f, secondaryTone = Color(0xFFC7A15D), accentTone = Color(0xFFE7C38C))
    val Aurora = SignalExpressiveProfile("aurora", "Aurora", "Expressive teal with coral restraint", Color(0xFF00A8AE), Color(0xFFEB4E4D), 0.018f, 0.09f, 0.05f, 0.10f, secondaryTone = Color(0xFF00E0DD), accentTone = Color(0xFFFF6A6A))

    val All = listOf(Neptune, Ocean, Graphite, Midnight, Slate, Emerald, Arctic, Sand, Aurora)
}
