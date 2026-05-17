# Getting Started

## Minimal Setup

```kotlin
SignalTheme(
    brand = SignalBrandDefaults.Neptune,
    mode = SignalColorMode.Dark,
) {
    AppContent()
}
```

## Prism Experience Setup

```kotlin
val resolved = SignalPrismExperienceResolver.resolve(
    bankPolicy = bankPolicy,
    userPreference = userPreference,
)
```

Use the resolved experience to decide card mode, nav style, appearance, density, and feedback settings.
