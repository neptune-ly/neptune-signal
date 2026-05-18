# Prism Nav Bar Audit

Date: 2026-05-17

## Scope

Audited current navigation contracts and components:

- `SignalNavItem`
- `SignalNavCenterAction`
- `SignalBottomNavVisualLanguage`
- `SignalBottomNav`
- `SignalPrismFloatingNav`
- `SignalPrismPaymentAction`
- `SignalAppShell`
- `SignalPrismNavStyle`

## Current Read

Prism navigation currently supports a shell-owned floating dock with a center payment action. The dock is flat/tonal. The payment action is solid Prism tone, not a gradient. That aligns with the gradient-control rules.

The current Prism dock also renders a U-shaped cradle whenever a center action exists. Earlier docs described cradle as future or optional; current code has effectively made the center-action shape the active Prism implementation.

## Strengths

- Navigation is centralized in `SignalAppShell`.
- Root items are data-driven with icon slots.
- Center action is a typed contract, not a fifth root item.
- Active and inactive states use container/content contrast.
- `SignalPrismNavStyle` provides policy choices: `ClassicDock`, `FloatingDock`, `PaymentForward`, and `DenseOperational`.
- Payment action uses solid `prism.tones.payment` or `prism.tones.primary`.

## Risks

- Cradle behavior is currently coupled to `centerAction != null`, not an explicit shape mode.
- The cradle geometry uses local proportions instead of the documented radius tokens.
- Center payment action can be visually overused if screens also add a primary transfer button.
- Label truncation can hide meaning for localized root labels.
- The top bar and bottom nav can disagree on route identity if apps provide ad hoc keys.

## Gradient Decision

Navigation is a no-gradient zone.

Allowed:

- solid dock container,
- solid active item pill,
- solid center action,
- flat border,
- shadow/elevation tuned by appearance.

Not allowed:

- nav sheen,
- gradient center action,
- decorative background wash behind the dock,
- gradient active tabs.

## Recommended Navigation Rules

- Root tabs should be stable across root destinations.
- The center payment action is global money movement and does not replace a root tab.
- Use tonal state for selected root and semantic payment tone for the center action.
- Keep bottom nav near-opaque in light, dark, and OLED modes.
- Treat cradle rendering as a Prism shape policy to document and test explicitly.

## Acceptance Checklist

- Every root screen uses the same nav item order.
- Active root key matches the selected destination.
- Center action content description equals its label or a localized equivalent.
- Dock and payment action are flat/tonal.
- No navigation component depends on a gradient for hierarchy.
