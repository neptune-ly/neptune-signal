# Payment Ownership Architecture

Date: 2026-05-15

## Problem

The Home dashboard had a dominant Transfer dock while the navigation also introduced a center payment action. Two primary payment entry points create hierarchy conflict and make the wallet identity feel unresolved.

## Decision

The center navigation action is the primary payment owner.

It may be configured per bank/product as Pay, Transfer, Scan, QR, or Wallet, but it remains the structural owner of money movement. Dashboard shortcuts are secondary utilities.

## Rules

- Do not place another dominant payment CTA on Home when the center action is present.
- Home quick actions may include QR, vouchers, services, statements, or support utilities.
- Transfer/payment routes can still be accessed from contextual screens such as account details or card details when the action is explicitly scoped to that object.
- The center action must be reachable, visually balanced, and safe-area aware.

## Nova Policy

Nova wires the center action to the current product route. It should not create one-off large payment buttons on the dashboard unless a bank disables the center action.

