# Motion Contract Finalization - Batch 07

## Signal Motion Values

- Press: `80-160ms`
- Onboarding snap: `240ms`
- Onboarding indicator: `160ms`
- Auth handoff: `320ms`
- IME choreography: `180ms`
- Standard route: `240ms`
- Container transform: `460ms`
- Result: `320ms`

## Implemented Corrections

- Added `OnboardingSnapMs`.
- Added `OnboardingIndicatorMs`.
- Added `AuthHandoffMs`.
- Added `ImeChoreographyMs`.
- `SignalOnboardingCarousel` uses the onboarding snap timing for programmatic page changes.
- Onboarding active dot width now animates using Signal timing.

## Motion Rules

- Onboarding does not autoplay.
- Pagination animation is width/color only.
- Auth handoff should be subtle and fast.
- Keyboard entry must not wait for decorative motion.
- Reduced-motion support remains a required next hardening item.

## Rejected Patterns

- `transition: all`.
- Bouncy onboarding.
- Looping decorative auth motion.
- Large travel for every route.
- Animation that changes field height or padding.
