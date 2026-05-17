# Batch 08 Wallet Premiumization Context

Date: 2026-05-15

## Current State

Signal now owns the first part of the wallet-premiumization system:

- central shell action API,
- center action sizing metrics,
- reserved nav geometry,
- softer app shell tonal background,
- softer action dock and insight surfaces.

Nova consumes the center action visually and routes it to the existing transfer flow.

## Implementation Boundary

This batch is visual and architectural only.

No payment execution logic, API behavior, auth logic, or backend route changes were added.

## Follow-Up Backlog

- Add screenshots for Home, Accounts, Cards, and Auth after this batch.
- Define `SignalDashboardScaffold` slots: hero, primary actions, alerts, activity, secondary modules.
- Add badge support to `SignalNavItem`.
- Add per-root state restoration policy to Nova.
- Add dark/OLED screenshot audit for the center action.
- Add RTL screenshot audit for the center action and bottom nav.
- Decide whether the center action label is Pay, Transfer, Scan, or Wallet per bank product configuration.

