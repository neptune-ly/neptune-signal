# White-Label Configuration System

Signal Prism is an engine, not one skin.

## Priority Order

1. Accessibility and security guardrails.
2. Bank/client policy.
3. Card scheme rules.
4. Product personality.
5. User preference.
6. Neptune defaults.

## Configuration Inputs

- bank identity and palette
- product personality
- allowed user moods
- allowed card modes
- allowed nav styles
- default appearance
- density and typography
- motion and feedback preferences
- module enablement

## SDK Output

`SignalPrismExperienceResolver` outputs `SignalPrismResolvedExperience`. Components should consume resolved values instead of inventing local style decisions.
