# Prism Cross-Screen Navigation Consistency

Date: 2026-05-17

## Goal

Prism navigation should feel like one app shell across Home, Accounts, Cards, Other, Notifications, and task/detail flows.

## Root Contract

Root destinations should share:

- the same bottom nav item order,
- the same active route keys,
- the same center payment action,
- the same bottom nav visual language,
- root top bars with no back button.

Recommended root keys:

- `home`
- `accounts`
- `cards`
- `more`

Notifications may be reached from Home or top-bar actions, but should not become a bottom root unless the product explicitly changes the root model.

## Detail Contract

Detail and task screens should:

- keep bottom nav hidden when the flow needs focus, or keep it visible only for shallow read-only detail,
- use top-bar `Back` or `Close`,
- avoid redefining the center payment action,
- return to the originating root where possible.

## Payment Contract

The center action is global money movement. It should:

- remain visually consistent on all root screens,
- use solid Prism payment tone,
- launch the transfer/payment entry flow,
- not be duplicated by a second floating action button on the same screen.

## Gradient Contract

Navigation consistency includes gradient restraint:

- bottom nav: tonal only,
- top bar: tonal only,
- center action: solid tone only,
- route surfaces: gradients only in allowed hero/ritual/campaign media zones.

## Audit Checklist

- Root tab order does not change between screens.
- Active root key is correct after deep links.
- Top bar navigation matches route depth.
- Center action is present only where the shell allows it.
- No screen adds local nav chrome to work around shell behavior.
