package com.squrlabs.peertube.di

import com.squrlabs.peertube.ui.mobile.MobileViewModel
import com.squrlabs.peertube.ui.mobile.instance.InstanceViewModel
import com.squrlabs.peertube.ui.mobile.home.HomeViewModel
import com.squrlabs.peertube.ui.tv.TvViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@FlowPreview
@ExperimentalCoroutinesApi
val viewModelModule = module {
    viewModel { MobileViewModel(get()) }
    viewModel { TvViewModel(get(), get()) }
    viewModel { InstanceViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}