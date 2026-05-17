# Fintech Sound Assets V4

Purpose:
- Asset playback is the product sound path; synth is only a quiet developer fallback.

Asset roles:
- `signal_feedback_prepare.wav`
- `signal_feedback_verify.wav`
- `signal_feedback_confirm.wav`
- `signal_feedback_success.wav`
- `signal_feedback_failure.wav`
- `signal_feedback_pending.wav`
- `signal_feedback_cancel.wav`
- `payment_success.wav`

Sound direction:
- Short, warm, low-volume, under 300ms.
- Harmonically related cues.
- No notification beeps, arcade sounds, harsh synthetic chirps, or copied proprietary payment sounds.

Fallback:
- Synth amplitudes are intentionally very low.
- If licensed assets are missing, fallback should feel subtle or nearly silent.
