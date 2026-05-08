# Component: Account

## Account Row

Used in the Accounts tab. It must be simple and clearly tappable.

Content:

- Account icon.
- Account name.
- Type/status/semantic compact identifier, such as `IBAN ending 0101`.
- Balance or masked state.
- Direction-aware chevron.

No summary block is shown above the list. Accounts is a real list of all accounts, not a repeated Home carousel.

KMP provides both `SignalAccountRow` for the common IBAN-backed case and `SignalAccountSummaryRow` when the product already computed the compact identifier.

## Account Detail Header

Account details are the opened state of the account row, not a duplicate carousel card.

Content:

- Account name.
- Balance.
- Full grouped IBAN with copy/share.
- Full alias with copy/share if available.
- More info action.

The visual should feel like the selected account row expanded into a detail state. It should not repeat the exact Home account card inside the details page.

## Information Rules

- Account lists optimize scanning and use semantic compact identifiers.
- Account details show the full IBAN and alias.
- Detail headers should try to keep the grouped IBAN on one readable line with horizontal overflow before wrapping.
- Masking is intentional privacy behavior, not a layout shortcut.
- Copy actions always copy the full underlying value.

## Motion

Account row opens through a container transform:

1. Row compresses.
2. Sibling rows dim.
3. Surface lifts.
4. Surface expands into the detail header.
5. Detail content settles.
