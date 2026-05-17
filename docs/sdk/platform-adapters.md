# Platform Adapters

Adapters keep `commonMain` platform-safe.

## Common Contracts

```kotlin
SignalSoundAdapter
SignalHapticAdapter
SignalSecureClipboardAdapter
SignalSystemBarsAdapter
```

## Android

Android implementations should live in `androidMain` and handle:

- raw WAV asset playback
- system haptics
- sensitive clipboard metadata
- system bar appearance
- screenshot policy where required

## Other Platforms

Use no-op or host-specific safe fallbacks until platform implementations exist.
