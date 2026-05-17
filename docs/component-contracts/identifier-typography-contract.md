# Identifier Typography Contract

Identifiers are operational financial strings. They must be readable, copy-safe, and directionally stable.

Applies to:
- PAN
- IBAN
- account numbers
- references
- aliases containing Latin characters

Contract:
- `textDirection = Ltr`
- `fontFeatureSettings = "tnum"`
- no visual reversal in Arabic screens
- masked values preserve grouping and last-four clarity
