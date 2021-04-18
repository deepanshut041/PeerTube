package com.deepanshut041.peertube.ui.mobile.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeLibraryScreen(
    homeViewModel: HomeViewModel = getViewModel(),
    setVideoModel: (Long) -> Unit
) {
    Text(text = "Library")
}