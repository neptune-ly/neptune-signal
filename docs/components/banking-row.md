# Component: Banking Row

Banking rows are the default list unit for transactions, settings, consents, support, and operations.

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
- Metadata should be one concise phrase.

## KMP API

```kotlin
@Composable
fun BankingRow(
    icon: SignalIcon,
    title: String,
    metadata: String,
    trailing: BankingRowTrailing = BankingRowTrailing.Chevron,
    state: BankingRowState = BankingRowState.Default,
    onClick: (() -> Unit)? = null
)
```

