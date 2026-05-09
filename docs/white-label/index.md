# White-Label Theming

Neptune. Signal is white-label ready. Bank colors can change, but Neptune. structure remains.

## Philosophy

White-label does not mean every bank gets a different product. It means every bank can feel like itself while the customer still receives the same Neptune. Signal quality:

- Same navigation logic.
- Same spacing, shape, typography, and component anatomy.
- Same motion language.
- Same accessibility rules.
- Same performance rules.
- Same financial clarity.
- Different bank identity through controlled color, logo, name, and selected brand moments.
- Light, dark, and black appearance modes for every bank preset.
- Customer personalization only inside approved, accessible choices.

The bank owns the brand layer. Neptune. Signal owns the operating layer.

## Token Model

Every bank theme is reduced into a small set of semantic inputs. Components must never read raw brand colors directly.

```text
bank.primary
bank.secondary
bank.accent
bank.ink
bank.surface
appearance.preference
appearance.mode
```

| Token | Purpose | Typical use |
| --- | --- | --- |
| `bank.primary` | Main bank identity color | Top hierarchy, primary buttons, active navigation, account hero |
| `bank.secondary` | Supporting brand color | Secondary states, selected chips, small highlights |
| `bank.accent` | Signature highlight | Important brand moments, focus rings, splash, rare emphasis |
| `bank.ink` | Text and icon color on light surfaces | Page titles, dense rows, navigation icons |
| `bank.surface` | Optional soft brand-tinted background | Quiet page backgrounds, panels, disabled states |
| `appearance.preference` | Customer setting | `system`, `light`, `dark`, or `black` |
| `appearance.mode` | Resolved rendering mode | `light`, `dark`, or explicit `black` |

Neptune. Signal tokens still exist below the bank layer:

```text
--npt-navy
--npt-teal
--npt-coral
--npt-beige
--npt-sky
--bank-primary
--bank-secondary
--bank-accent
--bank-ink
--bank-surface
```

## What Can Change

Bank themes may change:

- Logo.
- Bank name.
- `bank.primary`.
- `bank.secondary`.
- `bank.accent`.
- Optional background tint.
- Card face color palettes.
- Splash brand lockup.
- Marketing artwork inside approved banner surfaces.
- Default customer appearance mode.
- Approved customer accent variants.

Bank themes must not change:

- Navigation roots.
- Component spacing.
- Text scale.
- Touch target minimums.
- Receipt information hierarchy.
- Status meaning.
- Error, success, and warning color behavior beyond approved semantic adjustment.
- Motion timing model.
- Arabic, English, and French layout support.
- Minimum contrast in dark and black modes.

## Color Assignment Rules

### Primary

Primary is the bank's strongest brand color. It must be dark or saturated enough to work behind white text.

Use it for:

- Primary action buttons.
- Active navigation.
- Important account headers.
- App bars when a bank explicitly wants a stronger branded shell.

Do not use it for:

- Every icon.
- Every card border.
- Large page backgrounds when it harms readability.
- Success or failure states.

### Secondary

Secondary supports primary. It is useful when the bank has two major colors, such as navy and teal, red and gold, green and white, or brown and gold.

Use it for:

- Selected filters.
- Progress accents.
- Secondary cards.
- Subtle highlight surfaces.

Do not use it as a second competing primary action color.

### Accent

Accent is a sharp signal, not decoration. It should appear rarely so it keeps meaning.

Use it for:

- Splash orbit or signature moment.
- Focus ring.
- One key insight or promotion highlight.
- Risk-adjacent attention when not replacing semantic danger.

Do not use it for:

- Random list icons.
- Every call to action.
- Full-page backgrounds.
- Transaction status colors.

### Ink

Ink is the customer-reading color. It must be stable and calm.

Use it for:

- Page titles.
- Row titles.
- Important identifiers.
- Navigation labels.

Do not make body text low contrast to satisfy brand aesthetics.

## Semantic Colors Stay Protected

Financial status colors are not bank branding.

| Semantic | Default direction | Rule |
| --- | --- | --- |
| Success | Green | Must remain recognizable as success |
| Warning | Amber | Must remain recognizable as warning |
| Danger | Red | Must remain recognizable as danger or risk |
| Pending | Accent or sky | Must read as waiting, not success |
| Disabled | Neutral gray | Must read as unavailable |

If a bank's primary color is red, danger still needs a separate danger treatment. For example, primary red can be deeper and branded, while danger uses semantic red with error copy, icon, and receipt context.

## Dark Mode

Each bank theme has three palettes:

1. Light roles for daytime, branch support, and paper-like banking surfaces.
2. Dark roles for night use, low-light environments, and premium secure surfaces.
3. Black roles for customers who prefer deeper surfaces while keeping visible containers and bank colors.

Dark mode rules:

- Never simply invert the light palette.
- Keep dense rows on dark surface containers, not on full primary backgrounds.
- Keep account and card detail headers branded, but preserve readable balance, IBAN, and status text.
- Use softer primary containers for selected navigation and chips.
- Preserve semantic success, warning, danger, and pending colors across all banks.
- Validate Home, Accounts, Card Details, Transfer, Status Result, Voucher Receipt, Notifications, and OpenWave flows in light, dark, and black mode.

## Customer Personalization

Personalization is allowed, but bounded:

