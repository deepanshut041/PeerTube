package com.squrlabs.peertube.ui.mobile.home.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.sp
import coil.transform.CircleCropTransformation
import com.mikepenz.iconics.compose.Image
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial
import com.squrlabs.peertube.common.service.model.VideoModel
import com.squrlabs.peertube.ui.mobile.MobileViewModel
import com.squrlabs.peertube.util.duration
import com.squrlabs.peertube.util.getTimeAgo
import com.squrlabs.peertube.util.humanReadableBigNumber
import dev.chrisbanes.accompanist.coil.CoilImage


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
        model.displayName?.let {
            channelName = if (it.length > 20) it.subSequence(0, 18).toString() + "..." else it
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth().clickable(onClick = {
            mainViewModel.setVideoModel(videoModel.id)
        })
    ) {
        Spacer(Modifier.preferredHeight(5.dp))
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
                        modifier = Modifier.size(34.dp).clip(CircleShape)
                            .background(Color.LightGray)
                    )
                },
                error = {
                    Box(
                        modifier = Modifier.size(34.dp).clip(CircleShape)
                            .background(Color.LightGray)
                    )
                },
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(34.dp).clickable(onClick = {  }),
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
                Text(
                    text = "$channelName • $views • $date",
                    style = MaterialTheme.typography.overline.copy(fontSize = 10.sp)
                )
            }
            Image(
                CommunityMaterial.Icon.cmd_dots_vertical,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary),
                modifier = Modifier.size(24.dp).clickable(onClick = { })
            )
        }
        Spacer(Modifier.preferredHeight(15.dp))
    }
}