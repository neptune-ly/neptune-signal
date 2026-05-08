# Design Principles

## 1. Own the Signal

Every screen should make the customer understand what matters now:

- Account state.
- Transaction status.
- Required action.
- Risk or approval.
- Next step.

## 2. Respect Banking Density

Banking UI is used repeatedly. It should be calm, dense, and clear.

Do not add decorative blocks when a row, section, or action would explain the work better.

## 3. Make White-Label Structural

Bank colors can change. Neptune. quality should not.

The theme changes color. The standard keeps:

- Spacing.
- Type.
- Component anatomy.
- Motion.
- Accessibility.
- Data-fetching rules.

## 4. Treat Motion as Navigation

Motion should explain where the customer went.

Good motion:

- Opens account row into account detail.
- Opens voucher into voucher purchase.
- Turns waiting into success or failure.

Bad motion:

- Decorative motion with no meaning.
- Heavy animation files.
- Slow transitions that block banking tasks.

## 5. Design for Real System Cost

Do not fetch expensive data until the customer asks for the page that needs it.

Examples:

- Card list does not fetch balances.
- Card details can fetch balance and latest card transactions.
- Async payment waits route to a status page instead of blocking forever.

