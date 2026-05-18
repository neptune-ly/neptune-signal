# Prism Navigation Spec

Date: 2026-05-17

## Purpose

This spec defines the documentation-only Prism navigation target for the current design pass.

## Components

Prism navigation is owned by Signal:

- `SignalAppShell`
- `SignalNavItem`
- `SignalNavCenterAction`
- `SignalPrismFloatingNav`
- `SignalPrismPaymentAction`
- `SignalPrismTopBar`
- `SignalTopBarContract`

Apps provide route keys, labels, icons, callbacks, and localized strings. Apps should not reimplement nav chrome.

## Root Navigation

Default root model:

| Key | Label intent | Purpose |
| --- | --- | --- |
| `home` | Home | Daily cockpit and summary. |
| `accounts` | Accounts | Account balances and account details. |
| `cards` | Cards | Card list, card detail, card controls. |
| `more` | More/Other | Services, profile, settings, approvals. |

The center action is not a root. It is global money movement.

## Visual Model

Prism dock:

- floating or dense tonal dock,
- light floating dock uses solid `inverseSurface`,
- dark/OLED floating dock uses solid deep Prism surface,
- token-driven center cradle when center action is present,
- selected item pill,
- stable labels,
- solid center payment action.

Final cradle defaults:

```text
              detached 76dp action
                    ●
          ┌─────────╲____╱─────────┐
          │                         │
          │  nav   nav      nav nav │
          └─────────────────────────┘

radius: 36dp
cradle width: 96dp
cradle depth: 26dp
center action offset: 0dp from shell top
```

The cut-out is a real dock path, not a glow or decorative blob. The center action floats above it with elevation and a solid payment color.

Top bar:

- flat Prism surface,
- root screens use no navigation icon,
- detail screens use back or close,
- title and subtitle are single-line with truncation.

## Style Policy

`SignalPrismNavStyle` maps product personality to nav emphasis:

- `ClassicDock`: restrained bank dock.
- `FloatingDock`: default Prism dock.
- `PaymentForward`: payment-forward wallet personality.
- `DenseOperational`: corporate or high-density utility.

All styles remain tonal. Style affects shape, density, and tone selection, not gradient usage.

## Gradient Policy

Navigation never uses expressive gradients.

Allowed visual tools:

- solid Prism tone containers,
- selected tonal pills,
- border opacity,
- shadow/elevation,
- icon and label weight.

Disallowed:

- nav sheen,
- gradient center action,
- gradient selected tabs,
- decorative page wash behind nav.

## Accessibility

- Center action touch target must remain at least 48 dp.
- Active state must include semantics and visible shape change.
- Labels should remain visible unless a product-specific compact mode defines another accessible pattern.
- Back/close controls need localized content descriptions.
- Dock contrast must hold in light, dark, and OLED.

## Implementation Notes

Current code renders a cradle-shaped Prism dock when `centerAction` is present. Geometry is controlled by `SignalPrismRadiusTokens.prismNavShapeCradleWidth`, `prismNavShapeCradleDepth`, and `prismRadiusFloatingNav`, allowing banks to tune the cut-out without changing app code. Future implementation should add a separate explicit cradle policy only if banks need multiple center-action shapes beyond token tuning.

The latest tuning increases the center action to `76.dp`, reserves `88.dp` in the dock row, and raises light-mode elevation so the action reads as detached instead of embedded.
