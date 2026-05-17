# Publishing

## Local

```bash
gradle --no-configuration-cache :packages:kmp-compose:publishToMavenLocal
```

## Release Repository

Future publishing target should be Maven Central or GitHub Packages.

## Required Before Release

- version bump
- changelog update
- API stability review
- docs update
- build all configured targets
- demo/gallery smoke test
