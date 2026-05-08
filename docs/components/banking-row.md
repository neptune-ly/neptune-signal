# Component: Banking Row

Banking rows are the default list unit for transactions, settings, consents, support, and operations.

Rows sit inside a `SignalListGroup` surface when several rows belong to one task. This follows Material list behavior while keeping Neptune. Signal denser and more banking-specific.

## Anatomy

```text
Icon box | Title + metadata | Amount/status/chevron
```

## States

- Default.
- Pressed.
- Selected.
- Disabled.
- Pending.
- Success.
- Failed.
- Risk.

## Rules

- The entire row is tappable when it navigates.
- Use one clear trailing affordance.
- Do not mix random icon colors in the same list.
- Critical amounts must stay visible.
- Identifier text in rows must be semantic compact text, such as `IBAN ending 0101`.
- Metadata should be one concise phrase.
- Use `SignalListDivider` between related rows only when the group needs extra scan structure.

## KMP API

```kotlin
SignalListGroup {
    SignalTransactionRow(
        title = "حوالة LyPay واردة",
        metadata = "PAY-3921 · أمس 16:08",
        amount = "+450 د.ل",
        incoming = true,
        onClick = onOpenTransaction
    )
    SignalListDivider()
    SignalBankingRow(
        title = "اشتراك خدمة رسائل قصيرة",
        metadata = "TT2520452CJ6 · اليوم 10:24",
        amount = "-75 د.ل",
        tone = SignalRowTone.Danger,
        onClick = onOpenTransaction
    )
}
```
