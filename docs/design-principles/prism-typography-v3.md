# Prism Typography V3

Neptune Signal Prism uses a branded logical type system called **Neptune Prism Sans**.

Prism Sans is a product typography stack, not a single hardcoded platform font. Apps can provide bank-approved font assets, but the roles and behavior stay Signal-owned.

## Current Android Stack

- Arabic: IBM Plex Sans Arabic for clear Arabic rhythm and readable banking labels.
- Latin and numerics: Manrope for clean product UI and financial numerics.
- Identifiers: Prism identifier roles force LTR direction and tabular numeric rhythm where supported.

## Principles

- Arabic first: Arabic line height must breathe and labels must not be crushed.
- Financial numbers are strong, but not noisy.
- Metadata is calm and never competes with money or actions.
- Navigation labels are readable and compact, not over-bold.
- Status chips use semibold weight, not black weight.
- PAN, IBAN, card references, and account numbers render LTR inside RTL UI.
- Bank/client white-label apps may replace the font assets, but should preserve Prism roles.

## Core Roles

- `prismTitleHero`: account/card hero title.
- `prismTitleSection`: section headers.
- `prismTitleCard`: compact card and surface titles.
- `prismBodyPrimary`: readable Arabic body.
- `prismBodySecondary`: secondary explanation text.
- `prismMeta`: timestamps, small notes, contextual metadata.
- `prismNavLabel`: bottom navigation labels.
- `prismAmountLarge`: hero financial amount.
- `prismAmountCompact`: row and tile financial amount.
- `prismIdentifier`: IBAN, references, aliases, and account numbers.
- `prismCardPan`: card PAN display.
- `prismCardMeta`: card metadata.
- `prismStatus`: chips and small state labels.

## White-Label Contract

Banks can provide:

- Arabic font family.
- Latin/numeric font family.
- density preference.
- typography preference: standard, larger, high-readability.

Signal keeps:

- role naming.
- financial numeric behavior.
- identifier direction.
- accessibility minimums.
- text scaling compatibility.

