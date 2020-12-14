package com.squrlabs.peertube.ui.splash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.squrlabs.peertube.common.service.repository.InstanceRepository
import com.squrlabs.peertube.util.SingleLiveEvent

class SplashViewModel(private val instanceRepository: InstanceRepository): ViewModel() {

    val launchState: LiveData<LaunchState>
        get() = _launchState

    private val _launchState = SingleLiveEvent<LaunchState>()

    fun loadLaunchState() {
        instanceRepository.getCurrentHost()?.let {
            Log.i("SplashActivity", it)
            _launchState.value = LaunchState(LaunchState.STATE_LAUNCH_MAIN)
        }?: run{
            _launchState.value = LaunchState(LaunchState.STATE_LAUNCH_INSTANCES)
        }
    }

    companion object{
        class LaunchState (var code: Int) {
            companion object {
                const val STATE_LAUNCH_INSTANCES = 0
                const val STATE_LAUNCH_MAIN = 1
            }
        }
    }
}