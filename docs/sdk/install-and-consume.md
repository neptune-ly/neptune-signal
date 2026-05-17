# Install And Consume

## Local Composite Build

Use an included build while developing host apps:

```kotlin
includeBuild("../neptune-signal")
```

## Maven Local

Signal has Maven publishing metadata. Run:

```bash
gradle --no-configuration-cache :packages:kmp-compose:publishToMavenLocal
```

Then consume `ly.neptune.signal:packages-kmp-compose:<version>` based on generated publication coordinates.

## Version Alignment

Keep Kotlin and Compose versions aligned between Signal and host apps.
