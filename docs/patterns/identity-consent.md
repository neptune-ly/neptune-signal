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

Show:

- Provider.
- Data scopes.
- Read-only state.
- Last used.
- Expiry.
- Revoke/update actions.

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

