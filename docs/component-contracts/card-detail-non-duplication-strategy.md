# Card Detail Non-Duplication Strategy

The Cards tab and Card Details page must not repeat the same large card without purpose.

## Rules

- From `Carousel`: the large card itself is not the details trigger. Details are opened through an explicit action such as `بيانات البطاقة`, and the details route uses `NoDuplicateHero` by default because the card face already carried the card identity.
- From `List`: details may use `ExpandedArtwork` because the row only showed a compact identity.
- If `showLargeArtworkInDetails` is false, details must never render a giant duplicate card.
- Details are a secure control and information surface, not an artwork gallery.

The detail page should prioritize secure data, copy actions, limits, controls, and recent activity.
# Current Implementation Note

Carousel entry uses `SecureDataFirst`; the detail page starts with the secure data panel and operational controls.

List entry may use `ExpandedArtwork`, but only because list mode shows compact rows instead of full artwork.

No route should show a full card twice in sequence.
