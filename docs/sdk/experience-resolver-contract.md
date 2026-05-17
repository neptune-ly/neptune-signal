# Experience Resolver Contract

`SignalPrismExperienceResolver` merges bank policy, user preference, accessibility state, and module/component policy.

## Inputs

- `SignalPrismBankExperiencePolicy`
- `SignalPrismUserPreference`
- `SignalPrismAccessibilityState`
- `SignalPrismComponentPolicy`
- `SignalPrismModulePolicy`

## Output

- `SignalPrismResolvedExperience`

## Guardrails

- User values outside bank allowed sets are rejected.
- Accessibility reduced motion overrides user motion.
- Accessibility reduced feedback turns sound and haptics off.
- Locked bank options stay locked.
