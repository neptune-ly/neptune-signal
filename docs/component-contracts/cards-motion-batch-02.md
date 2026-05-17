# Component Contract Batch 02 - Cards + Motion

## Intent

Cards are one of the primary premium moments in Neptune Nova. The Signal contract must make cards feel precise, calm, and modern without becoming decorative or performance-heavy.

## Contract Updates

### SignalPaymentCardStack

- Cards use stable list height from `SignalComponentMetrics.cardFaceListHeight`.
- Stack gap is `12dp`.
- Entry motion is subtle: scale, alpha, and small vertical translation only.
- Card list never displays balance or fetched financial values.

### SignalPaymentCard

- Primary hierarchy:
  1. Card label.
  2. Masked PAN.
  3. Usage/status metadata.
  4. Scheme mark.
- Decorative texture must stay behind data and never compete with PAN or status.
- Scheme marks are compact and supportive, not dominant.

### SignalCardDetailStage

- Detail stage owns the expanded card experience.
- Header text uses stage foreground, not card foreground.
- Card visual preserves card color and scheme identity.
- Metrics are compact receipt-like facts, not large tiles.

### Motion

- Card detail open uses `SignalMotionMetrics.sharedElementMillis`.
- 3D reveal is allowed only as subtle continuity:
  - no extreme rotation,
  - no bounce,
  - no long theatrical flip,
  - reduced motion must still leave clear state change.

### Dark/OLED

- Black mode uses true surface where the card is not the active surface.
- White overlays must remain low alpha.
- Avoid glow as a primary hierarchy tool.

## SDK Ownership

Nova must consume these components. App screens may provide card data and route orchestration, but must not duplicate card visuals, motion, or dark-mode surface rules locally.
