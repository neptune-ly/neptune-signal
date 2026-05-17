# Theme Setup

Signal theme provides classic Signal tokens and Prism tokens.

```kotlin
SignalTheme(
    brand = bankBrand,
    mode = SignalColorMode.Dark,
) {
    val prism = SignalTheme.prism
}
```

## Appearance Modes

- Light
- Dark
- OLED/Black

Dark is the Prism flagship, but light and OLED must remain first-class.
