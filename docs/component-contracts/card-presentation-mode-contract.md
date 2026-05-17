# Card Presentation Mode Contract

Cards in Prism are policy driven. A bank profile or remote config chooses the default presentation and may allow a user override.

## Modes

- `Carousel`: wallet-like browsing with one selected large card. The card itself selects/browses only; it should not be the primary details entry point because that creates duplicate large-card pages.
- `List`: clear operational rows for traditional, corporate, or clarity-first banks.
- `CompactStack`: dense many-card mode. The contract exists for future corporate/many-card scenarios, but it should not be exposed until a distinct stack UI exists.

## Policy

`SignalCardPresentationPolicy` defines:
- default mode
- allowed modes
- whether the user can override
- card-detail hero behavior
- whether details may show large artwork

Nova consumes the policy; Signal owns the visual patterns.
# Current Implementation Note

`SignalCardPresentationPolicy` now separates tab presentation from detail presentation:

- `detailModeFromCarousel`: defaults to `SecureDataFirst`.
- `detailModeFromList`: defaults to `ExpandedArtwork`.
- `showLargeArtworkInDetails`: hard stop for banks that do not allow large detail artwork.

This prevents the carousel from becoming "large card, then same large card again".
