package com.squrlabs.peertube.ui.mobile.main

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Main") })
        },
        bodyContent = {
            Text(text = "Main Screen")
        }
    )
}