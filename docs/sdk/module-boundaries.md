# Module Boundaries

## Signal Owns

- Prism tokens and profiles.
- Reusable components.
- Motion contracts.
- Feedback and clipboard adapter contracts.
- Financial formatting helpers.
- RTL/accessibility helpers.
- Demo/gallery sample data in dedicated demo surfaces.

## Host Apps Own

- Product data.
- Backend calls.
- Routing.
- Persistence.
- Analytics.
- Permissions.
- Platform adapter implementations.

## Hard Rules

- No Nova imports in Signal.
- No app route classes in Signal components.
- No Android APIs in `commonMain`.
- No demo-only data as production defaults.
