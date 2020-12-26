package com.squrlabs.peertube.ui.mobile.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.viewModel
import com.squrlabs.peertube.ui.mobile.MobileViewModel

@Composable
fun HomeLibraryScreen(
    mainViewModel: MobileViewModel = viewModel(),
    homeViewModel: HomeViewModel = viewModel()
){
    Text(text = "Library")
}