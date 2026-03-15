<p align="center">
  <img src="screenshots/header.png" alt="KMP Loading Animation" />
</p>

[![](https://jitpack.io/v/akimaleo/kmp-loading-animations.svg)](https://jitpack.io/#akimaleo/kmp-loading-animations)

# KMP Loading Animation

A collection of beautiful, ready-to-use loading & spinner animations for
**Compose Multiplatform** — Android, iOS, Desktop (JVM), and Web.

Adapted from the famous
[AVLoadingIndicatorView](https://github.com/HarlonWang/AVLoadingIndicatorView)
and rebuilt from the ground up with Kotlin Multiplatform + Compose.

---

## Demo

<p align="center">
  <img src="screenshots/screenshot.gif" alt="Loading Showcase" width="320" />
</p>


---

## Installation

### Step 1 — Add the JitPack repository

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
    repositories {
        maven { url = uri("https://jitpack.io") }
    }
}
```

### Step 2 — Add the dependency

#### Option A — Version catalog (TOML)

```toml
# gradle/libs.versions.toml
[versions]
kmp-loading = "2.0.3"

[libraries]
kmp-loading-animation = { module = "com.github.akimaleo:kmp-loading-animations", version.ref = "kmp-loading" }
```

```kotlin
// build.gradle.kts
dependencies {
    implementation(libs.kmp.loading.animation)
}
```

#### Option B — Direct dependency

```kotlin
// build.gradle.kts
dependencies {
    implementation("com.github.akimaleo:kmp-loading-animations:2.0.3")
}
```

### Step 3 — Use any indicator

```kotlin
@Composable
fun Greeting() {
    PacmanIndicator()
}
```

Every indicator exposes optional parameters for full customisation:

```kotlin
PacmanIndicator(
    color = Color.Black,
    ballDiameter = 60f,
    canvasSize = 60.dp,
    animationDuration = 1000
)
```

---

## Available Indicators

| # | Indicator | # | Indicator |
|---|-----------|---|-----------|
| 1 | `PulsatingDot` | 15 | `LineScaleIndicator` (random) |
| 2 | `GridIndicator` (pulsating) | 16 | `BallScaleMultipleIndicator` |
| 3 | `CircularPulsatingIndicator` | 17 | `BallPulseSyncIndicator` |
| 4 | `BallClipRotatePulseIndicator` | 18 | `BallBeatIndicator` |
| 5 | `SquareSpinIndicator` | 19 | `LineScaleIndicator` (symmetric) |
| 6 | `BallClipRotateMultipleIndicator` | 20 | `LineScaleIndicator` (pulse-out) |
| 7 | `BallPulseRiseIndicator` | 21 | `BallScaleRippleIndicator` |
| 8 | `BallRotateIndicator` | 22 | `BallScaleRippleMultipleIndicator` |
| 9 | `CubeTransitionIndicator` | 23 | `BallSpinFadeLoaderIndicator` |
| 10 | `BallZigZagIndicator` | 24 | `LineSpinFadeLoaderIndicator` |
| 11 | `BallZigZagDeflectIndicator` | 25 | `TriangleSpinIndicator` |
| 12 | `BallTrianglePathIndicator` | 26 | `PacmanIndicator` |
| 13 | `BallScaleIndicator` | 27 | `GridIndicator` (beating) |
| 14 | `LineScaleIndicator` (accordion) | 28 | `SemiCircleSpinIndicator` |

Additional indicators: `GridFadeDiagonal`, `GridFadeAntiDiagonal`,
`BallRespectivelyExitIndicator`, `TriangleShapeIndicator`,
`CircleShapeIndicator`.

---

## Catalog API

The `indicatorsCatalog()` function returns a
`Map<IndicatorType, @Composable () -> Unit>` — a **linked map** that preserves
insertion order. Use it to look up a specific indicator by key, iterate over all
entries, or pick one at random.

```kotlin
import com.kawa.loading.kmp.indicatorsCatalog
import com.kawa.loading.kmp.enums.IndicatorType
```

### Get all indicators with default settings

```kotlin
val catalog = indicatorsCatalog()          // white, 40.dp
```

### Custom color and size

```kotlin
val catalog = indicatorsCatalog(
    color = Color.Cyan,
    sizeDp = 60.dp
)
```

### Pick a specific indicator by type

```kotlin
@Composable
fun MyScreen() {
    // Direct map access — no searching required
    indicatorsCatalog()[IndicatorType.PACMAN]?.invoke()
}
```

### Show a random loading indicator

```kotlin
@Composable
fun RandomLoader() {
    val catalog = remember { indicatorsCatalog(color = Color.White) }
    val (type, indicator) = remember { catalog.entries.random() }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        indicator()
        Text(type.displayName)
    }
}
```

### Iterate over all indicators

```kotlin
@Composable
fun ListAll() {
    val catalog = indicatorsCatalog()
    catalog.forEach { (type, indicator) ->
        Row {
            indicator()
            Text(type.displayName)
        }
    }
}
```

### Display the full showcase grid

```kotlin
@Composable
fun App() {
    LoadingShowcase()   // adaptive grid with labels
}
```

### Available `IndicatorType` values

```
PULSATING_DOT, GRID_PULSATING, CIRCULAR_PULSATING,
BALL_CLIP_ROTATE_PULSE, SQUARE_SPIN, BALL_CLIP_ROTATE_MULTIPLE,
BALL_PULSE_RISE, BALL_ROTATE, CUBE_TRANSITION,
BALL_ZIG_ZAG, BALL_ZIG_ZAG_DEFLECT, BALL_TRIANGLE_PATH,
BALL_SCALE, LINE_SCALE_ACCORDION, LINE_SCALE_RANDOM,
BALL_SCALE_MULTIPLE, BALL_PULSE_SYNC, BALL_BEAT,
LINE_SCALE_SYMMETRIC, LINE_SCALE_PULSE_OUT,
BALL_SCALE_RIPPLE, BALL_SCALE_RIPPLE_MULTIPLE,
BALL_SPIN_FADE_LOADER, LINE_SPIN_FADE_LOADER,
TRIANGLE_SPIN, PACMAN, GRID_BEAT, SEMI_CIRCLE_SPIN
```

---

## Targets

| Platform | Status |
|----------|--------|
| Android  | ✅ |
| iOS      | ✅ |
| Desktop (JVM) | ✅ |
| Web (JS) | ✅ |
| Web (Wasm) | ✅ |

---

## License

```
Copyright 2023 Mahboubeh Seyedpour

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
