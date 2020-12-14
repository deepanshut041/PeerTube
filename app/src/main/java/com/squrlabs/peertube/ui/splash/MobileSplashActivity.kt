package com.squrlabs.peertube.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.squrlabs.peertube.ui.splash.SplashViewModel.Companion.LaunchState.Companion.STATE_LAUNCH_INSTANCES
import com.squrlabs.peertube.ui.splash.SplashViewModel.Companion.LaunchState.Companion.STATE_LAUNCH_MAIN
import com.squrlabs.peertube.util.AppNavigation
import org.koin.androidx.viewmodel.ext.android.viewModel

class MobileSplashActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null

    private val viewModel: SplashViewModel by viewModel()

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            viewModel.loadLaunchState()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize the Handler
        mDelayHandler = Handler()
        //Navigate with delay
        mDelayHandler!!.postDelayed(
            mRunnable,
            SPLASH_DELAY
        )

        with(viewModel){
            launchState.observe(this@MobileSplashActivity, {
                when(it.code){
                    STATE_LAUNCH_MAIN -> {
                        finish()
                        startActivity(AppNavigation.mobileMainActivity(this@MobileSplashActivity))
                    }
                    STATE_LAUNCH_INSTANCES -> {
                        finish()
                        startActivity(AppNavigation.mobileInstanceActivity(this@MobileSplashActivity))
                    }
                }
            })
        }
    }

    companion object {
        private const val SPLASH_DELAY: Long = 1000
    }
}