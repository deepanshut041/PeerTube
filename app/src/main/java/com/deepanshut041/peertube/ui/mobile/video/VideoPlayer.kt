package com.deepanshut041.peertube.ui.mobile.video

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.deepanshut041.peertube.common.service.model.FileModel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

@Composable
fun VideoPlayer(
    modifier: Modifier,
    files: List<FileModel>,
    isPlaying: Boolean = true,
    seek: Float = 0f,
    showControls: Boolean = false
) {
    val context = LocalContext.current

    val exoPlayer = remember {
        val trackSelector = DefaultTrackSelector(context)
        trackSelector.setParameters(trackSelector.buildUponParameters().setMaxVideoSizeSd())
        SimpleExoPlayer.Builder(context).setTrackSelector(trackSelector).build()
    }

    DisposableEffect(files) {
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
            context,
            Util.getUserAgent(context, context.packageName)
        )

        val source = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(
                MediaItem.fromUri("")
            )

        exoPlayer.setMediaSource(source)
        exoPlayer.play()
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
        StyledPlayerView(it)
    }, modifier = modifier) { view ->
        view.player = exoPlayer
        view.useController = showControls
        view.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH

    }
}