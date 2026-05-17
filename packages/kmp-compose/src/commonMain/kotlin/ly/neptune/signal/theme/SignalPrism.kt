package ly.neptune.signal.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class SignalPrismAppearance {
    Dark,
    Light,
    Oled,
}

enum class SignalPrismPersonality {
    ClassicBank,
    PremiumWallet,
    YouthFintech,
    CorporateDense,
    IslamicCalm,
    MerchantEnergy,
}

enum class SignalGradientIntensity {
    Minimal,
    Controlled,
    Expressive,
    HighEnergy,
}

enum class SignalMotionIntensity {
    Reduced,
    Calm,
    Fluid,
    Expressive,
}

enum class SignalPrismNavStyle {
    ClassicDock,
    FloatingDock,
    PaymentForward,
    DenseOperational,
}

enum class SignalPrismCardStyle {
    CalmSurface,
    GradientHero,
    DenseOperational,
    BrandStatement,
}

enum class SignalPrismDensity {
    Comfortable,
    Compact,
    Corporate,
}

@Immutable
data class SignalPrismPalette(
    val prismInk: Color = Color(0xFF050A12),
    val prismNight: Color = Color(0xFF07111F),
    val prismDeepNavy: Color = Color(0xFF082B5A),
    val prismOcean: Color = Color(0xFF075E85),
    val prismCyan: Color = Color(0xFF29D4FF),
    val prismViolet: Color = Color(0xFF846BFF),
    val prismCoral: Color = Color(0xFFFF6A6A),
    val prismEmerald: Color = Color(0xFF22C98B),
    val prismGold: Color = Color(0xFFD8AE58),
    val prismMist: Color = Color(0xFFEAF3F8),
    val prismFrost: Color = Color(0xFFF7FBFF),
    val prismGlass: Color = Color(0x1FFFFFFF),
    val prismStroke: Color = Color(0x33FFFFFF),
    val prismTextPrimary: Color = Color(0xFFF7FBFF),
    val prismTextSecondary: Color = Color(0xFFB9C8D5),
    val prismTextMuted: Color = Color(0xFF748899),
)

@Immutable
data class SignalPrismGradient(
    val colors: List<Color>,
    val alpha: Float = 1f,
)

@Immutable
data class SignalPrismGradientTokens(
    val prismHeroGradient: SignalPrismGradient,
    val prismAccountGradient: SignalPrismGradient,
    val prismPaymentGradient: SignalPrismGradient,
    val prismAccentGradient: SignalPrismGradient,
    val prismSuccessGradient: SignalPrismGradient,
    val prismCampaignGradient: SignalPrismGradient,
    val prismAmbientWash: SignalPrismGradient,
    val prismCardGlow: SignalPrismGradient,
    val prismNavSheen: SignalPrismGradient,
)

@Immutable
data class SignalPrismSurfaceTokens(
    val prismBackground: Color,
    val prismSurface: Color,
    val prismSurfaceRaised: Color,
    val prismSurfaceFloating: Color,
    val prismSurfaceGlass: Color,
    val prismSurfaceMuted: Color,
    val prismSurfaceStrong: Color,
)

@Immutable
data class SignalPrismToneTokens(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val secondary: Color,
    val onSecondary: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val accent: Color,
    val onAccent: Color,
    val accentContainer: Color,
    val onAccentContainer: Color,
    val payment: Color,
    val onPayment: Color,
    val paymentContainer: Color,
    val onPaymentContainer: Color,
)

@Immutable
data class SignalPrismOverlayTokens(
    val prismGlowSubtle: Color,
    val prismGlowMedium: Color,
    val prismShadowFloating: Color,
    val prismShadowDeep: Color,
    val prismBorderSoft: Color,
    val prismBorderLuminous: Color,
)

@Immutable
data class SignalPrismMotionTokens(
    val prismMotionSoft: Int = 180,
    val prismMotionFluid: Int = 320,
    val prismMotionPayment: Int = 520,
    val prismMotionFeedback: Int = 160,
    val prismMotionCarousel: Int = 380,
    val prismMotionReduced: Int = 80,
)

