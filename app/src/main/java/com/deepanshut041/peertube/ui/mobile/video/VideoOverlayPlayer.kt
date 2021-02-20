package com.deepanshut041.peertube.ui.mobile.video

import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.mikepenz.iconics.compose.Image
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial
import com.deepanshut041.peertube.common.service.Resource
import com.deepanshut041.peertube.common.service.model.VideoCommentModel
import com.deepanshut041.peertube.common.service.model.VideoModel
import com.deepanshut041.peertube.ui.mobile.base.BottomSheet
import com.deepanshut041.peertube.ui.mobile.base.TextIcon
import com.deepanshut041.peertube.util.LoadingView
import com.deepanshut041.peertube.util.getTimeAgo
import com.deepanshut041.peertube.util.getViewModel
import com.deepanshut041.peertube.util.humanReadableBigNumber
import dev.chrisbanes.accompanist.coil.CoilImage
import androidx.compose.runtime.DisposableEffect
import com.mikepenz.iconics.compose.ExperimentalIconics

val PLAYER_HEIGHT = 240.dp
const val MAX_Y_SCALE = 0.3f

@ExperimentalIconics
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
    val iconButtonsModifier =
        Modifier.fillMaxHeight().width(40.dp).padding(4.dp, 20.dp).alpha(iconButtonsOpacity)

    DisposableEffect(videoId) {
        viewModel.fetchVideoDetails(videoId)
        stickyDraggingConfig.expand()
        onDispose {}
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
                    url = videoResult.data?.files?.firstOrNull()?.fileUrl ?: "",
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
                    style = MaterialTheme.typography.body2.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                )
            }

            Image(
                if (isPlaying) CommunityMaterial.Icon3.cmd_pause else CommunityMaterial.Icon3.cmd_play,
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

        if (scaleY > MAX_Y_SCALE)
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
                                    descriptionState = {
                                        bottomSheet.animateTo(
                                            ModalBottomSheetValue.Expanded
                                        )
                                    }
                                )

                            if (comment.isDeleted == false) {
                                VideoComment(comment)
                                Divider(modifier = Modifier.fillMaxWidth())
                            }
                        }
                    }

                } else
                    LoadingView(modifier = Modifier.fillMaxSize())
            }, modifier = Modifier.alpha(opacity).offset(y = stickyDraggingConfig.offset)
                .fillMaxWidth()
                .weight(1f)
                .background(MaterialTheme.colors.background)
            )

    }
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
private fun VideoHeader(
    video: VideoModel,
    descriptionState: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth().background(MaterialTheme.colors.background)
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Text(
                text = video.name ?: "",
                style = MaterialTheme.typography.subtitle2.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.clickable(onClick = descriptionState)
            )
        }
        Text(
            text = buildAnnotatedString {
                pushStyle(SpanStyle(fontSize = 12.sp))
                append(video.createdAt?.getTimeAgo() ?: "")
                video.views?.also {
                    append(" • ${it.humanReadableBigNumber()} views")
                }
            },
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Row(modifier = Modifier.padding(vertical = 5.dp)) {
            TextIcon(
                asset = CommunityMaterial.Icon3.cmd_thumb_up,
                text = video.likes?.humanReadableBigNumber() ?: "",
                modifier = Modifier.padding(vertical = 8.dp).weight(1f).clickable(onClick = { })
            )
            TextIcon(
                asset = CommunityMaterial.Icon3.cmd_thumb_down,
                text = video.dislikes?.humanReadableBigNumber() ?: "",
                modifier = Modifier.padding(vertical = 8.dp).weight(1f).clickable(onClick = { })
            )
            TextIcon(
                asset = CommunityMaterial.Icon3.cmd_share,
                text = "Share",
                modifier = Modifier.padding(vertical = 8.dp).weight(1f).clickable(onClick = { })
            )
            TextIcon(
                asset = CommunityMaterial.Icon.cmd_cloud_download,
                text = "Download",
                modifier = Modifier.padding(vertical = 8.dp).weight(1f).clickable(onClick = { })
            )
            TextIcon(
                asset = CommunityMaterial.Icon.cmd_account_multiple_plus,
                text = "Save",
                modifier = Modifier.padding(vertical = 8.dp).weight(1f).clickable(onClick = { })
            )
        }
        Divider(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun VideoPlayer(
    modifier: Modifier,
    url: String,
    isPlaying: Boolean = true,
    seek: Float = 0f,
    showControls: Boolean = false
) {
    val context = LocalContext.current

    val exoPlayer = remember {
        SimpleExoPlayer.Builder(context).build()
    }

    DisposableEffect(url) {
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
            context,
            Util.getUserAgent(context, context.packageName)
        )

        val source = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(
                Uri.parse(
                    url
                )
            )

        exoPlayer.prepare(source)
        onDispose { }
    }

    DisposableEffect(seek){
        exoPlayer.seekTo((exoPlayer.duration * seek.toLong()))
        onDispose { }
    }

    DisposableEffect(Unit){
        onDispose {
            exoPlayer.release()
        }
    }

    DisposableEffect(isPlaying){
        exoPlayer.playWhenReady = isPlaying
        onDispose { }
    }

    AndroidView({
        PlayerView(it)
    }, modifier = modifier) { view ->
        view.player = exoPlayer
        view.useController = showControls
        view.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
    }
}

@Composable
private fun VideoComment(comment: VideoCommentModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.background(MaterialTheme.colors.surface).padding(16.dp)
    ) {
        comment.account?.also { account ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Card(
                    modifier = Modifier
                        .preferredSize(32.dp),
                    shape = CircleShape
                ) {
                    CoilImage(
                        data = "https://${account.host}${account.avatar?.path}",
                        contentDescription = "Logo",
                        fadeIn = true,
                        error = {
                            CoilImage(data = "https://${account.host}/client/assets/images/default-avatar.png",  contentDescription = "Logo",)
                        }
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    buildAnnotatedString  {
                        pushStyle(SpanStyle(fontWeight = FontWeight.Bold, fontSize = 12.sp))
                        append("${account.displayName}")
                        pop()
                        comment.createdAt?.also {
                            pushStyle(SpanStyle(color = Color.DarkGray, fontSize = 12.sp))
                            append(" • ${it.getTimeAgo()}")
                            pop()
                        }
                    }
                )
            }
        }
        comment.text?.also {
            Text(text = it)
        }
    }
}