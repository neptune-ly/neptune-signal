# Motion

Motion in Neptune. Signal follows Material motion principles while staying native, lightweight, and finance-safe.

No Lottie, no video dependency, no heavy animation files.

## Motion Philosophy

Motion must make the system feel alive and explain continuity:

- Account card opens into account details using a container transform.
- Payment card opens into card details using the same card color and scheme context.
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
| Card container transform | 380-440ms | expressive standard |
| Product open | 280-360ms | expressive standard |
| Result settle | 280-340ms | emphasized |
| Waiting pulse | continuous, subtle | linear/cubic |

## Account Container Transform

Used when opening account details.

1. Source compresses.
2. Siblings dim.
3. Surface lifts.
4. Primary surface expands into the full upper account detail stage.
5. Header chrome appears.
6. Account text and balance settle.
7. Identifiers/actions follow.
8. Page content settles.

The detail page must not show the exact same account card again. The source surface becomes a new header layout so the transition feels like one object opening, not a duplicate card appearing.

The destination header must use the same `bankPrimary` surface as the source card. This is a Neptune. Signal rule: account detail is an opened account surface, not a white page with a repeated card. Use opacity, scale, and one vertical axis to communicate continuity. Content should arrive in groups: chrome first, primary account facts next, then secondary actions and lists.

KMP:

- Use `SignalAccountHeader(mode = SignalAccountHeaderMode.DetailStage)` for opened account detail headers.
- Use `SignalMotion.containerTransformSpec()` for shared account opening movement.
- Use `SignalMotion.containerChromeSpec()` for the top bar/chrome entrance.
- Delay secondary content by `SignalMotion.ContainerContentDelayMs`.

## Card Container Transform

Used when opening card details.

1. Source card compresses.
2. Other cards fade back without changing the list into a stacked deck.
3. The selected flat card lifts from the list and expands into the full upper card detail stage.
4. The destination stage is the opened card surface: same color, scheme mark, last four digits, and signal accent.
5. Do not place a second card inside the opened stage. Move the card content into the stage and use a short 3D settle to preserve the object metaphor.
6. Balance and live card values load only after the detail screen is open.
7. Controls and recent card transactions settle after the card surface lands.

This follows the same transition principles as account opening: fade irrelevant layers, scale the selected object, keep direction consistent, and reveal detail groups in priority order.

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
