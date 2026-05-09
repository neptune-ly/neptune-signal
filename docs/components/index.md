# Components

Neptune. Signal components are platform-neutral specifications. KMP is the first implementation target, but the component contract should also work for Flutter, native iOS, native Android, and web SDKs.

Components follow Material 3 implementation expectations for state, shape, touch target, typography, and accessibility. Signal adds banking data rules, white-label behavior, Arabic-first layout, and performance constraints.

## Use the Catalog

The component catalog is the working documentation surface for product teams:

- Preview the component visually.
- Check states and usage rules.
- Copy KMP Compose code directly into an app after adding the SDK.
- Use the same contract later for Flutter, native iOS, native Android, and web SDKs.

[Open the component gallery](gallery.html)

## Component Contract

Every component must define:

- Purpose.
- Anatomy.
- Variants.
- States.
- Tokens.
- Motion.
- Content rules.
- Accessibility.
- RTL/LTR behavior.
- KMP API.
- Future SDK mapping.
- Figma component properties.

[Open the component anatomy model](anatomy.html)

## Critical Value Components

Critical banking values use `SignalValueLine` or an equivalent platform component.

Values that must not truncate:

- Amount.
- IBAN.
- Alias.
- Account number.
- MTCN.
- Reference.
- Voucher PIN.
- Voucher serial.
- Consent or mandate ID.

Compact rows use semantic labels like `IBAN ending 0101`; detail and receipt surfaces show the full grouped value.

## Component Status

| Component | Standard | KMP 0.2 | Flutter | Native | Web |
| --- | --- | --- | --- | --- | --- |
| App Shell | Draft 0.2 | Available | Planned | Planned | Planned |
| Screen Container | Draft 0.2 | Available | Planned | Planned | Planned |
| Top App Bar | Draft 0.2 | Available | Planned | Planned | Planned |
| Bottom Navigation | Draft 0.2 | Available | Planned | Planned | Planned |
| Button | Draft 0.2 | Available | Planned | Planned | Planned |
| Icon Button | Draft 0.2 | Available | Planned | Planned | Planned |
| Segmented Control | Draft 0.2 | Available | Planned | Planned | Planned |
| List Group | Draft 0.2 | Available | Planned | Planned | Planned |
| List Item | Draft 0.2 | Available | Planned | Planned | Planned |
| Banking Row | Draft 0.2 | Available | Planned | Planned | Planned |
| Transaction Row | Draft 0.2 | Available | Planned | Planned | Planned |
| Account Row | Draft 0.2 | Available | Planned | Planned | Planned |
| Account Summary Row | Draft 0.2 | Available | Planned | Planned | Planned |
| Account Header | Draft 0.2 | Available | Planned | Planned | Planned |
| Account Carousel | Draft 0.2 | Available | Planned | Planned | Planned |
| Account List | Draft 0.2 | Available | Planned | Planned | Planned |
| Card Face | Draft 0.2 | Available | Planned | Planned | Planned |
| Card Stack | Draft 0.2 | Available | Planned | Planned | Planned |
| Card Detail Stage | Draft 0.2 | Available | Planned | Planned | Planned |
| Action Dock | Draft 0.2 | Available | Planned | Planned | Planned |
| Quick Action Button | Draft 0.2 | Available | Planned | Planned | Planned |
| Insight Card | Draft 0.2 | Available | Planned | Planned | Planned |
| Voucher Tile | Draft 0.2 | Available | Planned | Planned | Planned |
| Voucher Store | Draft 0.2 | Available | Planned | Planned | Planned |
| Voucher Value Selector | Draft 0.2 | Available | Planned | Planned | Planned |
| Service Tile | Draft 0.2 | Available | Planned | Planned | Planned |
| Form Field | Draft 0.2 | Available | Planned | Planned | Planned |
| Amount Field | Draft 0.2 | Available | Planned | Planned | Planned |
| IBAN Field | Draft 0.2 | Available | Planned | Planned | Planned |
| Value Line | Draft 0.2 | Available | Planned | Planned | Planned |
| Consent Overview | Draft 0.2 | Available | Planned | Planned | Planned |
| Consent Card | Draft 0.2 | Available | Planned | Planned | Planned |
| Consent Scope Row | Draft 0.2 | Available | Planned | Planned | Planned |
| Status Result | Draft 0.2 | Available | Planned | Planned | Planned |
| Status Mark | Draft 0.2 | Available | Planned | Planned | Planned |
| Appearance Selector | Draft 0.2 | Available | Planned | Planned | Planned |
| Theme Preset List | Draft 0.2 | Available | Planned | Planned | Planned |
| Notification Row | Draft 0.2 | Available | Planned | Planned | Planned |
| Notification Banner | Draft 0.2 | Available | Planned | Planned | Planned |
| Empty State | Draft 0.2 | Available | Planned | Planned | Planned |
| Splash / Planet Mark | Draft 0.2 | Available | Planned | Planned | Planned |
| Language Selector | Draft 0.2 | Planned | Planned | Planned | Planned |

## Families

### Shell

- App shell.
- Top app bar.
- Bottom navigation.
- Screen container.
- Section header.
- Appearance selector.
- Theme preset list.
- Splash / planet mark.

### Actions

- Primary button.
- Secondary button.
- Destructive button.
- Icon button.
- Toolbar action.
- Floating contextual action, only where justified.

### Data Rows

- Banking row.
- Transaction row.
- Account row.
- Account summary row.
- Card transaction row.
- Consent row.
- Notification row.
- Support ticket row.

### Financial Inputs

- Amount field.
- IBAN field.
- Alias field.
- OTP/passcode field.
- Search field.
- Select field.

### Banking Surfaces

- Account detail header.
- Account carousel.
- Account list.
- Card face.
- Card stack.
- Card detail stage.
- Voucher product tile.
- Voucher store.
- Voucher value selector.
- Service tile.
- Receipt card.
- Consent scope panel.
- Action dock.
- Insight card.

### Feedback

- Status mark.
- Result receipt.
- Inline error.
- Loading state.
- Empty state.
- Pending async state.
- Notification banner.

## KMP 0.2 Available APIs

The current SDK package includes:

```kotlin
SignalTheme
SignalThemeConfig
SignalBrand
SignalBrandDefaults
SignalColorMode
SignalColors
SignalTypography
SignalShapes
SignalDimensions
SignalAppShell
SignalScreen
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
SignalAccountCarousel
SignalAccountList
SignalTextField
SignalAmountField
SignalIbanField
SignalValueLine
SignalCopyGlyph
SignalShareGlyph
SignalAppearanceSelector
SignalThemePresetList
SignalThemePreset.toBrand
SignalThemePreset.toThemeConfig
SignalSegmentedControl
SignalTile
SignalVoucherTile
SignalServiceTile
SignalVoucherStore
SignalVoucherValueSelector
SignalStatusResult
SignalStatusMark
SignalConsentOverview
SignalConsentCard
SignalConsentScopeRow
SignalCardFace
SignalCardStack
SignalCardDetailStage
SignalActionDock
SignalQuickActionButton
SignalShortcutRow
SignalInsightCard
SignalMetricStrip
SignalNotificationBanner
SignalSplash
SignalPlanetMark
SignalSectionHeader
SignalEmptyState
SignalNotificationRow
```

## Demo Source

The first component set is extracted from the Mobile Banking Demo 0.2:

[Open interactive mobile demo](../demos/mobile/prototype.html)
