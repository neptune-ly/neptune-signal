# Prism Light Palette Tuning

Date: 2026-05-17

## Problem

The previous light palette looked washed out because too many roles clustered near white:

- `surface` was almost pure white.
- `surfaceContainer`, `surfaceContainerHigh`, and `surfaceVariant` were only slightly tinted.
- `primaryContainer`, `secondaryContainer`, and `tertiaryContainer` were low-chroma pastels.
- component-level fixes still inherited a pale page canvas, so quick actions, movement previews, nav, notifications, and More rows looked weak.

## Decision

Light mode now uses a tuned SDK palette through `SignalPrismLightPalette`.

Default values:

| Role | Value |
| --- | --- |
| `surface` | `#F0F7FA` |
| `surfaceBright` | `#FBFEFF` |
| `surfaceContainerLowest` | `#FFFFFF` |
| `surfaceContainerLow` | `#E7F1F5` |
| `surfaceContainer` | `#DCEAF0` |
| `surfaceContainerHigh` | `#CFE1E9` |
| `surfaceContainerHighest` | `#BED4DE` |
| `onSurface` | `#061A2B` |
| `onSurfaceVariant` | `#435967` |
| `outline` | `#95ACB8` |
| `outlineVariant` | `#B9CCD5` |
| `inverseSurface` | `#061A2B` |

Containers are still light, but they now have enough chroma and luminance separation to make the page feel intentional instead of washed out.

## Bank Branding

The palette is not a fixed Neptune skin. It blends bank colors into semantic containers:

- `primaryContainer = surfaceContainerLow` blended with bank primary at `0.17`
- `secondaryContainer = surfaceContainerLow` blended with bank secondary at `0.22`
- `tertiaryContainer = surfaceContainerLow` blended with bank accent at `0.16`

Banks can override the `SignalPrismLightPalette` values through `SignalPrismProfile.lightPalette`.

## Trade-Off

The page canvas is slightly darker than before. That is intentional. The older canvas preserved maximum whitespace, but it made large surfaces, buttons, rows, and nav feel cheap. The tuned palette gives Prism light mode a clearer fintech identity while retaining accessible dark text.
