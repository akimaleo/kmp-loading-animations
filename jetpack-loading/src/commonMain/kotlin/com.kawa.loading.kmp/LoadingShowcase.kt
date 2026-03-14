package com.kawa.loading.kmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
import com.kawa.loading.kmp.enums.PunchType

@Preview
@Composable
fun LoadingShowcase() {
    val rowHeight = 50.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        // Row #1
        Row(
            modifier = Modifier.height(rowHeight).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PulsatingDot()
            GridIndicator(animationType = GridAnimationType.PULSATING)
            CircularPulsatingIndicator()
            BallClipRotatePulseIndicator()
        }

        // Row #2
        Row(
            modifier = Modifier.height(rowHeight).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SquareSpinIndicator()
            BallClipRotateMultipleIndicator()
            BallPulseRiseIndicator()
            BallRotateIndicator()
        }

        // Row #3
        Row(
            modifier = Modifier.height(rowHeight).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CubeTransitionIndicator()
            BallZigZagIndicator()
            BallZigZagDeflectIndicator()
            BallTrianglePathIndicator()
        }

        // Row #4
        Row(
            modifier = Modifier.height(rowHeight).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BallScaleIndicator()
            LineScaleIndicator(punchType = PunchType.ACCORDION_PUNCH)
            LineScaleIndicator(punchType = PunchType.RANDOM_PUNCH)
            BallScaleMultipleIndicator()
        }

        // Row #5
        Row(
            modifier = Modifier.height(rowHeight).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BallPulseSyncIndicator()
            BallBeatIndicator()
            LineScaleIndicator(punchType = PunchType.SYMMETRIC_PUNCH)
            LineScaleIndicator(punchType = PunchType.PULSE_OUT_PUNCH)
        }

        // Row #6
        Row(
            modifier = Modifier.height(rowHeight).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BallScaleRippleIndicator()
            BallScaleRippleMultipleIndicator()
            BallSpinFadeLoaderIndicator()
            LineSpinFadeLoaderIndicator()
        }

        // Row #7
        Row(
            modifier = Modifier.height(rowHeight).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TriangleSpinIndicator()
            PacmanIndicator()
            GridIndicator(animationType = GridAnimationType.BEATING)
            SemiCircleSpinIndicator()
        }
    }
}

