# KMP Implementation

Neptune. Signal is optimized for Kotlin Multiplatform and Compose.

## Package Direction

```text
ly.neptune.signal.theme
ly.neptune.signal.components
ly.neptune.signal.motion
ly.neptune.signal.patterns
```

Current package path:

```text
packages/kmp-compose
```

Current Gradle module:

```text
:packages:kmp-compose
```

## Current Components

The first SDK slice includes:

- `SignalTheme`
- `SignalColors`
- `SignalThemeConfig`
- `SignalBrand`
- `SignalBrandDefaults`
- `SignalColorMode`
- `SignalButton`
- `SignalIconButton`
- `SignalTopBar`
- `SignalBottomNav`
- `SignalBankingRow`
- `SignalAccountRow`
- `SignalAccountHeader`
- `SignalStatusResult`
- `SignalConsentScopeRow`
- `SignalCardFace`
- `SignalAppShell`
- `SignalScreen`
- `SignalTextField`
- `SignalAmountField`
- `SignalIbanField`
- `SignalSegmentedControl`
- `SignalTile`
- `SignalVoucherTile`
- `SignalServiceTile`
- `SignalAppearanceSelector`
- `SignalThemePresetList`
- `SignalThemePreset.toBrand`
- `SignalThemePreset.toThemeConfig`
- `SignalSectionHeader`
- `SignalEmptyState`
- `SignalNotificationRow`
- `SignalActionDock`
- `SignalInsightCard`
- `SignalAccountCarousel`
- `SignalAccountList`
- `SignalCardStack`
- `SignalCardDetailStage`
- `SignalMetricStrip`
- `SignalNotificationBanner`
- `SignalVoucherStore`
- `SignalVoucherValueSelector`
- `SignalSplash`
- `SignalPlanetMark`

## Theme Model

```kotlin
SignalTheme(
    config = SignalThemeConfig(
        brand = SignalBrandDefaults.Neptune,
        mode = SignalColorMode.Light
    )
) {
    BankingApp()
}

SignalTheme(
    brand = SignalBrandDefaults.Andalus,
    mode = SignalColorMode.Dark,
    shapes = SignalShapes(md = 16.dp, lg = 22.dp, xl = 28.dp),
    dimensions = SignalDimensions(buttonHeight = 52.dp)
) {
    BankingApp()
}
```

Core configuration types:

```kotlin
SignalThemeConfig
SignalBrand
SignalBrandDefaults
SignalColorMode
SignalColors
SignalTypography
SignalShapes
SignalDimensions
SignalMotion
```

## Usage

```kotlin
SignalTheme {
    SignalAccountRow(
        name = "حساب الجاري",
        accountType = "جاري",
        status = "نشط",
        iban = "LY810240010100006712020101",
        balance = "1,000,000 د.ل",
        onClick = { }
    )
}
```

App shell:

```kotlin
SignalAppShell(
    title = "الرئيسية",
    activeRoot = "home",
    navItems = navItems,
    onRootSelected = { route -> },
) {
    SignalScreen {
        SignalBankingRow(
            title = "حوالة واردة",
            metadata = "PAY-3921 · أمس 16:08",
            amount = "+450 د.ل",
            tone = SignalRowTone.Success
        )
    }
}
```

White-label theme:

```kotlin
SignalTheme(
    brand = SignalBrandDefaults.Andalus,
    mode = SignalColorMode.Light
) {
    // All Signal components inherit the bank colors globally.
}

SignalTheme(
    config = SignalThemeConfig(
        brand = SignalBrandDefaults.NCB,
        mode = SignalColorMode.Dark
    )
) {
    // Reusable app-level configuration.
}

SignalTheme(
    colors = SignalColorDefaults.whiteLabel(
        primary = Color(0xFFB4232A),
        secondary = Color(0xFF1A335E),
        accent = Color(0xFFD9A441),
        dark = false
    )
) {
    // Bank app UI
}

SignalTheme(
    colors = SignalColorDefaults.whiteLabel(
        primary = Color(0xFF8FC7FF),
        secondary = Color(0xFF4FD5D9),
        accent = Color(0xFFFFB3AE),
        dark = true
    )
) {
    // Dark bank app UI
}

SignalTheme(
    colors = SignalColorDefaults.whiteLabel(
        primary = Color(0xFF8FC7FF),
        secondary = Color(0xFF4FD5D9),
        accent = Color(0xFFFFB3AE),
        black = true
    )
) {
    // Black appearance app UI
}
```

Personalization:

```kotlin
SignalAppearanceSelector(
    selectedMode = SignalAppearanceMode.System,
    onModeSelected = onModeSelected
)

SignalThemePresetList(
    presets = SignalThemePresetDefaults.BankSamples,
    selectedKey = "neptune",
    onPresetSelected = onPresetSelected
)

val selectedPreset = SignalThemePresetDefaults.BankSamples.first { it.key == "ncb" }

SignalTheme(
    config = selectedPreset.toThemeConfig(mode = SignalColorMode.Dark)
) {
    BankingApp()
}
```

Splash:

```kotlin
SignalSplash(
    title = "Neptune. Signal",
    subtitle = "Powered by Neptune. Fintech",
    animated = true
)
```

## Component Examples

The GitHub Pages component gallery shows the current visual preview and KMP Compose snippet for each major SDK family:

- Theme roles and personalization.
- Splash / planet mark.
- App shell and navigation.
- Buttons and icon actions.
- Forms and critical value lines.
- Lists, banking rows, and account rows.
- Account carousel and account detail header.
- Card stack and card detail stage.
- Action dock, shortcuts, and insight cards.
- Voucher store and value selector.
- Identity and consent.
- Status result.
- Notifications and empty state.

[Open component gallery](../components/gallery.html)

## Principles

- Tokens are generated from JSON.
- Components expose stable APIs.
- RTL and LTR are first-class.
- Light, dark, and black modes are first-class for every bank preset.
- Motion constants are shared.
- Accessibility labels are required for icon-only actions.
- Product screens should compose patterns, not duplicate component internals.
- Bank colors are applied globally through `SignalTheme`, never by per-screen hardcoded overrides.
