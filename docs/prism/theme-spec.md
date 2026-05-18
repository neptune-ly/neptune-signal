# Prism Theme Spec

Date: 2026-05-17

## Goal

Signal Prism must be a reusable white-label design engine, not a single fixed Neptune skin.

The Prism theme system provides:

- dark, light, and OLED appearances
- bank-influenced palettes
- product personality presets
- user-safe personalization
- controlled gradient and radial accents in approved zones only
- SDK-owned component tokens

## Existing Foundation

Signal already exposes:

- `SignalTheme`
- `SignalThemeConfig`
- `SignalThemeSettings`
- `SignalPrismTokens`
- `SignalPrismProfile`
- `SignalPrismPalette`
- `SignalPrismGradientTokens`
- `SignalPrismSurfaceTokens`
- `SignalPrismToneTokens`
- `SignalPrismOverlayTokens`
- `SignalPrismMotionTokens`
- `SignalPrismRadiusTokens`
- `SignalPrismDensityTokens`
- `SignalPrismLightPalette`

The resolver derives Prism tokens from `SignalBrand`, appearance mode, and Prism personality.

## Tuned Light Palette

Light mode now passes through `SignalPrismLightPalette` before Material color roles are exposed. This fixes the washed-out look caused by near-white surfaces and low-chroma containers.

Default light roles:

- `surface`: `#F0F7FA`
- `surfaceContainerLow`: `#E7F1F5`
- `surfaceContainer`: `#DCEAF0`
- `surfaceContainerHigh`: `#CFE1E9`
- `surfaceContainerHighest`: `#BED4DE`
- `onSurface`: `#061A2B`
- `onSurfaceVariant`: `#435967`
- `inverseSurface`: `#061A2B`

The palette is overrideable through `SignalPrismProfile.lightPalette`, so a white-label bank can make the light mode warmer, cooler, denser, or more conservative without editing components.

See `docs/prism/light-palette-tuning.md`.

## New Prism Background Tokens

Added to `SignalPrismGradientTokens`:

- `library`
- `prismGradientStart`
- `prismGradientEnd`
- `prismBackgroundPattern`
- `prismRadialPrimary`
- `prismRadialSecondary`
- `prismHeroGradient`
- `prismAccountGradient`
- `prismPaymentGradient`
- `prismAccentGradient`
- `prismSuccessGradient`
- `prismCampaignGradient`
- `prismAmbientWash`
- `prismCardGlow`
- `prismNavSheen`

These tokens allow components and SDK demos to create Prism atmosphere without hardcoding Neptune colors.

## Gradient Library

`SignalPrismGradientLibrary` is the canonical gradient policy surface.

It exposes:

- `heroGradient`
- `ritualGradient`
- `fxGradient`
- `campaignOverlayGradient`
- `disabled`

Only three expressive zones are approved:

- hero/account stage
- transfer ritual radial/background
- FX/currency hero or rate ritual zones
- campaign image overlay

All operational controls, dense financial panels, navigation chrome, status summaries, limits, and transactions should use tonal Prism surfaces instead of local gradients.

See:

- `docs/prism/gradient-audit.md`
- `docs/prism/gradient-library.md`
- `docs/prism/gradient-usage-decisions.md`

## New Prism Nav Shape Tokens

Added to `SignalPrismRadiusTokens`:

- `prismNavShapeCradleWidth`
- `prismNavShapeCradleDepth`
- `prismRadiusFloatingNav`

These are geometry tokens for the Prism floating dock cradle. Banks can tune the cut-out width, depth, and corner radius without changing component code.

Default geometry:

- radius: `36.dp`
- cradle width: `96.dp`
- cradle depth: `26.dp`

The center action is intentionally detached from the dock and uses solid color plus elevation, not a gradient.

## Quick Transfer Tone

`SignalPrismToneTokens.quickTransferContainer` and `onQuickTransferContainer` define the repeat-transfer/quick-transfer surface. This keeps the card bank-branded and high contrast without reintroducing beige or washed-out local tints. The token derives from the active bank/Prism palette:

- Light: a controlled secondary/payment container tint with dark readable text.
- Dark: a richer tonal payment container on the Prism surface.
- OLED: a near-black tonal payment container with strong text contrast.

Components should consume this token instead of choosing local `surfaceRaised`, beige, or gradient backgrounds.

## Dynamic Bank Gradient Logic

Prism palette generation currently blends the default Neptune Prism palette with bank brand colors:

- `prismDeepNavy` blends toward bank primary.
- `prismOcean` blends toward bank secondary.
- `prismCyan` blends toward bank secondary with reduced influence.
- `prismViolet` blends toward a primary/accent mix.
- `prismCoral` blends toward bank accent.

The blend strength is personality-aware:

- Classic Bank and Corporate Dense allow stronger bank dominance.
- Premium Wallet keeps more Neptune identity.
- Islamic Calm keeps restrained emerald/teal influence.
- Youth and Merchant modes allow more expressive accents.

## Background Component

`SignalPrismBackground` is the reusable SDK background layer.

It provides:

- a background-safe vertical gradient
- soft radial accents
- large low-opacity arcs
- dark/light/OLED appearance control
- intensity control
- no Nova dependency

It should be used for high-level surfaces and demos, not inside every component.

## Usage

```kotlin
SignalTheme(settings = settings) {
    SignalPrismBackground {
        ScreenContent()
    }
}
```

## Guardrails

- Never place small text directly on animated or high-energy gradients.
- Use Prism gradients only as hero/account stage, transfer ritual background, or campaign image overlay.
- Do not add local component gradients when a tonal surface, icon well, border, or type hierarchy can carry the state.
- Backend or demo campaign `gradientStops` must be treated as advisory or migration-only until resolved through SDK gradient policy.
- Bank branding can dominate, but accessibility wins over brand colors.
- Light mode must not wash out text.
- OLED must reduce glow and avoid muddy blue/green surfaces.
- Cards and account surfaces should consume resolved Prism/bank tokens, not local random colors.

## Implemented In This Batch

Added:

- `SignalPrismBackground`
- `SignalPrismPaymentVisual`
- first-class background/radial tokens
- first-class cradle geometry tokens

Nova now uses `SignalPrismPaymentVisual` in the transfer flow.

## Next Implementation Step

Move the Prism floating navigation implementation from capsule-only to a policy-driven shape system that can support:

- default capsule
- compact dock
- token-driven cradle dock

The cradle renderer is enabled for Prism floating navigation when a center action exists. Future bank profiles can tune geometry through the nav shape tokens.
