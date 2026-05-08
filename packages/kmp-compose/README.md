# Neptune. Signal KMP Compose SDK

Status: active start. Version: `0.1.0`.

This is the first target implementation for Neptune. Signal.

Planned modules:

```text
ly.neptune.signal.theme
ly.neptune.signal.components
ly.neptune.signal.motion
ly.neptune.signal.patterns
ly.neptune.signal.icons
```

Initial component priority:

- `SignalTheme`
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

## Current Usage

```kotlin
SignalTheme {
    SignalButton(
        label = "تحويل",
        onClick = { /* route to transfer */ }
    )
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
    // App UI
}
```

## Scope of 0.1.0

This release is a foundation package, not the full final app kit. It gives the next mobile project a stable starting point for:

- Theme tokens.
- Basic navigation shell.
- Banking rows.
- Account surfaces.
- Status/receipt pattern.
- Card face pattern.
- Form field patterns.
- Tile patterns for vouchers and services.
- Shell/navigation pattern.

The SDK will grow from the mobile demo components into a full production component library.
