# Prism Floating Nav Contract

`SignalPrismFloatingNav` will own:
- Floating dock geometry.
- Destination icon sizing.
- Arabic label rhythm.
- Active destination illumination.
- Safe-area behavior.
- Dark, light, and OLED surface treatment.

The dock may use `prismSurfaceFloating`, `prismNavSheen`, `prismShadowFloating`, and `prismBorderSoft`.

No screen may create a local bottom nav style once Prism nav is migrated.

Batch 2 implementation:
- `SignalPrismFloatingNav` is implemented beside the classic `SignalBottomNav`.
- `SignalAppShell` accepts `bottomNavVisualLanguage`.
- `SignalBottomNavVisualLanguage.Prism` selects the Prism dock.
- The dock uses Prism floating surfaces, Prism border/overlay roles, and a restrained vertical sheen.
- Standard nav icons remain 22-24dp with readable Arabic labels.
- Center payment spacing is reserved structurally through the dock layout rather than a cradle blob.
