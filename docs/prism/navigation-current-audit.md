# Navigation Current Audit

Date: 2026-05-17

## Scope

Audited:

- `SignalBottomNav`
- `SignalPrismFloatingNav`
- `SignalPrismPaymentAction`
- `SignalPrismPaymentIcon`
- `SignalTopBar`
- `SignalPrismTopBar`
- `SignalTopBarContract`
- `SignalAppShell`

## Current Behavior

Signal currently supports two bottom navigation visual languages:

- `SignalClassic`
- `Prism`

`SignalAppShell` switches between `SignalBottomNav` and `SignalPrismFloatingNav` through `SignalBottomNavVisualLanguage`.

The Prism dock is a rounded floating capsule with:

- appearance-aware surface colors
- active destination pill
- center payment action
- payment gradient based on Prism tones
- no cradle shape
- no explicit U-shaped cutout

Top bars are centralized enough for current Nova usage:

- `SignalTopBar` is the classic top bar.
- `SignalPrismTopBar` provides Static, Collapsing, FloatingCompact, ContentFirst, and Detail variants.
- `SignalTopBarContract` carries navigation type, title, subtitle, and content descriptions.

## Reusable Today

- Navigation components are Signal-owned and app-neutral.
- Root selection is callback based.
- Icons are slots, so app-specific icon sets do not leak into Signal.
- Prism nav style is tokenized by `SignalPrismNavStyle`.
- Payment action uses a Signal-owned payment icon, not a paper-plane icon.
- Top-bar back/close semantics are centralized by contract.

## Current Gaps

- The dock shape is rounded-only. The new Prism research asks for an optional U-shaped cradle, but previous Prism contracts explicitly rejected cradle blobs. This must be an opt-in shape mode, not the default.
- The nav does not expose cradle geometry even though token fields now exist for width/depth.
- The center action is always circular and always uses the same three-stop gradient expression.
- Top-bar collapsing behavior is variant-level, not a full scroll behavior object.
- App shell chooses top bar behavior, but route-level exceptions still exist in Nova.
- Light mode can still look too washed out when bank palettes are pale or highly saturated.

## Hardcoded / Risky Areas

- `SignalPrismFloatingNav` has fixed horizontal padding, height presets, active item height, and payment slot width.
- `SignalPrismPaymentAction` fixes button size to 70dp and icon to 30dp.
- Payment gradient is hardcoded as accent plus ocean blend. It respects bank tones indirectly but should route through `prismPaymentGradient`.
- `SignalPrismTopBar` uses centered titles by default. This works for Arabic retail screens but must remain configurable for future LTR and corporate apps.

## Prism Direction

Navigation should support three explicit SDK shapes:

- Floating capsule: default Prism dock.
- Compact dock: dense and operational.
- Cradle dock: optional, brand/demo-controlled, never a muddy blob.

The U-shaped cradle should be defined by tokens:

- `prismNavShapeCradleWidth`
- `prismNavShapeCradleDepth`

The default remains the capsule because it is safer and aligns with earlier Prism quality rules. The cradle variant can be added once screenshots prove it improves payment focus without making the nav look childish or see-through.

## Top-Bar Direction

Keep `SignalPrismTopBar`, but add a scroll behavior contract later:

- expanded title integrated into content when needed
- collapsed compact title on scroll
- no free-floating sticky titles
- route-level screens should consume the same contract

## Accessibility Guardrails

- Bottom nav labels and icons must remain readable over all scroll positions.
- Dock background must be opaque or near-opaque.
- Center payment action must have a 48dp+ touch target.
- Active state must not depend only on color.
- Back/close buttons must keep consistent content descriptions.

## Implementation Notes

This batch added Prism nav geometry tokens to `SignalPrismRadiusTokens`:

- `prismNavShapeCradleWidth`
- `prismNavShapeCradleDepth`

No cradle rendering was enabled yet. That should be a deliberate next step with visual review.
