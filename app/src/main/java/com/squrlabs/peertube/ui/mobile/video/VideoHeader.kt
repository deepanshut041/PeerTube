package com.squrlabs.peertube.ui.mobile.video

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.annotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikepenz.iconics.compose.Image
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial
import com.mikepenz.iconics.typeface.IIcon
import com.squrlabs.peertube.common.service.model.VideoModel
import com.squrlabs.peertube.util.getTimeAgo
import com.squrlabs.peertube.util.humanReadableBigNumber

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun VideoHeader(
    video: VideoModel,
    descriptionState: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(16.dp)
            .background(MaterialTheme.colors.background)
    ) {
        Row(modifier = Modifier.padding(bottom = 10.dp)) {
            Text(
                text = video.name ?: "",
                style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
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
            }
        )

        Row(modifier = Modifier.padding(vertical = 10.dp)) {
            TextIcon(
                asset = CommunityMaterial.Icon3.cmd_thumb_up,
                text = video.likes?.humanReadableBigNumber() ?: "",
                modifier = Modifier.padding(vertical = 8.dp).weight(1f).clickable(onClick = { })
            )
            TextIcon(
                asset = CommunityMaterial.Icon3.cmd_thumb_up,
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
    }
    Divider(modifier = Modifier.fillMaxWidth())
}

@Composable
fun TextIcon(
    text: String,
    asset: IIcon,
    modifier: Modifier = Modifier
) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            asset,
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
            modifier = Modifier.size(24.dp)
        )
        Text(text = text, style = MaterialTheme.typography.caption)
    }
}
