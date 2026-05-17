# Release Process

## Versioning

Use semantic versioning:

- patch: bug fixes and docs that do not alter APIs
- minor: new compatible APIs/components
- major: source or behavior-breaking API changes

## Checklist

- run full build
- run tests
- run `git diff --check`
- update `CHANGELOG.md`
- update SDK docs
- publish to Maven Local
- validate host app consumption
- tag release after review
