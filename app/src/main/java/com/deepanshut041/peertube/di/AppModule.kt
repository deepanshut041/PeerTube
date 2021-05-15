package com.deepanshut041.peertube.di

import com.deepanshut041.peertube.ui.mobile.MobileViewModel
import com.deepanshut041.peertube.ui.mobile.instance.InstanceViewModel
import com.deepanshut041.peertube.ui.mobile.home.HomeViewModel
import com.deepanshut041.peertube.ui.mobile.overlay_player.VideoPlayerViewModel
import com.deepanshut041.peertube.ui.tv.TvViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@FlowPreview
@ExperimentalCoroutinesApi
val viewModelModule = module {

    // Mobile ViewModel
    viewModel { MobileViewModel(get()) }
    viewModel { InstanceViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { VideoPlayerViewModel(get()) }

    // Tv ViewModel
    viewModel { TvViewModel(get()) }
}