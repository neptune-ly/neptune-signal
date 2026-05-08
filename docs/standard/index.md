# Neptune. Signal Standard 0.1.1

Neptune. Signal Standard is the source of truth for how Neptune. financial products should look, move, read, and behave.

This standard is not a single app design. It governs multiple product implementations:

- Mobile banking.
- Internet banking.
- Merchant portals.
- Admin and operations consoles.
- Payment and identity approval surfaces.
- Future KMP libraries and Figma kits.

## SDK Implementation Model

Components are defined once in the standard, then implemented by SDKs:

```text
Signal Standard Component
  -> KMP Compose implementation
  -> Flutter implementation
  -> Native iOS implementation
  -> Native Android implementation
  -> Web implementation
```

KMP is the first active target. Other platforms are planned and should follow the same component contract.

## Standard Scope

Version `0.1.1` covers:

- Brand and white-label token model.
- Typography scale and density.
- Spacing, layout, shape, elevation, and icon rules.
- Core mobile app shell.
- Banking rows and information hierarchy.
- Critical value presentation for IBAN, alias, amount, voucher, reference, MTCN, and consent values.
- Transfer, status, consent, account, card, voucher, service, and support patterns.
- Native motion language.
- Accessibility and multilingual rules.
- KMP Compose implementation guidance.
- Figma translation guidance.

## Product Demo Versions

The standard version and demo versions are separate.

```text
Neptune. Signal Standard 0.1.1
Mobile Banking Demo 0.1.1
Internet Banking Demo planned
KMP Compose Library 0.1.1
Figma Kit planned
```

## Compatibility Rule

A product can claim Neptune. Signal compatibility only when it follows:

- Token model.
- Typography roles.
- Root navigation model.
- Component behavior.
- Motion behavior.
- Accessibility and language rules.
- Banking-specific performance rules.
