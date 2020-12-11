package com.squrlabs.peertube.mobile.ui.instance

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val instancesModule = module(override = true) {
    viewModel {
        InstanceViewModel(get())
    }
}