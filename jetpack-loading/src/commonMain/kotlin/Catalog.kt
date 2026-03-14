package com.kawa.loading.kmp

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import com.kawa.loading.kmp.components.indicators.BallBeatIndicator
import com.kawa.loading.kmp.components.indicators.BallClipRotateMultipleIndicator
import com.kawa.loading.kmp.components.indicators.BallClipRotatePulseIndicator
import com.kawa.loading.kmp.components.indicators.BallPulseRiseIndicator
import com.kawa.loading.kmp.components.indicators.BallPulseSyncIndicator
import com.kawa.loading.kmp.components.indicators.BallRotateIndicator
import com.kawa.loading.kmp.components.indicators.BallScaleIndicator
import com.kawa.loading.kmp.components.indicators.BallScaleMultipleIndicator
import com.kawa.loading.kmp.components.indicators.BallScaleRippleIndicator
import com.kawa.loading.kmp.components.indicators.BallScaleRippleMultipleIndicator
import com.kawa.loading.kmp.components.indicators.BallSpinFadeLoaderIndicator
import com.kawa.loading.kmp.components.indicators.BallTrianglePathIndicator
import com.kawa.loading.kmp.components.indicators.BallZigZagDeflectIndicator
import com.kawa.loading.kmp.components.indicators.BallZigZagIndicator
import com.kawa.loading.kmp.components.indicators.CircularPulsatingIndicator
import com.kawa.loading.kmp.components.indicators.CubeTransitionIndicator
import com.kawa.loading.kmp.components.indicators.LineSpinFadeLoaderIndicator
import com.kawa.loading.kmp.components.indicators.PacmanIndicator
import com.kawa.loading.kmp.components.indicators.PulsatingDot
import com.kawa.loading.kmp.components.indicators.SemiCircleSpinIndicator
import com.kawa.loading.kmp.components.indicators.SquareSpinIndicator
import com.kawa.loading.kmp.components.indicators.TriangleSpinIndicator
import com.kawa.loading.kmp.components.indicators.gridIndicator.GridIndicator
import com.kawa.loading.kmp.components.indicators.lineScaleIndicator.LineScaleIndicator
import com.kawa.loading.kmp.enums.GridAnimationType
import com.kawa.loading.kmp.enums.PunchType

val indicatorsCatalog: List<@Composable () -> Unit> = listOf(
    { PulsatingDot() },
    { GridIndicator(animationType = GridAnimationType.PULSATING) },
    { CircularPulsatingIndicator() },
    { BallClipRotatePulseIndicator() },
    { SquareSpinIndicator() },
    { BallClipRotateMultipleIndicator() },
    { BallPulseRiseIndicator() },
    { BallRotateIndicator() },
    { CubeTransitionIndicator() },
    { BallZigZagIndicator() },
    { BallZigZagDeflectIndicator() },
    { BallTrianglePathIndicator() },
    { BallScaleIndicator() },
    { LineScaleIndicator(punchType = PunchType.ACCORDION_PUNCH) },
    { LineScaleIndicator(punchType = PunchType.RANDOM_PUNCH) },
    { BallScaleMultipleIndicator() },
    { BallPulseSyncIndicator() },
    { BallBeatIndicator() },
    { LineScaleIndicator(punchType = PunchType.SYMMETRIC_PUNCH) },
    { LineScaleIndicator(punchType = PunchType.PULSE_OUT_PUNCH) },
    { BallScaleRippleIndicator() },
    { BallScaleRippleMultipleIndicator() },
    { BallSpinFadeLoaderIndicator() },
    { LineSpinFadeLoaderIndicator() },
    { TriangleSpinIndicator() },
    { PacmanIndicator() },
    { GridIndicator(animationType = GridAnimationType.BEATING) },
    { SemiCircleSpinIndicator() },
)
