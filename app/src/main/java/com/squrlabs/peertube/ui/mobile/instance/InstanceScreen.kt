package com.squrlabs.peertube.ui.mobile.instance

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import org.koin.androidx.compose.getViewModel

@Composable
@Preview
fun InstanceScreen() {
    val viewModel = getViewModel<InstanceViewModel>()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Splash") })
        },
        bodyContent = {
            Text(text = "Splash Screen")
        }
    )
}