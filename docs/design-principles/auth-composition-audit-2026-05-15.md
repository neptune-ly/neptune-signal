# Auth Composition Audit - 2026-05-15

## Reference Principles

The external onboarding reference was treated as a composition reference only. Principles extracted:

- confident asymmetry
- staged vertical rhythm
- one clear brand anchor
- hero content that supports the product story
- CTA stack with a dominant path
- bottom interaction zone designed for reach
- surface layering that guides attention instead of adding decoration

## Current Issues

### Intro

- Hero art is abstract and does not communicate banking, trust, device registration, or secure session value.
- Logo appears in both hero and content card, weakening hierarchy.
- Lower content card is too independent from the hero; it feels placed over the screen rather than choreographed.
- The empty lower viewport makes the composition feel unfinished.

### Login

- Brand header, state card, and form panel compete as three heavy surfaces.
- The form starts too late because the brand header is large and centered.
- The security message is visually heavier than its function.
- Passkey reads as another large CTA instead of a trusted-device shortcut.
- Language action differs from intro, which weakens system consistency.

## Composition Directions

### Direction A - Cinematic Trust Hero

Upper 55-60% is an edge-to-edge product hero with one integrated brand mark and a restrained device/card visualization. CTAs live in a lower reachable zone.

Strength: strongest first impression.
Risk: can become fake-dribbble if overdecorated.

### Direction B - Bank Identity Masthead

Compact masthead holds bank logo/name/language. A single auth panel carries form, CTA, and a slim trust strip.

Strength: fastest, clearest login.
Risk: intro can still feel too utilitarian if used alone.

### Direction C - Signal Console

Intro shows a secure banking console: masked account/card hints, device trust, and session signal. Login reuses the same trust language as a compact strip.

Strength: connects Neptune. Signal identity to real banking behavior.
Risk: must avoid clutter and fake data noise.

## Approved Direction

Use Direction C for intro and Direction B for login.

## Implementation Rules

- Signal owns masthead, trust strip, auth panel spacing, icon sizing, and button hierarchy.
- Nova owns bank profile, copy, and route callbacks.
- Avoid new gradients unless they are token-driven and restrained.
- Preserve 48dp touch targets and keyboard visibility.
- Reduced motion must remove nonessential looping motion.
