package com.squrlabs.peertube.ui.mobile.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.mikepenz.iconics.compose.Image
import com.mikepenz.iconics.typeface.IIcon

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