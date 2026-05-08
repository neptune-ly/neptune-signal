# Foundations: Tokens

Tokens are the bridge between Figma, CSS, KMP Compose, and product themes.

## Core Neptune Tokens

```text
neptune.navy   #07315F
neptune.teal   #00A8AE
neptune.coral  #EB4E4D
neptune.beige  #F0CE9D
neptune.sky    #3BC1EE
```

## White-Label Semantic Tokens

```text
bank.primary
bank.secondary
bank.accent
bank.ink
surface.paper
surface.card
surface.soft
text.primary
text.secondary
text.inverse
border.default
border.strong
status.success
status.warning
status.danger
```

## Usage

- `bank.primary`: navigation, top app bar, primary buttons, account secure surfaces.
- `bank.secondary`: supporting actions, secondary selected states.
- `bank.accent`: signal strip, risk moments, status emphasis, branded splash motion.
- `surface.paper`: app background.
- `surface.card`: cards, rows, panels.
- `border.default`: row and card strokes.

## White-Label Rule

Bank themes may replace `bank.primary`, `bank.secondary`, and `bank.accent`, but must not change:

- Spacing.
- Typography roles.
- Component anatomy.
- Motion timing logic.
- Accessibility rules.

