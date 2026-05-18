# Prism Top Bar Navigation Contract

Date: 2026-05-17

## Scope

This contract documents Prism top navigation behavior using the current Signal APIs:

- `SignalTopBarContract`
- `SignalTopBarNavigation`
- `SignalTopBarType`
- `SignalContractTopBar`
- `SignalPrismTopBar`
- `SignalPrismTopBarVariant`
- `SignalAppShell`

## Navigation Types

Use `SignalTopBarNavigation.None` for root destinations.

Use `SignalTopBarNavigation.Back` for pushed detail screens that return to the previous route.

Use `SignalTopBarNavigation.Close` for modal or interruptive flows that dismiss to the prior stable context.

Content descriptions must be localized by the app when English "Back" is not appropriate.

## Prism Variants

Current Prism variants:

- `Static`: default root/detail top bar.
- `Collapsing`: reserved for scroll-linked behavior.
- `FloatingCompact`: compact overlay-like treatment.
- `ContentFirst`: no top bar; content owns the first viewport.
- `Detail`: detail title treatment.

`SignalAppShell` currently uses `SignalPrismTopBarVariant.Static` when the bottom nav visual language is Prism.

## Route Rules

- Root screens: top navigation `None`.
- Detail screens: top navigation `Back`.
- Modal-like tasks: top navigation `Close`.
- Content-first hero screens: use `ContentFirst` only when content provides its own accessible title.
- Transaction and payment confirmation: prefer a stable title and explicit close/back behavior.

## Visual Rules

Top bars are flat/tonal surfaces.

Do not add:

- gradient top bars,
- decorative hero washes behind top bars,
- per-route custom nav icon styling,
- route-local title bars that duplicate shell chrome.

## Acceptance Checklist

- Every screen declares one top-bar navigation behavior.
- Root destinations do not show back controls.
- Back and close controls have clear semantics.
- Title and subtitle fit in one line each or truncate.
- Top bar visuals remain flat/tonal in all appearances.
