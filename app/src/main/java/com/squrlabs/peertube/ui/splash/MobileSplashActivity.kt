package com.squrlabs.peertube.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.squrlabs.peertube.util.AppNavigation
import org.koin.androidx.viewmodel.ext.android.viewModel

class MobileSplashActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null

    private val viewModel: SplashViewModel by viewModel()

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            startActivity(AppNavigation.mobileInstanceActivity(this))
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
    }

    companion object {
        private const val SPLASH_DELAY: Long = 1000
    }
}