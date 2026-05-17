# Recomposition Audit

## Current Risks

- Large component files make it harder to reason about stable inputs.
- Several UI models are not annotated yet.
- Some components build `Brush` and list values inline.
- Carousels and payment visuals should be profiled on low-end Android.

## Mitigations Added

- New Prism resolver models are immutable.
- Platform adapter payloads are immutable.
- Identifier rendering helpers are pure functions and test-covered.

## Next Work

- Add `@Immutable` to card, service hub, notification, and campaign models where correct.
- Extract remembered brush helpers for Prism artwork.
- Add benchmark/smoke gallery screens for nav, card carousel, notification center, and payment ritual.
