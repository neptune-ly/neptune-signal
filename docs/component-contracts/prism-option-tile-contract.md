# Prism Option Tile Contract

`SignalPrismOptionTile` replaces cheap chips with premium selectable tiles.

`SignalPrismVisualChoiceTile` is the stronger visual variant for choices where the option has a real look and feel: appearance modes, Prism palettes, card display modes, and other skin-level choices.

Required states:
- selected
- unlocked
- locked by bank
- swatch/icon preview
- disabled reason

Arabic labels must not crop. Locked options use `محدد من المصرف`.

## Visual Choice Rules

- Use visual choice tiles for the main personalization decisions.
- Use regular option tiles for secondary/advanced settings.
- Show a mini visual swatch or gradient preview instead of long explanation text.
- Keep tiles compact enough that the screen does not become a long technical settings page.
- Do not expose arbitrary color pickers; palettes remain curated and bank-policy-safe.
