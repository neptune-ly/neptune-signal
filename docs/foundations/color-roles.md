# Foundations: Color Roles

Color in Neptune. Signal follows Material 3 role thinking, then maps those roles to Neptune and white-label bank brands.

## Role Model

| Role | Default | Purpose |
| --- | --- | --- |
| `primary` | Neptune navy | Primary actions, selected navigation, secure account surfaces |
| `onPrimary` | White | Text and icons on primary |
| `primaryContainer` | Pale navy container | Selected surfaces, subtle branded backgrounds |
| `secondary` | Neptune teal | Supporting actions and operational signal |
| `secondaryContainer` | Pale teal container | Supporting selected state and low emphasis controls |
| `tertiary` | Neptune coral | Signature accent, risk, interruption, and brand pulse |
| `tertiaryContainer` | Pale coral container | Soft risk or attention surfaces |
| `surface` | Paper white | Main app surface |
| `surfaceContainer` | Soft blue-gray | Grouped panels and screen backgrounds |
| `surfaceContainerHigh` | Deeper container | Bottom navigation, sheets, elevated panels |
| `outline` | Cool gray | Strokes on rows, fields, separators |
| `error` | Signal danger | Failed transactions and validation errors |

## Neptune Defaults

```text
primary              #07315F
secondary            #00A8AE
tertiary             #EB4E4D
surface              #FAFCFC
surfaceContainer     #EEF5F5
outline              #C8D8DD
onSurface            #071C2E
onSurfaceVariant     #60747C
```

## Dark Mode Defaults

Dark mode is not an inverted palette. It is a separate banking surface system:

```text
surface              #071217
surfaceContainer     #142832
surfaceContainerHigh #1A333D
onSurface            #EEF7F8
onSurfaceVariant     #9DB1B8
outline              #38555F
outlineVariant       #263F48
```

Rules:

- Keep account and card secure surfaces branded, but move regular rows, fields, receipts, and lists onto dark surface containers.
- Primary, secondary, and tertiary roles must be derived for contrast in light, dark, and black modes.
- Black appearance uses black page surfaces with explicit row/container boundaries. It must not flatten the UI into one undifferentiated surface.
- Bottom navigation, status bars, sheets, receipts, and result pages must be reviewed in all appearance modes.

## White-Label Mapping

Bank colors enter through the bank layer, then generate Material-compatible roles:

```text
bank.primary   -> primary
bank.secondary -> secondary
bank.accent    -> tertiary
bank.ink       -> onSurface
bank.surface   -> surface
```

ATIB can use red as primary. NCB can use green. Yaqeen can use brown/gold. Andalus can use its own primary. The product should still feel like Neptune. Signal because spacing, hierarchy, copy behavior, motion, and components do not change.

Every bank preset must ship with light, dark, and black role sets. The bank chooses identity colors; Neptune. Signal derives accessible containers, text, outlines, and state layers for each appearance mode.

Black appearance is a deeper dark variant, not a different product. It keeps bank primary, secondary, accent, semantic status colors, and component hierarchy while moving page surfaces to black and raised containers to explicit dark layers.

## Usage Rules

- Do not use accent color randomly. Accent is for signature moments, risk, and attention.
- Do not make every icon teal or every selected state coral.
- Use primary for the main system state and root navigation.
- Use secondary for helpful operational signals.
- Use tertiary for the Neptune. pulse, risk, alerts, or one small signature mark.
- Status colors override brand colors when meaning matters.
- Contrast must be checked after every bank theme is created.
- Customer personalization may choose approved presets or appearance mode, but cannot alter status colors, typography scale, spacing, or receipt hierarchy.
- Black appearance must still use visible containers, outlines, state layers, and readable muted text. Do not remove structure by making every surface the same color.

## Anti-Patterns

| Anti-pattern | Fix |
| --- | --- |
| Primary color changes only the page background | Map primary into buttons, selected nav, headers, and state rings. |
| Mixed random icon colors | Icon colors come from role and row tone. |
| Accent overuse | Reserve tertiary for the signal moment. |
| Bank theme changes component shape | Only color roles change. Anatomy stays fixed. |
| Low contrast brand color | Generate a darker accessible role or use container roles. |
