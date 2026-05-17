# Prism Account Command Center

The account detail surface is a command center for one selected account, not a settings page.

Signal owns the reusable UI primitives:
- `SignalPrismAccountDetailHero`
- `SignalPrismAccountIdentityPanel`
- `SignalPrismAccountActionCluster`
- `SignalPrismBalanceBreakdown`
- `SignalPrismAccountPackagePanel`
- `SignalPrismAccountActivityPreview`
- `SignalPrismAccountRequestPanel`
- `SignalPrismAccountDocumentsPanel`
- `SignalPrismLinkedAccountServices`

Nova supplies account data, route actions, demo copy, backend capabilities, and clipboard adapters.

The command center must answer: account identity, available balance, copyable receiving identifiers, account-specific actions, balance state, limits and fees, recent activity, requests, documents, and linked services.