@Immutable
data class SignalPrismRadiusTokens(
    val prismRadiusSmall: Dp = 12.dp,
    val prismRadiusMedium: Dp = 18.dp,
    val prismRadiusLarge: Dp = 28.dp,
    val prismRadiusHero: Dp = 34.dp,
    val prismRadiusFloatingNav: Dp = 34.dp,
)

@Immutable
data class SignalPrismDensityTokens(
    val prismDensityComfortable: Dp = 16.dp,
    val prismDensityCompact: Dp = 12.dp,
    val prismDensityCorporate: Dp = 10.dp,
)

@Immutable
data class SignalPrismProfile(
    val personality: SignalPrismPersonality = SignalPrismPersonality.PremiumWallet,
    val gradientIntensity: SignalGradientIntensity = SignalGradientIntensity.Controlled,
    val motionIntensity: SignalMotionIntensity = SignalMotionIntensity.Fluid,
    val navStyle: SignalPrismNavStyle = SignalPrismNavStyle.FloatingDock,
    val cardStyle: SignalPrismCardStyle = SignalPrismCardStyle.GradientHero,
    val density: SignalPrismDensity = SignalPrismDensity.Comfortable,
    val palette: SignalPrismPalette = SignalPrismPalette(),
)

@Immutable
data class SignalPrismTokens(
    val appearance: SignalPrismAppearance,
    val profile: SignalPrismProfile,
    val palette: SignalPrismPalette,
    val gradients: SignalPrismGradientTokens,
    val surfaces: SignalPrismSurfaceTokens,
    val tones: SignalPrismToneTokens,
    val overlays: SignalPrismOverlayTokens,
    val motion: SignalPrismMotionTokens,
    val radii: SignalPrismRadiusTokens,
    val density: SignalPrismDensityTokens,
)

object SignalPrismDefaults {
    val PremiumWallet = SignalPrismProfile(
        personality = SignalPrismPersonality.PremiumWallet,
        gradientIntensity = SignalGradientIntensity.Controlled,
        motionIntensity = SignalMotionIntensity.Fluid,
        navStyle = SignalPrismNavStyle.FloatingDock,
        cardStyle = SignalPrismCardStyle.GradientHero,
    )

    fun appearanceFor(mode: SignalColorMode): SignalPrismAppearance = when (mode) {
        SignalColorMode.Light -> SignalPrismAppearance.Light
        SignalColorMode.Dark -> SignalPrismAppearance.Dark
        SignalColorMode.Black -> SignalPrismAppearance.Oled
    }

    fun profileFor(
        brand: SignalBrand,
        personality: SignalPrismPersonality = SignalPrismPersonality.PremiumWallet,
    ): SignalPrismProfile {
        val base = when (personality) {
            SignalPrismPersonality.ClassicBank -> PremiumWallet.copy(
                personality = personality,
                gradientIntensity = SignalGradientIntensity.Minimal,
                motionIntensity = SignalMotionIntensity.Calm,
                navStyle = SignalPrismNavStyle.ClassicDock,
                cardStyle = SignalPrismCardStyle.CalmSurface,
            )
            SignalPrismPersonality.PremiumWallet -> PremiumWallet
            SignalPrismPersonality.YouthFintech -> PremiumWallet.copy(
                personality = personality,
                gradientIntensity = SignalGradientIntensity.Expressive,
                motionIntensity = SignalMotionIntensity.Expressive,
            )
            SignalPrismPersonality.CorporateDense -> PremiumWallet.copy(
                personality = personality,
                gradientIntensity = SignalGradientIntensity.Minimal,
                motionIntensity = SignalMotionIntensity.Calm,
                navStyle = SignalPrismNavStyle.DenseOperational,
                cardStyle = SignalPrismCardStyle.DenseOperational,
                density = SignalPrismDensity.Corporate,
            )
            SignalPrismPersonality.IslamicCalm -> PremiumWallet.copy(
                personality = personality,
                gradientIntensity = SignalGradientIntensity.Controlled,
                motionIntensity = SignalMotionIntensity.Calm,
                cardStyle = SignalPrismCardStyle.BrandStatement,
            )
            SignalPrismPersonality.MerchantEnergy -> PremiumWallet.copy(
                personality = personality,
                gradientIntensity = SignalGradientIntensity.Expressive,
                motionIntensity = SignalMotionIntensity.Fluid,
                navStyle = SignalPrismNavStyle.PaymentForward,
            )
        }
        return base.copy(
            palette = base.palette.copy(
                prismDeepNavy = brand.primary,
                prismOcean = brand.secondary,
                prismCoral = brand.accent,
            ),
        )
    }

