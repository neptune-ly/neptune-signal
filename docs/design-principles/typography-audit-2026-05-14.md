# Typography Audit - 2026-05-14

## Scope

Auth, entry, home, accounts, cards, and navigation typography were reviewed as one Signal system. The target remains Material 3 behavior with Neptune. Signal banking density.

## Findings

- Auth copy was using the same rhythm as regular screen rows. Entry screens need stronger hierarchy without becoming marketing pages.
- Button labels and critical actions need one stable Signal role instead of local screen overrides.
- Balance and financial values must keep tabular numeric treatment and stronger weight than metadata.
- Small labels are acceptable only when paired with enough contrast and stable row height.
- Arabic, English, and French must use the same role sizes; language should change direction and copy, not dimensions.

## Decisions

- Signal typography remains the source of truth for Nova. Nova may provide a bank font family, but not local text sizes.
- Auth title and panel copy use `screenTitle`, `rowMeta`, `fieldLabel`, `fieldValue`, and `button` from Signal.
- Critical banking values keep their existing Signal value-line rules: no vague ellipsis, copy/share beside exact values, tabular numbers for financial values.
- Future refinement should add explicit aliases for `authHeadline`, `authSupport`, and `legalBody` only if repeated product screens need them.

## Affected Contracts

- `SignalTypography`
- `SignalLoginPanel`
- `SignalAuthStateCard`
- `SignalTextField`
- `SignalButton`

## Regressions Avoided

- No return to oversized demo typography.
- No screen-local font sizing in Nova.
- No language-specific sizing drift.
