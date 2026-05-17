# Prism Control Center

Prism Control Center is the customer-facing personalization surface for Neptune Signal Prism.

It separates:
- bank/client experience policy
- card scheme rules
- product personality
- customer-safe preferences
- accessibility/security guardrails

It must not behave like developer settings. Options are visual, Arabic-first, curated, and constrained by bank policy.

Active in Nova now:
- compact live preview
- appearance mode as visual cards: system, light, dark, OLED
- curated Prism palette/mood selection as visual palette tiles
- card presentation mode as a small visual choice
- advanced home, typography, density, motion, sound, and haptics controls behind progressive disclosure
- bank identity demo switch behind a clearly labeled bank/demo disclosure

Contract-ready:
- home layout presets
- nav/payment action style
- typography/density
- motion/sound/haptics

## 2026-05-17 Simplification Rule

The first viewport must not be a full settings catalogue. It should answer only:

- كيف سيبدو التطبيق؟
- ما هو وضع الإضاءة؟
- ما هي لوحة الألوان؟
- كيف ستظهر البطاقات؟

Everything else is secondary. Bank/client configuration is not a customer control and must be collapsed or read-only in production.
