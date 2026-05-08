# Motion

Motion in Neptune. Signal follows Material motion principles while staying native, lightweight, and finance-safe.

No Lottie, no video dependency, no heavy animation files.

## Motion Philosophy

Motion must make the system feel alive and explain continuity:

- Account card opens into account details using a container transform.
- Voucher tile opens into a purchase detail screen.
- Waiting, success, and failure use the same result grammar.
- Motion never hides a financial result or delays access to critical data.
- Reduced motion keeps meaning without decorative movement.

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

The detail page must not show the exact same account card again. The source surface becomes a new header layout so the transition feels like one object opening, not a duplicate card appearing.

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
