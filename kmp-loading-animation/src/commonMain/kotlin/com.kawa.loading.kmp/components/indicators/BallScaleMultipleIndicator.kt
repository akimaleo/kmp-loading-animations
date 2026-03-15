package com.kawa.loading.kmp.components.indicators

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BallScaleMultipleIndicator(
    color: Color = Color.White,
    largestBallDiameter: Float = 70f,
    animationDuration: Int = 1500,
    minScale: Float = 0f,
    maxScale: Float = 2.5f,
    rippleCount: Int = 4,
    maxAlpha: Float = 0.3f
) {

    val smallestBallDiameter = largestBallDiameter / rippleCount
    val diameterSteps = (largestBallDiameter - smallestBallDiameter) / rippleCount

    data class RippleState(val scale: Float, val alpha: Float)

    val rippleStates: List<RippleState> = (0 until rippleCount).map { index ->
        var scale by remember { mutableStateOf(minScale) }
        var alpha by remember { mutableStateOf(maxAlpha) }

        LaunchedEffect(key1 = Unit) {
            val duration = (rippleCount - index) * animationDuration / rippleCount
            val delay = index * animationDuration / rippleCount

            // Scale animation
            animate(
                initialValue = minScale,
                targetValue = maxScale,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = duration,
                        delayMillis = delay,
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Restart,
                ),
            ) { value, _ ->
                scale = value
                // Fade out as the ripple expands so it doesn't pop
                alpha = maxAlpha * (1f - value / maxScale)
            }
        }
        RippleState(scale, alpha)
    }

    Canvas(modifier = Modifier) {
        for (index in 0 until rippleCount) {
            val radius = (largestBallDiameter / 2) - (index * (diameterSteps / 2))
            drawCircle(
                color = color,
                center = center,
                radius = radius * rippleStates[index].scale,
                alpha = rippleStates[index].alpha
            )
        }
    }
}

@Preview
@Composable
fun BallScaleMultipleIndicatorPreview() {
    Box(
        modifier = Modifier
            .background(Color.DarkGray)
            .padding(40.dp)
            .size(120.dp)
    ) {
        BallScaleMultipleIndicator()
    }
}
