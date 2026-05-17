# Auth Premiumization Batch 04

## Goal

Make auth and entry feel like a premium fintech product while keeping Signal as the implementation owner.

## Implemented

- Reduced auth icon sizes back into the Signal contract instead of local Nova overrides.
- Tuned auth header height to reduce empty vertical space.
- Lifted login form panels onto `surfaceCard`/low dark surfaces for calmer premium layering.
- Centered passkey glyph through Signal metrics.
- Kept Material 3 compatible action heights and touch target rules.

## Product Rules

- Intro/welcome is emotional but still banking-first.
- Login is secure, quiet, and fast.
- Language choice is a modal action with a clear 48dp close target.
- Public/guest auth surfaces must eventually use the same localization source as login.
- System back must have a defined auth route policy.

## Signal Ownership

Signal owns:

- auth panel surface treatment
- auth action heights
- icon/glyph sizing
- typography roles
- passkey action presentation

Nova owns:

- bank logo
- localized copy
- route state
- validation result
- backend/auth orchestration

## Rejected

- Copying Dribbble visuals directly.
- Noisy gradients and decorative crypto-style cards.
- Screen-local typography or icon-size patches.
