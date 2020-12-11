package com.squrlabs.peertube.ui.splash

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.squrlabs.peertube.common.service.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: SplashViewModel) {
        with(viewModel) {
            getInstance.observe(this@SplashActivity, {
                Log.i("SplashActivity", "state ${it.state} ${it.data?: emptyList()}")
            })
        }
    }
}