package com.kawa.loading.kmp.components.indicators

import androidx.compose.animation.core.FastOutSlowInEasing
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BallScaleIndicator(
    color: Color = Color.White,
    minAlpha: Float = 0f,
    maxAlpha: Float = 0.3f,
    minScale: Float = 0f,
    maxScale: Float = 2.5f,
    animationDuration: Int = 1100,
    ballDiameter: Float = 70f
) {

    val transition = rememberInfiniteTransition()

    val alpha by transition.animateFloat(
        initialValue = maxAlpha,
        targetValue = minAlpha,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val scale by transition.animateFloat(
        initialValue = minScale,
        targetValue = maxScale,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = Modifier) {
        drawCircle(
            color = color,
            radius = (ballDiameter / 2) * scale,
            center = center,
            alpha = alpha
        )
    }
}

@Preview
@Composable
fun BallScaleIndicatorPreview() {
    Box(
        modifier = Modifier
            .background(Color.DarkGray)
            .padding(40.dp)
            .size(120.dp)
    ) {
        BallScaleIndicator()
    }
}
