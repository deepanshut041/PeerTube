package com.squrlabs.peertube.mobile.ui.instance

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class InstanceActivity: AppCompatActivity() {

    // Loading InstanceModule Module
    private val loadFeatures by lazy { loadKoinModules(instancesModule) }

    private fun injectFeatures() = loadFeatures

    private val viewModel: InstanceViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectFeatures()
        observeViewModel()
    }

    private fun observeViewModel() {
        with(viewModel) {
            getInstance.observe(this@InstanceActivity, {
                Log.i("InstanceActivity", "state: ${it.state} ${it.data?: emptyList()}")
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(instancesModule)
    }

}