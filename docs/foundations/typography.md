# Foundations: Typography

Neptune. Signal typography is dense, clear, and banking-oriented.

Preferred stack:

```text
SF Compact Rounded
SF Pro Rounded
Inter
System Arabic fallback
```

## Type Roles

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

## Rules

- Do not scale font sizes with viewport width.
- Do not use negative letter spacing.
- Use truncation for row meta, not for critical identifiers.
- IBAN, MTCN, references, voucher PINs, and serials must be readable and copyable.
- Arabic layout must be RTL. English and French must be LTR.
- Mixed Arabic/English strings are allowed only for technical identifiers and brand names.

