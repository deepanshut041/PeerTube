package com.squrlabs.peertube.ui.mobile.splash

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.getViewModel

@Composable
fun SplashScreen() {

//    val viewModel = getViewModel<SplashViewModel>()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Splash") })
        },
        bodyContent = {
            Text(text = "Splash Screen")
        }
    )
}