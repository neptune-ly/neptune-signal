# Material 3 Extension Model

Neptune. Signal is a financial product layer on top of Material 3 principles. The goal is not to make Neptune apps look like Google apps. The goal is to inherit proven platform logic, then add Neptune's banking rules.

Official reference points:

- [Material 3 Get Started](https://m3.material.io/get-started)
- [Material 3 Color Roles](https://m3.material.io/styles/color/roles)
- [Material 3 Typography](https://m3.material.io/styles/typography/type-scale-tokens)
- [Material 3 Motion](https://m3.material.io/styles/motion/overview)

## What We Reuse

| Material idea | Signal implementation |
| --- | --- |
| Color roles | `primary`, `secondary`, `tertiary`, `surface`, `surfaceContainer`, `outline`, `error`, and inverse roles exist in tokens and SDKs. |
| Type scale | Signal keeps display, headline, title, body, and label roles, then adds banking-specific roles. |
| State layers | Press, focus, drag, selected, disabled, and loading states use consistent alpha and timing. |
| Navigation patterns | Bottom navigation, top app bars, sheets, lists, and form patterns follow familiar mobile platform behavior. |
| Motion system | Route changes, container transforms, result states, and reduced-motion behavior use native CSS or platform animation APIs. |
| Accessibility | Minimum touch targets, semantic labels, contrast, directionality, and clear focus states are mandatory. |

## What Signal Adds

| Banking need | Signal rule |
| --- | --- |
| Critical values | Amounts, IBANs, aliases, voucher PINs, serials, MTCNs, references, and consent IDs never use vague truncation. |
| White-label banks | Bank colors can change, but anatomy, spacing, motion, and information hierarchy cannot drift. |
| Arabic-first products | Arabic RTL is a first-class layout, not a translation afterthought. English and French must preserve the same component contract. |
| Performance-aware finance | Heavy values are fetched only where useful. Card list does not show live balance. Details screens can fetch details. |
| Transaction trust | Status screens show what happened, what is pending, what can be copied or shared, and where to report a problem. |
| Consent and identity | OpenWave and NPT Alias flows show scopes, duration, account access, recurring mandates, and revocation clearly. |

## Decision Rule

When a product team is unsure, use this order:

1. Start with the Material 3 interaction pattern the user already knows.
2. Apply Neptune. Signal tokens, density, Arabic layout, and banking hierarchy.
3. Apply financial information rules.
4. Confirm the component can be implemented in KMP without heavy runtime cost.
5. Confirm the same contract can later map to Flutter, SwiftUI, Android Compose, and web.

## Not Allowed

- Copying Material colors or making the product look generic.
- Creating decorative banking cards where a dense row or receipt would be clearer.
- Treating bank brand colors as permission to redesign the app.
- Hiding critical values behind `...`.
- Adding heavy animation files for basic transitions.
- Creating a component that cannot be described as a platform-neutral contract.
