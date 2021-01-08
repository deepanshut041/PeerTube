package com.squrlabs.peertube.ui.mobile.instance

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.viewModel
import com.mikepenz.iconics.compose.ExperimentalIconics
import com.mikepenz.iconics.compose.Image
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial
import com.squrlabs.peertube.R
import com.squrlabs.peertube.common.service.model.InstanceModel
import com.squrlabs.peertube.common.service.model.VideoModel
import com.squrlabs.peertube.ui.mobile.MobileViewModel
import com.squrlabs.peertube.ui.mobile.utils.MainInputText
import com.squrlabs.peertube.ui.mobile.video.TextIcon
import com.squrlabs.peertube.util.getViewModel
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.FlowPreview

@ExperimentalIconics
@FlowPreview
@Composable
fun InstanceScreen(
    mainViewModel: MobileViewModel = viewModel(),
    instanceViewModel: InstanceViewModel = getViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val inSearchMode by instanceViewModel.inSearchMode.collectAsState()
    val instances by instanceViewModel.instances.collectAsState()
    val instanceParams by instanceViewModel.instanceParams.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopAppBar(
                title = {
                    if (!inSearchMode) {
                        Row {
                            CoilImage(
                                data = R.drawable.logo,
                                modifier = Modifier.size(32.dp).align(Alignment.CenterVertically)
                            )
                            Spacer(modifier = Modifier.preferredWidth(10.dp))
                            Text(text = "Instances")
                        }
                    } else {
                        MainInputText(
                            text = instanceParams.text ?: "",
                            onTextChanged = instanceViewModel::performSearch,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = "Search..."
                        )
                    }
                },
                actions = {
                    if (inSearchMode) {
                        IconButton(onClick = { instanceViewModel.switchToSearchMode(!inSearchMode) }) {
                            Image(
                                CommunityMaterial.Icon.cmd_close,
                                colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    } else {
                        IconButton(onClick = { instanceViewModel.switchToSearchMode(!inSearchMode) }) {
                            Image(
                                CommunityMaterial.Icon3.cmd_magnify,
                                colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        IconButton(onClick = { }) {
                            Image(
                                CommunityMaterial.Icon3.cmd_tune,
                                colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                },
                backgroundColor = MaterialTheme.colors.background
            )
        },
        bodyContent = {
            LazyColumn {
                items(items = instances, itemContent = { instance ->
                    InstanceListItem(instance, mainViewModel::setCurrentHost)
                })
            }
        }
    )
}

@Composable
fun InstanceListItem(instance: InstanceModel, onSelected: (InstanceModel) -> Unit = {}) {
    val context = AmbientContext.current

    Row(
        modifier = Modifier.fillMaxWidth().clickable(onClick = { onSelected(instance) })
            .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 12.dp)
    ) {
        Column(modifier = Modifier.width(50.dp)) {
            CoilImage(
                data = instance.country?.let { "https://www.countryflags.io/${it}/shiny/32.png" }
                    ?: "",
                loading = {
                    Box(
                        Modifier.width(32.dp).height(32.dp)
                            .background(MaterialTheme.colors.secondary)
                    )
                },
                error = {
                    Box(
                        Modifier.width(32.dp).height(32.dp)
                            .background(MaterialTheme.colors.secondary)
                    )
                },
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.width(32.dp).height(32.dp)
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = instance.host ?: "",
                style = MaterialTheme.typography.overline
            )
            Text(
                text = instance.name ?: "",
                style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = instance.shortDescription ?: "",
                maxLines = 2,
                style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Light)
            )

            Row(modifier = Modifier.padding(top = 8.dp).fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(
                    text = "${instance.totalVideos} videos",
                    style = MaterialTheme.typography.caption.copy(fontSize = 10.sp),
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.border(
                        border = BorderStroke(.5.dp, MaterialTheme.colors.primary),
                        shape = RoundedCornerShape(50)
                    ).padding(horizontal = 5.dp, vertical = 3.dp)
                )
                Text(
                    text = "${instance.totalInstanceFollowing} Following",
                    style = MaterialTheme.typography.caption.copy(fontSize = 10.sp),
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.border(
                        border = BorderStroke(.5.dp, MaterialTheme.colors.primary),
                        shape = RoundedCornerShape(50)
                    ).padding(horizontal = 5.dp, vertical = 3.dp)
                )
                Text(
                    text = "${instance.totalInstanceFollowers} Followers",
                    style = MaterialTheme.typography.caption.copy(fontSize = 10.sp),
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.border(
                        border = BorderStroke(.5.dp, MaterialTheme.colors.primary),
                        shape = RoundedCornerShape(50)
                    ).padding(horizontal = 5.dp, vertical = 3.dp)
                )
                Text(
                    text = "${instance.totalUsers} Users",
                    style = MaterialTheme.typography.caption.copy(fontSize = 10.sp),
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.border(
                        border = BorderStroke(.5.dp, MaterialTheme.colors.primary),
                        shape = RoundedCornerShape(50)
                    ).padding(horizontal = 5.dp, vertical = 3.dp)
                )
            }
        }
    }
    Divider()
}
