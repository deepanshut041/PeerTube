package com.squrlabs.peertube.ui.mobile.video

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikepenz.iconics.compose.Image
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial
import com.squrlabs.peertube.common.service.Resource
import com.squrlabs.peertube.ui.mobile.base.VideoPlayer
import com.squrlabs.peertube.util.getViewModel

val PLAYER_HEIGHT = 240.dp
const val MAX_Y_SCALE = 0.3f

@Composable
fun VideoOverlayPlayer(
    videoId: Long,
    viewModel: VideoPlayerViewModel = getViewModel(),
    requestClose: () -> Unit = {}
) {
    var isPlaying by remember { mutableStateOf(true) }
    val videoResult by viewModel.video.collectAsState()

    val height = screenDimensions().height - (PLAYER_HEIGHT * MAX_Y_SCALE)
    val stickyDraggingConfig = remember(height) { StickyDraggingConfig(false, 0.dp, height) }
    val opacity = interpolate(stickyDraggingConfig.progress, 0f..1f, 1f..0f)
    val scaleY = interpolate(stickyDraggingConfig.progress, 0f..1f, 1f..0.3f)
    val scaleX = interpolate(stickyDraggingConfig.progress, 0.7f..1f, 1f..MAX_Y_SCALE)
    val iconButtonsOpacity = interpolate(stickyDraggingConfig.progress, 0.7f..1f, 0f..1f)
    val iconButtonsModifier = Modifier.fillMaxHeight().width(40.dp).padding(4.dp, 20.dp).alpha(iconButtonsOpacity)

    onCommit(videoId) {
        viewModel.fetchVideoDetails(videoId)
        stickyDraggingConfig.expand()
    }

    Column {
        Row(
            Modifier
                .stickyDrag(config = stickyDraggingConfig)
                .clickable { stickyDraggingConfig.expand() }
                .background(Color(0xFF292929))
                .height(PLAYER_HEIGHT * scaleY)
                .align(Alignment.CenterHorizontally)
        ) {
            if (videoResult.state == Resource.SUCCESS)
                VideoPlayer(
                    url = videoResult.data?.files?.get(0)?.fileUrl ?:"",
                    modifier = Modifier.fillMaxWidth(scaleX)
                        .clickable(
                            enabled = !stickyDraggingConfig.isExpanded,
                            onClick = { isPlaying = !isPlaying }),
                    isPlaying = isPlaying,
                )
            else
                Box(modifier = Modifier.fillMaxWidth(scaleX).background(Color.Black))

            Box(Modifier.padding(8.dp).fillMaxHeight().weight(1f)) {
                Text(
                    text =  videoResult.data?.name?:"",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontWeight = FontWeight.W600,
                    style = TextStyle(MaterialTheme.colors.secondary, fontSize = 14.sp)
                )
            }

            Image(
                CommunityMaterial.Icon3.cmd_play,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary),
                modifier = iconButtonsModifier.clickable(onClick = { isPlaying = !isPlaying })
            )

            Image(
                CommunityMaterial.Icon.cmd_close,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary),
                modifier = iconButtonsModifier.clickable(onClick = {
                    isPlaying = false
                    requestClose()
                })
            )
        }

        Box(
            modifier = Modifier.alpha(opacity).offset(y = stickyDraggingConfig.offset)
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White)

        )
    }
}

@Composable
@Preview
fun VideoPlayerBottom() {
    Column {
        Row {

        }
    }
}