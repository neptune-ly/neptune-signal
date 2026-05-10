# Foundations: Layout

## Grid

- Base unit: `4px / 4dp`.
- Primary rhythm: `8px / 8dp`.
- Compact screen horizontal padding: `16px / 16dp`.
- Wide compact screen horizontal padding: `20px / 20dp`.
- Content max width: `430px / 430dp`.
- Dense row gap: `8px / 8dp`.
- Card/list gap: `12px / 12dp`.
- Section gap: `16px / 16dp`.
- Major block gap: `20-24px / 20-24dp`.

`1dp` in KMP Compose equals `1px` in the web demo contract. Product screens must use the SDK metrics rather than local screen constants.

## Phone Shell

Mobile target:

```text
390 x 844
375 x 812
430 x 932
```

Desktop preview should use a stable phone frame with aspect ratio `390 / 844`.

## Locked Component Metrics

| Metric | Value |
| --- | ---: |
| Minimum touch target | `48px / 48dp` |
| Standard icon | `31px / 31dp` |
| Row icon glyph | `29px / 29dp` |
| Bottom navigation icon | `26.5px / 26.5dp` |
| Small action icon | `25px / 25dp` |
| Icon button box | `44px / 44dp` |
| Text field | `62px / 62dp` |
| Button | `58px / 58dp` |
| Bottom navigation | `86px / 86dp` plus safe area |
| Banking row | `58px / 58dp` minimum |
| Transaction row | `72px / 72dp` minimum |
| Account row | `76px / 76dp` minimum |
| Account carousel | `176px / 176dp` |
| Card face | `136px / 136dp` list, `190px / 190dp` detail |

## Auth Reference Screen

The login screen is the sizing reference for Neptune. Signal:

- Screen padding: `16px / 16dp` horizontal and `22px / 22dp` vertical.
- Brand header: `58px / 58dp`.
- Header icon box: `40px / 40dp`, glyph `24px / 24dp`, radius `13px / 13dp`.
- Login state card: full width, radius `20px / 20dp`, padding `12px / 12dp`.
- Form panel: full width, radius `20px / 20dp`, padding `12px / 12dp`, gap `10px / 10dp`.
- Text field: `62px / 62dp`.
- Button: `58px / 58dp`.
- Passkey glyph: `24px / 24dp`.

## Navigation

Root tabs:

- Home.
- Accounts.
- Cards.
- More.

Nested routes keep the active root stable.

## Density

Banking apps need high information density. Avoid decorative empty space. Every visible block should help the customer decide or act.
