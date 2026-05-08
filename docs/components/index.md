# Components

Neptune. Signal components are platform-neutral specifications. KMP is the first implementation target, but the component contract should also work for Flutter, native iOS, native Android, and web SDKs.

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

## Component Status

| Component | Standard | KMP | Flutter | Native | Web |
| --- | --- | --- | --- | --- | --- |
| App Shell | Draft 0.1 | Planned | Planned | Planned | Planned |
| Top App Bar | Draft 0.1 | Planned | Planned | Planned | Planned |
| Bottom Navigation | Draft 0.1 | Planned | Planned | Planned | Planned |
| Button | Draft 0.1 | Planned | Planned | Planned | Planned |
| Icon Button | Draft 0.1 | Planned | Planned | Planned | Planned |
| Segmented Control | Draft 0.1 | Planned | Planned | Planned | Planned |
| Banking Row | Draft 0.1 | Planned | Planned | Planned | Planned |
| Account Row | Draft 0.1 | Planned | Planned | Planned | Planned |
| Account Header | Draft 0.1 | Planned | Planned | Planned | Planned |
| Transaction Row | Draft 0.1 | Planned | Planned | Planned | Planned |
| Card Face | Draft 0.1 | Planned | Planned | Planned | Planned |
| Voucher Tile | Draft 0.1 | Planned | Planned | Planned | Planned |
| Service Tile | Draft 0.1 | Planned | Planned | Planned | Planned |
| Form Field | Draft 0.1 | Planned | Planned | Planned | Planned |
| Amount Field | Draft 0.1 | Planned | Planned | Planned | Planned |
| IBAN Field | Draft 0.1 | Planned | Planned | Planned | Planned |
| Consent Scope Row | Draft 0.1 | Planned | Planned | Planned | Planned |
| Status Result | Draft 0.1 | Planned | Planned | Planned | Planned |
| Language Selector | Draft 0.1 | Planned | Planned | Planned | Planned |
| App Shell SDK | Draft 0.1 | Started | Planned | Planned | Planned |
| Text Field | Draft 0.1 | Started | Planned | Planned | Planned |
| Amount Field | Draft 0.1 | Started | Planned | Planned | Planned |
| IBAN Field | Draft 0.1 | Started | Planned | Planned | Planned |
| Segmented Control SDK | Draft 0.1 | Started | Planned | Planned | Planned |
| Voucher Tile SDK | Draft 0.1 | Started | Planned | Planned | Planned |
| Service Tile SDK | Draft 0.1 | Started | Planned | Planned | Planned |
| Notification Row | Draft 0.1 | Started | Planned | Planned | Planned |
| Empty State | Draft 0.1 | Started | Planned | Planned | Planned |

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

## KMP 0.1 Started APIs

The current SDK package includes:

```kotlin
SignalTheme
SignalAppShell
SignalScreen
SignalButton
SignalIconButton
SignalTopBar
SignalBottomNav
SignalBankingRow
SignalAccountRow
SignalAccountHeader
SignalTextField
SignalAmountField
SignalIbanField
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

The first component set is extracted from the Mobile Banking Demo 0.1:

[Open interactive mobile demo](../demos/mobile/prototype.html)
