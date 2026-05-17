# Prism Daily Cockpit

Prism Daily Cockpit defines Home as a daily financial control surface, not a widget stack.

Order:
1. Header
2. Selected account hero
3. Selected account actions
4. Repeat / quick transfer
5. Last two money movements
6. Smart financial insight
7. Bank campaign carousel
8. Financial timeline preview
9. Floating nav

The selected account is the context owner. Downstream modules must be able to update from the selected account ID, balance, alias, currency, and capability set.

Guardrails:
- Do not expose corporate, merchant, or voucher/VAS products as retail Home shortcuts.
- Use one hero gradient and one payment gradient at most.
- Keep timeline events separate from money movements.
- Demo content is acceptable, but component contracts must remain backend-ready.
