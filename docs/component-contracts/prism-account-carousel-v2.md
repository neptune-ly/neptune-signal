# Prism Account Carousel V2

The account carousel is the Home financial hero. It stays swipeable and remains the source of account context for Home modules.

V2 requirements:
- Account cards support account-specific Prism gradients.
- Bank profiles can override the gradient.
- OLED uses lower luminance and protected text zones.
- Balance hierarchy remains text-led; no decoration behind financial values.
- IBAN strip remains readable in RTL with LTR numeric grouping.
- Mask/unmask uses a soft fade and never shifts layout.

Account types should map to artwork intent:
- Salary/current: deep navy + cyan.
- Savings: navy + emerald.
- Wallet: ocean + violet.
- Business: restrained ink + gold.

Nova owns account data and selected account state. Signal owns visual language.
