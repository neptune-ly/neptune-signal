# Card Detail Presentation Mode Contract

Card Details is a secure card control center, not a repeated browsing card.

`SignalCardDetailPresentationMode` defines how detail opens:

- `SecureDataFirst`: used after carousel entry. It skips the large artwork and puts the secure data panel first.
- `CompactIdentity`: compact card identity header, then secure data and controls.
- `ExpandedArtwork`: allowed when entry came from list mode and the user has not just seen a large card.
- `ControlCenter`: compact identity plus operational grouping for controls, limits, and security.

Rules:

- Carousel entry must not repeat the same large card.
- List entry may show artwork only if bank policy allows it.
- `showLargeArtworkInDetails = false` means no giant card in detail.
- Secure data, controls, limits, and activity are the detail page's primary content.

