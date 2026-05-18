# Prism Notifications Redesign

Date: 2026-05-17

## Intent

Notifications should behave like a banking inbox: fast to scan, clear about urgency, and connected to the destination that resolves the item.

## Layout

Recommended order:

1. Top bar title: Notifications or localized equivalent.
2. Unread count and optional bulk action.
3. Category tabs.
4. Notification rows grouped by recency where needed.

## Row Anatomy

Each row should include:

- priority/category icon well,
- title,
- body summary,
- time,
- optional amount,
- optional status,
- read/unread surface state.

Priority must not depend on color alone. Pair tone with icon, status copy, or unread state.

## Category Tabs

Tabs should remain tonal pills in a flat container:

- selected tab: primary/payment container,
- unselected tab: transparent,
- overflow: horizontal scroll,
- semantics: selected state.

## Read State

Unread rows may use a stronger tonal surface, border, and text weight. Read rows should be quieter but still fully legible.

## Gradient Policy

Notification list surfaces are no-gradient.

Do not use:

- gradient tabs,
- gradient unread rows,
- gradient priority backgrounds,
- campaign artwork inside a normal notification row.

Campaign detail screens may use `campaignOverlayGradient` when the opened destination is a campaign media surface.

## Acceptance Criteria

- Rows scan at one and two lines of body copy.
- Amount/status metadata does not crowd the title.
- Tabs work for RTL and LTR labels.
- Read/unread state is available to accessibility semantics.
- No notification state requires a gradient.
