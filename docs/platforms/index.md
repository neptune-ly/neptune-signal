# Platform Support

Neptune. Signal is platform-neutral. Platform SDKs implement the same design contract.

## Current Support

| Platform | Package | Status |
| --- | --- | --- |
| Kotlin Multiplatform | `packages/kmp-compose` | Active start |
| Tokens | `packages/tokens` | Active start |
| Figma | `docs/figma` | Spec and kit guidance |
| Flutter | `packages/flutter` | Planned |
| iOS SwiftUI | `packages/native-ios` | Planned |
| Android Compose | `packages/native-android` | Planned |
| Web | `packages/web` | Planned |

## Platform Rule

The standard defines what a component is. The SDK defines how that component is built.

Example:

```text
Standard component: Banking Row
KMP: @Composable fun SignalBankingRow(...)
Flutter: class SignalBankingRow extends StatelessWidget
iOS: struct SignalBankingRow: View
Web: <SignalBankingRow />
```

## Shared Requirements

Every platform must support:

- White-label theme tokens.
- RTL and LTR.
- Accessibility labels.
- Sensitive data masking.
- Reduced motion.
- Standard result/status behavior.
- Direction-aware icons.

## Platform Differences

Platform conventions are allowed when they improve native quality:

- iOS can use native haptics and navigation affordances.
- Android can use Material motion primitives.
- Web can use keyboard focus, responsive side navigation, and URL routing.
- Flutter can use its own animation and theming primitives.

The visual and behavioral contract must remain Neptune. Signal compatible.

