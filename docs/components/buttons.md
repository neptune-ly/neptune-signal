# Component: Buttons

## Types

- Primary.
- Secondary.
- Destructive.
- Icon.
- Icon plus label.
- Segmented control button.

## Rules

- Primary buttons use `bank.primary`.
- Destructive buttons use danger/risk styling.
- Icon-only buttons require accessible labels.
- Buttons should not change size between loading and completed states.
- Loading buttons must preserve the action label or provide a clear processing state.

## Sizes

| Size | Height | Radius | Usage |
| --- | ---: | ---: | --- |
| Small | 36 | 999 | Compact controls |
| Medium | 44 | 999 | Forms and sheets |
| Large | 56 | 22-999 | Primary actions |

## KMP API

```kotlin
@Composable
fun SignalButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: SignalButtonVariant = SignalButtonVariant.Primary,
    enabled: Boolean = true,
    loading: Boolean = false,
    icon: SignalIcon? = null
)
```

