#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
NOVA_DIR="$(cd "$ROOT_DIR/.." && pwd)/neptune-nova"

echo "Stopping Gradle daemons..."
(
  cd "$ROOT_DIR"
  gradle --stop >/dev/null || true
)

if [ -d "$NOVA_DIR" ]; then
  (
    cd "$NOVA_DIR"
    ./gradlew --stop >/dev/null || true
  )
fi

echo "Removing Kotlin incremental compile caches..."
rm -rf "$ROOT_DIR/packages/kmp-compose/build/kotlin"

if [ -d "$NOVA_DIR/.gradle" ]; then
  rm -rf "$NOVA_DIR/.gradle/kotlin"
fi

echo "Gradle/Kotlin local compile caches stabilized."

