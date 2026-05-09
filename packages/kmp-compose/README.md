# Neptune. Signal KMP Compose SDK

Status: active SDK foundation. Version: `0.2.0`.

This is the first target implementation for Neptune. Signal.

Public modules:

```text
ly.neptune.signal.theme
ly.neptune.signal.components
ly.neptune.signal.motion
```

Core components and patterns:

- `SignalTheme`
- `SignalButton`
- `SignalIconButton`
- `SignalTopBar`
- `SignalBottomNav`
- `SignalListGroup`
- `SignalListItem`
- `SignalListDivider`
- `SignalBankingRow`
- `SignalTransactionRow`
- `SignalAccountRow`
- `SignalAccountSummaryRow`
- `SignalAccountHeader`
- `SignalAccountHeaderMode.DetailStage` for opened account detail headers
- `SignalStatusResult`
- `SignalConsentOverview`
- `SignalConsentCard`
- `SignalConsentScopeRow`
- `SignalCardFace`
- `SignalAppShell`
- `SignalScreen`
- `SignalTextField`
- `SignalAmountField`
- `SignalIbanField`
- `SignalValueLine`
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
- `SignalThemeConfig`
- `SignalBrand`
- `SignalBrandDefaults`
- `SignalColorMode`
- `SignalAccountCarousel`
- `SignalAccountList`
- `SignalActionDock`
- `SignalQuickActionButton`
- `SignalInsightCard`
- `SignalCardStack`
- `SignalCardDetailStage`
- `SignalMetricStrip`
- `SignalNotificationBanner`
- `SignalVoucherStore`
- `SignalVoucherValueSelector`
- `SignalSplash`
- `SignalPlanetMark`

## Current Usage

```kotlin
SignalTheme {
    SignalButton(
        label = "تحويل",
        onClick = { /* route to transfer */ }
    )
}
```

White-label theme with global bank colors:

```kotlin
val bankBrand = SignalBrand(
    key = "andalus",
    label = "Andalus Bank",
    primary = Color(0xFF07315F),
    secondary = Color(0xFF00A8AE),
    accent = Color(0xFF3BC1EE)
)

SignalTheme(
    brand = bankBrand,
    mode = SignalColorMode.Light
) {
    // All Signal components read primary, secondary, accent, surfaces, and status colors globally.
}
```

Reusable configuration object:

```kotlin
val signalConfig = SignalThemeConfig(
    brand = SignalBrandDefaults.Andalus,
    mode = SignalColorMode.Dark
)

SignalTheme(config = signalConfig) {
    BankingApp()
}
```

Customer personalization should still resolve into the same app-level configuration:

```kotlin
val selectedPreset = SignalThemePresetDefaults.BankSamples.first { it.key == "ncb" }

SignalTheme(
    config = selectedPreset.toThemeConfig(mode = SignalColorMode.Black)
) {
    BankingApp()
}
```

Direct color override:

```kotlin
SignalTheme(
    colors = SignalColorDefaults.whiteLabel(
        primary = Color(0xFFB4232A),
        secondary = Color(0xFF1A335E),
        accent = Color(0xFFD9A441),
        dark = false
    )
) {
    // App UI
}

SignalTheme(
    colors = SignalColorDefaults.whiteLabel(
        primary = Color(0xFF8FC7FF),
        secondary = Color(0xFF4FD5D9),
        accent = Color(0xFFFFB3AE),
        dark = true
    )
) {
    // Dark mode app UI
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

## Scope of 0.2.0

This release is the first usable component kit for the mobile app. It gives the next mobile project a stable starting point for:

- Theme tokens.
- Basic navigation shell.
- Banking rows.
- Account surfaces.
- Status/receipt pattern.
- Card face pattern.
- Form field patterns.
- Critical value presentation for IBAN, alias, amount, reference, voucher, and consent values.
- OpenWave/NPT Alias consent overview and consent card patterns.
- Tile patterns for vouchers and services.
- Shell/navigation pattern.
- Light/dark/black bank presets and personalization selectors.
- Global brand configuration from `SignalBrand`.
- Demo-matched mobile patterns: home action dock, account carousel/list, card stack/detail stage, voucher store, notification banner, and insight card.
- Neptune. Signal splash/planet mark component using Compose drawing and theme tokens.
- Bank-aware component color behavior: primary buttons, account surfaces, card stages, form focus, status receipts, and selection states inherit the configured brand safely across light, dark, and black modes.

The SDK should be consumed through `SignalTheme` and component APIs, not by copying internal styles.

## Account Open Motion

Account details should use a shared primary surface. In Compose, render the opened header with:

```kotlin
SignalAccountHeader(
    accountName = "حساب بالعملة الليبية",
    balance = "د.ل 1,000,000",
    iban = "LY810240010100006712020101",
    alias = "mohamed@andalus",
    mode = SignalAccountHeaderMode.DetailStage,
)
```

Use `SignalMotion.containerTransformSpec()` for the card-to-detail container transform, `SignalMotion.containerChromeSpec()` for title/chrome entrance, and delay secondary content by `SignalMotion.ContainerContentDelayMs`.

## Card Open Motion

Card details should carry the selected card surface forward. The list card and detail card use the same scheme, masked number, and background color. Use `SignalMotion.cardContainerTransformSpec()` for the selected card surface, then reveal live balance, controls, and recent card transactions after the card lands.
