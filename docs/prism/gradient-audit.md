# Prism Gradient Audit

Date: 2026-05-17

## Scope

This audit covers the current `rg` gradient findings for the Neptune Signal Prism Gradient Control Pass.

Owned surfaces:

- `SignalPrism.kt` token and gradient library definitions
- `SignalNavigation` dock and payment action
- `SignalBankingPatterns` account action cluster, quick transfer, movement preview, campaign carousel, and campaign media
- `SignalPrismCards` card actions, status, limits, transactions, hero artwork, and text-protection scrims
- `SignalPrismControlCenter` preview tiles and sample balance preview
- `SignalPrismServiceHub` campaign detail surface
- Nova or demo campaign payloads that provide `gradientStops`

## Allowed Gradient Zones

Gradients are allowed only in these zones:

1. Hero or account stage surfaces where the gradient is the main identity artwork.
2. Transfer ritual radial or background surfaces where motion/payment focus needs a controlled visual field.
3. Campaign image overlays where the gradient protects text or bridges missing/remote media.

All other gradients should be removed or converted to tonal surfaces unless there is an explicit component contract that says otherwise.

## Audit Table

| Area | Current finding | Purpose | Impact | Decision |
| --- | --- | --- | --- | --- |
| `SignalPrism.kt` `SignalPrismGradientLibrary` | Defines `heroGradient`, `ritualGradient`, and `campaignOverlayGradient`, plus disabled fallback. | Centralizes the only approved expressive gradient roles. | Good direction; gives SDK a single policy surface instead of scattered local brush recipes. | Keep. Treat this as the canonical library. |
| `SignalPrism.kt` legacy gradient tokens | Exposes `prismHeroGradient`, `prismAccountGradient`, `prismPaymentGradient`, `prismAccentGradient`, `prismSuccessGradient`, `prismCampaignGradient`, `prismAmbientWash`, `prismCardGlow`, and `prismNavSheen`. | Compatibility and migration bridge for existing components. | Risk of semantic sprawl if components choose decorative roles freely. | Keep as compatibility tokens, but route future usage through the library roles. |
| `SignalPrismBackground` | Previously used `prismGradientStart`, `prismGradientEnd`, `prismBackgroundPattern`, and radial colors. | App-level atmosphere. | Page-level washes made light mode feel washed out and reintroduced gradients outside hero/ritual zones. | Flatten to solid Prism background by default. Keep expressive gradients for hero/account stages, transfer ritual, and campaign media overlays. |
| `SignalNavigation` nav dock | Uses vertical and horizontal gradient overlays on dock container. | Adds sheen and product identity to persistent navigation. | Persistent chrome becomes visually busy and competes with content; not in an allowed zone. | Remove or convert to tonal overlay. `prismNavSheen` should remain disabled. |
| `SignalNavigation` payment action | Previously used a local three-stop linear gradient based on accent/ocean blend. | Makes payment center action primary. | Payment intent is important, but persistent chrome should not carry gradients. | Convert to solid payment/action container. Keep `prismPaymentGradient` for ritual visuals only. |
| `SignalBankingPatterns` account action cluster container | Uses linear gradient around action cells. | Groups account actions. | Decorative group gradient outside hero/account stage; creates repeated glow under small text. | Convert to tonal surface. |
| `SignalBankingPatterns` account action cells | Uses per-cell linear gradients. | Differentiates suggested and secondary actions. | Repeated gradients turn utility actions into competing promos. | Convert to tonal fills with accent icon wells and borders. |
| `SignalBankingPatterns` quick transfer row | Uses payment/accent gradient. | Promotes transfer as a high-value action. | Allowed only if presented as a transfer ritual entry surface, not as a generic list row. | Convert to tonal for normal rows; keep only in transfer ritual context. |
| `SignalBankingPatterns` money movement preview | Uses subtle container gradients. | Adds depth to movement list. | Not an allowed zone; financial list readability should win. | Convert to tonal surface. |
| `SignalBankingPatterns` campaign carousel card | Uses `campaign.gradientStops` fallback gradient. | Campaign art/overlay fallback. | Allowed when functioning as campaign image overlay or media fallback; risky as arbitrary backend decoration. | Keep only through campaign overlay role. Backend stops must be normalized or ignored. |
| `SignalBankingPatterns` campaign media placeholder | Uses navy/ocean gradient behind generated media placeholder. | Fallback for missing remote image. | Allowed as campaign image overlay/media fallback. | Keep, but source from `campaignOverlayGradient` or tonal fallback. |
| `SignalPrismCards` workspace/account action buttons | Uses local action gradients. | Highlights account actions. | Not in allowed zone; utility action density suffers. | Convert to tonal. |
| `SignalPrismCards` card action cluster and cells | Uses local gradients. | Groups card controls. | Card management controls should feel operational, not promotional. | Convert to tonal. |
| `SignalPrismCards` card status panel | Uses subtle surface gradients. | Adds depth to status summary. | Not an allowed zone; status must read as stable state. | Convert to tonal. |
| `SignalPrismCards` card detail hero stage | Uses protected navy/payment/accent gradient. | Card hero artwork. | Allowed as hero/account stage if text has protection and contrast is verified. | Keep. Route through hero/account gradient library over time. |
| `SignalPrismCards` card limits summary | Uses local gradients. | Separates control summaries. | Not an allowed zone. | Convert to tonal. |
| `SignalPrismCards` card transactions preview | Uses local gradients. | Groups recent transactions. | Not an allowed zone; transaction rows need calm scanability. | Convert to tonal. |
| `SignalPrismCards` card and account artwork brushes | Builds card/account gradient artwork by personality, bank palette, and account style. | Primary card/account identity artwork. | Allowed in hero/account stage, but must stay protected for text and bank override. | Keep, then align to `heroGradient` and account-stage roles. |
| `SignalPrismCards` text-protection scrims | Uses linear/vertical black scrims over artwork. | Protects text over card/account gradients. | Required when text sits on image/gradient artwork. | Keep. Scrims are protection, not decoration. |
| `SignalPrismControlCenter` preview tiles | Uses linear gradients in personalization previews. | Shows theme presets visually. | Preview context can show a miniature gradient, but it should not imply production surfaces all use gradients. | Keep as preview-only swatches; label and constrain as non-production visuals. |
| `SignalPrismControlCenter` balance preview tile | Uses primary/accent gradient. | Demo preview of home/account balance stage. | Acceptable only as a miniature hero/account-stage preview. | Keep as preview-only; use hero/account role. |
| `SignalPrismServiceHub` campaign detail | Uses navy/violet/coral gradient. | Campaign hero/detail surface. | Allowed only as campaign image overlay or campaign hero art with protected copy. | Keep if treated as campaign overlay; route to `campaignOverlayGradient`. |
| Nova/demo campaign `gradientStops` payloads | Campaign model accepts arbitrary `gradientStops`. | Lets backend/demo influence campaign visuals. | High drift risk: demo payloads can reintroduce loud gradients across bank apps. | Convert to policy. Payloads may request a campaign art profile, but SDK resolves final stops. |

## Implementation Priority

1. Remove nav dock sheen first because it appears on every screen.
2. Convert operational clusters, status, limits, transactions, and movement previews to tonal surfaces.
3. Keep payment gradients in ritual visuals only; route campaign fallback and service campaign detail through library roles.
4. Keep card/account hero artwork and scrims, then normalize their brush creation behind a single account-stage role.

## Acceptance Rules

- No new local `Brush.linearGradient` in operational controls.
- No gradient behind dense financial text unless protected by a scrim and allowed zone.
- Backend or demo `gradientStops` are advisory, not direct rendering authority.
- Campaign, hero/account stage, and transfer ritual are the only expressive gradient lanes.
