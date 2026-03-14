package com.example.myapplication

import androidx.compose.material3.MaterialTheme
import com.github.takahirom.roborazzi.captureRoboImage
import com.kawa.loading.kmp.LoadingShowcase
import org.junit.Test

/**
 * Records [LoadingShowcase] as a screenshot using Roborazzi on Desktop.
 *
 * Run:
 *   ./gradlew :composeApp:jvmTest
 *
 * The image is written to screenshots/animationrec.png
 */
class RecordShowcaseGifTest {

    @Test
    fun captureShowcase() {
        captureRoboImage("screenshots/animationrec.png") {
            MaterialTheme {
                LoadingShowcase()
            }
        }
    }
}
