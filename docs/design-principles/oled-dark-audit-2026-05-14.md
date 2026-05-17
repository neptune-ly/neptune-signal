# OLED + Dark Audit - 2026-05-14

## Scope

Reviewed Signal card surfaces and detail-stage panels because they are the highest-contrast dark-mode banking surfaces.

## Findings

- Card list colors were acceptable, but decorative overlays could become cloudy in dark/OLED modes.
- Card detail metric tiles used white overlays that were slightly too strong for black mode.
- Detail stage background should use the actual black surface in OLED mode instead of a gray container.
- Borders should be present but quiet; dark-mode hierarchy should come from tone, not glow.

## Decisions

- In black mode, card detail stage uses `colors.surface`.
- In dark mode, card detail stage uses `colors.surfaceCard`.
- Metric tile overlays were reduced in dark and black modes.
- Card texture alpha was reduced for both list and detail cards.

## Standard Rule

Dark and OLED Signal surfaces must avoid cloudy translucent panels. Use tonal surfaces first, then low-alpha borders for structure. Accent color should identify state or brand, not create glow/noise.
