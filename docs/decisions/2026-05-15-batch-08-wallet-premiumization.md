# Batch 08 Wallet Premiumization Decision

Date: 2026-05-15
Status: Accepted for bounded implementation
Scope: Neptune. Signal SDK and Nova visual shell

## Decision

Neptune. Signal will evolve toward a premium wallet-banking hybrid through system-owned tonal layering, softer surface hierarchy, and a first-class central money-movement action in the app shell.

The reference e-wallet design is used only for principles. We are not cloning its layout, visual identity, gradients, icons, card treatment, or copy.

## Rationale

The current Nova UI has the correct banking structure, but still risks feeling too rigid and too close to default Material Compose. Premium wallet apps feel smoother because their shell, surfaces, and primary action hierarchy are composed as one system, not as stacked components.

The central action belongs in Signal because it affects:

- bottom navigation geometry,
- touch target rules,
- safe-area spacing,
- route hierarchy,
- motion timing,
- accessibility semantics,
- white-label theming.

If Nova owns this locally, each bank app will drift.

## Accepted Changes

- Add `SignalNavCenterAction` to the SDK.
- Add `centerNavAction` to `SignalAppShell`.
- Add center-action sizing to `SignalComponentMetrics`.
- Reserve a physical center slot in `SignalBottomNav` so the action does not cover tab labels.
- Keep the action as a safe entry point only. It must open a flow such as Pay, Transfer, Scan, or Wallet. It must never execute money movement directly.
- Use soft tonal background layering in `SignalAppShell`.
- Soften dashboard action dock, quick actions, and insight surfaces through Signal components.

## Rejected Changes

- Copying the reference design.
- Adding neon lighting, loud gradients, crypto-style glow, or decorative glass effects.
- Adding business logic for payment execution.
- Creating a Nova-only floating button.
- Hiding bottom navigation labels for primary banking roots.
- Using opacity alone to communicate disabled or inactive states.

## Quality Gate

This batch is accepted only if:

- SDK builds.
- Nova Android builds.
- Center action has at least a 48dp touch target.
- Tab row reserves space for the center action.
- Root navigation remains Home, Accounts, Cards, More.
- Nested routes may hide bottom navigation when full task focus is required.
- RTL/LTR labels remain readable.
- Dark and OLED themes keep enough tonal separation.

