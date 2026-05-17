# Neptune Signal Prism

Neptune Signal Prism is a reusable KMP UI SDK and premium fintech design language for Neptune Nova Retail, corporate, merchant/vPOS, vouchers/VAS, future wallet apps, and white-label bank apps.

Prism is no longer a strict Material visual layer. Material remains a guardrail for accessibility, tap targets, navigation logic, semantic color discipline, and motion usability. Prism owns the product identity: dark/OLED atmosphere, controlled gradients, account and card surfaces, floating navigation, payment action, personalization, and white-label brand adaptation.

## Live Demo

- [Mobile Prism Prototype](demos/mobile/prototype.html)
- [Mobile Demo Notes](demos/mobile/index.html)

The mobile demo now reflects the semantic Prism palette model:

- Neptune default: deep navy `#082B5A`, cyan `#29D4FF`, coral `#FF6A6A`, with a derived violet/payment tone.
- Bank palettes feed semantic roles, not random component colors.
- Quick actions, account card, nav, payment/action surfaces, and raised surfaces consume the same resolved tones.
- Dark and OLED modes use opaque or near-opaque surfaces so text remains readable.
- Light mode uses soft frosted surfaces without black-on-gradient account/card failures.

## Start Here

- [SDK Architecture](sdk/index.md)
- [Signal Prism SDK Audit](sdk/signal-prism-sdk-audit.md)
- [Public API Contract](sdk/public-api-contract.md)
- [Component Catalog](components/README.md)
- [Demo Gallery Architecture](demo/demo-gallery-architecture.md)
- [GitHub Pages Site Map](site/signal-prism-site-map.md)

## Core Guides

- [White-label Configuration](sdk/white-label-configuration-system.md)
- [Experience Resolver](sdk/experience-resolver-contract.md)
- [Prism Theme Contract](component-contracts/prism-theme-contract.md)
- [Prism Control Center](component-contracts/prism-control-center.md)
- [Prism Service Hub](component-contracts/prism-service-hub.md)
- [KMP Platform Readiness](sdk/kmp-platform-readiness.md)
- [Accessibility Contract](sdk/accessibility-contract.md)
- [RTL/LTR Contract](sdk/rtl-ltr-contract.md)
- [Performance Guidelines](sdk/performance-guidelines.md)
- [Release Process](sdk/release-process.md)
