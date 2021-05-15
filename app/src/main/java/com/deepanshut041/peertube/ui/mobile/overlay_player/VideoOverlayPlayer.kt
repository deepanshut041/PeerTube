package com.deepanshut041.peertube.ui.mobile.overlay_player

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.mikepenz.iconics.compose.ExperimentalIconics
import org.koin.androidx.compose.getViewModel

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

}