- Customer may choose `system`, `light`, `dark`, or `black`.
- Customer may choose an approved bank color preset when the bank permits it.
- Customer may choose a small approved accent style such as default, ocean, warm, or sky.
- Customer may not change status colors, typography, spacing, shape, transaction hierarchy, or receipt layout.

This keeps the app personal without becoming inconsistent or unsafe.

## Contrast Rules

Minimum rules:

- Primary button text: WCAG AA contrast against `bank.primary`.
- Body text: WCAG AA contrast against surface.
- Small metadata: never below usable contrast, even when muted.
- Disabled controls: visibly disabled but still readable.
- Focus state: visible against both white and branded surfaces.

Practical guidance:

- If `bank.primary` is too light, derive a darker interaction shade.
- If `bank.accent` is too bright, use it only for strokes, small marks, and focus rings.
- If `bank.surface` is tinted, keep dense banking rows on white or near-white cards.

## Theme Families

These are implementation presets for testing and presentation. They are not official brand claims.

| Family | Primary | Secondary | Accent | Notes |
| --- | --- | --- | --- | --- |
| Neptune. | Navy | Teal | Coral | Default reference theme |
| Red white bank | Deep red | White or deep navy | Warm gold | Useful for banks with strong red identity |
| Brown gold bank | Deep brown | Gold | Cream | Needs careful contrast, avoid beige-heavy screens |
| Green bank | Deep green | White or mint | Sky | Good for trust and public banking identity |
| Blue bank | Royal blue | Teal | Coral | Works for modern retail banking |
| Black white bank | Black | Cool gray | Teal or coral | Premium but must avoid harsh empty screens |
| Gold navy bank | Navy | Gold | Coral | Good for private banking or premium tiers |

## Example Presets

These are placeholders for presentation and testing, not official brand claims.

| Preset | `bank.primary` | `bank.secondary` | `bank.accent` | `bank.ink` |
| --- | --- | --- | --- | --- |
| Neptune. Signal | `#07315F` | `#00A8AE` | `#EB4E4D` | `#071C2E` |
| Red White | `#A71930` | `#FFFFFF` | `#C89B3C` | `#1A1A1A` |
| Brown Gold | `#5B3A22` | `#B58A35` | `#F0CE9D` | `#24170F` |
| Green Public | `#006B3F` | `#E8F5EF` | `#3BC1EE` | `#06251A` |
| Blue Teal | `#0B3D91` | `#00A8AE` | `#EB4E4D` | `#071C2E` |
| Black Premium | `#111111` | `#E8ECEF` | `#00A8AE` | `#111111` |

## Brand Application Matrix

| Surface | Primary | Secondary | Accent | Notes |
| --- | --- | --- | --- | --- |
| Splash | Strong | Optional | Strong but brief | Good place for bank identity and Neptune. Signal motion |
| Home account header | Strong | Optional | Rare | Balance readability is more important than decoration |
| Primary button | Strong | No | No | One clear action color |
| Bottom navigation active | Strong | Soft surface | No | Must stay calm and consistent |
| Transaction rows | No | No | No | Use semantic and neutral colors |
| Voucher store | Limited | Category support | Rare | Product catalog must stay scannable |
| Status result | No | No | Only pending if appropriate | Success and failure use semantic colors |
| Ads and banners | Optional | Optional | Optional | Must not pollute core banking hierarchy |

## Deriving a Bank Theme

Use this process for every bank:

1. Collect official logo, primary brand color, secondary brand color, and accent if available.
2. Test primary with white text. If it fails, derive `primaryDark`.
3. Test primary on soft background. If too heavy, keep it for buttons and headers only.
4. Define `bank.ink` separately from primary if primary is too saturated for text.
5. Define one accent. Do not invent multiple accents.
6. Map success, warning, danger, and pending separately from brand.
7. Test Arabic, English, and French labels.
8. Test home, accounts, card details, transfer, status result, voucher receipt, and OpenWave consent.
9. Test every preset in light, dark, and black mode.
10. Test customer accent choices against the same screens.

## KMP Example

```kotlin
val redWhiteTheme = SignalColorDefaults.whiteLabel(
    primary = Color(0xFFA71930),
    secondary = Color(0xFFFFFFFF),
    accent = Color(0xFFC89B3C),
    ink = Color(0xFF1A1A1A)
)

SignalTheme(colors = redWhiteTheme) {
    BankApp()
}

val redWhiteDark = SignalColorDefaults.whiteLabel(
    primary = Color(0xFFA71930),
    secondary = Color(0xFFEAF2F4),
    accent = Color(0xFFFFB3AE),
    dark = true
)

SignalTheme(colors = redWhiteDark) {
    BankApp()
}
```

## CSS Example

```css
:root {
  --bank-primary: #A71930;
  --bank-secondary: #FFFFFF;
  --bank-accent: #C89B3C;
  --bank-ink: #1A1A1A;
  --bank-surface: #F7FAF9;
}
```

## Review Checklist

Before accepting a bank theme:

- Primary buttons are readable.
- Active navigation is clear but not loud.
- Status result screens still communicate success, waiting, and failure instantly.
- Transaction rows do not become a rainbow of brand colors.
- Copy, share, report, and support icons are consistent.
- Arabic RTL remains aligned.
- English and French do not overflow labels.
- The app still feels like Neptune. Signal, not a custom one-off skin.
