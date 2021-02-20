package com.deepanshut041.peertube.ui.mobile.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.deepanshut041.peertube.common.service.model.VideoModel
import com.deepanshut041.peertube.ui.mobile.home.utils.FeedItem
import com.deepanshut041.peertube.util.ErrorItem
import com.deepanshut041.peertube.util.LoadingItem
import com.deepanshut041.peertube.util.LoadingView


@Composable
fun HomeLocalScreen(
    localTimeline: LazyPagingItems<VideoModel>,
    setVideoModel: (Long) -> Unit
) {
    LazyColumn {
        items(localTimeline) { item ->
            FeedItem(videoModel = item!!, setVideoModel)
        }
        localTimeline.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = localTimeline.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = localTimeline.loadState.append as LoadState.Error
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