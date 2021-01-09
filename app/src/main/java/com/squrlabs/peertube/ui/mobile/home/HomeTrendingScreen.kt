package com.squrlabs.peertube.ui.mobile.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.squrlabs.peertube.common.service.model.VideoModel
import com.squrlabs.peertube.ui.mobile.home.utils.FeedItem
import com.squrlabs.peertube.util.ErrorItem
import com.squrlabs.peertube.util.LoadingItem
import com.squrlabs.peertube.util.LoadingView


@Composable
fun HomeTrendingScreen(
    trendingTimeline: LazyPagingItems<VideoModel>,
    setVideoModel: (Long) -> Unit
) {
    LazyColumn {
        items(trendingTimeline) { item ->
            FeedItem(videoModel = item!!, setVideoModel)
        }
        trendingTimeline.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = trendingTimeline.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = trendingTimeline.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}