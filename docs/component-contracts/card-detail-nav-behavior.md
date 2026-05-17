# Card Detail Navigation Behavior

Card detail routes are operational detail surfaces.

Rules:

- Use canonical Prism top bar.
- Back icon is Signal-owned and RTL-correct.
- Bottom nav may be hidden or padded away from content.
- Content must never render behind the floating dock.
- Sensitive reveal state must clear on back/navigation.

Default:

- Cards tab keeps bottom nav.
- Card Details uses detail top bar and reserves enough bottom padding if shell nav remains visible.

