# Prism Quick Actions

Date: 2026-05-17

## Purpose

Quick actions must feel related, bank-branded, and touchable without turning into a pastel grid. They should use tonal role blends, not local gradients or arbitrary pale backgrounds.

## Mapping

| Action family | Arabic examples | Prism role |
| --- | --- | --- |
| Transfer | `تحويل` | `payment` / `paymentContainer` |
| Receive / QR | `رمز QR`, `استلام` | violet secondary accent |
| Services / Bills / Wallets | `خدمات`, `محافظ و فواتير` | `accent` / `accentContainer` |
| Cards / Vouchers / Statements | `كروت`, `كشف` | emerald or gold operational accent |

## Surface Rule

Each cell uses:

- a base `prismSurface` or `prismSurfaceRaised`
- a small blend from the action accent
- an icon well using the same accent at a stronger alpha
- dark readable text from `prismTextPrimary`

This keeps actions distinct while preserving a single Prism family.

## Do Not

- Do not use gradients in quick actions.
- Do not use beige or near-white cells.
- Do not make every action the same color.
- Do not use low-contrast icon wells in light mode.

## White-Labeling

Banks can influence the colors through the bank palette and `SignalPrismLightPalette` blend weights. Product teams should not hardcode action-specific colors in Nova.
