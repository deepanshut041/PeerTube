package com.deepanshut041.peertube.ui.mobile.video

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.deepanshut041.peertube.common.service.model.VideoModel
import com.deepanshut041.peertube.ui.mobile.base.TextIcon
import com.deepanshut041.peertube.util.getTimeAgo
import com.deepanshut041.peertube.util.humanReadableBigNumber
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun VideoHeader(
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
