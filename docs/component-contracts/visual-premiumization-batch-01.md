# Visual Premiumization Batch 01 Component Contract

Date: 2026-05-14

## Principle

Signal components should feel premium through hierarchy, restraint, stable data presentation, and carefully tuned density. Decorative graphics must not compete with banking information.

## Typography Contract

- `balance` remains the highest financial emphasis role, with tabular numerals.
- `pageTitle` and `screenTitle` are reserved for page, carousel, and hero surfaces.
- `rowTitle`, `rowMeta`, and `statusPill` define banking density and must not be replaced with local one-off sizes.
- Metadata should be readable but quiet; it must not compete with amounts, account names, card numbers, or primary actions.

## Navigation Contract

- Bottom navigation is a persistent control surface, not a hero.
- Active state uses tonal primary container, not strong solid brand color.
- Icons use `SignalComponentMetrics.bottomNavIcon`.
- Labels use a compact metadata role with strong weight for Arabic readability.

## Row Contract

- `SignalListGroup` owns the card-like surface.
- Rows inside a list group should be tonal and calm, not individually framed cards.
- Leading icons should clarify category without becoming colored blocks.
- Trailing financial values use stable width and tabular numerics.

## Account Carousel Contract

- Account carousel may be visually expressive because it is the primary dashboard object.
- IBAN must remain exact and copyable.
- Decorative orbit/signal texture must stay behind content and below information contrast.
- Default carousel height is tuned to preserve the first two customer-facing transactions on the home screen at common compact phone heights.

## Card Contract

- Card color and scheme mark provide identity.
- Card number, card name, and status remain readable first.
- Decorative texture opacity must stay low enough to avoid competing with numbers.
- Card list height should allow scanning more than one card without feeling like a poster.
- Card list visuals should feel flat, clear, and bank-brandable; avoid stacking extra panels behind cards.

## Dark Mode Contract

- Tonal surfaces should use lower alpha than light mode to preserve contrast.
- Borders should be present only where they separate layers, not as default row decoration.

## RTL Contract

- Directional icons and nav ordering remain driven by Compose layout direction.
- Numeric/card/IBAN values may use internal LTR composition while the surrounding layout remains RTL.
