# Wallet Reference Study

Date: 2026-05-15
Reference: Preview-only e-wallet mobile app design

## What Makes The Reference Feel Modern

The reference feels premium because it controls visual weight. It does not simply add decoration. The strongest qualities are:

- one dominant wallet object per screen,
- soft tonal planes instead of hard boxes,
- restrained gradients used as atmosphere,
- clear negative space around the financial hero,
- a primary money action that feels physically important,
- dense but calm transaction presentation,
- smooth hierarchy from hero to actions to activity,
- consistent spacing rhythm.

## Neptune Interpretation

Neptune. Signal should translate those qualities into a banking-first wallet system:

- The account/card surface is the financial hero.
- Transfer or payment is the central shell action, not another dashboard tile.
- Recent activity remains dense and readable.
- Tonal layering replaces repeated borders.
- Gradients stay subtle and structural, never decorative.
- The shell remains white-label ready through tokens, not hand-painted screen colors.

## Surface Direction

Signal surfaces should use a small set of tonal planes:

| Plane | Purpose |
| --- | --- |
| `surface` | app canvas and scroll background |
| `surfaceContainerLow` | atmospheric page depth |
| `surfaceCard` | primary panels and cards |
| `surfaceContainerHigh` | dark-mode elevated panels |
| `bankPrimary` | trusted financial emphasis |
| `bankSecondary` | primary action signal |

Borders should be softer and less frequent. Elevation should come from tone, spacing, and restrained shadow rather than heavy outlines.

## Dashboard Direction

The dashboard should read in this order:

1. Who is using the app.
2. What account/card is currently active.
3. What primary money action is available.
4. What happened recently.
5. What secondary services are available.

This avoids widget soup and keeps the first screen wallet-oriented without turning it into a marketing page.

## Motion Direction

Motion should be restrained:

- press compression is small and fast,
- carousel snap feels natural,
- shared transitions keep the selected financial object continuous,
- central action opens a flow with a short spatial transition,
- reduced motion keeps state changes clear without morphs.

Rejected motion: bouncing, spinning payment icons, animated coins, confetti, card flips, parallax dashboards, and slow cinematic transitions.

