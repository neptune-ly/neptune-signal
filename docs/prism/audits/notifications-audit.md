# Prism Notifications Audit

Date: 2026-05-17

## Scope

This audit covers:

- `SignalPrismNotificationCenter`
- `SignalPrismNotificationCategoryTabs`
- `SignalPrismNotificationRow`
- notification entry points such as `SignalBankHeader`
- attention summaries such as `SignalPrismAttentionStrip`

## Current Read

Notifications are currently modeled as an operational list, not a campaign feed. This is correct for banking. The center uses tonal category tabs, unread/read surface differences, border tone for priority, and semantic icon cells.

## Strengths

- Notification rows distinguish read and unread through surface elevation and border treatment.
- Category tabs are flat pills inside a tonal container.
- Priority uses semantic tone, not a decorative background.
- Amount and status have dedicated trailing space, which preserves scanability.
- The Home header notification action can deep-link into the center without duplicating notification content on every screen.

## Risks

- Category tab scrolling must remain reachable in RTL and LTR contexts.
- Long Arabic titles and bodies can overflow if amounts/status text is also long.
- Priority borders can be missed by low-vision users if no text/status cue accompanies the state.
- Campaign notifications may pressure the row language toward promotional artwork.

## Gradient Decision

Notifications are a no-gradient surface.

Use:

- `prismSurfaceRaised` and `prismSurfaceFloating`,
- `prismBorderSoft`,
- semantic priority borders,
- flat icon wells,
- typography weight and unread counts.

Do not use:

- row gradients,
- category gradients,
- nav/action gradients,
- campaign media overlays inside standard notification rows.

If a notification opens a campaign detail page, the detail page may use the campaign media overlay rule. The list row remains tonal.

## Recommended Interaction Contract

- Header notification icon opens the notification center.
- Category selection filters in place and preserves scroll context where possible.
- Row click opens the relevant detail or deeplink.
- Read/unread state must be visible in text or semantics, not only surface tone.
- Amount/status metadata is optional and should truncate before title/body become unreadable.

## Acceptance Checklist

- Notification center has one title and one unread count.
- Tabs are tonal and horizontally scrollable.
- Rows remain readable with two-line body copy.
- Priority is represented by icon tone, border tone, and optional status copy.
- No local `Brush.linearGradient` is needed for notification state.
