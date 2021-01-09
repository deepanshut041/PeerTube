package com.squrlabs.peertube.ui.mobile.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.viewModel
import com.squrlabs.peertube.ui.mobile.MobileViewModel

@Composable
fun HomeLibraryScreen(
    homeViewModel: HomeViewModel = viewModel(),
    setVideoModel: (Long) -> Unit
){
    Text(text = "Library")
}