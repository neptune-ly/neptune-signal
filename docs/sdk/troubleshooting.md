# Troubleshooting

## Composite Build Version Mismatch

Align Kotlin and Compose versions between Signal and host apps.

## Android Build Locking

Avoid running standalone Signal and host app composite builds concurrently against the same Signal build directory.

## Missing Feedback

Check that the host app supplied platform adapters. Common no-op adapters intentionally do nothing.

## Broken RTL Identifiers

Use `SignalIdentifierRendering` helpers for IBAN/PAN/account numbers.
