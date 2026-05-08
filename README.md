# Neptune. Signal

Neptune. Signal is a white-label fintech design language for banking, payments, identity, and financial operations.

Current release:

- Standard: `0.1.0`
- Mobile Banking Demo: `0.1.0`
- KMP UI Library: planned
- Internet Banking Demo: planned

Neptune. Signal separates the design standard from product demos. The standard defines tokens, typography, motion, components, accessibility, and banking UX rules. Demos show how the standard is applied to mobile banking, internet banking, merchant portals, and internal tools.

## Principles

- Arabic-first, multilingual by design.
- White-label ready without losing Neptune. structure.
- Premium fintech, not generic banking UI.
- Material 3.1 inspired, Apple HIG aware, Neptune. owned.
- Performance-aware: fetch sensitive or heavy data only when the customer opens the right context.
- Motion is native, fast, and implementable in real code.

## Repository Structure

```text
docs/
  index.html
  standard/
  foundations/
  components/
  patterns/
  motion/
  accessibility/
  white-label/
  kmp/
  figma/
  demos/mobile/
assets/logo/
packages/
  tokens/
  kmp-compose/
```

## Public Site

This repo is ready for GitHub Pages. Use `docs/` as the Pages source.

Suggested URL:

```text
https://neptune.ly/signal
```

or GitHub Pages:

```text
https://<org>.github.io/neptune-signal
```

## Versioning

The standard has its own semantic version:

```text
Neptune. Signal Standard 0.1.0
```

Product demos and implementation libraries version separately:

```text
Mobile Banking Demo 0.1.0
Internet Banking Demo 0.1.0
KMP Compose Library 0.1.0
Figma Kit 0.1.0
```

See [Versioning](docs/standard/versioning.md).

## License

Copyright Neptune.

The public license should be selected before publishing. Recommended options:

- Apache-2.0 for code and tokens.
- CC BY 4.0 for documentation and design guidance.
- Custom brand license for Neptune. marks and logos.

