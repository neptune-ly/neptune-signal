# Prism Account Detail Transition

The account-card-to-detail transition should feel spatially connected to the Home account card.

Contract:
- Duration target: 260-360 ms.
- Preserve account color, title, account type, and balance hierarchy.
- RTL motion enters from the expected account-card direction.
- Avoid slow cinematic movement, bouncy easing, and large scale distortion.
- If shared-element support is unavailable, use the same account artwork in the detail hero with a short fade/slide container transform.

Current implementation uses route-level fade/slide continuity plus `SignalPrismAccountDetailHero` that reuses the same `SignalPrismAccountCardModel` artwork contract as Home.

