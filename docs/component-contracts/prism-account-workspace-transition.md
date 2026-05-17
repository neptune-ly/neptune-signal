# Prism Account Workspace Transition

`SignalPrismAccountWorkspaceTransition` provides a compact account-card-to-workspace motion wrapper.

Motion rules:
- 260-360 ms preferred duration
- preserve account color and identity continuity
- no slow cinematic motion
- no bounce or decorative movement
- RTL-aware route motion remains owned by Nova shell transitions

The current implementation uses a restrained fade and scale transform. A future shared-element implementation can replace the internals without changing Nova call sites.

