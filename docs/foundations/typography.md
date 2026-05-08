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
| Display Balance | 32-36 | 900-950 | 0.98-1.05 | Account balances and high-value amounts |
| Page Title | 18-20 | 900-950 | 1.15 | App bar titles |
| Section Title | 13-15 | 900-950 | 1.2 | Section headers |
| Row Title | 13-14 | 850-950 | 1.25 | Banking list rows |
| Row Meta | 10.5-12 | 750-850 | 1.35 | Transaction metadata |
| Button | 13-15 | 900-950 | 1.2 | Filled and secondary buttons |
| Status Pill | 9.5-11 | 850-950 | 1.1 | Active, pending, failed |
| Receipt Title | 22-28 | 900-950 | 1.15 | Success/failure states |
| Legal/Support | 11-13 | 650-800 | 1.45 | Consent scope, support copy |

## 0.2.0 KMP Defaults

| SDK role | Size | Weight | Usage |
| --- | ---: | ---: | --- |
| `displayLarge` | 44 | 950 | Large financial hero only |
| `headlineMedium` | 28 | 950 | Result title and major detail title |
| `titleLarge` | 20 | 950 | Page app bar title |
| `titleMedium` | 16 | 900 | Section and card title |
| `bodyMedium` | 14 | 650 | Supporting text |
| `labelLarge` | 15 | 950 | Primary buttons |
| `labelSmall` | 11 | 850 | Status, nav, compact metadata |

## Rules

- Do not scale font sizes with viewport width.
- Do not use negative letter spacing.
- Use truncation for row meta, not for critical identifiers.
- IBAN, MTCN, references, voucher PINs, and serials must be readable and copyable.
- Amounts must use stable numeric spacing where supported and keep the currency attached.
- Use semantic compact text for lists, such as `IBAN ending 0101`, instead of unclear partial values.
- Arabic layout must be RTL. English and French must be LTR.
- Mixed Arabic/English strings are allowed only for technical identifiers and brand names.
