# Premium Wallet Surface Audit

Status: Batch 09 draft
Owner: Neptune. Signal

## Goal

Move Neptune. Signal from a modern banking UI toward a premium wallet/banking hybrid without copying reference visuals or adding fake decoration.

## Current Problems Addressed

- Flat page backgrounds made screens feel too rigid.
- Dashboard modules were readable but still felt like stacked component-library blocks.
- Bottom navigation did not yet feel structurally connected to money movement.
- Surface separation relied too much on borders.

## Surface Direction

Signal now uses a layered surface model:

- app canvas: quiet, low-noise tonal gradient,
- atmospheric backdrop: static low-alpha brand circles,
- nav rail: translucent tonal rail with softer vertical depth,
- dashboard dock: elevated wallet panel with tonal gradient,
- insight rows: softened surfaces with lower border contrast,
- center action: elevated circular money-movement entry point.

## Acceptance Criteria

- One financial object remains dominant per screen.
- Tonal depth supports hierarchy, never decoration.
- Borders become secondary to tone, spacing, and elevation.
- Dark and OLED modes avoid muddy gray surfaces.
- Bank brands can override colors without changing component structure.
- No animated background gradients, glow effects, or crypto-style lighting.

## Rejected

- loud gradients,
- glassmorphism as a primary style,
- neon glow,
- decorative payment particles,
- card piles as the main dashboard language,
- Nova-only visual fixes.

