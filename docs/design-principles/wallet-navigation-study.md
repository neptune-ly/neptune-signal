# Wallet Navigation Study

Status: Active

## Problem

The center payment action must be structurally important without becoming a generic Android FAB.

## Composition Studies

### Study A: Overlaid FAB

Circle floats above nav rail.

Rejected as primary direction because it reads generic and detached.

### Study B: Center-Docked Action

Circle is partially seated into the nav rail with reserved spacing and a surface moat.

Accepted direction. It keeps wallet identity while preserving root nav clarity.

### Study C: Full Center Pill

Payment action becomes a wide pill inside the nav rail.

Rejected for now because it competes with root labels and can become language-length fragile.

## Accepted Rules

- Center action is seated into the nav rail.
- Root labels remain visible.
- Nav row reserves a physical center slot.
- The action uses restrained solid tonal color, not a gradient.
- Shadow is subtle and reduced in dark/OLED.
- The action opens the transfer/payment hub, not execution.

## Ergonomics

- 64-68dp visual action.
- Minimum 48dp touch target.
- Safe-area padding remains owned by shell.
- Nested focus flows may hide the bottom nav.

