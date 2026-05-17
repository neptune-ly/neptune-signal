# Platform Adapter Contracts

Common adapter interfaces live in `ly.neptune.signal.feedback`.

## Adapters

- `SignalFeedbackAdapter`
- `SignalSoundAdapter`
- `SignalHapticAdapter`
- `SignalClipboardAdapter`
- `SignalSecureClipboardAdapter`
- `SignalSystemBarsAdapter`

## Host Responsibilities

Android, iOS, desktop, and web hosts provide implementations. Signal components should receive adapters through the host app layer or composition locals in a later batch.

## Security

Secure clipboard payloads include:

- label
- value
- sensitive flag
- optional clear timeout

Host adapters must not log payload values.
