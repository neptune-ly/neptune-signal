# Onboarding/Auth Transition Audit - 2026-05-15

## Orchestrator Summary

Batch 07 refines onboarding-to-login continuity without over-animating. The transition now uses a dedicated auth handoff timing instead of the generic route slide used by other screens.

## Findings

- Onboarding and login share the bank masthead and security language.
- The previous generic route slide made login feel like a different section.
- The correct transition is restrained: fade plus slight scale, not cinematic movement.
- The login screen should be ready for input immediately after the handoff.

## Implemented Corrections

- Added `SignalMotion.AuthHandoffMs`.
- Nova uses a dedicated Intro/Login transition:
  - fade in
  - slight scale in from `0.985`
  - quick fade/scale cleanup
- Other routes keep the existing route transition.

## Rejected Directions

- Hero morph that delays credential input.
- Large shared-element travel before the app has a formal shared transition system.
- Full-screen parallax or decorative onboarding movement.
- Route slide reused for every auth transition.

## Remaining Work

- Add a reusable Signal auth transition helper once auth scaffolding moves fully into Signal.
- Add reduced-motion handling for auth handoff in the shared motion layer.
