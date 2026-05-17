# Navigation Color Role Contract

Navigation color must follow Material 3 roles.

Rules:
- Nav shell: `surfaceContainerHigh` / `surfaceContainerHighest` family.
- Center payment: `primary` / `onPrimary`.
- Active root: `primaryContainer` / `onPrimaryContainer`.
- Inactive roots: `onSurfaceVariant`.
- Border: `outlineVariant` only at low alpha.
- Elevation and divider behavior must be subtle; contrast should come from container roles before stroke.
- Expressive profiles may tune surface tone, but must not repaint the dock into a themed slab.

Green is reserved for success or money-in unless the bank profile defines green as primary.
