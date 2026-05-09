# Pattern: Identity and Consent

## NPT Alias

NPT Alias is a customer identity handle:

```text
username@bank-handle
```

The UI must show:

- Full alias.
- Username.
- Bank handle.
- Default receiving account.
- Linked accounts.
- Approval verification state.

## Open Banking Consent

Open Banking consent is read access.

The OpenWave landing page is a hub list. It should not expose every consent, metric, and mandate in one screen. Each object type opens its own page.

Show:

- Provider.
- Data scopes.
- Read-only state.
- Last used.
- Expiry.
- Revoke/update actions.

Do not mix Open Banking consent with payment mandates. The customer must understand that this object can read approved data only and cannot move money. Use read-only tags, scope rows, last-used time, expiry, and the exact consent reference.

## Recurring Payment Subscription

Recurring payment consent is a payment mandate.

Show:

- Merchant.
- Maximum amount.
- Frequency.
- Funding account.
- Next charge.
- Mandate ID.
- Cancel/change limit actions.

Recurring payments must look like controlled debit rules, not generic subscriptions. Always show the maximum amount, frequency, funding account, next charge, mandate ID, and cancel/change limit actions on the detail page. In lists, use semantic compact labels and clear payment tags.

## One-Time Approval

One-time approval is a specific transaction request.

Show:

- Merchant.
- Amount.
- Account.
- Alias.
- Expiry.
- Risk signal.
- Approve and decline actions.

One-time approvals should look like a decision screen. The amount, merchant, alias, funding account, expiry, and request reference must be visible before the approve button. Approval requires the same sensitive-action treatment as transfers: passkey or biometric confirmation when available.
