package com.squrlabs.peertube.ui.mobile.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squrlabs.peertube.common.service.Resource
import com.squrlabs.peertube.common.service.model.VideoModel
import com.squrlabs.peertube.common.service.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VideoPlayerViewModel(private val videoRepository: VideoRepository): ViewModel() {

    private val _video = MutableStateFlow<Resource<VideoModel>>(Resource.empty())

    val video: StateFlow<Resource<VideoModel>>
        get() = _video

    fun fetchVideoDetails(id: Long) {
        _video.value = Resource.loading()
        viewModelScope.launch {
            _video.value = videoRepository.getVideo(id)
        }
    }
}