# Prism Floating Navigation

Prism navigation is a floating control dock, not a Material tray.

The dock should feel light, tactile, and premium:
- Dark: translucent deep navy or ink capsule with soft depth.
- Light: frosted off-white capsule with subtle shadow.
- OLED: near-black capsule with luminance edge and reduced glow.

The center payment action is product identity:
- It floats above the dock.
- It uses `prismPaymentGradient`.
- It has a clean white payment glyph.
- It has no cradle blob, glow puddle, thick ring, or heavy shadow.

Active destinations use quiet illumination, not loud filled pills. Arabic labels remain visible for clarity.

Current migration status:
- Prism dock has been added as a selectable shell visual language.
- Nova Home/root shell now requests Prism nav.
- The old nav remains available for rollback and comparison until Prism stabilizes.
