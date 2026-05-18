# Transfer Ritual Current Audit

Date: 2026-05-17

## Scope

Audited:

- `SignalPaymentRitual`
- `SignalPaymentRitualVisual`
- `SignalMotion`
- feedback cue contracts used by Nova transfer flows

## Current Behavior

`SignalPaymentRitual` already models payment progress as typed phases:

- Preparing
- Routing
- Verifying
- BankConfirmation
- SettlementConfirmation
- Completed
- Pending
- Failed
- Timeout

The phase model is reusable and app-neutral. It supports multiple execution paths for instant, synchronous, async callback, operational, and infrastructure-grade flows.

`SignalPaymentRitualVisual` currently renders:

- a central orbital/ring surface
- an animated sweep arc
- a phase icon
- optional lifecycle rail
- phase title and message

This is already closer to Prism than the old linear dot model, but it is not yet fully Prism-token driven.

## Reusable Today

- Phase model and execution paths are SDK-ready.
- Arabic copy is centralized through `SignalPaymentRitualCopy`.
- Feedback cue roles are typed.
- Motion durations are centralized in `SignalMotion`.
- Visual is app-neutral and does not import Nova routes.

## Current Gaps

- Ring colors still resolve mostly through generic `SignalColors` roles, not dedicated Prism radial tokens.
- The visual has no explicit bank-brand radial theme profile.
- Directionality is visual-neutral; it does not yet expose RTL-aware clockwise/counter-clockwise ritual behavior.
- The component triggers no feedback by itself. The app must coordinate haptics/sound externally.
- The lifecycle rail is useful but can become text-heavy on compact screens.
- There is no asset-first sound binding in common API; cue metadata exists, but platform playback is app-owned.

## Hardcoded / Risky Areas

- Arc geometry is fixed at 112dp and not exposed as a role or size preset.
- Inner icon container radius and size are fixed.
- Active sweep values are hardcoded per terminal state.
- Success/failure/pending colors are generic semantic roles. This is correct for meaning, but Prism needs a separate radial accent system for non-terminal phases.

## Prism Direction

Keep the existing phase model. Add a Prism payment visual layer that consumes:

- `prismRadialPrimary`
- `prismRadialSecondary`
- `prismPaymentGradient`
- reduced motion preference
- feedback adapter hooks

`SignalPrismPaymentVisual` now exists as a sibling to `SignalPaymentRitualVisual`. It consumes Prism radial tokens and keeps the existing phase/copy model. Nova can opt into the Prism visual while older white-label clients keep the current ritual.

## Accessibility Guardrails

- Do not rely on color alone for phase meaning.
- Keep title/message text visible outside the animation.
- Respect reduced motion by disabling sweep animation and using stepped state.
- Success/failure states must remain semantically distinct from brand colors.

## Implementation Notes

The new Prism radial tokens were added to `SignalPrismGradientTokens` and are consumed by `SignalPrismPaymentVisual`:

- `prismRadialPrimary`
- `prismRadialSecondary`

These should become the default non-terminal ring accents for the next Prism ritual component.
