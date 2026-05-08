# Foundations: Surfaces

Surface design is how Neptune. Signal gets a modern Material-informed feel without becoming a stack of cards.

## Surface Ladder

| Surface | Token | Usage |
| --- | --- | --- |
| Paper | `surface` | Main screen surface |
| Low container | `surfaceContainerLow` | Scroll background, grouped quiet sections |
| Container | `surfaceContainer` | Rows, fields, vouchers, list groups |
| High container | `surfaceContainerHigh` | Bottom navigation, sheets, sticky controls |
| Highest container | `surfaceContainerHighest` | Modal content, selected controls, dense panels |
| Primary surface | `primary` | Secure account hero, filled primary action, selected nav |

## Elevation

Neptune. Signal uses restrained elevation:

| Level | Use |
| --- | --- |
| 0 | Flat rows, lists, app background |
| 1 | Interactive rows and fields |
| 2 | Bottom navigation and app bars |
| 3 | Sheets and menus |
| 4 | Dialogs and focused confirmation surfaces |
| 5 | Temporary morph overlays only |

## Rules

- Use fewer surfaces than typical fintech dashboards.
- Prefer lists and sections over card piles.
- Do not put cards inside cards.
- Use stroke and container color before heavy shadows.
- Important financial receipt screens may use a full-width surface because receipts need room.
- Detail pages should not repeat the same account card from the home carousel. They should transform the source into a new header layout.

## State Layers

| State | Alpha |
| --- | ---: |
| Hover | 0.08 |
| Focus | 0.12 |
| Press | 0.12 |
| Drag | 0.16 |
| Disabled container | 0.12 |
| Disabled content | 0.38 |

State layers must use the role color of the component, not an arbitrary color.
