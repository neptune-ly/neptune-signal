# Secure Card Data Panel Contract

`SignalPrismCardSecureDataPanel` owns card data reveal and copy UI.

Fields:

- PAN: masked by default, full reveal only when policy allows.
- Expiry: masked by default unless policy allows reveal.
- CVV: masked and copy-disabled by default.
- Cardholder: visible and copyable when policy allows.
- Card reference: optional, non-PAN operational identifier.

Behavior:

- Field-by-field copy only.
- No copy-all action.
- Reveal is temporary and remasks automatically.
- Reveal state must clear on navigation away.
- UI feedback is inline, not toast-based.
- PAN/expiry/CVV use LTR identifier rendering inside Arabic UI.

