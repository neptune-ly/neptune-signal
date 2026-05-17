# Wallet Navigation Contract

Status: Draft 0.2
Owner: Neptune. Signal

## Purpose

Wallet navigation defines how primary banking roots and the primary money-movement action coexist without competing visually or semantically.

## Root Navigation

Root tabs remain:

- Home
- Accounts
- Cards
- More

These roots must stay stable across bank brands. Labels localize, but route ownership does not drift.

## Center Action

`SignalNavCenterAction` is an optional shell-level action.

Allowed purposes:

- Pay
- Transfer
- Scan
- QR
- Wallet

Rules:

- It is an entry point only.
- It never executes a payment directly.
- It must open a reviewable flow.
- It must be at least 48dp.
- It must reserve physical space in the tab row.
- It must not cover tab labels, snackbars, legal text, or keyboard content.
- It must be disabled or hidden when the current route requires full task focus.

## Semantics

Bottom navigation items expose tab semantics and selected state.

The center action exposes button semantics and a localized accessible label.

Labels remain visible for primary banking roots. Icon-only primary root navigation is not allowed.

## RTL

The root order remains product-stable. Directional icons inside labels or flow actions must mirror. Static icons do not mirror.

Swipe direction follows the active layout direction.

## Theming

The center action uses:

- `bankSecondary` for the action surface,
- `bankPrimary` for the inner tonal plate,
- `textInverse` for glyphs.

Bank brands may override these roles, but the structure, size, and behavior stay Signal-owned.

## Motion

Recommended timing:

- press compression: 80-120ms,
- center action route open: 240-280ms,
- shared financial surface transition: 420ms only when a copied surface exists.

Rejected:

- bouncing,
- pulsing,
- spinning,
- slow cinematic expansion,
- decorative payment animation before review.