    fun tokens(
        brand: SignalBrand = SignalBrandDefaults.Neptune,
        appearance: SignalPrismAppearance = SignalPrismAppearance.Dark,
        profile: SignalPrismProfile = profileFor(brand),
    ): SignalPrismTokens {
        val palette = paletteFor(brand, appearance, profile)
        return SignalPrismTokens(
            appearance = appearance,
            profile = profile.copy(palette = palette),
            palette = palette,
            gradients = gradientsFor(palette, appearance, profile.gradientIntensity),
            surfaces = surfacesFor(palette, appearance),
            tones = tonesFor(palette, appearance),
            overlays = overlaysFor(palette, appearance),
            motion = motionFor(profile.motionIntensity),
            radii = radiiFor(profile),
            density = densityFor(profile.density),
        )
    }

    private fun paletteFor(
        brand: SignalBrand,
        appearance: SignalPrismAppearance,
        profile: SignalPrismProfile,
    ): SignalPrismPalette {
        val base = profile.palette.copy(
            prismDeepNavy = brand.primary,
            prismOcean = brand.secondary,
            prismCyan = brand.secondary,
            prismViolet = lerp(brand.primary, brand.accent, 0.55f),
            prismCoral = brand.accent,
        )
        return when (appearance) {
            SignalPrismAppearance.Dark -> base
            SignalPrismAppearance.Light -> base.copy(
                prismInk = Color(0xFF081B2D),
                prismNight = Color(0xFFF0F6FA),
                prismGlass = Color(0xA6FFFFFF),
                prismStroke = Color(0x26081B2D),
                prismTextPrimary = Color(0xFF071C2E),
                prismTextSecondary = Color(0xFF526675),
                prismTextMuted = Color(0xFF7B8D99),
            )
            SignalPrismAppearance.Oled -> base.copy(
                prismInk = Color(0xFF000000),
                prismNight = Color(0xFF030609),
                prismGlass = Color(0x14080F16),
                prismStroke = Color(0x24FFFFFF),
                prismTextPrimary = Color(0xFFF7FBFF),
                prismTextSecondary = Color(0xFFB0C0CB),
                prismTextMuted = Color(0xFF718493),
            )
        }
    }

    private fun gradientsFor(
        palette: SignalPrismPalette,
        appearance: SignalPrismAppearance,
        intensity: SignalGradientIntensity,
    ): SignalPrismGradientTokens {
        val factor = when (intensity) {
            SignalGradientIntensity.Minimal -> 0.55f
            SignalGradientIntensity.Controlled -> 0.76f
            SignalGradientIntensity.Expressive -> 0.90f
            SignalGradientIntensity.HighEnergy -> 1f
        }
        val darkAlpha = when (appearance) {
            SignalPrismAppearance.Oled -> factor * 0.68f
            SignalPrismAppearance.Light -> factor * 0.84f
            SignalPrismAppearance.Dark -> factor
        }
        return SignalPrismGradientTokens(
            prismHeroGradient = SignalPrismGradient(listOf(palette.prismNight, palette.prismDeepNavy, palette.prismViolet), darkAlpha),
            prismAccountGradient = SignalPrismGradient(listOf(palette.prismDeepNavy, palette.prismOcean, palette.prismViolet), darkAlpha),
            prismPaymentGradient = SignalPrismGradient(listOf(palette.prismCyan, palette.prismViolet, palette.prismCoral), darkAlpha),
            prismAccentGradient = SignalPrismGradient(listOf(palette.prismOcean, palette.prismCyan), darkAlpha),
            prismSuccessGradient = SignalPrismGradient(listOf(palette.prismEmerald, palette.prismOcean), darkAlpha),
            prismCampaignGradient = SignalPrismGradient(listOf(palette.prismDeepNavy, palette.prismCoral), darkAlpha * 0.72f),
            prismAmbientWash = SignalPrismGradient(listOf(palette.prismNight, palette.prismOcean, palette.prismViolet), darkAlpha * 0.18f),
            prismCardGlow = SignalPrismGradient(listOf(palette.prismCyan, Color.Transparent), darkAlpha * 0.26f),
            prismNavSheen = SignalPrismGradient(listOf(Color.White, palette.prismCyan), darkAlpha * 0.10f),
        )
    }

