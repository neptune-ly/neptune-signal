# Prism Card Scheme System

Updated for card presentation architecture.

Prism separates card scheme identity from Neptune's default visual style. `SignalCardScheme` supports Visa, Mastercard, NUMO, AMEX, Private Label, and Unknown. `SignalPrismCardArtworkProfile` combines the scheme, bank palette, personality, background style, mark style, and typography style.

## Current UI Contracts

- Carousel mode can show full artwork.
- List mode uses `SignalPrismMiniCardArtwork`.
- Detail pages use `SignalPrismCardDetailHeader` so large artwork is not repeated after carousel browsing.

## Production Rule

Official network marks and scheme artwork must come from bank/backend assets. Signal demo placeholders are intentionally simplified and must not be treated as official brand assets.

Cards are not one fixed Prism gradient. Card artwork is resolved from scheme, bank palette, product personality, and backend-provided artwork policy.

Supported schemes:
- `Visa`
- `Mastercard`
- `Numo`
- `Amex`
- `PrivateLabel`
- `Unknown`

Prototype scheme marks are text placeholders. Production builds must use issuer/scheme-approved assets and brand rules.
