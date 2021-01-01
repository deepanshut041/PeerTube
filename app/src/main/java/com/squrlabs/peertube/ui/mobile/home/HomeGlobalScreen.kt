package com.squrlabs.peertube.ui.mobile.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.squrlabs.peertube.ui.mobile.MobileViewModel
import com.squrlabs.peertube.ui.mobile.home.utils.FeedItem
import com.squrlabs.peertube.util.ErrorItem
import com.squrlabs.peertube.util.LoadingItem
import com.squrlabs.peertube.util.LoadingView

@Composable
fun HomeGlobalScreen(
    mainViewModel: MobileViewModel = viewModel(),
    homeViewModel: HomeViewModel = viewModel()
) {
    val lazyTimelineItems = homeViewModel.timeline.collectAsLazyPagingItems()

    LazyColumn {
        items(lazyTimelineItems) { item ->
            FeedItem(videoModel = item!!, mainViewModel)
        }
        lazyTimelineItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyTimelineItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = lazyTimelineItems.loadState.append as LoadState.Error
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
