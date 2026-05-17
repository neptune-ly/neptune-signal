# Testing Strategy

## Current Tests

- theme configuration
- version exposure
- Prism experience resolver
- RTL identifier rendering

## Required Expansion

- card reveal policy tests
- secure clipboard policy tests
- money formatting tests
- component smoke previews
- Android demo smoke tests
- visual screenshot regression once gallery automation exists

## Build Gate

```bash
gradle --no-configuration-cache :packages:kmp-compose:build
```
