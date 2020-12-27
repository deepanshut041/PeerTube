package com.squrlabs.peertube.ui.mobile.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.transform.CircleCropTransformation
import com.mikepenz.iconics.compose.Image
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial
import com.squrlabs.peertube.common.service.model.VideoModel
import com.squrlabs.peertube.ui.mobile.MobileViewModel
import com.squrlabs.peertube.util.*
import dev.chrisbanes.accompanist.coil.CoilImage


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

@Composable
fun FeedItem(videoModel: VideoModel, mainViewModel: MobileViewModel) {
    val name = videoModel.name ?: ""
    val previewUrl = videoModel.currentHost + videoModel.previewPath
    val views = "${(videoModel.views ?: 0).humanReadableBigNumber()} views"
    val date = videoModel.publishedAt?.getTimeAgo()
    val duration = (videoModel.duration ?: 0).duration()
    var channelName = ""
    var avatarUrl = ""

    videoModel.channel?.let { model ->
        model.avatar?.let {
            avatarUrl = videoModel.currentHost + it.path!!
        }
        channelName = model.displayName ?: ""
    }

    Column(
        modifier = Modifier.fillMaxWidth().padding(0.dp, 10.dp).clickable(onClick = {
            mainViewModel.setVideoModel(videoModel.id)
        })
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            CoilImage(
                data = previewUrl,
                loading = {
                    Box(Modifier.fillMaxWidth().height(200.dp).background(Color.LightGray)) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                },
                error = { Box(Modifier.fillMaxWidth().height(200.dp).background(Color.LightGray)) },
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                style = TextStyle(color = Color.White),
                text = duration,
                modifier = Modifier.align(Alignment.BottomEnd)
                    .background(color = Color.Black)
                    .padding(2.dp)
            )
        }
        Spacer(Modifier.preferredHeight(10.dp))
        Row {
            Spacer(Modifier.preferredWidth(10.dp))
            CoilImage(
                data = avatarUrl,
                loading = {
                    Box(
                        Modifier.size(34.dp).background(Color.LightGray).clip(CircleShape)
                    )
                },
                error = { Box(Modifier.size(34.dp).background(Color.LightGray).clip(CircleShape)) },
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(34.dp),
                requestBuilder = { transformations(CircleCropTransformation()) },
            )
            Spacer(Modifier.preferredWidth(10.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    style = MaterialTheme.typography.body2,
                    text = name,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.preferredWidth(5.dp))
                Row {
                    Text(
                        text = channelName,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxWidth(fraction = .5f),
                        style = MaterialTheme.typography.overline,
                    )
                    Text(text = " • ", style = MaterialTheme.typography.overline)
                    Text(text = views, style = MaterialTheme.typography.overline)
                    Text(text = " • ", style = MaterialTheme.typography.overline)
                    Text(text = date.toString(), style = MaterialTheme.typography.overline)
                }
            }
            IconButton(onClick = { }, modifier = Modifier.padding(0.dp)) {
                Image(
                    CommunityMaterial.Icon.cmd_dots_vertical,
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}