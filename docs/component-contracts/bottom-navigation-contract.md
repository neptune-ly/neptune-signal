# Bottom Navigation Contract

Signal owns the root mobile navigation shell.

Current contract:
- Four root destinations plus one center payment action.
- Root labels remain visible for Arabic clarity.
- Shell height targets 76dp before gesture-area padding.
- Standard root icons use 22dp glyphs.
- Active root uses a quiet `primaryContainer` state, not a saturated fill.
- The center action is the only elevated action and is not a tab.
- The shell uses neutral Material 3 container roles, not profile-tinted trays.
- The center action may protrude above the dock, but it must remain visually seated by spacing and shell rhythm rather than a blob/cradle.
- The dock must remain readable in RTL with labels present and evenly weighted around the center action.

Rejected:
- Large gray tray treatment.
- Cradle puddles, glows, blobs, halo rings, or translucent content bleed.
- Screen-local nav styling in Nova.

May 16 pass:
- `SignalBottomNav` was reduced to a slimmer floating dock with lower shadow and quieter active indicators.
- The center payment control was resized to the 64-68dp target range and moved away from the previous inserted/FAB-like treatment.
- Green is not the default payment color; payment uses the configured `primary` role and leaves green for success/money-in semantics unless a bank profile owns green as primary.
- The light dock now uses a lower neutral container role so it reads as a calm floating control, not a heavy gray tray.
