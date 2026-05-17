# Prism Money Movement Preview

`SignalPrismMoneyMovementPreview` shows the last two account-relevant money movements on Home.

It is not a full transaction list.

Contract fields:
- `id`
- `title`
- `metadata`
- `amount`
- `tone`
- `postedAt`
- `deeplink`

Rules:
- Max two rows on Home.
- Amounts use tabular formatting from Nova data.
- Incoming/outgoing tone must be semantic, not decorative.
- Full history belongs in activity/details surfaces.
