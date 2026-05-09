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

                SignalAccountHeader(
                    accountName = "حساب بالعملة الليبية",
                    balance = "د.ل 1,000,000",
                    alias = "mohamed@andalus",
                    iban = "LY810240010100006712020101",
                    mode = SignalAccountHeaderMode.DetailStage,
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
val andalusBrand = SignalBrand(
    key = "andalus",
    label = "Andalus Bank",
    primary = Color(0xFF07315F),
    secondary = Color(0xFF00A8AE),
    accent = Color(0xFF3BC1EE),
)

SignalTheme(
    brand = andalusBrand,
    mode = SignalColorMode.Light
) {
    BankingHome()
}
```

Use Material role names when mapping into platform primitives, then use Signal component names for banking UI.

For app-wide reuse, keep one configuration object near your app root:

```kotlin
val signalConfig = SignalThemeConfig(
    brand = SignalBrandDefaults.Andalus,
    mode = SignalColorMode.Dark
)

SignalTheme(config = signalConfig) {
    BankingApp()
}
```

For customer personalization, presets convert into the same configuration model. The app should never fork component styling per screen.

```kotlin
val selectedPreset = SignalThemePresetDefaults.BankSamples.first { it.key == "ncb" }

SignalTheme(
    config = selectedPreset.toThemeConfig(mode = SignalColorMode.Black)
) {
    BankingApp()
}
```

## Appearance and Personalization

Every bank preset must provide light, dark, and black roles. Customer personalization is bounded to approved appearance choices.

```kotlin
val light = SignalColorDefaults.whiteLabel(
    primary = Color(0xFF07315F),
    secondary = Color(0xFF00A8AE),
    accent = Color(0xFFEB4E4D)
)

val dark = SignalColorDefaults.whiteLabel(
    primary = Color(0xFF6FB8FF),
    secondary = Color(0xFF4FD5D9),
    accent = Color(0xFFFFB3AE),
    dark = true
)

val black = SignalColorDefaults.whiteLabel(
    primary = Color(0xFF6FB8FF),
    secondary = Color(0xFF4FD5D9),
    accent = Color(0xFFFFB3AE),
    black = true
)

SignalTheme(colors = when (appearanceMode) {
    SignalAppearanceMode.Black -> black
    SignalAppearanceMode.Dark -> dark
    else -> light
}) {
    SignalAppearanceSelector(
        selectedMode = appearanceMode,
        onModeSelected = onAppearanceMode
    )

    SignalThemePresetList(
        presets = SignalThemePresetDefaults.BankSamples,
        selectedKey = selectedPreset,
        onPresetSelected = onPresetSelected
    )
}
```

Do not expose arbitrary unsafe color pickers inside production banking apps. Use approved presets and accent styles with contrast-tested light, dark, and black roles.

Primary buttons, account headers, card detail stages, form focus, selected rows, and receipt states must read from the global Signal theme. Screen code should not hardcode bank colors.

Splash and boot screens use the same token path:

```kotlin
SignalSplash(
    title = "Neptune. Signal",
    subtitle = "Powered by Neptune. Fintech",
    animated = true
)
```

## Motion Mapping

Account details and card details use container transforms instead of hard route cuts.

```kotlin
SignalMotion.containerTransformSpec()
SignalMotion.cardContainerTransformSpec()
SignalMotion.containerChromeSpec()
```

For cards, keep the selected card background, scheme mark, and masked number consistent between list and detail. Fetch balance and live card activity only after the detail screen is open.

## Identity and Consent Components

OpenWave and NPT Alias use dedicated components so a product does not collapse very different permissions into one generic list.

```kotlin
SignalConsentOverview(
    title = "مركز موافقات OpenWave",
    description = "قراءة بيانات، خصم متكرر، وموافقات دفع فورية",
    alias = "mohamed@andalus",
    defaultAccount = "IBAN ending 0101",
    metrics = listOf(
        SignalConsentMetric("قراءة بيانات", "2"),
        SignalConsentMetric("خصم متكرر", "3"),
        SignalConsentMetric("تحتاج تأكيد", "1")
    )
)

SignalConsentCard(
    title = "Budget Lens",
    description = "قراءة حسابات وأرصدة وحركات فقط",
    status = "نشط",
    kind = SignalConsentKind.DataAccess,
    tags = listOf(SignalConsentTag("قراءة فقط")),
    onClick = onOpenConsent
)
```

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
SignalTheme
SignalThemeConfig
SignalBrand
SignalBrandDefaults
SignalColorMode
SignalColors
SignalTypography
SignalShapes
SignalDimensions
SignalButton
SignalIconButton
SignalTopBar
SignalBottomNav
SignalAppShell
SignalScreen
SignalListGroup
SignalListItem
SignalListDivider
SignalBankingRow
SignalTransactionRow
SignalAccountRow
SignalAccountSummaryRow
SignalAccountHeader
SignalAccountCarousel
SignalAccountList
SignalTextField
SignalAmountField
SignalIbanField
SignalValueLine
SignalCopyGlyph
SignalShareGlyph
SignalStatusResult
SignalStatusMark
SignalConsentOverview
SignalConsentCard
SignalConsentScopeRow
SignalVoucherTile
SignalVoucherStore
SignalVoucherValueSelector
SignalServiceTile
SignalCardFace
SignalCardStack
SignalCardDetailStage
SignalAppearanceSelector
SignalThemePresetList
SignalActionDock
SignalQuickActionButton
SignalShortcutRow
SignalInsightCard
SignalMetricStrip
SignalNotificationBanner
SignalSectionHeader
SignalEmptyState
SignalNotificationRow
SignalTile
SignalSegmentedControl
SignalSplash
SignalPlanetMark
```

## Implementation Priorities

KMP 0.2 available:

- Tokens.
- Theme, brand presets, shapes, dimensions, and appearance modes.
- Material-compatible color roles and typography roles.
- Surface and state layer tokens.
- Buttons and icon buttons.
- Top app bar, bottom navigation, app shell, and screen container.
- List group, list item, banking row, transaction row, notification row, and empty state.
- Account row, account summary row, account header, account carousel, and account list.
- Card face, card stack, and card detail stage.
- Text field, amount field, IBAN field, segmented control, value line, copy glyph, and share glyph.
- Status result and status mark.
- Consent overview, consent card, and consent scope row.
- Voucher tile, service tile, voucher store, and voucher value selector.
- Action dock, shortcut row, quick action button, insight card, metric strip, notification banner.
- Splash and planet mark.

Phase 2:

- Modal bottom sheets.
- Search and select fields.
- OTP/passcode field.
- Account request forms.
- OpenWave detail screen templates.
- Language selector.
- Visual regression test fixtures.
- Generated preview app that renders every KMP component from the SDK module.

Phase 3:

- Screen templates.
- Visual regression tests.
- Figma component parity.
- Flutter, SwiftUI, native Android, and web component SDKs.

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
