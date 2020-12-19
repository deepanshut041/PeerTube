package com.squrlabs.peertube.di

import com.squrlabs.peertube.ui.mobile.MobileViewModel
import com.squrlabs.peertube.ui.tv.TvViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MobileViewModel(get(), get())
    }

    viewModel {
        TvViewModel(get(), get())
    }
}