# SDK Architecture

Neptune. Signal is a design standard first. SDKs are implementation packages that follow the standard.

## Version Model

```text
Neptune. Signal Standard 0.1.0
KMP SDK 0.1.0
Flutter SDK planned
iOS SDK planned
Android SDK planned
Web SDK planned
```

The standard can evolve independently from SDK releases. SDKs must declare which standard version they implement.

## Package Strategy

Each platform gets its own package, but all packages share the same component contract.

```text
packages/
  tokens/             Source token data
  kmp-compose/        First implementation target
  flutter/            Planned
  native-ios/         Planned SwiftUI
  native-android/     Planned Android Compose
  web/                Planned React/Web Components
```

## Shared Contract

Every SDK component should map to the same standard component:

```text
SignalButton
SignalIconButton
SignalTopBar
SignalBottomNav
SignalBankingRow
SignalAccountRow
SignalAccountHeader
SignalAmountField
SignalIbanField
SignalStatusResult
SignalConsentScopeRow
SignalVoucherTile
SignalCardFace
```

## Implementation Priorities

Phase 1 status:

- Tokens: started.
- Theme: started.
- Buttons: started.
- Icon buttons: started.
- Top app bar: started.
- Bottom navigation: started.
- Banking row: started.
- Account row: started.
- Account header: started.
- Status result: started.
- Consent scope row: started.
- Card face: started.

Phase 2:

- Forms.
- Sheets.
- Segmented controls.
- Transaction rows.
- Voucher tiles.
- Card faces.
- Consent scope rows.
- Language selector.

Phase 3:

- Full banking patterns.
- Screen templates.
- Visual regression tests.
- Figma component parity.

## KMP First

KMP is the first supported SDK because Neptune. products can share logic and UI across Android, iOS, and desktop where appropriate.

The KMP SDK should be split into:

```text
ly.neptune.signal.theme
ly.neptune.signal.components
ly.neptune.signal.motion
ly.neptune.signal.patterns
ly.neptune.signal.icons
```

## Future SDK Rules

Flutter, iOS, Android, and Web SDKs should not invent new behavior. They should implement the same standard contract using platform-native conventions.

## Local Build

```bash
gradle :packages:kmp-compose:build
```

## Local Publish

```bash
gradle :packages:kmp-compose:publishToMavenLocal
```

Initial Maven coordinates:

```text
group: ly.neptune.signal
artifact: kmp-compose
version: 0.1.0
```