    private fun surfacesFor(
        palette: SignalPrismPalette,
        appearance: SignalPrismAppearance,
    ): SignalPrismSurfaceTokens = when (appearance) {
        SignalPrismAppearance.Dark -> SignalPrismSurfaceTokens(
            prismBackground = palette.prismInk,
            prismSurface = palette.prismNight,
            prismSurfaceRaised = lerp(palette.prismNight, palette.prismDeepNavy, 0.18f),
            prismSurfaceFloating = lerp(palette.prismNight, palette.prismOcean, 0.14f),
            prismSurfaceGlass = palette.prismGlass,
            prismSurfaceMuted = lerp(palette.prismNight, palette.prismMist, 0.05f),
            prismSurfaceStrong = lerp(palette.prismNight, palette.prismDeepNavy, 0.32f),
        )
        SignalPrismAppearance.Light -> SignalPrismSurfaceTokens(
            prismBackground = palette.prismFrost,
            prismSurface = palette.prismMist,
            prismSurfaceRaised = lerp(palette.prismFrost, palette.prismCyan, 0.035f),
            prismSurfaceFloating = lerp(palette.prismFrost, palette.prismDeepNavy, 0.055f),
            prismSurfaceGlass = palette.prismGlass,
            prismSurfaceMuted = lerp(palette.prismMist, palette.prismDeepNavy, 0.04f),
            prismSurfaceStrong = lerp(palette.prismMist, palette.prismDeepNavy, 0.10f),
        )
        SignalPrismAppearance.Oled -> SignalPrismSurfaceTokens(
            prismBackground = Color.Black,
            prismSurface = palette.prismNight,
            prismSurfaceRaised = Color(0xFF070B10),
            prismSurfaceFloating = Color(0xFF0A1018),
            prismSurfaceGlass = palette.prismGlass,
            prismSurfaceMuted = Color(0xFF05080C),
            prismSurfaceStrong = Color(0xFF0D1724),
        )
    }

    private fun tonesFor(
        palette: SignalPrismPalette,
        appearance: SignalPrismAppearance,
    ): SignalPrismToneTokens {
        val primaryContainerAlpha = when (appearance) {
            SignalPrismAppearance.Light -> 0.12f
            SignalPrismAppearance.Dark -> 0.24f
            SignalPrismAppearance.Oled -> 0.30f
        }
        val secondaryContainerAlpha = when (appearance) {
            SignalPrismAppearance.Light -> 0.20f
            SignalPrismAppearance.Dark -> 0.20f
            SignalPrismAppearance.Oled -> 0.26f
        }
        val accentContainerAlpha = when (appearance) {
            SignalPrismAppearance.Light -> 0.18f
            SignalPrismAppearance.Dark -> 0.18f
            SignalPrismAppearance.Oled -> 0.23f
        }
        val payment = lerp(palette.prismCyan, palette.prismViolet, 0.30f)
        return SignalPrismToneTokens(
            primary = palette.prismDeepNavy,
            onPrimary = contentColorFor(palette.prismDeepNavy),
            primaryContainer = lerp(palette.prismSurfaceBase(appearance), palette.prismDeepNavy, primaryContainerAlpha),
            onPrimaryContainer = palette.prismTextPrimary,
            secondary = palette.prismCyan,
            onSecondary = contentColorFor(palette.prismCyan),
            secondaryContainer = lerp(palette.prismSurfaceBase(appearance), palette.prismCyan, secondaryContainerAlpha),
            onSecondaryContainer = palette.prismTextPrimary,
            accent = palette.prismCoral,
            onAccent = contentColorFor(palette.prismCoral),
            accentContainer = lerp(palette.prismSurfaceBase(appearance), palette.prismCoral, accentContainerAlpha),
            onAccentContainer = palette.prismTextPrimary,
            payment = payment,
            onPayment = contentColorFor(payment),
            paymentContainer = lerp(palette.prismSurfaceBase(appearance), payment, secondaryContainerAlpha),
            onPaymentContainer = palette.prismTextPrimary,
        )
    }

