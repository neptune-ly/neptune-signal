# Navigation Motion Contract - 2026-05-14

## Purpose

Signal owns motion timing and route behavior expectations. Apps such as Nova may own route orchestration, but they must not invent independent motion timings or interaction rules.

## Timing

- Root route transition: `SignalMotion.RouteMs`.
- Detail container chrome: `SignalMotion.ContainerChromeMs`.
- Shared account/card transform: `SignalMotion.ContainerTransformMs`.
- Result/status feedback: `SignalMotion.ResultMs`.
- Press response: `SignalMotion.PressMs`.

## Route Hierarchy

Signal recognizes three navigation layers:

1. Root destinations.
2. Nested detail/operation destinations.
3. Transient result/status destinations.

Root destinations may show persistent bottom navigation. Nested and transient destinations should normally hide bottom navigation and show one clear back affordance.

## Motion Principles

- Root changes are light and fast.
- Account/card detail opens are spatial container transitions.
- Status/result screens prioritize clarity over motion.
- Back motion should feel like returning to the previous spatial context.
- Reduced motion must remove rotation and large offset movement first.

## App Requirements

- Use Signal motion constants.
- Preserve selected item state separately from route stack state.
- Preserve scroll state per route where practical.
- Do not use exaggerated 3D or cinematic transitions at the app shell level.
