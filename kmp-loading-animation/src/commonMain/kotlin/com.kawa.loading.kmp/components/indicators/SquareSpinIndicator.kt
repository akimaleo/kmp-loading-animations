package com.kawa.loading.kmp.components.indicators

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
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
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kawa.loading.kmp.enums.DrawStyleType
import com.kawa.loading.kmp.enums.RotationAxis
import com.kawa.loading.kmp.enums.SquareCardFace

@Composable
fun SquareSpinIndicator(
    color: Color = Color.White,
    animationDelay: Int = 800,
    canvasSize: Float = 60f,
    style: DrawStyleType = DrawStyleType.FILL,
    penThickness: Dp = 2.dp,
) {
    var cardFace by remember { mutableStateOf(SquareCardFace.AxisX) }
    val rotation = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            rotation.animateTo(
                targetValue = cardFace.angle,
                animationSpec = tween(
                    durationMillis = animationDelay,
                    easing = FastOutSlowInEasing,
                )
            )
            val nextFace = cardFace.next
            rotation.snapTo(0f)
            cardFace = nextFace
        }
    }

    val rotationValue = rotation.value

    Canvas(
        modifier = Modifier
            .graphicsLayer {
                if (cardFace.axis == RotationAxis.AxisX) {
                    rotationX = rotationValue
                } else {
                    rotationY = rotationValue
                }
                cameraDistance = 12f * density
            }
    ) {
        val path = Path().apply {
            addRect(
                rect = Rect(
                    left = -canvasSize,
                    top = -canvasSize,
                    right = canvasSize,
                    bottom = canvasSize
                )
            )
        }

        drawPath(
            path = path,
            color = color,
            style = when (style) {
                DrawStyleType.FILL -> Fill
                DrawStyleType.STROKE -> Stroke(
                    width = penThickness.toPx(),
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            }
        )
    }
}

@Preview
@Composable
fun SquareSpinIndicatorPreview() {
    Box(
        modifier = Modifier
            .background(Color.DarkGray)
            .padding(40.dp)
            .size(120.dp)
    ) {
        SquareSpinIndicator()
    }
}
