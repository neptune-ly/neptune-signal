# Floating Payment Action Redesign

Status: Active

## Current Issue

The center action is technically correct but still reads too much like a FAB dropped onto a nav bar.

## Target

An integrated wallet-native action seated into the bottom navigation shell.

## Visual Rules

- Use solid token color.
- No gradient.
- No glow.
- No pulse.
- No oversized icon.
- Small surface moat connects it to the nav rail.
- Shadow is subtle and only supports elevation.
- Icon is optically centered at standard action size.

## Behavior Rules

- Opens a flow only.
- Never executes payment directly.
- Hides on focused nested flows if bottom nav is hidden.
- Press feedback is small scale compression only.

## Study Decision

The accepted direction is center-docked, not overlaid.

