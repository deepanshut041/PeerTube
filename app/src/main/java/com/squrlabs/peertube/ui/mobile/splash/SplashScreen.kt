package com.squrlabs.peertube.ui.mobile.splash

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.loadImageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.viewModel
import com.squrlabs.peertube.R
import com.squrlabs.peertube.ui.mobile.MobileNavigation
import com.squrlabs.peertube.ui.mobile.MobileViewModel
import com.squrlabs.peertube.ui.mobile.splash.SplashViewModel.Companion.LaunchState.Companion.STATE_LAUNCH_INSTANCES
import com.squrlabs.peertube.ui.mobile.splash.SplashViewModel.Companion.LaunchState.Companion.STATE_LAUNCH_MAIN
import com.squrlabs.peertube.util.getViewModel

@Composable
@Preview
fun SplashScreen(
    mainViewModel: MobileViewModel = viewModel(),
    splashViewModel: SplashViewModel = getViewModel()
) {

    val launchState = splashViewModel.launchState.observeAsState()

    launchState.value?.let {
        when(it.code){
            STATE_LAUNCH_MAIN -> {
                mainViewModel.navigateTo(MobileNavigation.navigateMain())
            }
            STATE_LAUNCH_INSTANCES -> {
                mainViewModel.navigateTo(MobileNavigation.navigateInstances())
            }
            else -> {}
        }
    }?: run {
        Handler(Looper.getMainLooper()).postDelayed({ splashViewModel.loadLaunchState() }, 1000)
    }

    val image = loadImageResource(id = R.drawable.progress_anim)
    image.resource.resource?.let {
        Image(bitmap = it)
    }
}