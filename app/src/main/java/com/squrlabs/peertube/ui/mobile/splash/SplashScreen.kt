package com.squrlabs.peertube.ui.mobile.splash

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.viewModel
import com.squrlabs.peertube.R
import org.koin.core.context.KoinContextHandler

@Composable
@Preview
fun SplashScreen() {
    val context = KoinContextHandler.get()
    val splashViewModel by context.inject<SplashViewModel>()

    val image = loadImageResource(id = R.drawable.progress_anim)
    image.resource.resource?.let {
        Image(bitmap = it)
    }
}