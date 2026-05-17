# Account Identity Copy Contract

Account receiving identifiers are handled by `SignalPrismAccountIdentityPanel`.

Supported fields:
- `Alias`
- `Iban`
- `AccountNumber`
- `HolderName`
- `Branch`
- `Reference`

Rules:
- IBAN, account number, alias, and references render with LTR text direction inside RTL layouts.
- Copy is field-by-field only.
- Copy feedback is panel-level and row-level, short-lived, and localized (`تم نسخ ...`) so the confirmation remains visible even when identifier rows are visually dense.
- Signal defines the component; Nova provides platform clipboard behavior.
- Account identifiers are less sensitive than card PAN/CVV but can still be marked sensitive by platform clipboard metadata.
