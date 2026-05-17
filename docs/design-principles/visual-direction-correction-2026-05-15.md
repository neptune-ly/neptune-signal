# Visual Direction Correction

Date: 2026-05-15
Status: Active correction

## Problem

The latest wallet premiumization pass moved too far toward synthetic fintech visuals. The direction introduced too much atmospheric decoration and gradient behavior. That made the product feel closer to an AI-generated fintech concept than a high-end banking wallet.

## Current Failure Modes

- Static brand circles in the app shell read as decorative blobs.
- Gradient surfaces on nav, action dock, and command buttons compete with actual financial content.
- The center action still risks feeling like a generic FAB placed over a nav rail.
- Borders were reduced, but gradients replaced them as a new source of visual noise.
- Visual focus moved from account, balance, transfer, and activity toward surface styling.

## Why The Gradients Feel Wrong

The gradients are not tied to a job. They do not communicate state, hierarchy, or interaction. They make surfaces feel illustrated instead of engineered.

Neptune. Signal should use gradients only when:

- they support a branded asset or splash moment,
- they are low-contrast tonal transitions,
- they do not compete with text or financial data,
- they are component-level and repeatable.

## Why The Payment Action Feels Wrong

The center action is structurally correct as a shell-level action, but its visual treatment still feels too much like an overlaid button. It needs to feel fused with the bottom navigation rail.

Correct behavior:

- bottom nav owns app roots,
- center action owns money movement,
- the center action is visibly part of the nav architecture,
- it opens a safe flow and never executes money movement directly.

## New Direction

Neptune. Signal should move toward restrained Material 3 Expressive:

- hierarchy over decoration,
- tonal roles over gradients,
- proximity and grouping over card piles,
- one strong focal point per screen,
- quiet motion,
- deliberate contrast,
- bank data clarity first.

## Immediate Correction

- Remove decorative shell backdrop circles.
- Remove gradient nav rail.
- Remove gradient center action.
- Remove gradient action dock and insight surfaces.
- Keep the center action contract, but make it quieter and more integrated.
- Keep surface layering through tokenized tonal fills and spacing.

