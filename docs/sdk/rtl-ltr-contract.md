# RTL/LTR Contract

Signal Prism is Arabic-first but must support LTR products.

## Rules

- Layout direction follows host locale unless explicitly overridden for previews.
- Back/close icons follow semantic navigation, not decorative mirroring.
- Payment and transfer motion direction must respect layout direction.
- Financial identifiers render LTR inside RTL layouts.
- Mixed Arabic, Latin, and numbers must avoid broken visual order.

## Helpers

Use `SignalIdentifierRendering` for IBAN, PAN, aliases, and account numbers that must remain readable in RTL contexts.
