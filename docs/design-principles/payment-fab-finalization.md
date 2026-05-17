# Payment Center Action Finalization

Date: 2026-05-15

## Direction

The center action is not a generic floating action button. It is a wallet-native navigation action seated into the bottom shell.

## Requirements

- Integrated with nav rhythm and safe areas.
- Clear optical center and icon sizing.
- Subtle elevation, not visual drama.
- Works in light, dark, and OLED themes.
- RTL-safe because the center position does not depend on reading direction.
- Motion is restrained: quick press response and route continuity only.

## Behavior

The action opens the product's primary money-movement route. It does not own business logic; it owns navigation architecture and interaction emphasis.

## Rejected

- Large floating overlay button disconnected from the nav.
- Bright gradient FAB.
- Oversized icon glyphs.
- Dashboard duplicate primary transfer button.

