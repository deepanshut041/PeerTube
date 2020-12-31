package com.squrlabs.peertube.ui.mobile.video

import androidx.lifecycle.ViewModel
import com.squrlabs.peertube.common.service.model.VideoModel
import com.squrlabs.peertube.common.service.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class VideoPlayerViewModel(private val videoRepository: VideoRepository): ViewModel() {
    private val _video = MutableStateFlow<VideoModel?>(null)

    val video: StateFlow<VideoModel?>
        get() = _video
}