package com.squrlabs.peertube.ui.mobile.instance

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun InstanceScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Splash") })
        },
        bodyContent = {
            Text(text = "Splash Screen")
        }
    )
}