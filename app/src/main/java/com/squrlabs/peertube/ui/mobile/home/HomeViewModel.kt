package com.squrlabs.peertube.ui.mobile.home

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.squrlabs.peertube.common.service.model.VideoModel
import com.squrlabs.peertube.common.service.repository.VideoRepository
import com.squrlabs.peertube.ui.mobile.home.utils.FeedPagingSource
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val videoRepository: VideoRepository) : ViewModel() {

    val timeline: Flow<PagingData<VideoModel>> = Pager(PagingConfig(pageSize = 20)) {
        FeedPagingSource(videoRepository)
    }.flow
}