# Signal Prism Site Map

GitHub Pages deploys the checked-in `docs/` folder directly. Static HTML demos and landing pages must therefore be updated alongside Markdown contracts.

## Primary Navigation

1. Overview: `index.html` and `index.md`
2. Interactive mobile demo: `demos/mobile/prototype.html`
3. SDK architecture: `sdk/index.md`
4. White-label configuration: `sdk/white-label-configuration-system.md`
5. Experience resolver: `sdk/experience-resolver-contract.md`
6. Component catalog: `components/README.md`
7. Component contracts: `component-contracts/README.md`
8. Demo gallery architecture: `demo/demo-gallery-architecture.md`
9. Accessibility and RTL: `sdk/accessibility-contract.md`, `sdk/rtl-ltr-contract.md`
10. Performance and recomposition: `sdk/performance-guidelines.md`, `sdk/recomposition-audit.md`
11. Platform adapters: `sdk/platform-adapter-contracts.md`
12. Release process and changelog: `sdk/release-process.md`, `../CHANGELOG.md`

## Prism Screens To Surface

- Daily Cockpit Home
- Account Workspace V3
- Cards list mode and Card Control Center
- Prism Control Center
- Service Hub
- Notification Center
- Campaign carousel/detail
- NPT Alias
- OpenWave consents
- Recurring payments
- Approval Center

## Palette Story

The public site should state that Prism uses semantic tone resolution, not direct color swaps:

- Bank primary, secondary, and accent feed `primary`, `secondary`, `accent`, `payment`, container, and content roles.
- Neptune fallback uses deep navy `#082B5A`, cyan `#29D4FF`, coral `#FF6A6A`, plus derived violet.
- Components must consume semantic roles so palette changes remain readable across light, dark, and OLED.
- Customer personalization is curated and constrained by bank policy and accessibility guardrails.
