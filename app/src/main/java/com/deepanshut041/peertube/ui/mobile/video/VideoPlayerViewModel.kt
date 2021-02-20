package com.deepanshut041.peertube.ui.mobile.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepanshut041.peertube.common.service.Resource
import com.deepanshut041.peertube.common.service.model.VideoCommentModel
import com.deepanshut041.peertube.common.service.model.VideoDescriptionModel
import com.deepanshut041.peertube.common.service.model.VideoModel
import com.deepanshut041.peertube.common.service.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VideoPlayerViewModel(private val videoRepository: VideoRepository) : ViewModel() {

    private val _video = MutableStateFlow<Resource<VideoModel>>(Resource.empty())
    private val _videoDescription = MutableStateFlow(VideoDescriptionModel(description = ""))
    private val _comments = MutableStateFlow<List<VideoCommentModel>>(emptyList())

    val video: StateFlow<Resource<VideoModel>>
        get() = _video

    val videoDescription: StateFlow<VideoDescriptionModel>
        get() = _videoDescription

    val comments: StateFlow<List<VideoCommentModel>>
        get() = _comments

    fun fetchVideoDetails(id: Long) {
        _video.value = Resource.loading()
        viewModelScope.launch {
            _video.value = videoRepository.getVideo(id)
        }
    }

    init {
        _comments.value = listOf(VideoCommentModel())
    }
}