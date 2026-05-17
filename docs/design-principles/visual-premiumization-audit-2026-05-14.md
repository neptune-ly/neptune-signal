# Neptune. Signal Visual Premiumization Audit

Date: 2026-05-14

## Scope

Screens audited from Nova Android emulator:

- Auth intro: `/Users/mtellesy/GitHub/neptune-nova/build/visual-audit/01-auth-intro.png`
- Home: `/Users/mtellesy/GitHub/neptune-nova/build/visual-audit/02-home.png`
- Accounts: `/Users/mtellesy/GitHub/neptune-nova/build/visual-audit/03-accounts.png`
- Cards: `/Users/mtellesy/GitHub/neptune-nova/build/visual-audit/04-cards.png`
- More/navigation shell: `/Users/mtellesy/GitHub/neptune-nova/build/visual-audit/05-more-shell.png`

## Home

Hierarchy: The account carousel owns the screen correctly, but secondary modules compete too much because action dock, insight, and list rows all use similarly strong surfaces.

Spacing: The top header and carousel rhythm is strong. The lower dashboard needs calmer grouping and more bottom protection from the floating nav.

Typography: Balance hierarchy is strong but slightly oversized relative to the rest of the dashboard. Row metadata needs to feel quieter and more precise.

Density: The screen is close to the desired density, but repeated bordered surfaces create visual weight.

Weak Material Areas: List rows behaved like nested cards. Amount text in the lower transaction area can be partially obscured by the nav overlay when the viewport is at rest.

Premiumization: Reduce nested row borders, make transaction amount columns stable, reduce icon container loudness, and keep the IBAN exact but quieter.

Affected Signal Contracts: `SignalTypography`, `SignalComponentMetrics`, `SignalListGroup`, `SignalTransactionRow`, `SignalAccountCarousel`.

## Accounts

Hierarchy: The page correctly presents accounts as a list, but each account row still feels like a component demo because every row has its own surface/border inside a group.

Spacing: Internal row spacing is acceptable. The page would benefit from list-group ownership of the surface instead of repeated row ownership.

Typography: Account name and balance roles need to remain strong, while account type/status should be secondary.

Density: Good for banking. Needs less visual segmentation.

Weak Material Areas: Over-bordered list rows and icon containers feel generic.

Premiumization: Row backgrounds should become tonal, not boxed; leading rail should be thinner and more signal-like.

Affected Signal Contracts: `SignalAccountRow`, `SignalAccountSummaryRow`, `SignalIconSurface`, `SignalListGroup`.

## Cards

Hierarchy: Cards are visually clear, but the card list is still too graphic-heavy: large translucent shapes and strong label chips compete with card number and card name.

Spacing: Card proportions are usable, but list cards occupy too much vertical space for three cards plus nav.

Typography: Card name and number should be strong but not oversized. Scheme/status content should be quieter.

Density: Slightly low because each card has large internal decorative elements.

Weak Material Areas: The surface feels like a graphic block rather than a calm banking card.

Premiumization: Reduce texture alpha, reduce list card height, and use tighter typography inside the card face.

Affected Signal Contracts: `SignalPaymentCard`, `SignalPaymentCardStack`, `SignalCardModernTexture`.

## Navigation Shell

Hierarchy: The bottom navigation is clear but too visually heavy. It risks becoming a second hero surface.

Spacing: Vertical height is high; icon and label rhythm should be tighter.

Typography: Labels were readable but too heavy and large for persistent nav.

Density: Needs closer Material navigation density while preserving Arabic legibility.

Weak Material Areas: Active item container was too large and card-like.

Premiumization: Reduce nav height, active chip opacity, icon size, and label role weight.

Affected Signal Contracts: `SignalBottomNav`, `SignalComponentMetrics.bottomNavIcon`, `SignalComponentMetrics.bottomNavHeight`.

## Auth

Hierarchy: Auth intro has a strong brand moment and clear CTA. It still uses large marketing-like copy, but this is acceptable for first open.

Spacing: The card sits well in the viewport. Login/auth form screens should continue using compact non-scroll behavior where possible.

Typography: Hero copy is intentionally large, but secondary copy can be quieter in later passes.

Density: Good for first-open, too low for operational auth screens if repeated.

Weak Material Areas: The language chip and hero art need future localization and theming refinement.

Premiumization: Keep the first-open screen expressive; keep login/OTP/change password more operational and compact.

Affected Signal Contracts: `SignalAuthMetrics`, `SignalLoginPanel`, auth copy model.

## First Implementation Batch

Implemented as SDK-level changes:

- Calmer global typography: slightly tighter balance/title/meta/status roles.
- More compact navigation metrics and bottom nav rendering.
- Reduced card-list height and decorative texture opacity.
- Reduced account-card decorative texture opacity.
- Made row surfaces tonal instead of strongly bordered nested cards.
- Added stable transaction amount width and explicit clipping rules.
- Reduced row icon container visual weight.

## Regression Avoidance

- Critical values still avoid ellipsis where already covered by `SignalValueLine` / carousel IBAN behavior.
- Arabic-first RTL remains default for logged-in Nova until app copy is fully localized.
- No one-off Nova screen styling was introduced in this batch.
