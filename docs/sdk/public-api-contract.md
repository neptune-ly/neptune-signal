# Public API Contract

## Stable Entry Points

- `SignalTheme`
- `SignalPrismTheme` concept through `SignalTheme.prism`
- `SignalPrismProfile`
- `SignalPrismBankExperiencePolicy`
- `SignalPrismUserPreference`
- `SignalPrismExperienceResolver`
- `SignalPrismAppearance`
- `SignalPrismPersonality`
- `SignalPrismDensityPreference`
- `SignalPrismMotionPreference`
- `SignalPrismSoundPreference`
- `SignalPrismHapticPreference`

Canonical new policy imports should come from:

```kotlin
import ly.neptune.signal.prism.SignalPrismBankExperiencePolicy
import ly.neptune.signal.prism.SignalPrismExperienceResolver
import ly.neptune.signal.prism.SignalPrismUserPreference
```

## Component API Rules

- Components accept typed models or primitive display values.
- Components emit callbacks/events; apps resolve navigation and backend effects.
- Components must not import host app routes.
- Components must not own backend assumptions.
- Sensitive data components must accept policy objects.
- Demo data must not be embedded as default production behavior.

## Compatibility Rule

Existing component-package models remain usable during transition. New SDK integrations should prefer the `prism/`, `feedback/`, `format/`, and `accessibility/` package contracts.
