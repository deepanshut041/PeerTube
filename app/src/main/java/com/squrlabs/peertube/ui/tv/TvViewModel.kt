package com.squrlabs.peertube.ui.tv

import androidx.lifecycle.ViewModel
import com.squrlabs.peertube.common.service.repository.InstanceRepository
import com.squrlabs.peertube.common.service.repository.VideoRepository

class TvViewModel(
    private val instanceRepository: InstanceRepository,
    private val videoRepository: VideoRepository
) : ViewModel() {

}