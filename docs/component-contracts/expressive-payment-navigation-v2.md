# Expressive Payment Navigation V2

Purpose:
- Make the payment action a product identity element while keeping navigation familiar and calm.

Contract:
- Dock height targets 74dp before system gesture padding.
- Center action targets 68dp and protrudes slightly above the dock.
- Standard nav icons stay around 22dp.
- Active item uses a quiet tonal container.
- Center action uses `primary/onPrimary`, not semantic success green.
- One clean tonal layer only; no cradle puddle, glow, blob, or heavy halo.

Dark mode:
- Dock uses dark neutral container roles.
- Payment action remains primary but must not over-tint the Home screen.

Quality target:
- The control should feel like it emerges from the shell, not like a pasted Android FAB.
