# Prism Card Detail Header Contract

`SignalPrismCardDetailHeader` supports:

- `ExpandedArtwork`
- `CompactIdentityHeader`
- `ReceiptLikeHeader`
- `NoDuplicateHero`

The selected mode comes from `SignalCardPresentationPolicy` and the route context. This prevents carousel-to-details duplication while still allowing list-mode detail pages to show richer artwork when useful.
