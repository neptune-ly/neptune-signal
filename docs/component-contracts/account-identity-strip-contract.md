# Account Identity Strip Contract

`SignalPrismAccountIdentityStrip` exposes receiving identifiers in a compact, copy-first surface.

Supported identifiers:
- NPT Alias
- IBAN
- account number
- holder/reference fields when policy allows

Rules:
- identifiers render LTR inside Arabic UI
- copy is field-by-field only
- copy feedback is inline and short-lived
- no logging of copied values
- account identifiers can be marked sensitive on platform clipboards

