# Component: App Shell

The app shell provides consistent structure across banking products.

## Anatomy

```text
Status area
Top app bar
Scrollable content
Persistent bottom navigation
```

## Rules

- Bottom navigation is persistent across every root and nested route.
- Nested pages show a top app bar back action.
- Root tab active state remains stable on nested screens.
- Safe areas must be respected.
- Desktop preview must not stretch the phone to full browser width.

## KMP API

```kotlin
@Composable
fun SignalAppShell(
    activeRoot: SignalRoot,
    title: String,
    subtitle: String? = null,
    onBack: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable PaddingValues.() -> Unit
)
```

