package com.example.myapplication.OnBoardingModule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.theme.color_FBF6FC
import androidx.compose.runtime.LaunchedEffect
import com.example.myapplication.components.HeadingText
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit = {}) {
    LaunchedEffect(Unit) {
        delay(3000) // 3 seconds delay
        onTimeout()
    }
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding).background(color_FBF6FC),
            contentAlignment = Alignment.Center
        ) {
            HeadingText(text = "TrackHer")
        }
    }
}
