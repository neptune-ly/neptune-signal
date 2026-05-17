# Account Workspace Top Bar Contract

Account Workspace V3 integrates navigation into `SignalPrismAccountWorkspaceStage`.

## Behavior

- Expanded state: account name and type live inside the stage.
- Back action uses the canonical Signal `ArrowStart` icon.
- No separate sticky title is shown above the stage.
- Collapsed top-bar behavior can be added later, but it must not overlap content or duplicate the account name while the stage is visible.

The page should never show a random floating title or a screen-local back icon.
