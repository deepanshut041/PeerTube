package com.squrlabs.peertube.ui.mobile.video

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.annotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.squrlabs.peertube.common.service.model.VideoDescriptionModel
import com.squrlabs.peertube.common.service.model.VideoModel
import com.squrlabs.peertube.util.getTimeAgo
import com.squrlabs.peertube.util.humanReadableBigNumber

@ExperimentalAnimationApi
@Composable
fun VideoHeader(video: VideoModel,
                videoDescription: VideoDescriptionModel,
                modifier: Modifier = Modifier
)
{
    ConstraintLayout(
        modifier = modifier.fillMaxWidth().padding(16.dp).background(MaterialTheme.colors.background)
    ) {
        val (
            title,
            dateViews,
            expand,
            description,
            thumbUp,
            thumbDown,
            share,
            download
        ) = createRefs()

        video.name?.also {
            Text(
                modifier = modifier.constrainAs(title) {
                    top.linkTo(parent.top)
                },
                text = it,
                style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold)
            )
        }
        video.createdAt?.also {
            Text(
                modifier = modifier.constrainAs(dateViews) {
                    top.linkTo(title.bottom)
                },
                text = annotatedString {
                    pushStyle(SpanStyle(fontSize = 12.sp))
                    append(it.getTimeAgo())
                    video.views?.also {
                        append(" • ${it.humanReadableBigNumber()} views")
                    }
                }
            )
        }

        video.likes?.also {
            TextIcon(
                modifier = Modifier
                    .constrainAs(thumbUp) {
                        top.linkTo(dateViews.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(thumbDown.start)
                        width = Dimension.fillToConstraints
                    }
                    .padding(top = 16.dp),
                asset = Icons.Rounded.ThumbUp,
                text = it.humanReadableBigNumber()
            )
        }
        video.dislikes?.also {
            TextIcon(
                modifier = Modifier
                    .constrainAs(thumbDown) {
                        top.linkTo(dateViews.bottom)
                        start.linkTo(thumbUp.end)
                        end.linkTo(share.start)
                        width = Dimension.fillToConstraints
                    }
                    .padding(top = 16.dp),
                asset = Icons.Rounded.ThumbUp,
                text = it.humanReadableBigNumber()
            )
        }
        TextIcon(
            modifier = Modifier
                .constrainAs(share) {
                    top.linkTo(dateViews.bottom)
                    start.linkTo(thumbDown.end)
                    end.linkTo(download.start)
                    width = Dimension.fillToConstraints
                }
                .padding(top = 16.dp),
            asset = Icons.Rounded.Share,
            text = "Share"
        )
        TextIcon(
            modifier = Modifier
                .constrainAs(download) {
                    top.linkTo(dateViews.bottom)
                    start.linkTo(share.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .padding(top = 16.dp),
            asset = Icons.Rounded.Add,
            text = "Download"
        )

        val expanded = remember { mutableStateOf(false) }
        IconButton(
            modifier = Modifier
                .constrainAs(expand) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                },
            onClick = {
                expanded.value = expanded.value.not()
            }
        ) {
            Icon(if (expanded.value) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown)
        }
        AnimatedVisibility(
            enter = fadeIn() + slideIn({ IntOffset(0, -it.height) }),
            exit = fadeOut() + slideOut({ IntOffset(0, -it.height) }),
            modifier = Modifier
                .padding(top = 8.dp)
                .constrainAs(description) {
                    top.linkTo(thumbUp.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            visible = expanded.value
        ) {
            Divider(modifier = Modifier.fillMaxWidth())
            videoDescription.description?.also { text ->
                Text(text = text)
            }
        }
    }
    Divider(modifier = Modifier.fillMaxWidth())
}

@Composable
fun TextIcon(
    text: String,
    asset: ImageVector,
    modifier: Modifier = Modifier
)
{
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(asset)
        Text(text = text, style = MaterialTheme.typography.caption)
    }
}
