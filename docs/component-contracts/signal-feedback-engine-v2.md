# Signal Feedback Engine V2

Payment feedback is asset-first.

Rules:
- Success, verify, confirm, prepare, pending, failure, and cancel roles map to bundled WAV assets.
- Synth remains fallback/dev behavior only when assets are missing or explicitly configured.
- Silent mode and zero media volume suppress audio.
- Reduced feedback must shorten/soften cues.
- Haptics pair with feedback roles but remain platform-aware.

Current demo assets are generated placeholders. Production should replace them with licensed Boom/Krotos-quality UI assets under the same filenames.

Current asset policy:
- `payment_success.wav` and `signal_feedback_success.wav` are warm, short placeholder chimes generated for development only.
- Role files must stay under 300ms unless product research explicitly justifies a longer cue.
- Asset playback must win over synth in debug and release whenever a bundled asset exists.
- Synth fallback exists for missing assets and developer builds; it is not a production sound identity.
