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
- `SignalSectionHeader`
- `SignalEmptyState`
- `SignalNotificationRow`

## Theme Model

```kotlin
data class SignalTheme(
    val colors: SignalColors,
    val typography: SignalTypography,
    val shapes: SignalShapes,
    val motion: SignalMotion
)
```

## Usage

```kotlin
SignalTheme {
    SignalAccountRow(
        name = "حساب الجاري",
        metadata = "نشط · LY2101",
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
    colors = SignalColorDefaults.whiteLabel(
        primary = Color(0xFFB4232A),
        secondary = Color(0xFF1A335E),
        accent = Color(0xFFD9A441)
    )
) {
    // Bank app UI
}
```

## Principles

- Tokens are generated from JSON.
- Components expose stable APIs.
- RTL and LTR are first-class.
- Motion constants are shared.
- Accessibility labels are required for icon-only actions.
- Product screens should compose patterns, not duplicate component internals.
