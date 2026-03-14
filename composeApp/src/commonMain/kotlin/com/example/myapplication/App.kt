package com.example.myapplication

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kawa.loading.kmp.LoadingShowcase

@Composable
@Preview
fun App() {
    MaterialTheme {
        LoadingShowcase()
    }
}
