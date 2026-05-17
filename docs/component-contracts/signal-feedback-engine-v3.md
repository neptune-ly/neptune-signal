# Signal Feedback Engine V3

Contract:
- Feedback roles map to assets first.
- Synth fallback remains optional and developer-oriented.
- Reduced feedback shortens and softens cues.
- Silent/zero-volume conditions suppress playback where the platform can report them.
- Haptic hooks pair to prepare, verify, pending, success, failure, and cancel roles.

Current implementation:
- Signal defines roles, cues, durations, and fallback amplitude.
- Nova Android resolves bundled raw WAVs and applies final playback gain.
- Success gain is deliberately restrained to avoid cheap notification-like confirmation.

Production requirement:
- Replace generated placeholders with licensed professional UI assets while preserving filenames.
