# Auth Continuity Audit - 2026-05-15

## Orchestrator Summary

The auth entry should feel like one continuous premium fintech journey:

`Splash -> Onboarding -> Login -> OTP / Password / Terms`

Batch 06 connected onboarding and login through a shared bank masthead, security language, typography rhythm, and stable CTA hierarchy.

## Current State

Before this batch, the intro was stronger than a generic login screen but still too isolated. Login had its own security banner and form logic, while onboarding had a separate visual construction. The new carousel establishes a reusable Signal entry pattern.

## Continuity Rules

- The bank logo and bank name remain visible in onboarding and login.
- `Neptune. Signal` is supporting product language, not the main customer-facing bank identity.
- The secure-session story introduced in onboarding resolves into the login security banner.
- Language selection is visually consistent across entry screens.
- Login remains minimal and operational after onboarding creates confidence.

## Design Review Findings

- Login should not become a vertically centered default auth form.
- Onboarding should not become a promotional slide deck.
- Trust is communicated through verified device/session language, not decorative security art.
- White-label behavior is part of the story: the same Signal rhythm can carry each bank brand.

## UX Findings

- The login button is the primary action on every onboarding slide.
- The non-customer action remains secondary and stable.
- Legal links stay below the core CTAs.
- The login screen should inherit the selected language and direction from onboarding.

## Motion Findings

- Onboarding-to-login should feel like a controlled route transition with shared masthead continuity.
- Future work should add a subtle masthead/hero collapse handoff, but not block input readiness.
- Keyboard choreography belongs to the login form, not onboarding.

## Accessibility Findings

- Auth route changes must keep language and direction intact.
- Form errors need status/alert semantics.
- Carousel progress descriptions must be localized by Nova.
- CTAs remain full-width touch targets.

## Implemented Corrections

- Nova now passes localized progress labels to the Signal carousel.
- Nova retains selected onboarding slide state with `rememberSaveable`.
- The intro uses Signal's carousel component directly.
- The stale local intro console/loop composables were removed.

## Regressions Prevented

- No auth logic changes.
- No changes to OTP, password reset, terms routing, or passkey behavior.
- No one-off local sizing for carousel pagination.
- No decorative autoplay or long-running animation.

## Screenshot

- `/Users/mtellesy/GitHub/neptune-nova/build/onboarding-continuity-batch-06/04-login-continuity.png`

## Next Recommended Batch

Add a controlled shared transition contract from onboarding hero to login security banner once the route transition system is ready.
