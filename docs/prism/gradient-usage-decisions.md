# Prism Gradient Usage Decisions

Date: 2026-05-17

## Decision

Prism gradients are expressive identity tools, not the default surface treatment. The SDK should keep three allowed gradient zones and convert the rest of the UI to tonal surfaces.

Allowed zones:

- hero/account stage
- transfer ritual radial/background
- campaign image overlay

## Keep

Keep gradients when they are the surface's primary artwork or protective overlay:

- `SignalPrismGradientLibrary.heroGradient`
- `SignalPrismGradientLibrary.ritualGradient`
- `SignalPrismGradientLibrary.campaignOverlayGradient`
- account/card hero artwork in `SignalPrismCards`
- account/card text-protection scrims
- campaign image overlay and missing-media fallback
- transfer ritual radial/background visuals
- Control Center preview tiles only when they are clearly preview swatches

## Remove

Remove gradients from persistent chrome and dense operational surfaces:

- `SignalNavigation` nav dock sheen
- page-level background washes
- general action clusters
- account action cells
- card action cells
- card status panel
- card limits summary
- card transactions preview
- money movement preview
- service list rows or offer rows unless they become campaign media surfaces

## Convert To Tonal

Convert these patterns to Prism tonal surfaces:

- account action cluster container
- quick transfer row outside ritual context
- movement preview container
- card controls and summaries
- status and transaction panels
- inactive or secondary campaign/service cards without media

Recommended tonal tools:

- `prismSurface`, `prismSurfaceRaised`, `prismSurfaceFloating`, `prismSurfaceMuted`, `prismSurfaceStrong`
- semantic containers such as payment, secondary, accent, success, warning, and error containers
- 1 dp borders using `prismBorderSoft`
- icon wells with low-alpha accent fill
- typography and spacing hierarchy

## Payment Action Decision

The center payment action is a special case. It is a primary action, but it lives in persistent navigation.

Decision:

- Do not let it define a local gradient.
- Use a solid payment/action container in navigation.
- Keep `prismPaymentGradient` reserved for the transfer ritual and other explicitly approved ritual visuals, not persistent chrome.
- A future bank-specific gradient payment button would need a separate policy review; it is out of scope for this pass.

## Campaign Payload Decision

`SignalPrismCampaign.gradientStops` and Nova/demo payload equivalents should not directly define final production colors.

Decision:

- Campaign payloads may request an art profile or emphasis.
- SDK resolves final overlay through `campaignOverlayGradient`.
- Direct raw stops are acceptable only in demos or temporary migration paths.
- Remote images still need overlay protection when text is placed on top.

## Control Center Decision

Control Center preview tiles may show gradient swatches because they preview Prism personality, bank identity, or account-stage choices.

Decision:

- Keep preview gradients as miniature previews.
- Do not copy preview gradients into production tiles or operational controls.
- Preview labels should describe the mode, not promise that every screen will use that gradient.

## Migration Order

1. Disable nav sheen and remove gradient overlays from persistent chrome.
2. Convert operational clusters and list panels to tonal surfaces.
3. Route campaign surfaces through library roles and keep payment gradients reserved for ritual visuals.
4. Consolidate card/account artwork brush creation behind hero/account-stage policy.
5. Treat raw backend/demo `gradientStops` as migration-only data.

## Non-Goals

- Do not remove card/account hero artwork.
- Do not remove transfer ritual atmosphere.
- Do not remove campaign image overlay protection.
- Do not add a new desktop/web preview surface in this mobile-first pass.
