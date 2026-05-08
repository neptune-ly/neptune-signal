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
- Amount fields show currency clearly and keep it visually attached to the number.
- IBAN fields show the full identifier while editing; they must not use ellipsis.
- IBAN display values group and wrap safely.
- Paste should be supported for long identifiers.
- Copy/share actions belong next to the exact value they affect and use icon actions where possible.

## Critical Value Behavior

Do not render important values as partial strings:

```text
Bad:  partial IBAN with trailing dots
Good: LY81 0240 0101 0000 6712 0201 01
Good in compact lists: IBAN ending 0101
```

## Validation

Validation should happen:

- On blur for simple fields.
- On submit for expensive validation.
- Inline for format problems.
- With status page for async processing.
