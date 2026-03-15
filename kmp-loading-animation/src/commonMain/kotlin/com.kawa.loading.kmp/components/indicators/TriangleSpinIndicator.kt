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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kawa.loading.kmp.enums.RotationAxis
import com.kawa.loading.kmp.enums.TriangleCardFace
import kotlin.math.sqrt

@Composable
fun TriangleSpinIndicator(
    color: Color = Color.White,
    canvasSize: Dp = 40.dp,
    animationDelay: Int = 850
) {
    var cardFace by remember { mutableStateOf(TriangleCardFace.AxisY) }
    val rotation = remember { Animatable(cardFace.initValue) }

    LaunchedEffect(animationDelay) {
        while (true) {
            rotation.animateTo(
                targetValue = cardFace.targetValue,
                animationSpec = tween(
                    durationMillis = animationDelay,
                    easing = FastOutSlowInEasing,
                )
            )
            val nextFace = cardFace.next
            rotation.snapTo(nextFace.initValue)
            cardFace = nextFace
        }
    }

    val rotationValue = rotation.value

    val rX = when (cardFace.axis) {
        RotationAxis.AxisY -> 0f
        RotationAxis.AxisX -> rotationValue
        RotationAxis.AAxisY -> -180f
        RotationAxis.AAxisX -> rotationValue
    }

    val rY = when (cardFace.axis) {
        RotationAxis.AxisY -> rotationValue
        RotationAxis.AxisX -> 0f
        RotationAxis.AAxisY -> rotationValue
        RotationAxis.AAxisX -> 0f
    }

    Canvas(
        modifier = Modifier
            .size(canvasSize)
            .graphicsLayer {
                rotationX = rX
                rotationY = rY
                cameraDistance = 12f * density
            }
    ) {
        drawTriangle(color)
    }
}

private fun DrawScope.drawTriangle(color: Color) {
    val path = Path()
    val sideLength = size.width
    val triangleHeight = (sideLength * sqrt(3.0) / 2).toFloat()
    val verticalOffset = (size.height - triangleHeight) / 2f

    path.moveTo(sideLength / 2f, verticalOffset)
    path.lineTo(0f, triangleHeight + verticalOffset)
    path.lineTo(sideLength, triangleHeight + verticalOffset)
    path.close()

    drawPath(
        path = path,
        color = color
    )
}

@Preview
@Composable
fun TriangleSpinIndicatorPreview() {
    Box(
        modifier = Modifier
            .background(Color.Gray)
            .padding(20.dp)
    ) {
        TriangleSpinIndicator()
    }
}