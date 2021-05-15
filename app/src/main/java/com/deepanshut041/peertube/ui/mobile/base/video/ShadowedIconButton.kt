package com.deepanshut041.peertube.ui.mobile.base.video

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mikepenz.iconics.compose.Image
import com.mikepenz.iconics.typeface.IIcon


@Composable
fun ShadowedIcon(
    asset: IIcon,
    modifier: Modifier = Modifier,
    iconSize: Dp = 48.dp,
) {
    Box(modifier = modifier) {
        Image(
            asset,
            colorFilter = ColorFilter.tint(Color.Black.copy(alpha = 0.3f)),
            modifier = Modifier.size(iconSize).offset(2.dp, 2.dp).then(modifier)
        )
        Image(
            asset,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier.size(iconSize)
        )
    }
}