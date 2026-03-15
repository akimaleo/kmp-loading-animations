package com.kawa.loading.kmp.components.indicators

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BallScaleRippleIndicator(
    color: Color = Color.White,
    minRadiusRatio: Float = 0f,
    maxRadiusRatio: Float = 2.2f,
    radius: Float = 35f,
    penThickness: Float = 5f,
    animationDuration: Int = 1100,
    maxAlpha: Float = 1f
) {

    val transition = rememberInfiniteTransition()

    val scale by transition.animateFloat(
        initialValue = minRadiusRatio,
        targetValue = maxRadiusRatio,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val alpha by transition.animateFloat(
        initialValue = maxAlpha,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = Modifier) {
        drawCircle(
            color = color,
            radius = radius * scale,
            center = center,
            style = Stroke(width = penThickness),
            alpha = alpha
        )
    }
}

@Preview
@Composable
fun BallScaleRippleIndicatorPreview() {
    Box(
        modifier = Modifier
            .background(Color.DarkGray)
            .padding(40.dp)
            .size(120.dp)
    ) {
        BallScaleRippleIndicator()
    }
}