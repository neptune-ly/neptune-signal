# Top Bar Navigation Contract

Signal owns top-bar navigation semantics.

Rules:
- Back uses `SignalIconName.ArrowStart`.
- Close uses the Signal close mark.
- Hit targets are 48dp minimum.
- Back/close icons include button semantics and content descriptions.
- RTL mirroring is resolved by Signal icons, not screen-local arrow drawing.

Nova should pass route intent and callbacks to `SignalAppShell`; screens should not invent local back icons except stage headers that consume `SignalDetailBackAction`.

Current implementation notes:
- `SignalContractTopBar` now carries navigation content descriptions and 48dp hit targets.
- `SignalDetailBackAction` is the canonical detail/hero back control for card/account stages that need an over-surface action.
- Remaining route-specific headers must justify themselves by carrying flow context, such as transfer step state. They should still use Signal icon semantics.
