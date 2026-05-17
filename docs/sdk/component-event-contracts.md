# Component Event Contracts

Signal components emit intent, not app navigation.

## Event Pattern

```kotlin
data class ServiceHubEvent(val actionId: String)

SignalPrismServiceRow(
    action = SignalPrismServiceAction(...),
    onClick = { event -> appRouter.resolve(event) }
)
```

## Rules

- No component should depend on `AppRoute` or host app route classes.
- Rows and cards expose `onClick`, `onCopy`, `onReveal`, `onDismiss`, `onApprove`, `onReject`, or typed event callbacks.
- Host apps own routing, analytics, persistence, permissions, and backend calls.
- SDK demos may provide sample event handlers that log labels, but production SDK components must not log sensitive values.

## Sensitive Events

Card and account copy/reveal events must pass only the minimum required payload and respect platform clipboard policy.
