# Quick Actions Wallet Contract

Wallet actions sit directly under the account anchor.

Rules:
- Show exactly four actions.
- No extra product-surface shortcuts on Retail Home.
- Icons are light glyphs, not repeated heavy icon wells.
- Labels are short and Arabic-readable.
- Quick actions support the payment center; they do not replace it.

Component:
- `SignalWalletActionPanel`

May 16 implementation:
- Actions sit in one shared low-contrast action cluster.
- Individual action cells do not draw their own card backgrounds.
- The layout remains 2x2 for Arabic subtitle readability while reducing repeated rounded containers.

Current retail actions:
- `الفواتير` / `دفع سريع`
- `رمز QR` / `استلام أو مسح`
- `المفضلة` / `مستفيدون محفوظون`
- `القسائم` / `متجر رقمي`

Retail Home must not expose Corporate, vPOS, VAS administration, or ecosystem product shortcuts as primary actions.
