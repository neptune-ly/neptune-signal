# Foundations: Tokens

Tokens are the bridge between Figma, CSS, KMP Compose, Flutter, native apps, web, and product themes.

Neptune. Signal 0.2.0 uses two layers:

1. Material-compatible roles for platform behavior.
2. Neptune and bank semantic aliases for financial product identity.

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
appearance.mode
appearance.preference
```

`appearance.mode` is the resolved mode used by the UI: `light`, `dark`, or `black`.

`appearance.preference` is the customer setting: `system`, `light`, `dark`, or `black`.

## Material-Compatible Role Tokens

```text
primary
onPrimary
primaryContainer
onPrimaryContainer
secondary
onSecondary
secondaryContainer
onSecondaryContainer
tertiary
onTertiary
tertiaryContainer
onTertiaryContainer
surface
surfaceDim
surfaceBright
surfaceContainerLowest
surfaceContainerLow
surfaceContainer
surfaceContainerHigh
surfaceContainerHighest
onSurface
onSurfaceVariant
outline
outlineVariant
inverseSurface
inverseOnSurface
error
onError
errorContainer
onErrorContainer
```

## Usage

- `bank.primary`: navigation, top app bar, primary buttons, account secure surfaces.
- `bank.secondary`: supporting actions, secondary selected states.
- `bank.accent`: signal strip, risk moments, status emphasis, branded splash motion.
- `primary`: Material primary role, normally mapped from `bank.primary`.
- `secondary`: Material secondary role, normally mapped from `bank.secondary`.
- `tertiary`: Material tertiary role, normally mapped from `bank.accent`.
- `surface.paper`: app background.
- `surface.card`: cards, rows, panels.
- `border.default`: row and card strokes.
- `appearance.preference`: stored customer choice.
- `appearance.mode`: resolved rendering mode after system preference is applied. `black` is explicit; `system` resolves only to platform light or dark.

## White-Label Rule

Bank themes may replace `bank.primary`, `bank.secondary`, and `bank.accent`, but must not change:

- Spacing.
- Typography roles.
- Component anatomy.
- Motion timing logic.
- Accessibility rules.

Dark mode is not a bank override. It is a required mode for every preset. A bank can provide preferred dark identity colors, but the system still owns contrast, surface hierarchy, state layers, and status meaning.

## Token Governance

| Token family | Can bank override | Must stay Signal-controlled |
| --- | --- | --- |
| Brand hue | Yes | Contrast checks and role mapping |
| Surface containers | Limited | Light/dark hierarchy and elevation meaning |
| Error/success/warning | No, unless approved | Meaning and accessibility |
| Spacing and radius | No | Component stability |
| Typography roles | No | Readability and density |
| Motion durations | No | Performance and consistency |
