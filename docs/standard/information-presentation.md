# Information Presentation

Neptune. Signal treats financial values as customer evidence, not decoration. A customer must be able to read, verify, copy, share, and report the exact value when it matters.

## Critical Values

Critical values must never be rendered with vague truncation:

- Amounts.
- IBAN.
- Alias.
- Account numbers.
- MTCN.
- Transaction references.
- Voucher PIN.
- Voucher serial.
- Consent IDs and mandate IDs.

Do not show these values with trailing dots or any other unclear abbreviation.

## Allowed Presentation Modes

| Mode | Use | Rule |
| --- | --- | --- |
| Full | Detail, receipt, input, consent, support | Show the complete value, grouped when useful |
| Semantic compact | Lists and tight cards | Show meaning, such as `IBAN ending 0101` |
| Masked | Privacy mode | Hide intentionally with a clear masked state |
| Metadata truncation | Descriptions only | Allowed for non-critical supporting copy |

## Value Rules

- Group IBAN and voucher codes into readable chunks.
- Keep currency attached to the amount.
- Use tabular number styling for amounts where supported.
- Place copy and share actions next to the exact value they affect.
- If a value cannot fit on one line, wrap it or move it to a detail surface.
- Compact rows should not pretend to show the full value.

## Copy and Share

Copy and share actions must be scoped:

- Copy IBAN copies the full IBAN, even if the row shows `IBAN ending 0101`.
- Share voucher PIN shares only the PIN when that is the intended object.
- Voucher serial can be copied, but the PIN is the primary share object.
- Support reports attach the relevant transaction, voucher, consent, or account reference.

## Examples

| Bad | Good |
| --- | --- |
| Partial IBAN with trailing dots | `IBAN ending 0101` in a list |
| Partial IBAN in details | `LY81 0240 0101 0000 6712 0201 01` |
| Partial amount | `250.000 د.ل` |
| Partial voucher serial | `VCH-260508-884129` |

## SDK Mapping

KMP uses `SignalValueLine` for critical values. Product SDKs for Flutter, native, and web should map the same component contract:

```kotlin
SignalValueLine(
    label = "IBAN",
    value = "LY810240010100006712020101",
    format = SignalValueFormat.Iban,
    copyable = true,
    onCopy = copyIban
)
```
