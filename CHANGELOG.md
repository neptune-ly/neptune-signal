# Changelog

## 0.2.0

Material-compatible system restructure.

Added:

- Material 3 extension model for Neptune. Signal.
- Color role foundations for `primary`, `secondary`, `tertiary`, surface containers, outline, inverse, and error roles.
- Surface, elevation, and state-layer foundations.
- Component anatomy standard for purpose, variants, states, tokens, motion, accessibility, RTL, SDK mapping, and Figma properties.
- App structure pattern for root navigation, nested pages, performance, and transaction result grammar.
- Mobile Banking Demo 0.2 with Material-compatible grouped lists, stronger Accounts root, Account Details container motion, theme palette placement, and wider result receipts.
- KMP theme roles aligned to Material color scheme and typography APIs.
- KMP list primitives: `SignalListGroup`, `SignalListItem`, `SignalListDivider`, and `SignalTransactionRow`.
- KMP account summary primitives for tappable all-account lists with semantic compact identifiers.
- Token source expanded with Material-compatible roles, state layers, elevation, and richer type roles.

## 0.1.1

Information presentation update.

Added:

- Critical value rules for amount, IBAN, alias, MTCN, references, voucher PIN, voucher serial, and consent IDs.
- `SignalValueLine` for readable, copyable, shareable financial values.
- Semantic compact account identifiers, such as `IBAN ending 0101`.
- Safer account header and status receipt value rendering.
- Component gallery examples for full, compact, masked, and copied values.

## 0.1.0

Initial Neptune. Signal standard scaffold.

Added:

- Standard/versioning model.
- GitHub Pages documentation shell.
- Foundations: tokens, typography, layout, shape, iconography.
- Components: app shell, banking row, account, status result.
- Banking UX patterns.
- Motion standard.
- Accessibility standard.
- White-label theming guidance.
- KMP starter tokens.
- Figma kit guidance.
- SVG logo mark and lockup.
- KMP Compose SDK scaffold.
- `SignalTheme`, tokens, spacing, radius, and motion constants.
- First KMP components: button, icon button, top bar, bottom nav, banking row, account row, account header, status result, consent scope row, and card face.
- Local Maven publishing for `ly.neptune.signal:kmp-compose:0.1.0`.
