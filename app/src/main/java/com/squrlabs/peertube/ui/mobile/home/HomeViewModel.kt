package com.squrlabs.peertube.ui.mobile.home

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.squrlabs.peertube.common.service.model.VideoModel
import com.squrlabs.peertube.common.service.repository.VideoRepository
import com.squrlabs.peertube.ui.mobile.home.utils.FeedPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest

data class VideoSearchParams(val query: String)

class HomeViewModel(private val videoRepository: VideoRepository) : ViewModel() {

    val inSearchMode: StateFlow<Boolean>
        get() = _inSearchMode

    val videoSearchParams: StateFlow<VideoSearchParams>
        get() = _videoSearchParams

    private val _inSearchMode = MutableStateFlow(false)
    private val _videoSearchParams = MutableStateFlow(VideoSearchParams(""))

    val globalTimeline: Flow<PagingData<VideoModel>> =
        Pager(PagingConfig(pageSize = 20)) { FeedPagingSource(videoRepository) }.flow
    val localTimeline: Flow<PagingData<VideoModel>> = Pager(PagingConfig(pageSize = 20)) {
        FeedPagingSource(
            videoRepository,
            sort = "-trending"
        )
    }.flow
    val trendingTimeline: Flow<PagingData<VideoModel>> = Pager(PagingConfig(pageSize = 20)) {
        FeedPagingSource(
            videoRepository,
            filter = "local"
        )
    }.flow
    val searchVideos: Flow<PagingData<VideoModel>> = _videoSearchParams.flatMapLatest {
        Pager(PagingConfig(pageSize = 20)) {
            FeedPagingSource(
                videoRepository,
                filter = "local"
            )
        }.flow
    }

    fun performSearch(query: String) {
        updateParams(query)
    }

    fun updateParams(query: String?) {
        _videoSearchParams.value = _videoSearchParams.value.let { params ->
            var newParams = params
            query?.let { newParams = params.copy(query = it) }
            newParams
        }
    }

    fun switchToSearchMode(inSearchMode: Boolean) {
        _inSearchMode.value = inSearchMode
        if (!inSearchMode) {
//            updateParams(query = "")
        }
    }
}