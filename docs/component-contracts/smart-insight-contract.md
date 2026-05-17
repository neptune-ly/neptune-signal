# Smart Insight Contract

Smart insight surfaces communicate one financial observation plus compact status metrics.

Rules:
- One insight title.
- One calm explanation line.
- Up to two metrics.
- No separate widget stack for bills/subscriptions when a smart band can carry them.

Component:
- `SignalSmartFinancialBand`
- `SignalFinancialStatusMetric`

Current Home example:
- `مصروفاتك أعلى بـ 12%`
- `الخدمات والبطاقات هي السبب الرئيسي.`
- Metrics: upcoming bill and active recurring payments.

This replaces separate insight, bill, and subscription cards to reduce widget soup.
