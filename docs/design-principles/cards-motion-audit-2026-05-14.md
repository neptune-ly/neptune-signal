# Cards + Motion Audit - 2026-05-14

## Scope

Screens reviewed:

- Nova Cards list.
- Nova Card Details.
- Signal `SignalPaymentCardStack`.
- Signal `SignalCardDetailStage`.

Source screenshots:

- `/Users/mtellesy/GitHub/neptune-nova/build/visual-audit-after/04-cards-v2.png`
- `/Users/mtellesy/GitHub/neptune-nova/build/visual-audit-cards-motion/01-card-detail-before.png`

## Findings

### Card List

The list structure is sound, but the card surfaces were still too close to generic developer finance UI:

- Decorative circles competed with the masked PAN and scheme label.
- Card foreground hierarchy was split between large ornamental shapes and actual banking data.
- Card entry motion had more rotation than needed for a calm fintech product.
- The scheme pill was visually heavier than the metadata it supports.

### Card Details

The card detail stage had a stronger concept, but the transition and color model needed tightening:

- The detail header used the card foreground color even though it sits on the stage surface, creating contrast risk.
- The detail card used a strong 3D flip angle that felt more like a demo effect than production banking motion.
- Dark/OLED metric panels used milky white overlays that could look noisy under low brightness.
- The stage background was too generic in black mode and did not respect the OLED surface hierarchy.

### Motion

Motion should communicate continuity from selected card to card details. The current route transition and detail-card reveal had the right direction, but the physical values were too expressive:

- Open motion should be longer and calmer, using Signal shared motion timing.
- 3D perspective should be subtle, not theatrical.
- Alpha/scale should maintain continuity instead of making the card feel like a new unrelated object.

## Decisions

- Card list decoration is reduced so financial identity and status are the dominant signals.
- Card detail motion uses `SignalMotionMetrics.sharedElementMillis`.
- Card detail 3D motion is retained but reduced to a premium, subtle reveal.
- Stage header text uses stage foreground, while the card visual keeps card foreground.
- OLED/black surfaces use lower-alpha overlays and the true surface color.

## Affected Contracts

- `SignalPaymentCardStack`
- `SignalPaymentCard`
- `SignalCardDetailStage`
- `SignalMotionMetrics.sharedElementMillis`
- `SignalComponentMetrics.cardFaceListHeight`

## Regression Avoided

- No balance is introduced to card list.
- No heavy animation files or external dependencies were added.
- No app-specific Nova styling was introduced.
- RTL direction remains part of the card detail motion.
