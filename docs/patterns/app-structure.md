# Patterns: App Structure

Neptune. Signal app structure follows familiar Material mobile patterns, then tightens them for banking performance and trust.

## Root Navigation

Mobile banking uses four roots:

| Root | Purpose |
| --- | --- |
| Home | Current state, latest activity, primary transfer action, quick receive, vouchers, services |
| Accounts | All accounts with more account-level detail than Home |
| Cards | Card list and card details, no balance fetch on the list |
| More | Security, profile, language, notifications, support, alias, consent, settings, sign out |

Nested pages keep the active root stable. Opening account details from Home keeps Home active. Opening account details from Accounts keeps Accounts active.

## App Bars

Use a Material-style top app bar pattern:

- Root pages show title and one or two high-value actions.
- Nested pages show back, title, subtitle only when useful, and contextual actions.
- Page titles use `titleLarge` or Signal `pageTitle`.
- Do not place marketing copy inside app bars.

## Lists and Carousels

- Home can use a compact account carousel because it is a summary.
- Home order is account state, latest two transactions, primary action dock, then insight. The latest activity must not be buried below promotional or secondary actions.
- Accounts root should be a list of all accounts, not one large hero card.
- Details screens should transform into a detail header and operation list, not repeat the home card.
- Rows must look tappable when they navigate.
- Related rows use one grouped list surface. Avoid a stack of separate cards when the items are one list.

## Performance

- Fetch only what the current screen needs.
- Do not fetch card balance on the card list.
- Do not load heavy analytics before the analytics screen opens.
- Prefer compact summaries on Home and full values on details.
- Use skeleton or pending states that preserve layout size.

## Transaction Results

Success, failure, and pending pages use one result grammar:

```text
state mark
title
reference
critical details
copy/share actions
report problem
primary recovery or done action
```

Voucher receipts must show voucher PIN and serial separately. Sharing should target the voucher PIN or number only unless policy says otherwise.
