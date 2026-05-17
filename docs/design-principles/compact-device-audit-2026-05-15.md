# Compact Device Audit - 2026-05-15

## Orchestrator Summary

Batch 07 moves compact-device behavior from ad hoc Nova layout decisions into Signal-owned auth metrics and adaptive state. The goal is not simple scaling; compact devices must preserve hierarchy, CTA reachability, legal access, and keyboard safety.

## Findings

- A raw physical `390x844` emulator override is not a valid dp simulation at Android density 420; it creates an artificially tiny viewport.
- A realistic 390dp simulation uses approximately `1024x2216` physical pixels at density 420.
- Onboarding must compress the hero before it squeezes CTA/legal rhythm.
- Login must keep the primary action and passkey action reachable when the keyboard appears.
- Legal links should remain visible after primary and secondary actions, not compete with them.

## Implemented Corrections

- Added `SignalAuthAdaptiveState`.
- Added compact auth metrics:
  - `compactContentGap`
  - `onboardingCompactHeroHeight`
  - `onboardingCompactMinHeight`
  - `onboardingShortDeviceBreakpoint`
- Nova `AuthFrame` now computes compact height and keyboard visibility once, then passes the adaptive state to content.
- `SignalOnboardingCarousel` accepts `compact` and adapts hero height plus text line limits.
- Nova intro uses compact spacing only through the adaptive state.

## Screenshots

- `/Users/mtellesy/GitHub/neptune-nova/build/auth-micro-polish-batch-07/04-compact-390dp-onboarding.png`
- `/Users/mtellesy/GitHub/neptune-nova/build/auth-micro-polish-batch-07/05-compact-390dp-login.png`
- `/Users/mtellesy/GitHub/neptune-nova/build/auth-micro-polish-batch-07/06-compact-390dp-keyboard.png`

## Regressions Avoided

- No screen-level magic compact constants in Nova onboarding.
- No scaling of text below the Signal typography contract.
- No removal of legal links to make compact layouts pass.
- No change to auth behavior.

## Remaining Work

- Add a real Signal `AuthScaffold` so Nova stops owning auth frame geometry.
- Add pinned footer support for future very-short devices.
- Add formal IME action and focus traversal to Signal fields.
