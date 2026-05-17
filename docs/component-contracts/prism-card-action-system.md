# Prism Card Action System

Primary card actions:
- Freeze/unfreeze.
- Show details.
- Limits.
- Settings/PIN.

Each action must expose:
- `id`
- `label`
- `supportingText`
- `enabled`
- `destructive`
- `deeplink`

Visual priority:
- Freeze may use warning tone, but not aggressive red unless the action is destructive.
- Show details is security-gated.
- Disabled controls must remain readable and explain availability when backend supports it.
