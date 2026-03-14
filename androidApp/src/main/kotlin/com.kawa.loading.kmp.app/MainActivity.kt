package com.kawa.loading.kmp.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kawa.loading.kmp.LoadingShowcase
import com.kawa.loading.kmp.enums.PunchType
import com.kawa.loading.kmp.app.theme.JetpackLoadingTheme
import com.kawa.loading.kmp.app.theme.background
import com.kawa.loading.kmp.components.indicators.lineScaleIndicator.LineScaleIndicator
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
import com.kawa.loading.kmp.components.indicators.BallBeatIndicator
import com.kawa.loading.kmp.components.indicators.gridIndicator.GridIndicator
import com.kawa.loading.kmp.enums.GridAnimationType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackLoadingTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoadingShowcase()
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewApp() {
    JetpackLoadingTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LoadingShowcase()
        }
    }
}
