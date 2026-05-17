# Iconography Audit - 2026-05-14

## Scope

Auth icons, navigation icons, row icons, language controls, and passkey presentation were reviewed against Signal's Material 3 compatible touch-target contract.

## Findings

- Several icons had drifted upward in size after earlier tuning, making compact banking rows feel less precise.
- Auth language and modal close actions were visually smaller than the required interaction target.
- Passkey presentation needs to feel like a secure platform action, not a decorative icon.
- Directional icons must remain logical in RTL/LTR through `SignalIcon`, not local hardcoded arrows.

## Decisions

- `SignalComponentMetrics.standardIcon` is `26dp`.
- `SignalComponentMetrics.rowIconGlyph` is `24dp`.
- `SignalComponentMetrics.bottomNavIcon` remains `22dp` for Material navigation balance.
- `SignalComponentMetrics.smallActionIcon` is `20dp`.
- Touch targets remain `48dp` minimum, even when the drawn glyph is smaller.
- Auth passkey uses Signal icon sizing and centered composition instead of local one-off dimensions.

## Affected Contracts

- `SignalComponentMetrics`
- `SignalAuthMetrics`
- `SignalIcon`
- `SignalLoginPanel`
- `SignalAuthPasskeyButton`

## Follow-Up

- Add a formal `SignalLanguageButton` and `SignalTextAction` component so Nova stops hand-rolling modal and legal text actions.
