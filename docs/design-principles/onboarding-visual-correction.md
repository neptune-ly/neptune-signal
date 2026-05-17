# Onboarding Visual Correction

Date: 2026-05-15

## Problem

The onboarding hero was drifting toward concept-art onboarding: large decorative shapes, floating objects, and visual mass competing with the headline. That weakens trust because the first screen starts to feel like a generated fintech shot instead of a real banking product.

## Signal Direction

Onboarding is typography-first. The hero supports the message; it does not become the message.

Approved visual rules:

- Use restrained product cues: value panels, secure-state marks, account/payment metaphors, and compact status surfaces.
- Keep atmospheric depth subtle and tonal, not decorative.
- Avoid large floating illustration objects, glowing shapes, heavy gradients, and random orbit graphics.
- Let logo, headline, CTA, and legal links own the entry hierarchy.
- Preserve enough visual identity for white-label banks without forcing a decorative Neptune artifact into every client.

## Implementation Policy

Signal owns the onboarding hero component and its motion. Nova may provide slide content, bank logo, language, and theme tokens, but should not override hero composition locally.

## Rejected

- Illustration-first onboarding.
- Giant decorative hero cards.
- Crypto-style atmospheric glow.
- Random brand objects unrelated to product action.

