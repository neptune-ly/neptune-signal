# Foundations: Typography

Neptune. Signal typography is dense, clear, and banking-oriented.

Preferred stack:

```text
SF Compact Rounded
SF Pro Rounded
Inter
System Arabic fallback
```

## Material-Compatible Type Roles

Signal keeps the Material 3 type-scale structure so implementation teams can map cleanly into platform APIs.

| Material role | Signal use |
| --- | --- |
| Display | Large financial state, balance, campaign headline when needed |
| Headline | Page-level confirmation, receipt outcome, onboarding title |
| Title | App bar, section, card, sheet, and form group titles |
| Body | Descriptions, support copy, consent explanation |
| Label | Buttons, chips, tabs, navigation labels, form labels |

## Banking Type Roles

| Role | Size | Weight | Line | Usage |
| --- | ---: | ---: | ---: | --- |
| Balance | 36sp | 950 | 38sp | Account balances and high-value amounts |
| Page Title | 20sp | 950 | 26sp | Full page and result titles |
| Screen Title | 17sp | 950 | 23sp | Auth state cards and compact page headers |
| Section Title | 15sp | 950 | 20sp | Section headers |
| Row Title | 15sp | 850-950 | 20sp | Banking list rows |
| Row Meta | 13sp | 750-850 | 17sp | Transaction metadata |
| Field Label | 13sp | 850 | 16sp | Text field labels |
| Field Value | 17sp | 950 | 22sp | Text field values |
| Button | 16sp | 950 | 20sp | Filled and secondary buttons |
| Status Pill | 12.5sp | 850-950 | 15sp | Active, pending, failed |
| Receipt Title | 22-26 | 900-950 | 1.15 | Success/failure states |
| Legal/Support | 11-13 | 650-800 | 1.45 | Consent scope, support copy |

## Density Rules

Signal uses Material 3 type roles but keeps banking screens compact. Dense does not mean small: touch targets stay at least 48dp, fields stay stable, and type is reduced only where the user is scanning repeated banking data.

| Surface | Default height | Type rule |
| --- | ---: | --- |
| Button | 58dp | `labelLarge`, one line |
| Field | 62dp | 17sp input text |
| Banking row | 58dp minimum | One row title plus compact metadata |
| Account row | 76dp minimum | Balance and semantic identifier visible |
| Bottom navigation | 86dp plus safe area | 26.5dp nav icon plus 12.5sp label |

Do not enlarge login, OTP, settings, or support copy to hero sizes. Authentication screens are operational banking screens, not marketing pages.

## 0.2.1 KMP Defaults

| SDK role | Size | Weight | Usage |
| --- | ---: | ---: | --- |
| `displayLarge` | 42 | 950 | Large financial hero only |
| `headlineMedium` | 28 | 950 | Result title and major detail title |
| `pageTitle` | 20 | 950 | Full page and result title |
| `screenTitle` | 17 | 950 | Compact state title |
| `titleLarge` | 19 | 950 | Large component title |
| `titleMedium` | 16 | 950 | Section and card title |
| `bodyMedium` | 15 | 650 | Supporting text |
| `labelLarge` | 16 | 950 | Primary buttons |
| `labelSmall` | 13 | 850 | Status, nav, compact metadata |
| `fieldLabel` | 13 | 850 | Text field labels |
| `fieldValue` | 17 | 950 | Text field values |

## Rules

- Do not scale font sizes with viewport width.
- Do not use negative letter spacing.
- Use truncation for row meta, not for critical identifiers.
- IBAN, MTCN, references, voucher PINs, and serials must be readable and copyable.
- Amounts must use stable numeric spacing where supported and keep the currency attached.
- Use semantic compact text for lists, such as `IBAN ending 0101`, instead of unclear partial values.
- Arabic layout must be RTL. English and French must be LTR.
- Mixed Arabic/English strings are allowed only for technical identifiers and brand names.
