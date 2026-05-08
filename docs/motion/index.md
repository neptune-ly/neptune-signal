# Motion

Motion in Neptune. Signal is native and implementation-ready.

No Lottie, no video dependency, no heavy animation files.

## Timing

| Motion | Duration | Easing |
| --- | ---: | --- |
| Press compression | 120-180ms | ease out |
| Standard route | 220-260ms | cubic |
| Account container transform | 380-440ms | expressive standard |
| Product open | 280-360ms | expressive standard |
| Result settle | 280-340ms | emphasized |
| Waiting pulse | continuous, subtle | linear/cubic |

## Account Container Transform

Used when opening account details.

1. Source compresses.
2. Siblings dim.
3. Surface lifts.
4. Surface expands into header.
5. Page content settles.

## Result Motion

Waiting:

- Orbit or sweep motion.
- No blocking spinner that feels dead.

Success:

- Mark draws or resolves from signal ring.
- Receipt content appears after the mark settles.

Failure:

- Soft stop motion.
- Clear recovery action.

## Reduced Motion

When reduced motion is enabled:

- Disable morph overlays.
- Keep opacity changes under 150ms.
- Preserve navigation meaning.

