# Financial Timeline Home Contract

Home shows a compact preview of the user's financial life, not a full notification center.

Rules:
- Maximum 3 rows on Home.
- First row may be emphasized if action is required.
- Rows contain title, metadata, and time/status.
- No per-row chevrons.
- No random dots; only one restrained vertical marker for emphasis.
- Full history belongs in Notifications / Financial Timeline.

Component:
- `SignalFinancialTimelineHomePreview`
- `SignalFinancialTimelineHomeItem`

May 16 Home contract:
- Home owns only a preview: OpenWave approval, incoming LyPay transfer, and USD rate alert in the current demo content.
- Notification Center / Financial Timeline owns dense history, filters, actions, and category grouping.
- The Home preview uses typography and one restrained urgency marker instead of a pale feed card with repeated dots.
