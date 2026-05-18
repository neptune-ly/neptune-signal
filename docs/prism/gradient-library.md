# Prism Gradient Library

Date: 2026-05-17

## Purpose

The Prism gradient library defines the small set of gradient roles that may survive the Gradient Control Pass. It keeps brand expression available without letting every component invent a local `Brush.linearGradient`.

The current SDK library lives in `SignalPrismGradientLibrary`:

- `heroGradient`
- `ritualGradient`
- `campaignOverlayGradient`
- `disabled`

Legacy tokens remain for compatibility, but new component work should select one of these library roles or use a tonal surface.

## Library Roles

### Hero Gradient

Use for:

- account stage artwork
- card hero artwork
- first-viewport product identity moments
- compact previews of those hero/account stages

Do not use for:

- list rows
- action cells
- status panels
- navigation chrome
- transaction groups

Required protections:

- readable text zone or scrim
- contrast check in light, dark, and OLED
- bank palette safety clamp

### Ritual Gradient

Use for:

- transfer ritual radial field
- payment confirmation or in-progress visual field
- payment-focused background where motion and feedback are the primary content

Do not use for:

- normal transfer list rows
- persistent nav buttons unless the product explicitly accepts a tokenized payment surface
- generic accent surfaces

Required protections:

- radial or low-noise background treatment
- no small dense text directly on high-energy color
- intensity reduced in OLED and light mode

### Campaign Overlay Gradient

Use for:

- campaign image overlay
- campaign image fallback when backend media is missing
- campaign detail art when copy needs protection

Do not use for:

- generic promotional cards without media
- service list items
- account/card controls
- arbitrary backend-provided decorative stops

Required protections:

- copy-safe overlay direction, usually vertical
- text remains on the protected side of the image
- backend/demo stops are normalized through SDK policy

### Disabled

Use for:

- all components outside allowed zones
- bank profiles that request minimal styling
- accessibility modes that reduce visual noise

## Legacy Token Mapping

| Existing token | Library role | Policy |
| --- | --- | --- |
| `prismHeroGradient` | `heroGradient` | Keep as compatibility alias. |
| `prismAccountGradient` | `heroGradient` | Keep for account/card stage only. |
| `prismPaymentGradient` | `ritualGradient` | Keep for transfer ritual only; navigation payment actions use solid containers. |
| `prismCampaignGradient` | `campaignOverlayGradient` | Keep for campaign media overlays only. |
| `prismAccentGradient` | `disabled` by default | Convert component usage to tonal accents. |
| `prismSuccessGradient` | `disabled` by default | Use success tone/container instead. |
| `prismAmbientWash` | `disabled` by default | Only high-level background atmosphere may opt in. |
| `prismCardGlow` | `heroGradient` support only | Use as artwork support, not standalone glow. |
| `prismNavSheen` | `disabled` | Navigation should be tonal. |

## Component Consumption Rule

Components should not assemble expressive stops locally. They should ask one question:

Is this surface in an allowed gradient zone?

If yes, consume the matching library role. If no, use a tonal Prism surface with border, elevation, icon well, or typography hierarchy.

## Backend And Demo Payloads

Campaign payloads may include gradient intent, but not final rendering authority. Acceptable payload fields are profile-like decisions such as:

- campaign tone
- image presence
- preferred emphasis
- light/dark safety requirement

The SDK resolves final colors through `campaignOverlayGradient`. Raw `gradientStops` should be treated as demo-only or migrated to a validated profile.

## Review Checklist

- The component uses `heroGradient`, `ritualGradient`, `campaignOverlayGradient`, or no gradient.
- The use case is one of the allowed zones.
- Text contrast is protected by layout, scrim, or tonal surface.
- Light and OLED intensity is lower than dark mode.
- No operational control needs a gradient to communicate state.
