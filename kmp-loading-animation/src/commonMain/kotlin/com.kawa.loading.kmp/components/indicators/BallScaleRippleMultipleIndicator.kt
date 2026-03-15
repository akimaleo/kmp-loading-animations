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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BallScaleRippleMultipleIndicator(
    color: Color = Color.White,
    duration: Int = 1500,
    largerRadius: Float = 100f,
    circleCount: Int = 5,
    minAlpha: Float = 0.2f,
    maxAlpha: Float = 1.0f,
    minScale: Float = 0f,
    maxScale: Float = 1.0f,
    penThickness: Float = 3f,
    repeatMode: RepeatMode = RepeatMode.Restart
) {

    data class RippleState(val scale: Float, val alpha: Float)

    val rippleStates: List<RippleState> = (0 until circleCount).map { index ->
        var scale by remember { mutableStateOf(minScale) }
        var alpha by remember { mutableStateOf(maxAlpha) }

        LaunchedEffect(key1 = Unit) {
            val rippleDuration = (circleCount - index) * duration / circleCount
            val rippleDelay = duration - rippleDuration

            animate(
                initialValue = minScale,
                targetValue = maxScale,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = rippleDuration,
                        delayMillis = rippleDelay,
                        easing = LinearEasing
                    ),
                    repeatMode = repeatMode,
                ),
            ) { value, _ ->
                scale = value
                // Fade out as the ripple expands so it doesn't pop on restart
                val progress = (value - minScale) / (maxScale - minScale)
                alpha = maxAlpha - progress * (maxAlpha - minAlpha)
            }
        }
        RippleState(scale, alpha)
    }

    Canvas(modifier = Modifier) {
        for (index in 0 until circleCount) {
            val radius = (circleCount - index) * (largerRadius / circleCount)

            drawCircle(
                color = color,
                radius = radius * rippleStates[index].scale,
                center = center,
                style = Stroke(width = penThickness),
                alpha = rippleStates[index].alpha
            )
        }
    }
}

@Preview
@Composable
fun BallScaleRippleMultipleIndicatorPreview() {
    Box(
        modifier = Modifier
            .background(Color.DarkGray)
            .padding(40.dp)
            .size(120.dp)
    ) {
        BallScaleRippleMultipleIndicator()
    }
}
