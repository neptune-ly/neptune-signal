# Card Sensitive Data Mask Policy

Default:
- PAN: masked as `•••• •••• •••• 1234`.
- Expiry: policy-controlled; may be masked.
- CVV: masked and never considered stored permanent data.
- Holder name, network, and status may be visible.

Reveal:
- Explicit user action only.
- Biometric/PIN gate when available.
- Temporary reveal with auto-remask.
- Remask on navigation away and app background.
- No logs, no screenshots while sensitive reveal is active when platform support is enabled.
- No clipboard unless explicitly issuer-approved.

Prototype data is fake demo data.
