# Onboarding Hero Audit - 2026-05-15

## Orchestrator Summary

Batch 06 moves onboarding from a static intro poster to a reusable Signal-owned onboarding hero carousel. The approved direction is a three-state product-native banking narrative:

1. Secure session.
2. Everyday banking.
3. White-label bank identity.

The carousel is not a decorative marketing gallery. It is an entry journey that introduces trust, action, and bank customization while keeping the login call to action stable.

## Composition Analysis

The previous intro had stronger hierarchy than earlier auth work, but it still behaved like one large static hero. The missing layer was progression: the user had no sense that Nova has a broader banking system behind the login.

The approved structure is:

`Masthead -> Hero stage -> Headline/body -> Progress indicator -> Primary CTA -> Secondary CTA -> Legal links`

The masthead and CTA stack remain stable across slides. Only the hero and message change.

## Design Review Findings

- The hero must use product-native banking objects: secure session, transfer rails, bank identity, account/card surfaces, approvals.
- Generic stock illustrations, random 3D objects, and abstract decorative onboarding art are rejected.
- The slide system must feel calm and engineered, not playful.
- The CTA text must remain stable across slides so onboarding does not change the user's action model.
- The login screen must resolve into the secure-session story instead of feeling like a separate template.

## UX Findings

- Three slides are enough for the current product story.
- Four slides should only be added if privacy/legal needs its own explicit onboarding state.
- The selected slide is stateful and survives recomposition in Nova.
- Onboarding should not collect credentials; login remains the input-bearing screen.

## Motion Findings

- Carousel motion should use pager snap behavior, not a raw horizontal scroll row.
- Indicator movement must be quiet and fast.
- CTA placement must not jump between slides.
- Reduced motion should preserve state changes without relying on travel.

## Accessibility Findings

- The onboarding carousel needs an accessible progress description.
- Pagination targets must be larger than the visual dot size.
- RTL must mirror visual flow without changing canonical slide order in product documentation.
- Slide changes must not move focus unexpectedly.

## Implemented Corrections

- Added `SignalOnboardingSlide`.
- Added `SignalOnboardingCarousel`.
- Added an onboarding-specific interactive pagination indicator with 44dp targets.
- Kept account/card carousel dots unchanged to avoid unintended layout drift.
- Moved the old Nova-local hero illustration out of the intro path.

## Regressions Prevented

- No Nova-local carousel implementation.
- No stock illustration dependency.
- No heavy animation asset.
- No change to existing auth routing or credential logic.
- No duplicated login CTA behavior per slide.

## Screenshots

- `/Users/mtellesy/GitHub/neptune-nova/build/onboarding-continuity-batch-06/01-onboarding-slide-1.png`
- `/Users/mtellesy/GitHub/neptune-nova/build/onboarding-continuity-batch-06/02-onboarding-slide-2.png`
- `/Users/mtellesy/GitHub/neptune-nova/build/onboarding-continuity-batch-06/03-onboarding-slide-3.png`

## Next Recommended Batch

Refine hero artwork per slide so each state has a clearer product-specific visual while preserving the same Signal layout contract.
