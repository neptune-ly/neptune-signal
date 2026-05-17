# Tonal Depth Refinement

Status: Batch 09 draft

## Principle

Neptune. Signal uses tone and rhythm before decoration.

Depth should communicate:

- current financial focus,
- safe money movement,
- route hierarchy,
- scanability.

Depth should not create:

- visual noise,
- fake luxury,
- crypto-style glow,
- animation cost,
- white-label fragility.

## Tonal Stack

| Level | Usage |
| --- | --- |
| Canvas | page background and scroll root |
| Atmospheric backdrop | static low-alpha brand depth |
| Rail | persistent navigation surface |
| Panel | action dock and grouped controls |
| Financial object | account/card/payment source |
| Critical action | center payment action and primary CTA |

## Implementation Notes

- Atmospheric depth is a static `Canvas` in `SignalAppShell`.
- Panels use subtle vertical gradients with token colors.
- Light mode can use restrained shadow.
- Dark/OLED mode prefers tonal contrast over shadow.
- Borders are lower alpha and should not dominate.

## Future Work

- Add explicit `SignalWalletSurface` variants.
- Define screenshot-based contrast gates for dark and black modes.
- Add surface examples to the component gallery.

