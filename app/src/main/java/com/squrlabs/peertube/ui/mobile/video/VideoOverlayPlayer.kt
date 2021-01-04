package com.squrlabs.peertube.ui.mobile.video

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikepenz.iconics.compose.Image
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial
import com.squrlabs.peertube.common.service.Resource
import com.squrlabs.peertube.common.service.model.VideoCommentModel
import com.squrlabs.peertube.util.LoadingView
import com.squrlabs.peertube.util.getViewModel

val PLAYER_HEIGHT = 240.dp
const val MAX_Y_SCALE = 0.3f

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun VideoOverlayPlayer(
    videoId: Long,
    viewModel: VideoPlayerViewModel = getViewModel(),
    requestClose: () -> Unit = {}
) {
    var isPlaying by remember { mutableStateOf(true) }
    val videoResult by viewModel.video.collectAsState()
    val videoDescription by viewModel.videoDescription.collectAsState()
    val comments by viewModel.comments.collectAsState()
    val bottomSheet = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

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
                .background(MaterialTheme.colors.background)
                .height(PLAYER_HEIGHT * scaleY)
                .align(Alignment.CenterHorizontally)
        ) {
            if (videoResult.state == Resource.SUCCESS)
                VideoPlayer(
                    url = videoResult.data?.files?.get(0)?.fileUrl ?: "",
                    modifier = Modifier.fillMaxWidth(scaleX)
                        .clickable(
                            enabled = !stickyDraggingConfig.isExpanded,
                            onClick = { isPlaying = !isPlaying }),
                    isPlaying = isPlaying,
                    showControls = true
                )
            else
                Box(modifier = Modifier.fillMaxWidth(scaleX).background(Color.Black))

            Box(Modifier.padding(8.dp).fillMaxHeight().weight(1f)) {
                Text(
                    text = videoResult.data?.name ?: "",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold, fontSize = 12.sp)
                )
            }

            Image(
                if(isPlaying) CommunityMaterial.Icon3.cmd_pause else CommunityMaterial.Icon3.cmd_play,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
                modifier = iconButtonsModifier.clickable(onClick = { isPlaying = !isPlaying })
                    .size(24.dp)
            )

            Image(
                CommunityMaterial.Icon.cmd_close,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
                modifier = iconButtonsModifier.clickable(onClick = {
                    isPlaying = false
                    requestClose()
                }).size(24.dp)
            )
        }

        Box(
            modifier = Modifier.alpha(opacity).offset(y = stickyDraggingConfig.offset)
                .fillMaxWidth()
                .weight(1f)
                .background(MaterialTheme.colors.background)

        ) {
            BottomSheet(sheetContent = {
                Box(modifier = Modifier.fillMaxSize()) {
                    videoDescription.description?.let {
                        Text(text = it, style = MaterialTheme.typography.body2)
                    }
                }
            }, sheetState = bottomSheet, title = "Bottom Sheet", content = {
                if (videoResult.state == Resource.SUCCESS) {
                    LazyColumn {
                        itemsIndexed(comments) { index: Int, comment: VideoCommentModel ->
                            if (index == 0)
                                VideoHeader(
                                    videoResult.data!!,
                                    descriptionState = { bottomSheet.animateTo(ModalBottomSheetValue.Expanded) }
                                )

                            if (comment.isDeleted == false) {
                                VideoComment(comment)
                                Divider(modifier = Modifier.fillMaxWidth())
                            }
                        }
                    }

                } else
                    LoadingView(modifier = Modifier.fillMaxSize())
            })

        }
    }
}
