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
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PacmanIndicator(
    color: Color = Color.White,
    canvasSize: Dp = 40.dp,
    animationDuration: Int = 500
) {
    val lipStart = 0f
    val lipEnd = 45f

    val transition = rememberInfiniteTransition()

    // Position of the food relative to the canvas width (from right to center)
    val positionAnimation by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDuration, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    // Opening and closing of the mouth
    val lipAnimation by transition.animateFloat(
        initialValue = lipStart,
        targetValue = lipEnd,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration / 2, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(
        modifier = Modifier
            .size(canvasSize)
            .clipToBounds()
    ) {
        val pacmanDiameter = size.minDimension
        val pacmanRadius = pacmanDiameter / 2f
        val foodRadius = pacmanRadius / 4f // Make food smaller than Pacman

        // Draw Pacman (centered in the canvas)
        drawArc(
            color = color,
            startAngle = lipAnimation,
            sweepAngle = 360f - lipAnimation * 2f,
            topLeft = Offset(
                x = (size.width - pacmanDiameter) / 2f,
                y = (size.height - pacmanDiameter) / 2f
            ),
            size = Size(pacmanDiameter, pacmanDiameter),
            useCenter = true
        )

        // Draw Food (moving from right to center)
        drawCircle(
            color = color,
            radius = foodRadius,
            center = Offset(
                x = size.width * positionAnimation,
                y = size.height / 2f
            )
        )
    }
}

@Preview
@Composable
fun PacmanIndicatorPreview() {
    Box(
        modifier = Modifier
            .background(Color.Gray)
            .padding(20.dp)
    ) {
        PacmanIndicator()
    }
}