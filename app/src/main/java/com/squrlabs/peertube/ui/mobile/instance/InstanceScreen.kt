package com.squrlabs.peertube.ui.mobile.instance

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.mikepenz.iconics.compose.ExperimentalIconics
import com.mikepenz.iconics.compose.Image
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial
import com.squrlabs.peertube.common.service.model.InstanceModel
import com.squrlabs.peertube.common.service.model.VideoModel
import com.squrlabs.peertube.ui.mobile.MobileViewModel
import com.squrlabs.peertube.ui.mobile.utils.MainInputText
import com.squrlabs.peertube.util.getViewModel
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
        topBar = {
            TopAppBar(
                title = {
                    if (!inSearchMode) {
                        Text(text = "Instances")
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
                                colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    } else {
                        IconButton(onClick = { instanceViewModel.switchToSearchMode(!inSearchMode) }) {
                            Image(
                                CommunityMaterial.Icon3.cmd_magnify,
                                colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        IconButton(onClick = { }) {
                            Image(
                                CommunityMaterial.Icon3.cmd_tune,
                                colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
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
        modifier = Modifier.fillMaxWidth().clickable(onClick = {onSelected(instance)})
            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = instance.name ?: "", style = MaterialTheme.typography.h6)
    }
    Divider()
}

@Composable
@Preview
fun InstanceListItemPreview(){
   Column {
       InstanceListItem(instance = InstanceModel(id=0, name="Peertube",
           host = "host", shortDescription = "Sample"))
   }
}
