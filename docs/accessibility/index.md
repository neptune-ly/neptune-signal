# Accessibility

Neptune. Signal must be usable in Arabic, English, and French, with strong accessibility defaults.

## Requirements

- Minimum touch target: `44 x 44`.
- Text contrast should meet WCAG AA.
- Icon-only actions need accessible labels.
- Directional icons flip in RTL.
- Critical identifiers must be readable and copyable.
- Errors must explain what happened and what to do next.
- Loading states must not trap the customer.

## Language

- Arabic uses RTL.
- English and French use LTR.
- Technical identifiers can stay LTR inside Arabic UI when needed.
- Avoid mixing languages in customer-facing labels unless it is a brand or identifier.

## Sensitive Data

Provide mask/unmask controls for:

- Balance.
- IBAN.
- Alias when needed.
- Card number.
- Voucher PIN after purchase.

