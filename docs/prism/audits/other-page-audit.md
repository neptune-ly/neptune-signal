# Prism Other Page Audit

Date: 2026-05-17

## Scope

This audit covers the "Other" or "More" class of pages: service hubs, profile tools, alias management, consent management, recurring payments, approvals, and settings-like destinations.

Relevant current components include:

- `SignalPrismActiveSpaceCard`
- `SignalPrismAttentionStrip`
- `SignalPrismServiceGroup`
- `SignalPrismServiceRow`
- `SignalPrismAliasManagementScreen`
- `SignalPrismConsentItem`
- `SignalPrismRecurringPaymentItem`
- `SignalPrismApprovalItem`

## Current Read

The Other page should be the densest root destination. It is not a hero surface. Current service hub components already use Prism raised surfaces, soft borders, compact icon cells, and row grouping. That is the right direction.

The page should feel like a control center for banking services, not a marketing page.

## Strengths

- Service rows are app-neutral and based on typed action data.
- Service groups use tonal raised surfaces instead of decorative treatment.
- Attention strips are flat warning-tonal surfaces.
- Alias, consent, recurring payment, and approval records can share the same data-row language.
- Row badges and icon tone communicate state without needing background gradients.

## Risks

- Too many repeated cards can make the page feel fragmented. Prefer grouped lists for dense services.
- Attention strips can become too prominent if multiple urgent blocks stack.
- Promotional service entries must not smuggle campaign gradients into utility rows.
- The root label may be "More", "Other", or localized copy; docs and navigation should map it to one canonical route identity.

## Gradient Decision

The Other page is a no-gradient operational zone.

Do not use gradients for:

- service rows,
- service group containers,
- profile cards,
- settings rows,
- consent/approval rows,
- icon wells,
- top or bottom navigation.

Only campaign detail media may use `campaignOverlayGradient`, and only when it is visually a campaign media surface.

## Recommended Structure

1. Optional active space/session card.
2. One attention strip maximum in the first viewport.
3. Primary service group.
4. Money movement and account administration groups.
5. Security, consent, and settings groups.

Each group should use a single raised tonal container with dividers instead of independent floating cards for every row.

## Acceptance Checklist

- Root navigation highlights the same route key used by Home/Accounts/Cards.
- Top bar title is stable and localized by the app.
- Service groups stay flat/tonal.
- Campaign content is visually separated from service rows.
- No arbitrary backend gradients are rendered in utility rows.
