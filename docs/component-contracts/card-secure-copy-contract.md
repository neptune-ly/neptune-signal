# Card Secure Copy Contract

Prism supports field-by-field secure copy from the card details panel.

Copyable fields:
- PAN only when policy allows, usually after reveal
- expiry when policy allows
- cardholder name
- card reference/token

CVV copy is disabled by default. There is no “copy all card details” action unless a bank explicitly enables a policy later.

The UI uses `SignalPrismCopyableSecureField`; Nova supplies the platform clipboard adapter.
