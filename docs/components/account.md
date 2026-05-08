# Component: Account

## Account Row

Used in the Accounts tab. It must be simple and clearly tappable.

Content:

- Account icon.
- Account name.
- Type/status/short IBAN.
- Balance or masked state.
- Direction-aware chevron.

No summary block is shown above the list in v0.1.

## Account Detail Header

Account details are the opened state of the account row, not a duplicate carousel card.

Content:

- Account name.
- Balance.
- IBAN with copy/share.
- Alias if available.
- More info action.

## Motion

Account row opens through a container transform:

1. Row compresses.
2. Sibling rows dim.
3. Surface lifts.
4. Surface expands into the detail header.
5. Detail content settles.

