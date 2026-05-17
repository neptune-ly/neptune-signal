# GitHub Pages Update Plan

The repo already deploys the `docs/` folder through `.github/workflows/pages.yml`.

## Current Prism Site Update

- Landing page presents Prism as a dark-first KMP UI SDK, not a strict Material clone.
- Mobile demo exposes semantic Prism palette changes: Neptune, bank presets, dark, light, and OLED/black modes.
- Demo components use resolved roles for account card, quick actions, nav, payment/action surfaces, and raised surfaces.
- Site map points to SDK, white-label, resolver, component, accessibility, RTL, platform, and release docs.
- Component catalog documents the resolved-tone rule so palette changes do not break contrast.

## Next Site Update

- Add screenshot gallery structure with light/dark/OLED examples.
- Add component-specific rendered examples for Account Workspace, Cards, Service Hub, and Prism Control Center.
- Add white-label preset gallery: Neptune Premium Wallet, Classic Bank, Islamic Calm, Corporate Dense, Merchant Energy, Youth Fintech.
- Add changelog and release links.
- Add automated visual smoke captures for `docs/demos/mobile/prototype.html`.

## Deployment

Current workflow uploads `docs/` directly. No build step is required today.
