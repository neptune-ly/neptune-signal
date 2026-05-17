# Account Identifier Rail Contract

`SignalPrismAccountIdentifierRail` provides compact copy access to receiving identifiers.

## Content

- NPT Alias
- IBAN
- Account number when available

## Rules

- Render IBAN, account numbers, aliases, and references LTR inside RTL UI.
- Use field-by-field copy only.
- Show short inline copy feedback.
- Do not log copied identifiers.
- Support `SignalAccountClipboardPolicy` from the app/platform layer.

The rail replaces heavy identity panels on the default workspace screen.
