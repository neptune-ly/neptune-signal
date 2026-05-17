# Card Typography And Identifier Contract

Cards require stricter identifier typography than normal UI rows.

Rules:

- PAN, last4, expiry, and card references render LTR in Arabic layouts.
- Use tabular numeric rhythm where available.
- Metadata remains calmer than card name and status.
- Amounts use the financial formatting contract.
- CVV is never visually emphasized.
- Full PAN appears only during policy-approved reveal.

Primary roles:

- Card name: Prism title/card role.
- Scheme and last4: card metadata role.
- PAN and references: Prism identifier role.
- Limits and transactions: compact amount roles.

