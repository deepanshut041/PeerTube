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

class HomeViewModel(private val videoRepository: VideoRepository) : ViewModel() {

    private val _inSearchMode = MutableStateFlow(false)

    val inSearchMode: StateFlow<Boolean>
        get() = _inSearchMode

    val globalTimeline: Flow<PagingData<VideoModel>> = Pager(PagingConfig(pageSize = 20)) { FeedPagingSource(videoRepository) }.flow
    val localTimeline: Flow<PagingData<VideoModel>> = Pager(PagingConfig(pageSize = 20)) { FeedPagingSource(videoRepository, sort = "-trending") }.flow
    val trendingTimeline: Flow<PagingData<VideoModel>> = Pager(PagingConfig(pageSize = 20)) { FeedPagingSource(videoRepository, filter = "local") }.flow

    fun switchToSearchMode(inSearchMode: Boolean) {
        _inSearchMode.value = inSearchMode
        if (!inSearchMode) {
//            updateParams(query = "")
        }
    }
}