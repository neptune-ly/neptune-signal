# KMP Platform Readiness

## Current Targets

- Android library target.
- JVM target.

## Target Readiness

- Android: primary supported target.
- iOS: design-ready, target not configured yet.
- Desktop/JVM: build target exists; full gallery app pending.
- Web: static docs/prototype exist; Compose web target pending.

## Platform Isolation

Common components must avoid Android APIs. Platform behavior is delegated through adapters:

- feedback
- sound
- haptics
- clipboard
- secure clipboard
- system bars
- reduced motion
- screenshot policy

## Unsupported Platforms

Unsupported platform behavior should fail safe:

- no-op haptics
- no-op sound
- normal clipboard only when host supplies it
- no system bar mutation
- no screenshot blocking unless host supports it
