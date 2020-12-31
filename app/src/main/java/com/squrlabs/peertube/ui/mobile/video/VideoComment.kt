package com.squrlabs.peertube.ui.mobile.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.annotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.squrlabs.peertube.common.service.model.VideoCommentModel
import com.squrlabs.peertube.util.getTimeAgo
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun VideoComment(comment: VideoCommentModel, modifier: Modifier = Modifier) {
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
                        fadeIn = true,
                        error = {
                            CoilImage(data = "https://${account.host}/client/assets/images/default-avatar.png")
                        }
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = annotatedString {
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
