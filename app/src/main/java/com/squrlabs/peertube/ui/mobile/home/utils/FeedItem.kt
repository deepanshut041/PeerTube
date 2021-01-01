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
            avatarUrl = "https://${model.host}${it.path}"
        }
        model.displayName?.let {
            channelName = if (it.length > 30) it.subSequence(0, 28).toString() + "..." else it
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth().clickable(onClick = {
            mainViewModel.setVideoModel(videoModel.id)
        })
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            CoilImage(
                data = previewUrl,
                loading = {
                    Box(Modifier.fillMaxWidth().height(200.dp).background(MaterialTheme.colors.secondary))
                },
                error = { Box(Modifier.fillMaxWidth().height(200.dp).background(MaterialTheme.colors.secondary)) },
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
        Row {
            Box(modifier = Modifier.padding(10.dp)){
                CoilImage(
                    data = avatarUrl,
                    loading = {
                        Box(
                            modifier = Modifier.size(35.dp).clip(CircleShape).background(MaterialTheme.colors.secondary)
                        )
                    },
                    error = {
                        Box(
                            modifier = Modifier.size(35.dp).clip(CircleShape).background(MaterialTheme.colors.secondary)
                        )
                    },
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(35.dp).clickable(onClick = { }),
                    requestBuilder = { transformations(CircleCropTransformation()) },
                )
            }
            Column(modifier = Modifier.weight(1f).padding(0.dp, 10.dp)) {
                Text(
                    style = MaterialTheme.typography.caption,
                    text = name,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.preferredHeight(3.dp))
                Text(
                    text = "$channelName • $views • $date",
                    style = MaterialTheme.typography.caption.copy(fontSize = 10.sp)
                )
            }
            IconButton(onClick = {

            }, modifier = Modifier.width(20.dp)) {
                Image(
                    CommunityMaterial.Icon.cmd_dots_vertical,
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Spacer(Modifier.preferredHeight(20.dp))
    }
}