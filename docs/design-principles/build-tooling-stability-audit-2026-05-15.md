# Build Tooling Stability Audit

Date: 2026-05-15
Scope: Neptune. Signal SDK and Neptune Nova Android composite build

## Findings

The repeated build failure was not caused by the wallet/navigation code. It was caused by Kotlin incremental compile cache contention.

Observed failure shape:

- Kotlin daemon reported already-registered incremental cache files under `packages/kmp-compose/build/kotlin/compileDebugKotlinAndroid`.
- Concurrent standalone Signal and Nova composite builds touched the same Signal SDK build directory.
- Nova included Signal through `includeBuild("../neptune-signal")`.
- Signal used Kotlin `2.2.20`, while Nova used Kotlin `2.2.0`.

The version skew and concurrent access increased cache churn and made the local daemon state unreliable.

## Corrections

- Nova Kotlin plugin and Compose compiler plugin were aligned to Signal: `2.2.20`.
- Verification builds should run serially when both repos touch the included Signal SDK.
- `--no-configuration-cache` remains the required verification flag for release-quality checks in this phase.

## Stable Verification Policy

Use this order:

```sh
cd /Users/mtellesy/GitHub/neptune-signal
gradle --no-configuration-cache :packages:kmp-compose:build

cd /Users/mtellesy/GitHub/neptune-nova
./gradlew --no-configuration-cache :apps:android:assembleDebug
./gradlew --no-configuration-cache :apps:android:connectedDebugAndroidTest
```

Do not run the standalone Signal build and Nova composite build at the same time. They share the Signal SDK build outputs.

## Recovery Command

If Kotlin reports already-registered storage or cannot delete incremental caches:

```sh
cd /Users/mtellesy/GitHub/neptune-signal
bash scripts/stabilize_gradle.sh
```

Then rerun the stable verification policy.

## Reproducibility Rules

- Keep Kotlin plugin versions aligned between Signal and Nova.
- Keep Compose compiler plugin versions aligned with Kotlin.
- Prefer serial verification for composite builds.
- Treat cache failures separately from code failures.
- Do not delete source files, Gradle wrappers, or user worktree changes during recovery.

## Open Follow-Up

- Move shared plugin versions into a version catalog or convention plugin.
- Add CI jobs that build Signal first, then Nova, without concurrent access to the same included build output.
- Add a small CI note to both repo readmes once the public/private release workflow is finalized.

