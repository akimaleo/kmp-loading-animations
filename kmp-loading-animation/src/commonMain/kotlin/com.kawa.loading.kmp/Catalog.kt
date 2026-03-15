package com.kawa.loading.kmp

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
import com.kawa.loading.kmp.enums.IndicatorType
import com.kawa.loading.kmp.enums.PunchType

private val DEFAULT_SIZE = 40.dp

fun indicatorsCatalog(
    color: Color = Color.White,
    sizeDp: Dp = DEFAULT_SIZE
): Map<IndicatorType, @Composable () -> Unit> {
    val scale = sizeDp / DEFAULT_SIZE

    return linkedMapOf(
        IndicatorType.PULSATING_DOT to { PulsatingDot(color = color, ballDiameter = 40f * scale, horizontalSpace = 20f * scale) },
        IndicatorType.GRID_PULSATING to { GridIndicator(color = color, ballDiameter = 40f * scale, verticalSpace = 20f * scale, horizontalSpace = 20f * scale, animationType = GridAnimationType.PULSATING) },
        IndicatorType.CIRCULAR_PULSATING to { CircularPulsatingIndicator(color = color, canvasSize = 80f * scale) },
        IndicatorType.BALL_CLIP_ROTATE_PULSE to { BallClipRotatePulseIndicator(color = color, canvasSize = 80f * scale) },
        IndicatorType.SQUARE_SPIN to { SquareSpinIndicator(color = color, canvasSize = 60f * scale) },
        IndicatorType.BALL_CLIP_ROTATE_MULTIPLE to { BallClipRotateMultipleIndicator(color = color, canvasSize = 80f * scale) },
        IndicatorType.BALL_PULSE_RISE to { BallPulseRiseIndicator(color = color, ballDiameter = 40f * scale, verticalSpace = 50f * scale, horizontalSpace = 20f * scale) },
        IndicatorType.BALL_ROTATE to { BallRotateIndicator(color = color, diameter = 30f * scale, spacing = 60f * scale) },
        IndicatorType.CUBE_TRANSITION to { CubeTransitionIndicator(color = color, diameter = 20f * scale, spacing = 60f * scale) },
        IndicatorType.BALL_ZIG_ZAG to { BallZigZagIndicator(color = color, diameter = 30f * scale) },
        IndicatorType.BALL_ZIG_ZAG_DEFLECT to { BallZigZagDeflectIndicator(color = color, diameter = 30f * scale) },
        IndicatorType.BALL_TRIANGLE_PATH to { BallTrianglePathIndicator(color = color, diameter = 40f * scale, spacing = 80f * scale) },
        IndicatorType.BALL_SCALE to { BallScaleIndicator(color = color, ballDiameter = 70f * scale) },
        IndicatorType.LINE_SCALE_ACCORDION to { LineScaleIndicator(color = color, distanceOnXAxis = 30f * scale, lineHeight = (100 * scale).toInt(), penThickness = 15f * scale, punchType = PunchType.ACCORDION_PUNCH) },
        IndicatorType.LINE_SCALE_RANDOM to { LineScaleIndicator(color = color, distanceOnXAxis = 30f * scale, lineHeight = (100 * scale).toInt(), penThickness = 15f * scale, punchType = PunchType.RANDOM_PUNCH) },
        IndicatorType.BALL_SCALE_MULTIPLE to { BallScaleMultipleIndicator(color = color, largestBallDiameter = 70f * scale) },
        IndicatorType.BALL_PULSE_SYNC to { BallPulseSyncIndicator(color = color, ballDiameter = 40f * scale, spaceBetweenBalls = 20f * scale) },
        IndicatorType.BALL_BEAT to { BallBeatIndicator(color = color, ballDiameter = 40f * scale, spaceBetweenBalls = 20f * scale) },
        IndicatorType.LINE_SCALE_SYMMETRIC to { LineScaleIndicator(color = color, distanceOnXAxis = 30f * scale, lineHeight = (100 * scale).toInt(), penThickness = 15f * scale, punchType = PunchType.SYMMETRIC_PUNCH) },
        IndicatorType.LINE_SCALE_PULSE_OUT to { LineScaleIndicator(color = color, distanceOnXAxis = 30f * scale, lineHeight = (100 * scale).toInt(), penThickness = 15f * scale, punchType = PunchType.PULSE_OUT_PUNCH) },
        IndicatorType.BALL_SCALE_RIPPLE to { BallScaleRippleIndicator(color = color, radius = 35f * scale, penThickness = 5f * scale) },
        IndicatorType.BALL_SCALE_RIPPLE_MULTIPLE to { BallScaleRippleMultipleIndicator(color = color, largerRadius = 100f * scale, penThickness = 3f * scale) },
        IndicatorType.BALL_SPIN_FADE_LOADER to { BallSpinFadeLoaderIndicator(color = color, radius = 70f * scale, ballRadius = 12f * scale) },
        IndicatorType.LINE_SPIN_FADE_LOADER to { LineSpinFadeLoaderIndicator(color = color, radius = 55f * scale, elementHeight = 20f * scale, penThickness = 25f * scale) },
        IndicatorType.TRIANGLE_SPIN to { TriangleSpinIndicator(color = color, canvasSize = sizeDp) },
        IndicatorType.PACMAN to { PacmanIndicator(color = color, canvasSize = sizeDp) },
        IndicatorType.GRID_BEAT to { GridIndicator(color = color, ballDiameter = 40f * scale, verticalSpace = 20f * scale, horizontalSpace = 20f * scale, animationType = GridAnimationType.BEATING) },
        IndicatorType.SEMI_CIRCLE_SPIN to { SemiCircleSpinIndicator(color = color, canvasSize = sizeDp) },
    )
}
