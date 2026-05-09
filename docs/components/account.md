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

## Home Account Surface

The Home account surface is a quick balance and routing surface, not a full account detail component.

- Show the selected account name, type/status, balance, and one IBAN value.
- Do not show the alias on Home; alias belongs in Account Details and NPT Alias management.
- Do not show a visible copy icon on Home.
- Tapping the IBAN value copies the full IBAN.
- Label the Home value as `IBAN` inline on the same row as the value, not as a separate field block.
- If space is tight, keep the IBAN on one horizontal value line before wrapping. Never use vague ellipsis for the value.

## Account Detail Header

Account details are the opened state of the account row, not a duplicate carousel card.

The whole upper detail stage uses the same primary surface as the Home account carousel. The account card should not animate into a white page and then show another card. It opens into a blue/primary header where the title, balance, and identifiers are part of one expanded surface.

Content:

- Account name.
- Balance.
- Full grouped IBAN with copy/share.
- Full alias with copy/share if available.
- Account-specific operation list.
- Account-specific request list.

The visual should feel like the selected account row expanded into a detail state. It should not repeat the exact Home account card inside the details page.

Account Details must not become a second Home page. Avoid generic quick shortcut grids that duplicate Home actions. Every action should be phrased in account scope, such as `Transfer from this account`, `Receive to this account`, `Account documents`, and `Order card for this account`.

Use consistent list sections in account details:

- Account operations.
- Account requests.

Do not mix a request grid with an operations list on the same page unless the product has a strong reason and the hierarchy is visibly different.

KMP uses `SignalAccountHeaderMode.DetailStage` for this opened account state. Use the default `Card` mode for Home or compact account cards.

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
3. Primary surface lifts and expands upward into the detail stage.
4. Header chrome appears on the same primary surface.
5. Account text and balance settle.
6. Identifiers fade/slide in.
7. Detail content settles after the header lands.

Motion rules:

- The destination top area must inherit `bankPrimary` from the source account surface.
- Avoid showing the exact same card twice.
- Use opacity, scale, and vertical movement only; do not rotate banking content or use decorative assets.
- Keep the transform around 420-460ms with content delays under 300ms.
