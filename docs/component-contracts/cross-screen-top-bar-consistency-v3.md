# Cross-Screen Top Bar Consistency V3

Prism screens use `SignalPrismTopBar` for page and detail titles. The shell uses the static variant for root and hub screens. Detail routes can hide the shell title and render a local `Detail` variant at the top of the content.

Rules:
- One canonical back icon size: 24 dp inside the Signal icon button target.
- Opaque title background unless a screen explicitly chooses `ContentFirst`.
- No sticky floating title text over scrolling content.
- Title typography uses Prism typography roles, not ad hoc bold weights.
