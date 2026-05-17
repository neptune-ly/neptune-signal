# Performance Guidelines

## General Rules

- Use stable/immutable models for component inputs.
- Remember expensive brushes, shapes, and interaction sources.
- Avoid recalculating gradients every frame.
- Avoid infinite animations unless there is a product reason.
- Keep row heights predictable.
- Prefer lazy containers for long lists.
- Keep translucent/glow effects bounded.
- Reduced motion should remove work, not only hide animation.

## Component Notes

- Floating nav: one opaque/near-opaque surface, minimal shadow, no content blur.
- Card carousel: snap only, no continuous tilt loop.
- Account carousel: preserve stable keys and avoid rebuilding artwork profiles during scroll.
- Timeline/notification center: lazy rows for large data sets.
- Campaign carousel: images should be host-loaded and cached.
- Payment ritual: one focal animation, no dot overload or idle loops.
