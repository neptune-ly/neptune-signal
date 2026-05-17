# Prism Theme Contract

Signal exposes Prism through `SignalTheme.prism`.

The Prism token root contains:
- `appearance`
- `profile`
- `palette`
- `gradients`
- `surfaces`
- `overlays`
- `motion`
- `radii`
- `density`

Existing `SignalTheme.colors` remains available for current components. New Prism components must use `SignalTheme.prism` first and fall back to Material roles only for compatibility or accessibility.

Nova must pass bank profile and user preference into Signal. Nova must not hardcode Prism colors locally.
