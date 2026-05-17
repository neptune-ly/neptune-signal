# Prism Account Workspace

The Prism Account Workspace is the expanded state of a selected account. It is not a repeated Home account card and it is not a settings list.

Structure:
- compact Prism top bar owned by the shell/screen contract
- `SignalPrismAccountWorkspaceHero` for account identity and balances
- `SignalPrismAccountIdentityStrip` for fast copy of receiving identifiers
- `SignalPrismAccountPrimaryActions` for account-scoped actions
- `SignalPrismWorkspaceSegmentedControl` for progressive disclosure
- contextual panels for overview, activity, requests, and documents

The default visible section is Overview. Requests and documents are hidden behind segments so the page stays compact.

