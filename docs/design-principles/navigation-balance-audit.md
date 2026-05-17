# Navigation Balance Audit

Status: Batch 09 draft

## Findings

The shell needs a wallet-native rhythm:

- four stable root tabs,
- one central money-movement entry point,
- no extra fifth root,
- no hidden labels for banking roots,
- no generic oversized FAB floating over content.

## Implemented Rules

- `SignalBottomNav` reserves a center slot when a center action exists.
- Root tab semantics expose selected state.
- Center action exposes button semantics.
- Rail background uses softer tonal layering.
- Center action scale feedback is restrained and immediate.

## Open QA

- Capture Home, Accounts, Cards, and More in light/dark/OLED.
- Confirm the center action does not cover labels at 375dp width.
- Confirm RTL labels remain readable.
- Confirm nested routes hide the nav when full task focus is better.
- Add UI test assertions for the center action accessible label.

## Rejected

- icon-only root nav,
- dark navy nav rail as a second hero,
- unstable center action labels per screen,
- direct payment execution from the center action.

