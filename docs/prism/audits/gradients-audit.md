# Prism Gradients Audit

Date: 2026-05-17

## Scope

This document is the current Prism pass audit for gradient placement across Home, Other, Notifications, top navigation, bottom navigation, cards/accounts, campaigns, and transfer ritual surfaces.

It complements:

- `../gradient-audit.md`
- `../gradient-library.md`
- `../gradient-usage-decisions.md`

## Canonical Rule

Gradients are allowed only in these zones:

1. Hero/account/card artwork where the gradient is the primary identity surface.
2. Transfer ritual visual fields where payment progress is the primary content.
3. Campaign media overlays or media fallbacks where copy needs protection.

Everything else should be flat or tonal.

## Current Alignment

Aligned surfaces:

- `SignalPrismFloatingNav` uses tonal dock surfaces.
- `SignalPrismPaymentAction` uses solid payment/primary tones.
- `SignalPrismNotificationCenter` uses tonal tabs and rows.
- `SignalPrismServiceGroup` and service rows use raised Prism surfaces.
- Home timeline and insight components use tonal containers and icon wells.
- `SignalPrismCampaignDetail` is currently tonal, so it does not violate campaign overlay rules.

Allowed gradient-like surfaces remain in:

- card/account artwork brushes,
- card/account text-protection scrims,
- Prism payment ritual radial colors,
- campaign media/fallback policy.

## Watch List

- `SignalPrismCampaign.gradientStops` and demo payloads remain risky if rendered directly.
- Card/account brush creation is allowed, but should stay behind account-stage policy.
- Any future Home banner using gradient stops must be reviewed as campaign media, not a generic card.
- Control Center preview gradients must remain preview swatches only.

## Explicit Bans

Do not add gradients to:

- navigation bars,
- top bars,
- action buttons,
- quick action cells,
- notification rows,
- service rows,
- settings rows,
- status panels,
- transaction lists,
- metric cards,
- empty states.

## Review Checklist

- Is the surface one of the three allowed zones?
- Is text protected by a scrim or placed outside the gradient field?
- Does light mode avoid washed-out low contrast?
- Does OLED reduce glow and muddy color mixing?
- Is the gradient resolved by Prism policy rather than local color stops?
- Could a tonal surface, border, icon well, or typography hierarchy communicate the same state?
