# Dashboard Composition Audit - 2026-05-14

## Scope

Reviewed the Nova Home screen and its Signal-owned components:

- `SignalAccountCarousel`
- `SignalActionDock`
- `SignalInsightCard`
- `SignalTransactionRow`
- `SignalBottomNav`

## Findings

The home dashboard is now structurally close to the desired Signal direction, but first-screen polish depends on strict interaction ownership and controlled decoration.

### Hierarchy

- Account carousel is correctly the primary financial surface.
- Transfer/action dock is correctly second.
- Spending insight remains compact and should not compete with the account balance.
- Latest activity is readable and visible without forcing an early scroll.

### Spacing

- Current rhythm is acceptable after prior metric tightening.
- Account carousel height is intentionally taller than a standard card because it must carry balance and full IBAN.
- Extra decorative signals must remain behind content and low alpha.

### Interaction Ownership

Critical issue: the account surface must not treat every child action as "open account details."

Rules:

- IBAN row copies IBAN.
- Mask control only masks/unmasks.
- Account title/status/balance open account details.
- Decorative/background areas are not required to open details if that creates accidental navigation.

## Decisions

- Removed broad parent click ownership from the account carousel panel.
- Added targeted click ownership to account identity, status, and balance regions.
- Kept IBAN copy isolated to the IBAN row.
- Reduced account decorative alpha slightly to keep IBAN/balance dominant.

## Affected Contracts

- `SignalAccountCarousel`
- `SignalAccountPanel`
- `SignalAccountPanelDecor`

## Regression Avoided

- Critical IBAN copy behavior remains separate from account open navigation.
- Mask/unmask does not need to navigate.
- No new local Nova dashboard style was introduced.
