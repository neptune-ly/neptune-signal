# Prism Service Row Contract

`SignalPrismServiceGroup` and `SignalPrismServiceRow` represent compact service entries. Rows carry title, subtitle, icon, tone, optional badge, and an action callback.

Rows must not hardcode navigation. Product shells pass route handlers so backend-provided services can route through the app-level resolver.

