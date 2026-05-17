# Adaptive Layout Contract

## Purpose

Signal adaptive layout contracts prevent Nova and future bank apps from inventing screen-specific geometry. Compact adaptation is a product-quality feature, not a responsive afterthought.

## Current API

```kotlin
@Immutable
data class SignalAuthAdaptiveState(
    val compactHeight: Boolean,
    val keyboardVisible: Boolean,
    val fillContent: Boolean,
)
```

## Current Rules

- `compactHeight` is true when the available height is below the Signal auth breakpoint or the keyboard is visible.
- Compact mode reduces spacing before reducing information.
- Hero height yields before CTA/legal content is lost.
- Keyboard-visible state must make auth content scrollable.
- Signal metrics own breakpoints and heights.

## Signal Metrics

- `onboardingHeroHeight`
- `onboardingCompactHeroHeight`
- `onboardingMinHeight`
- `onboardingCompactMinHeight`
- `onboardingShortDeviceBreakpoint`
- `compactContentGap`

## Nova Boundary

Nova may:

- Pass localized copy.
- Pass bank profile/logo.
- Store route and selected slide state.
- Decide what auth step is active.

Nova must not:

- Invent onboarding hero heights.
- Use screen-local compact spacing hacks.
- Remove legal/support actions to fit a layout.
- Override Signal component structure.

## Future Work

- Promote Nova `AuthFrame` into a public `SignalAuthScaffold`.
- Add pinned auth footer region.
- Add IME-aware focus choreography.
