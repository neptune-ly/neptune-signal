# Financial Identifier Rendering

Identifiers must be readable, copyable, and safe.

## Covered Values

- IBAN
- account numbers
- PAN/card numbers
- aliases
- references

## Rendering Rules

- Display identifiers LTR inside Arabic UI.
- Group long numeric identifiers.
- Mask PAN by default.
- Never announce or toast full sensitive values after copy.

## SDK Helper

`SignalIdentifierRendering` provides:

- `ltrIdentifier`
- `groupedIdentifier`
- `maskedPan`
