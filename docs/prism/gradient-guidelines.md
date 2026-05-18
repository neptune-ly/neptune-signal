# Prism Gradient Guidelines

Date: 2026-05-17

## Principle

Gradients are reserved for expressive identity or protective media overlays. Prism uses flat and tonal surfaces for navigation, actions, cards, lists, and operational content.

## Allowed Zones

### Hero, Account, And Card Artwork

Use gradients when the gradient is the primary artwork surface:

- account stage,
- card face,
- card detail hero,
- compact preview of the same artwork.

Text over artwork needs a readable zone or scrim.

### Transfer Ritual

Use gradients or radial fields when payment progress is the primary visual event:

- payment preparation,
- routing,
- verification,
- confirmation,
- terminal feedback field.

Normal transfer rows and transfer shortcuts stay tonal.

### FX And Currency Hero

Use `fxGradient` only for currency-rate hero surfaces, calculator headers, or rate-alert ritual moments where the gradient communicates market context. FX rows, small tiles, service entries, and rate lists stay tonal.

### Campaign Media Overlay

Use campaign overlay gradients only for:

- image overlay,
- missing-media fallback,
- campaign detail artwork with protected copy.

Backend `gradientStops` are advisory. Prism resolves final production colors.

## Disallowed Zones

Do not use gradients in:

- nav bars,
- top bars,
- primary buttons,
- center payment action,
- quick actions,
- service rows,
- notification rows,
- status panels,
- card controls,
- account controls,
- transaction lists,
- metric tiles,
- settings surfaces.

## Tonal Alternatives

Use these instead:

- `prismSurface`,
- `prismSurfaceRaised`,
- `prismSurfaceFloating`,
- `prismSurfaceMuted`,
- `prismSurfaceStrong`,
- semantic tone containers,
- `prismBorderSoft`,
- icon wells,
- typography weight,
- spacing and grouping.

## Light Mode Strategy

Light mode uses a tuned tonal canvas rather than a colorful global wash:

- page background: subtle vertical blend between `prismBackground` and `prismSurface`
- hero gradients: capped at lower intensity than dark mode
- ritual gradients: capped and radial only
- FX gradients: capped at `0.30` by default
- campaign overlays: used only to protect image copy

This keeps light mode premium without making cards, rows, and nav look pale or cheap.

## Dark/OLED Strategy

Dark mode can carry stronger hero and ritual gradients because text contrast is easier to preserve on deep surfaces. OLED reduces gradient intensity and glow to avoid muddy blue/green halos on true black.

## Review Questions

- Is this an approved gradient zone?
- Is the gradient doing artwork/protection work, or just decoration?
- Can a tonal surface communicate the state?
- Is text protected in light, dark, and OLED?
- Is the source a Prism role, not local arbitrary stops?

## Final Rule

If a surface is not hero/account/card artwork, a transfer ritual field, or campaign media overlay, it should be flat or tonal.
