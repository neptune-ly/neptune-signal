# Card Reveal Security Contract

Signal owns:
- `SignalPrismCardRevealPolicy`
- `SignalPrismCardRevealState`
- `SignalPrismSensitiveCardField`
- `SignalPrismCardRevealSheet`

Nova owns:
- auth gate result
- issuer/backend policy
- fake demo card data
- route lifecycle remask

Full PAN and CVV must only render when policy allows and reveal state is active.
