# Secure Clipboard Policy

`SignalCardClipboardPolicy` controls card copy behavior:

- allow or block PAN, expiry, CVV, and cardholder copy
- require reveal before copying sensitive values
- request sensitive clipboard metadata where the platform supports it
- optionally clear clipboard after a timeout

No copied sensitive value should be logged or persisted. Hidden values must not be copied unless the reveal policy allows the field and the field is currently revealed.
