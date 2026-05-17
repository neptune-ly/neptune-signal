# Signal Prism SDK Audit

Date: 2026-05-17

## Summary

Signal is already reusable as a Compose Multiplatform component library, but Prism is still in an evolution phase. The repo has a real KMP module, publish metadata, a static GitHub Pages workflow, a large component set, and many component contracts. The main SDK gaps are package organization, public API consolidation, demo-data isolation, platform adapter formalization, and a stronger automated gallery/testing story.

## Reusable Today

- Theme, colors, spacing, shapes, typography, surfaces, Prism tokens.
- Core components: buttons, rows, lists, shells, navigation, account headers, card components, top bars, payment ritual visuals, service hub, control center.
- Formatting utilities under `format/`.
- Common KMP code for Android and JVM targets.
- Static docs and HTML prototype deployed from `docs/`.
- Maven publishing metadata and `publishToMavenLocal` support.

## Nova-Specific Coupling

- Signal has no direct `ly.neptune.nova` imports in source code.
- Several docs still describe Nova as the primary consumer. That is acceptable as integration guidance, but component docs must now speak to any host app.
- Route behavior is callback-based in new Prism surfaces, but older docs still mention Nova route ownership directly.

## Demo-Only Leakage

- Some sample bank presets and personalization samples live in common component files.
- Several components accept display strings directly, which is good for SDK reuse, but demo values must remain in demo/gallery code rather than component defaults.
- HTML demo content is currently under `docs/demos/mobile/`; it is useful but not yet a generated component gallery.

## Platform-Specific Readiness

- `commonMain` is mostly platform-safe.
- Android-specific feedback, sound, clipboard, secure clipboard, system bars, haptics, and screenshot policies need adapters.
- This batch adds common adapter contracts under `ly.neptune.signal.feedback`; Android implementations should live in `androidMain`.

## API Stability

- `SignalTheme` and core components are existing stable entry points.
- Prism policy/resolver types were split between component packages. This batch adds canonical SDK-facing Prism policy/resolver models under `ly.neptune.signal.prism`.
- Existing component package models should remain source-compatible until a documented migration.

## Component Contract Gaps

- Service Hub, cards, account workspace, notifications, campaigns, and control center now have contracts, but docs must be normalized into a component catalog.
- Component events should consistently use callbacks or event objects, not app route classes.

## Docs And Demo Gaps

- Missing SDK docs for getting started, install/consume, platform adapters, release process, and testing.
- Missing Pages site map for Prism.
- Missing screenshot naming convention and automated capture guide.

## iOS/Desktop/Web Blockers

- No iOS target is configured yet.
- JVM target exists, but no desktop gallery app is wired.
- Web demo is static HTML, not generated from KMP previews.
- Platform adapters need no-op fallbacks and Android actual implementations before host apps should rely on feedback/clipboard behavior.
