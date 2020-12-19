package com.squrlabs.peertube.ui.mobile

import androidx.lifecycle.ViewModel
import com.squrlabs.peertube.common.service.repository.InstanceRepository
import com.squrlabs.peertube.common.service.repository.VideoRepository

class MobileViewModel(
    private val instanceRepository: InstanceRepository,
    private val videoRepository: VideoRepository
): ViewModel() {

}