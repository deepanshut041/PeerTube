package com.squrlabs.peertube.mobile.ui.video

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val videoModule = module {
    viewModel {
        VideoViewModel(get())
    }
}