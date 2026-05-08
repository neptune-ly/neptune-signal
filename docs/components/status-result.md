# Component: Status Result

Status result screens handle success, waiting, failure, voucher receipt, transfer receipt, and OpenWave approval outcomes.

## Required Information

- Status mark.
- Status title.
- Reference.
- Amount.
- Account.
- Fees.
- Date/time.
- Specific domain details:
  - Voucher PIN.
  - Voucher serial.
  - MTCN.
  - LyPay reference.
  - OpenWave mandate ID.

## Actions

- Primary: return home or continue.
- Secondary: details.
- Copy/share actions must target the correct value.
- Receipt values must wrap safely and never use vague truncation.

Voucher rule:

- Voucher PIN can be copied and shared.
- Voucher serial can be copied.
- Serial is not the main share object.
