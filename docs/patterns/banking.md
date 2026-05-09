# Banking UX Patterns

## Accounts

- Accounts tab is a clean directory of all customer accounts.
- Account details contain account-specific requests and operations.
- Requests include card order, certified cheque, cheque book, and order tracking.

## Cards

- Card list shows flat card visuals and scheme marks.
- Card list must not fetch or show card balances.
- Card details can fetch balance and recent card transactions.
- Opening a card uses a container transform. The detail card keeps the same color, scheme mark, and card identity as the list card.

## Transfers

Transfer hub types:

- Internal.
- Between my accounts.
- LyPay.
- Western Union.

## LyPay

LyPay supports:

- IBAN.
- Alias.
- QR.
- Saved contacts.
- Async status page when processing passes the expected waiting threshold.

## OpenWave and NPT Alias

NPT Alias is customer identity and routing:

```text
username@bank-handle
```

Open Banking consents and recurring payment subscriptions are different objects and must be shown separately.

Open Banking access:

- Provider.
- Read-only scopes.
- Expiry.
- Last used.
- Revoke/update scope.
- Exact consent reference.

Recurring payments:

- Merchant.
- Maximum amount.
- Frequency.
- Funding account.
- Next charge.
- Mandate ID.
- Cancel/change limit.

OpenWave overview pages should be a clean hub list, not a dense dashboard. Link to separate pages for registered IBAN accounts, data access, recurring debit mandates, and one-time approvals.

Registered OpenWave accounts:

- A customer may have multiple IBANs in the same bank.
- Show all registered IBAN accounts with one default account.
- Changing the default account must be an explicit action.
- Detail pages show the full IBAN. The hub list can use semantic compact identifiers.
