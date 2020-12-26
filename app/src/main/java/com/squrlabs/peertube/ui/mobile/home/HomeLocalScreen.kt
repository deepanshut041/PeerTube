package com.squrlabs.peertube.ui.mobile.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.viewModel
import com.squrlabs.peertube.ui.mobile.MobileViewModel
import com.squrlabs.peertube.util.getViewModel


@Composable
fun HomeLocalScreen(
    mainViewModel: MobileViewModel = viewModel(),
    homeViewModel: HomeViewModel = viewModel()
){
    Text(text = "Local")
}