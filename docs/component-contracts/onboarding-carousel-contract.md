# Signal Onboarding Carousel Contract

## Purpose

`SignalOnboardingCarousel` presents a short, product-native onboarding story before login. It is used for premium fintech entry experiences where the bank wants to communicate trust, core banking capability, and white-label identity without creating a generic marketing slideshow.

## Component API

Current KMP API:

```kotlin
@Immutable
data class SignalOnboardingSlide(
    val eyebrow: String,
    val title: String,
    val body: String,
    val signalLabel: String,
    val primaryValue: String,
    val supportText: String,
    val icon: SignalIconName = SignalIconName.Shield,
)

@Composable
fun SignalOnboardingCarousel(
    slides: List<SignalOnboardingSlide>,
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    onSelectedIndexChange: (Int) -> Unit = {},
    progressLabel: (current: Int, total: Int) -> String = { current, total -> "$current / $total" },
)
```

## Anatomy

- Hero stage.
- Product-native banking preview surface.
- Eyebrow.
- Headline.
- Supporting body.
- Interactive progress indicator.

The CTA stack is intentionally outside the carousel. The parent screen owns primary, secondary, and legal actions.

## Slide Rules

- Use 2-4 slides.
- Preferred default is 3 slides.
- Each slide must have one job.
- Product-native visuals are required.
- Do not use stock people, fake 3D coins, decorative blobs, or random banking illustrations.
- Avoid changing CTA labels per slide.

## Motion

- Horizontal pager snap.
- Indicator width transition should be subtle.
- Route-level shared handoff is owned by the parent auth shell.
- Do not autoplay.
- Do not loop.
- Do not block login readiness with animation.

## Accessibility

- The parent supplies localized progress text.
- Visual dots are wrapped in 44dp hit targets.
- Selecting a dot changes slide state.
- RTL must mirror visual order while product analytics keep canonical slide order.

## Theming

The carousel uses Signal tokens:

- `bankPrimary`
- `bankSecondary`
- `bankAccent`
- `surfaceCard`
- `outlineVariant`
- Signal typography roles
- Signal spacing roles

Bank apps may override color and logo through theme/profile configuration. They must not override component structure locally.

## Rejected Patterns

- One-off Nova carousel code.
- Stock onboarding art.
- Autoplay.
- Heavy gradients.
- Lottie/video hero animation.
- Per-slide CTA churn.
- Overly centered generic auth layout.

## Future Work

- Add per-slide visual variants for account/card/transfer/identity previews.
- Add an optional compact mode for short devices.
- Add explicit reduced-motion behavior once the shared motion contract is centralized.
