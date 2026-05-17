# Component Catalog

Signal components are product-grade contracts, not isolated visual snippets.

## Current Prism Demo Rules

- Components consume resolved Prism roles, not raw bank colors.
- Palette changes must update account surfaces, quick actions, nav, payment action, campaign cards, and raised surfaces together.
- Components must keep readable `on-*` content colors in light, dark, and OLED.
- Neptune fallback uses deep navy, cyan, coral, and derived violet/payment tones; bank palettes can override those roles safely.
- Demo-only visuals must remain in demo surfaces and not leak into reusable SDK APIs.

## Foundation

- colors
- gradients
- typography
- spacing
- radius
- elevation
- motion
- density

## Components

- buttons
- chips, option tiles, and compact Prism selectors
- rows and list groups
- top bars
- floating nav and payment action
- account cards and account carousel
- account workspace stage and identifier rail
- card list mode and card control center
- secure card fields
- transfer ritual
- notification center
- campaign carousel
- service hub
- personalization controls
- forms and fields
- timelines and movement rows
- status badges

## Documentation Template

Each component doc should include:

- purpose
- when to use
- when not to use
- API
- required data
- events/callbacks
- accessibility notes
- RTL notes
- screenshots
- performance notes
- white-label options
- backend contract notes
