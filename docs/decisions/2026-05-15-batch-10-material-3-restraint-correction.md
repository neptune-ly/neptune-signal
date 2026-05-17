# Batch 10 Material 3 Restraint Correction

Date: 2026-05-15
Status: Accepted

## Decision

The gradient-heavy wallet direction is stopped. Neptune. Signal will use Material 3 Expressive as a restraint framework: stronger hierarchy, better grouping, meaningful emphasis, and quieter motion.

## Why

The previous pass improved architecture but introduced visual drift:

- synthetic atmospheric circles,
- gradient nav/action surfaces,
- decorative depth,
- payment action risk of generic FAB behavior.

That weakens the premium banking goal.

## Accepted Corrections

- Remove decorative shell backdrop circles.
- Remove gradients from shell/nav/action/insight surfaces.
- Keep the center action API and geometry.
- Make the center action quieter and more integrated.
- Use solid token colors and tonal fills.
- Let typography, spacing, and hierarchy carry quality.

## Rejected Corrections

- Removing the center action contract entirely.
- Returning to a fully generic four-tab banking nav.
- Adding more visual effect to compensate for weak hierarchy.
- One-off Nova screen styling.

## Verification

Required:

- Signal SDK build.
- Nova Android build.
- Connected Android UI tests.
- Screenshot checkpoint.
- `git diff --check`.

