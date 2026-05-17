# Dashboard Hierarchy Recomposition

Date: 2026-05-15

## Target

Home should read as a premium wallet operating surface:

1. Who is using the app.
2. Current account state.
3. Wallet utilities.
4. Payment focus/context.
5. Concise financial insight and upcoming items.
6. Governed promotional/contextual surface.
7. Recent activity timeline.

## Corrections

- The account carousel is the financial focal anchor.
- The center nav action owns payment movement.
- Wallet action panels are utility-level, not primary CTA-level.
- Insights remain compact and secondary.
- Recent activity stays readable, but it should not interrupt the account-to-action flow.

## Anti-Patterns

- Equal visual weight for every section.
- Repeated transfer/payment buttons.
- Dashboard widget soup.
- Stacked cards that create admin-dashboard rhythm.
- Promotions that outrank account state, payment context, or activity.

## Signal Impact

Shortcut and dashboard utility components must feel useful but visually quieter than account/card/payment owners. Signal must preserve this hierarchy through tokens, row heights, contrast, and motion.

## Component Ownership

Signal owns reusable dashboard primitives:

- account carousel
- wallet action panel
- payment focus/context surface
- financial context modules
- promotional surfaces
- timeline rows

Nova supplies product data, localized copy, routing, product-surface ordering, and feature availability.
