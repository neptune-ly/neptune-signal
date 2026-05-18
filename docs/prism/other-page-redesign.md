# Prism Other Page Redesign

Date: 2026-05-17

## Intent

The Prism Other page is the operational hub for secondary banking tasks. It should be compact, structured, and calm. It is not a second Home screen and not a campaign landing page.

## Information Architecture

Recommended sections:

1. Active space or profile context.
2. Attention strip for urgent service work.
3. Primary services.
4. Money movement administration.
5. Account aliases and receiving tools.
6. Consent, approvals, and security.
7. Settings and support.

## Surface Model

- Page background: `prismBackground` or shell surface.
- Section groups: `prismSurfaceRaised` with `prismBorderSoft`.
- Rows: transparent inside grouped containers.
- Icon cells: flat tonal wells using `SignalRowTone`.
- Badges: text plus semantic tone.
- Actions: standard Signal buttons or row clicks.

## Navigation

The root item may be labeled "More", "Other", or localized copy, but the route identity should be stable. Suggested key: `more`.

The page should use:

- shell top bar title for root context,
- no custom bottom chrome,
- the shared Prism bottom nav,
- no page-level center action override.

## Gradient Policy

The Other page should be flat/tonal.

Do not use gradients for service discovery, settings, profile, or approval surfaces. If a service opens a campaign or offer detail, that destination may use a campaign media overlay. The service row itself remains tonal.

## Empty And Loading States

Use compact status blocks:

- icon well,
- title,
- one supporting sentence,
- optional secondary button.

Avoid illustrative gradient hero cards for empty states.

## Acceptance Criteria

- One grouped-list language is used across all utility sections.
- Only one attention strip appears above the fold.
- Long labels truncate cleanly.
- Root navigation selection remains stable.
- No page-local gradients are introduced.
