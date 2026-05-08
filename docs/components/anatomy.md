# Component Anatomy

Every Neptune. Signal component is documented like a product standard, not only like sample code.

## Required Contract

Each component page must include:

- Purpose.
- Anatomy.
- Variants.
- States.
- Tokens.
- Motion.
- Content rules.
- Accessibility.
- RTL and LTR behavior.
- KMP API.
- Future SDK mapping.
- Figma component properties.

## Anatomy Template

```text
Component
  container
  leading slot
  primary label
  secondary metadata
  critical value
  trailing action
  state layer
  feedback region
```

Not every component uses every part. The anatomy names stay consistent so SDKs and Figma variants can map cleanly.

## States

All interactive components must define:

- Default.
- Hover or focus where platform supports it.
- Pressed.
- Selected.
- Disabled.
- Loading when async work starts.
- Error when user action fails.
- Success or completed when a transaction resolves.

## Content Rules

- Critical values use `SignalValueLine` or an equivalent value slot.
- Copy or share actions sit next to the exact value they affect.
- Metadata can truncate only when it is not essential for customer action.
- Arabic, English, and French labels must fit without changing component height unexpectedly.

## Accessibility Rules

- Interactive target is at least 44 by 44 dp.
- Icon-only buttons have labels.
- Status changes are announced.
- Focus order follows visual order.
- High contrast mode keeps action and status meaning.

## SDK Mapping

| Standard name | KMP | Flutter | SwiftUI | Android Compose | Web |
| --- | --- | --- | --- | --- | --- |
| SignalButton | `SignalButton` | `SignalButton` | `SignalButton` | `SignalButton` | `signal-button` |
| SignalValueLine | `SignalValueLine` | `SignalValueLine` | `SignalValueLine` | `SignalValueLine` | `signal-value-line` |
| SignalAccountRow | `SignalAccountRow` | `SignalAccountRow` | `SignalAccountRow` | `SignalAccountRow` | `signal-account-row` |
| SignalStatusResult | `SignalStatusResult` | `SignalStatusResult` | `SignalStatusResult` | `SignalStatusResult` | `signal-status-result` |
