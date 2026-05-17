# Payment Navigation Finalization

The center payment action should read as a restrained wallet control emerging from the nav shell.

Rules:
- Target size: 64-68dp.
- Protrusion: about 22-28dp above the nav shell.
- Icon: 26-28dp.
- Use `primary` / `onPrimary` unless a bank profile explicitly changes the primary role.
- Do not use semantic green for payment by default.
- No inner bullseye plate, thick halo, glow, or decorative cradle.

Signal implementation:
- `SignalBottomNav` owns the center payment action geometry.
- Nova only supplies label, route callback, and icon.

Quality bar:
- Content should remain the first visual anchor; the payment action anchors the shell, not the whole screen.
- The nav should pass OLED review without a muddy green-black tray, heavy outline, or hidden icon states.
- Active roots should be discoverable at a glance without becoming large colored pills.
