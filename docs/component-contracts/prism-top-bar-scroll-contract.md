# Prism Top Bar Scroll Contract

`SignalPrismTopBar` owns title behavior for Prism screens. Screens must not invent local sticky titles.

Supported variants:
- `Static`: fixed page title with opaque background and safe-area protection.
- `Collapsing`: reserved for future scroll-aware headers.
- `FloatingCompact`: compact floating header for modal-like surfaces.
- `ContentFirst`: no title bar; content owns the first viewport.
- `Detail`: canonical detail top bar with consistent back behavior.

Titles must not overlap content while scrolling. Back/close controls use start-edge placement and RTL-aware icons.
