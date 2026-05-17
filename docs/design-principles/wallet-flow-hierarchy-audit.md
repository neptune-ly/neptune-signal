# Wallet Flow Hierarchy Audit

Date: 2026-05-15

## Current Risk

Banking apps often distribute payment, transfer, QR, vouchers, and services across multiple equal CTAs. That creates interaction ambiguity and weakens user confidence.

## Signal Hierarchy

Primary:

- Center payment action.

Contextual:

- Transfer from an account detail.
- Card-specific controls from card detail.
- Receive/QR from account or wallet context.

Secondary:

- Dashboard utility shortcuts.
- Vouchers.
- Services.
- Statements.
- Support.

## Motion Policy

Primary routes may use stronger spatial continuity. Secondary utilities use standard route motion. Contextual object routes may use shared transitions only when the object visually persists.

## Review Checklist

- Is there more than one dominant payment CTA on the same screen?
- Does the user know which route owns money movement?
- Are utility shortcuts visually quieter than account/card owners?
- Are contextual actions scoped to the object the user opened?

