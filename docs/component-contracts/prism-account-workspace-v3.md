# Prism Account Workspace V3

Account Workspace V3 is the expanded control surface for one selected account. It must feel spatially connected to the Home account card without duplicating that card.

## Rules

- The Home account card is the entry point only.
- Details use `SignalPrismAccountWorkspaceStage`, not the Home card layout.
- The stage owns the account color, identity, balance, status, mask/unmask, and top navigation.
- Receiving identifiers use a compact rail.
- Actions are capped at four primary account actions.
- Overview, activity, requests, and documents are segmented so the page does not become a long settings list.

## Not Allowed

- Repeating the full Home account card in details.
- Exposing requests, documents, limits, services, and activity all at once.
- Page-local back buttons or sticky title hacks.
- Giant menu rows pretending to be account details.
