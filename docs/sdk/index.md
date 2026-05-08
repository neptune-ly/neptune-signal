# SDK Architecture

Neptune. Signal is a design standard first. SDKs are implementation packages that follow the standard.

## Install KMP SDK

The first production target is Kotlin Multiplatform Compose.

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("ly.neptune.signal:kmp-compose:0.2.0")
}
```

For local development before public package publishing:

```bash
gradle :packages:kmp-compose:publishToMavenLocal
```

```kotlin
repositories {
    mavenLocal()
    mavenCentral()
}
```

## First Screen

```kotlin
@Composable
fun BankingHome() {
    val navItems = listOf(
        SignalNavItem("home", "الرئيسية") { },
        SignalNavItem("accounts", "الحسابات") { },
        SignalNavItem("cards", "البطاقات") { },
        SignalNavItem("more", "أخرى") { }
    )

    SignalTheme {
        SignalAppShell(
            activeRoot = "home",
            title = "الرئيسية",
            subtitle = "Neptune. Signal",
            navItems = navItems,
            onRootSelected = {}
        ) {
            SignalScreen {
                SignalAccountHeader(
                    accountName = "حساب بالعملة الليبية",
                    balance = "د.ل 1,000,000",
                    alias = "mohamed@andalus",
                    iban = "LY810240010100006712020101",
                    masked = false,
                    onCopyIban = {},
                    onCopyAlias = {}
                )

                SignalButton(
                    label = "تحويل",
                    onClick = {}
                )
            }
        }
    }
}
```

## Material-Compatible Theme

Neptune. Signal 0.2.0 exposes Material-compatible roles and banking aliases.

```kotlin
val andalusTheme = SignalColorDefaults.whiteLabel(
    primary = Color(0xFF07315F),
    secondary = Color(0xFF00A8AE),
    accent = Color(0xFFEB4E4D),
    ink = Color(0xFF071C2E),
)

SignalTheme(colors = andalusTheme) {
    BankingHome()
}
```

Use Material role names when mapping into platform primitives, then use Signal component names for banking UI.

## Component Copy Model

Every component page should provide:

- A visual preview.
- KMP Compose snippet.
- Token list.
- State list.
- Accessibility requirements.
- RTL/LTR behavior.
- Mapping names for future Flutter, iOS, Android, and web SDKs.

[Open component gallery](../components/gallery.html)

## Version Model

```text
Neptune. Signal Standard 0.2.0
KMP SDK 0.2.0
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
SignalListGroup
SignalListItem
SignalListDivider
SignalBankingRow
SignalTransactionRow
SignalAccountRow
SignalAccountSummaryRow
SignalAccountHeader
SignalAmountField
SignalIbanField
SignalValueLine
SignalStatusResult
SignalConsentScopeRow
SignalVoucherTile
SignalCardFace
```

## Implementation Priorities

Phase 1 status:

- Tokens: started.
- Theme: started.
- Material-compatible color roles: started.
- Material-compatible typography roles: started.
- Surface and state layer tokens: started.
- Buttons: started.
- Icon buttons: started.
- Top app bar: started.
- Bottom navigation: started.
- List group: started.
- List item: started.
- Banking row: started.
- Transaction row: started.
- Account row: started.
- Account summary row: started.
- Account header: started.
- Status result: started.
- Consent scope row: started.
- Card face: started.
- App shell: started.
- Text field: started.
- Amount field: started.
- IBAN field: started.
- Value line: started.
- Segmented control: started.
- Voucher tile: started.
- Service tile: started.
- Notification row: started.
- Empty state: started.

Phase 2:

- Modal bottom sheets.
- Search and select fields.
- OTP/passcode field.
- Account request forms.
- Language selector.
- Visual regression test fixtures.

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
version: 0.2.0
```
