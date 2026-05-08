# KMP Implementation

Neptune. Signal is optimized for Kotlin Multiplatform and Compose.

## Package Direction

```text
ly.neptune.signal.theme
ly.neptune.signal.components
ly.neptune.signal.motion
ly.neptune.signal.patterns
```

## Theme Model

```kotlin
data class SignalTheme(
    val colors: SignalColors,
    val typography: SignalTypography,
    val shapes: SignalShapes,
    val motion: SignalMotion
)
```

## Principles

- Tokens are generated from JSON.
- Components expose stable APIs.
- RTL and LTR are first-class.
- Motion constants are shared.
- Accessibility labels are required for icon-only actions.
- Product screens should compose patterns, not duplicate component internals.

