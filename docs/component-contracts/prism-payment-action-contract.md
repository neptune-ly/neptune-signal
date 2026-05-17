# Prism Payment Action Contract

`SignalPrismPaymentAction` is the primary money-movement control.

Rules:
- Uses `prismPaymentGradient`.
- Floats above the dock.
- Uses a clean white payment glyph.
- Supports press scale and reduced motion.
- Does not use a cradle blob, thick outline, glow puddle, or heavy shadow.
- Keeps at least 48dp tap target and clear RTL semantics.

Payment action remains iconic but must not overpower the account hero.

Batch 2 implementation:
- `SignalPrismPaymentAction` uses `prismPaymentGradient`.
- Size is 72dp with a 30dp glyph slot.
- It floats above `SignalPrismFloatingNav` and uses press scale.
- A translucent inner plate gives tactile depth without a glow puddle.
- Existing center action data (`SignalNavCenterAction`) is reused, so Nova supplies behavior while Signal owns presentation.
