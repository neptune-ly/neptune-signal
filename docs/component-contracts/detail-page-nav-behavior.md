# Detail Page Navigation Behavior

Detail routes should not blindly keep the root floating dock.

Current rule:
- root screens show the floating dock
- account workspace hides the dock and uses compact top navigation
- card details keep the existing hero detail treatment

Content must not render under system bars unless the route deliberately owns edge-to-edge behavior.
# Detail Page Navigation Behavior

Root screens own the Prism floating dock. Detail/workspace pages should not let the dock dominate or overlap content.

## Account Workspace

Account Workspace V3 hides the bottom nav through the app shell detail-route rules. The stage owns the back action and workspace orientation.

If a future detail page keeps bottom navigation visible, it must reserve enough bottom padding and prove no content is hidden behind the dock.