    private fun SignalPrismPalette.prismSurfaceBase(appearance: SignalPrismAppearance): Color = when (appearance) {
        SignalPrismAppearance.Light -> prismFrost
        SignalPrismAppearance.Dark -> prismNight
        SignalPrismAppearance.Oled -> Color.Black
    }

    private fun contentColorFor(color: Color): Color {
        val luma = (0.299f * color.red) + (0.587f * color.green) + (0.114f * color.blue)
        return if (luma > 0.60f) Color(0xFF06111E) else Color.White
    }

    private fun overlaysFor(
        palette: SignalPrismPalette,
        appearance: SignalPrismAppearance,
    ): SignalPrismOverlayTokens {
        val glowAlpha = when (appearance) {
            SignalPrismAppearance.Oled -> 0.12f
            SignalPrismAppearance.Dark -> 0.20f
            SignalPrismAppearance.Light -> 0.10f
        }
        return SignalPrismOverlayTokens(
            prismGlowSubtle = palette.prismCyan.copy(alpha = glowAlpha),
            prismGlowMedium = palette.prismViolet.copy(alpha = glowAlpha * 1.4f),
            prismShadowFloating = Color.Black.copy(alpha = if (appearance == SignalPrismAppearance.Light) 0.14f else 0.36f),
            prismShadowDeep = Color.Black.copy(alpha = if (appearance == SignalPrismAppearance.Light) 0.20f else 0.50f),
            prismBorderSoft = palette.prismStroke,
            prismBorderLuminous = palette.prismCyan.copy(alpha = glowAlpha * 0.88f),
        )
    }

    private fun motionFor(intensity: SignalMotionIntensity): SignalPrismMotionTokens = when (intensity) {
        SignalMotionIntensity.Reduced -> SignalPrismMotionTokens(80, 100, 140, 80, 100, 60)
        SignalMotionIntensity.Calm -> SignalPrismMotionTokens(160, 260, 420, 140, 320, 80)
        SignalMotionIntensity.Fluid -> SignalPrismMotionTokens()
        SignalMotionIntensity.Expressive -> SignalPrismMotionTokens(210, 380, 620, 180, 460, 80)
    }

    private fun radiiFor(profile: SignalPrismProfile): SignalPrismRadiusTokens = when (profile.personality) {
        SignalPrismPersonality.CorporateDense -> SignalPrismRadiusTokens(8.dp, 14.dp, 22.dp, 28.dp, 26.dp)
        SignalPrismPersonality.ClassicBank -> SignalPrismRadiusTokens(10.dp, 16.dp, 24.dp, 30.dp, 30.dp)
        else -> SignalPrismRadiusTokens()
    }

    private fun densityFor(density: SignalPrismDensity): SignalPrismDensityTokens = when (density) {
        SignalPrismDensity.Comfortable -> SignalPrismDensityTokens()
        SignalPrismDensity.Compact -> SignalPrismDensityTokens(14.dp, 10.dp, 9.dp)
        SignalPrismDensity.Corporate -> SignalPrismDensityTokens(12.dp, 9.dp, 8.dp)
    }
}
