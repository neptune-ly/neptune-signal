# Component: Forms and Inputs

Forms must be clear, short, and resilient to Arabic and long financial identifiers.

## Field Types

- Text.
- Amount.
- IBAN.
- Alias.
- Phone or wallet identifier.
- Search.
- Select.
- Date.
- OTP/passcode.

## Rules

- Label is always visible or recoverable.
- Error text explains the fix.
- Amount fields show currency clearly.
- IBAN fields group and wrap safely.
- Paste should be supported for long identifiers.
- Copy/share actions belong next to the relevant value.

## Validation

Validation should happen:

- On blur for simple fields.
- On submit for expensive validation.
- Inline for format problems.
- With status page for async processing.

