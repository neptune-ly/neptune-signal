# Central Payment Action Contract

Status: Draft 0.2
Owner: Neptune. Signal

## Name

Although this may look like a FAB, the Signal name is `SignalNavCenterAction`.

The word FAB is avoided in product specs because this is not a generic Android floating action button. It is the wallet shell's primary money-movement entry point.

## Purpose

Provide a stable, centered, thumb-friendly entry point for future wallet actions:

- Pay
- Transfer
- Scan
- QR
- Wallet

## Rules

- The action opens a flow. It never performs payment execution directly.
- It is shown only where persistent shell navigation is appropriate.
- It is hidden on focused nested task screens.
- It reserves physical space in bottom navigation.
- It uses a 68dp outer action and a 42dp inner optical plate by default.
- It must keep a minimum 48dp touch target.
- It exposes localized button semantics.

## Visual Treatment

- Surface: `bankSecondary` into `bankPrimary` tonal gradient.
- Glyph: `textInverse`.
- Border moat: app surface color, not pure white in dark mode.
- Motion: press scale only, no bounce or pulse.
- Shadow: stronger in light mode, restrained in dark/OLED.

## Motion

- Press: 80-120ms, scale down to roughly `0.972`.
- Route open: 240-280ms.
- Shared financial object transition: 420ms only when a real source object exists.

## Accessibility

- Labels for primary root tabs remain visible.
- Center action has a localized content description.
- It must not overlap snackbars, keyboard surfaces, legal text, or bottom safe area.
- It must remain distinguishable in light, dark, and black modes.

