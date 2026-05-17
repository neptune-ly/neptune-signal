# Signal Prism Package Architecture

## Target Shape

`ly.neptune.signal` remains the SDK root. Prism should converge toward these packages:

- `theme/`: theme primitives, color modes, brand colors, typography, radius, spacing, surfaces.
- `prism/`: public Prism experience contracts, bank policy, user preference, resolver, appearance, mood, module policy.
- `components/`: reusable Compose UI components.
- `motion/`: durations, easing, transition specs, reduced-motion helpers.
- `feedback/`: sound, haptic, clipboard, secure clipboard, system-bar adapter interfaces.
- `format/`: money, currency, IBAN, PAN, date/time, and RTL-safe identifier formatting.
- `accessibility/`: semantic labels, identifier display helpers, touch/contrast/reduced-motion contracts.
- `navigation/`: future home for top-bar, bottom-nav, route-intent, shell contracts.
- `demo/`: future demo-only sample data and gallery surfaces.

## Current Shape

Current source is mostly:

- `components/`
- `theme/`
- `motion/`
- `format/`

This is buildable and usable, but broad component files such as `SignalBankingPatterns.kt` and `SignalPrismCards.kt` should be split over time.

## Migration Rule

Do not break Nova or other consumers by moving public symbols abruptly. Add canonical SDK packages first, then deprecate old package locations with migration notes.

## Public/Internal Boundary

Implementation helpers should be `private` or `internal`. Public APIs should be typed, immutable where possible, and callback-driven.
