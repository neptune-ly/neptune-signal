# Prism Home Audit

Date: 2026-05-17

## Scope

This audit covers the current Home-facing Prism building blocks visible in Signal:

- `SignalBankHeader`
- `SignalFinancialTimelineHomePreview`
- `SignalSmartFinancialBand`
- `SignalInsightCard`
- `SignalNotificationBanner`
- shell-provided `SignalPrismTopBar`
- shell-provided `SignalPrismFloatingNav`

No Kotlin changes are part of this pass.

## Current Read

Home is already moving toward a restrained operational dashboard. Header, timeline rows, financial status bands, and insight cards use flat or tonal surfaces with icon wells, borders, and type hierarchy. This matches the Prism gradient-control direction.

The current SDK surface model gives Home enough structure without needing local gradients:

- key financial rows use tonal emphasis and semantic icon wells,
- financial status cells use quiet containers,
- insight cards use flat card surfaces and borders,
- persistent top and bottom chrome are owned by the shell.

## Strengths

- Home content can be assembled from app-neutral Signal components.
- The top notification action in `SignalBankHeader` is a simple icon action, not a promotional surface.
- Timeline rows are scannable and use emphasis sparingly.
- Smart financial bands separate summary copy from metric cells.
- Prism navigation can be applied at shell level without page-specific chrome.

## Risks

- `SignalNotificationBanner` still reads as a broad bank-primary feature banner. It is acceptable as a flat campaign/attention block, but should not become a gradient hero unless it gains campaign media or protected artwork.
- If Home adds campaign tiles, they must follow the campaign media overlay rule instead of applying arbitrary `gradientStops`.
- Top-bar title and page header can duplicate each other if both are enabled without a route contract.
- The center payment action can compete with a Home transfer shortcut if both are visually primary.

## Gradient Decision

Home should not introduce decorative gradients in:

- dashboard cards,
- quick actions,
- notification buttons,
- timeline rows,
- metric panels,
- bottom navigation,
- top navigation.

Gradients may appear only when Home includes one of the approved zones:

- account/card hero artwork,
- transfer ritual entry or active ritual field,
- campaign media overlay or media fallback.

## Recommended Home Contract

- Use `SignalAppShell` for top and bottom chrome.
- Use a route-level title only when the content does not already contain a stronger Home header.
- Keep the bank/identity header flat.
- Treat payment as the single persistent center action; secondary transfer entry points should be tonal.
- Use semantic `SignalRowTone` and Prism tone containers for row emphasis.
- Reserve campaign media for actual image/art moments with overlay protection.

## Acceptance Checklist

- No Home operational card uses a local gradient.
- Top bar and bottom nav remain tonal.
- Payment entry in persistent nav uses a solid Prism tone.
- Notification and attention surfaces are flat/tonal unless they are campaign media.
- The first viewport has one clear primary action and one clear navigation model.
