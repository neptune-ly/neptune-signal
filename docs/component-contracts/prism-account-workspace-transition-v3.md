# Prism Account Workspace Transition V3

The account transition should preserve the relationship between the Home account card and the Account Workspace.

Use `SignalPrismAccountWorkspaceTransition` with `SignalPrismAccountWorkspaceTransitionSpec`.

## Motion Contract

- Duration target: 260-360ms.
- Use fade plus subtle scale/slide when full shared-element transform is not available.
- Preserve account color and identity continuity.
- Respect RTL direction in app-level route transitions.
- Avoid bounce, slow cinematic movement, or decorative motion.

Back navigation should feel like returning from the workspace to the source account context even when a true reverse shared transition is not available.
