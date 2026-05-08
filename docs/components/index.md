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

| Component | Standard | KMP | Flutter | Native | Web |
| --- | --- | --- | --- | --- | --- |
| App Shell | Draft 0.2 | Started | Planned | Planned | Planned |
| Top App Bar | Draft 0.2 | Started | Planned | Planned | Planned |
| Bottom Navigation | Draft 0.2 | Started | Planned | Planned | Planned |
| Button | Draft 0.2 | Started | Planned | Planned | Planned |
| Icon Button | Draft 0.2 | Started | Planned | Planned | Planned |
| Segmented Control | Draft 0.2 | Started | Planned | Planned | Planned |
| List Group | Draft 0.2 | Started | Planned | Planned | Planned |
| Banking Row | Draft 0.2 | Started | Planned | Planned | Planned |
| Transaction Row | Draft 0.2 | Started | Planned | Planned | Planned |
| Account Row | Draft 0.2 | Started | Planned | Planned | Planned |
| Account Summary Row | Draft 0.2 | Started | Planned | Planned | Planned |
| Account Header | Draft 0.2 | Started | Planned | Planned | Planned |
| Card Face | Draft 0.2 | Started | Planned | Planned | Planned |
| Voucher Tile | Draft 0.2 | Started | Planned | Planned | Planned |
| Service Tile | Draft 0.2 | Started | Planned | Planned | Planned |
| Form Field | Draft 0.2 | Started | Planned | Planned | Planned |
| Amount Field | Draft 0.2 | Started | Planned | Planned | Planned |
| IBAN Field | Draft 0.2 | Started | Planned | Planned | Planned |
| Consent Scope Row | Draft 0.2 | Started | Planned | Planned | Planned |
| Status Result | Draft 0.2 | Started | Planned | Planned | Planned |
| Value Line | Draft 0.2 | Started | Planned | Planned | Planned |
| Language Selector | Draft 0.2 | Planned | Planned | Planned | Planned |
| Notification Row | Draft 0.2 | Started | Planned | Planned | Planned |
| Empty State | Draft 0.2 | Started | Planned | Planned | Planned |

## Families

### Shell

- App shell.
- Top app bar.
- Bottom navigation.
- Screen container.
- Section header.

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
- Card face.
- Voucher product tile.
- Service tile.
- Receipt card.
- Consent scope panel.

### Feedback

- Status mark.
- Result receipt.
- Inline error.
- Loading state.
- Empty state.
- Pending async state.

## KMP 0.2 Started APIs

The current SDK package includes:

```kotlin
SignalTheme
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
SignalTextField
SignalAmountField
SignalIbanField
SignalValueLine
SignalSegmentedControl
SignalTile
SignalVoucherTile
SignalServiceTile
SignalStatusResult
SignalConsentScopeRow
SignalCardFace
SignalSectionHeader
SignalEmptyState
SignalNotificationRow
```

## Demo Source

The first component set is extracted from the Mobile Banking Demo 0.2:

[Open interactive mobile demo](../demos/mobile/prototype.html)
