# Design Principles

## 1. Own the Signal

Every screen should make the customer understand what matters now:

- Account state.
- Transaction status.
- Required action.
- Risk or approval.
- Next step.

Signal is not decoration. It is the most important visible fact or action at that moment.

## 2. Respect Banking Density

Banking UI is used repeatedly. It should be calm, dense, and clear.

Do not add decorative blocks when a row, section, or action would explain the work better.

Dense does not mean cluttered. It means every element earns its place.

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

## 6. Build Once, Implement Everywhere

Components are standard contracts first. KMP, Flutter, native iOS, native Android, and web implementations should share the same anatomy and behavior.

Platform-specific polish is allowed. Platform-specific contradiction is not.

## 7. Make Trust Visible

Banking customers need evidence.

Show:

- Reference numbers.
- Status.
- Fees.
- Account source.
- Merchant or receiver.
- Date and time.
- Support/report action where needed.

Do not hide the information needed for a customer to trust the result.
