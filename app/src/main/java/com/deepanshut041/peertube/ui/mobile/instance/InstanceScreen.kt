package com.deepanshut041.peertube.ui.mobile.instance

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikepenz.iconics.compose.Image
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial
import com.deepanshut041.peertube.R
import com.deepanshut041.peertube.common.service.model.InstanceModel
import com.deepanshut041.peertube.common.service.params.InstancesFilterParams
import com.deepanshut041.peertube.ui.mobile.base.MainInputText
import com.deepanshut041.peertube.util.getViewModel
import com.mikepenz.iconics.compose.ExperimentalIconics
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.FlowPreview

@ExperimentalIconics
@FlowPreview
@Composable
fun InstanceScreen(
    setCurrentHost: (InstanceModel) -> Unit,
    instanceViewModel: InstanceViewModel = getViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val inSearchMode by instanceViewModel.inSearchMode.collectAsState()
    val instances by instanceViewModel.instances.collectAsState()
    val instanceParams by instanceViewModel.instanceParams.collectAsState()
    var showFilter by remember { mutableStateOf(false) }

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
                                contentDescription = "Logo",
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
                        IconButton(onClick = { showFilter = true }) {
                            Image(
                                CommunityMaterial.Icon3.cmd_tune,
                                colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                },
                backgroundColor = MaterialTheme.colors.background,
            )
        },
        bodyContent = {
            LazyColumn {
                this.items(items = instances, itemContent = { instance ->
                    InstanceListItem(instance, setCurrentHost)
                })
            }
            if (showFilter) FilterDialog(
                closeDialog = { showFilter = false },
                instanceParams,
                instanceViewModel::updateParams
            )

        }
    )
}

@Composable
private fun InstanceListItem(instance: InstanceModel, onSelected: (InstanceModel) -> Unit = {}) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable(onClick = { onSelected(instance) })
            .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 12.dp)
    ) {
        Column(modifier = Modifier.width(50.dp)) {
            CoilImage(
                data = instance.country?.let { "https://www.countryflags.io/${it}/shiny/32.png" }
                    ?: "",
                contentDescription="Flag Icon",
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

            Row(
                modifier = Modifier.padding(top = 8.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
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

@ExperimentalIconics
@Composable
private fun FilterDialog(
    closeDialog: () -> Unit = {},
    params: InstancesFilterParams,
    update: (String?, Int?, Int?, Int?) -> Unit
) {
    var showSortBy by remember { mutableStateOf(false) }
    var showHealthy by remember { mutableStateOf(false) }
    var showSignup by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = closeDialog,
        title = {
            Text(
                text = "Sort Filter",
                style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
            )
        },
        text = {
            Column(modifier = Modifier.background(color = MaterialTheme.colors.background)) {
                FilterRow(
                    title = "Sort By",
                    value = params.sort ?: "Relevance",
                    items = listOf("Relevance", "Videos", "Following", "Followers", "Users"),
                    showMenu = showSortBy,
                    toggleMenu = { showSortBy = it },
                    indexSelected = { update(null, it, null, null) }
                )
                FilterRow(
                    title = "Healthy",
                    value = params.healthy?.let { if (it) "Healthy" else "Unhealthy" }
                        ?: "All",
                    items = listOf("All", "Healthy", "Unhealthy"),
                    showMenu = showHealthy,
                    toggleMenu = { showHealthy = it },
                    indexSelected = { update(null, null, it, null) }
                )
                FilterRow(
                    title = "Signup Allowed",
                    value = params.signupAllowed?.let { if (it) "Yes" else "No" } ?: "All",
                    items = listOf("All", "Yes", "No"),
                    showMenu = showSignup,
                    toggleMenu = { showSignup = it },
                    indexSelected = { update(null, null, null, it) }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = closeDialog) { Text("Done") }
        },
        dismissButton = {
            TextButton(onClick = {
                update(null, 0, 0, 0)
                closeDialog()
            }) { Text("Clear") }
        }
    )
}

@ExperimentalIconics
@Composable
fun FilterRow(
    title: String,
    value: String,
    items: List<String>,
    showMenu: Boolean,
    toggleMenu: (Boolean) -> Unit,
    indexSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1.copy(
                fontSize = 11.sp,
                fontWeight = FontWeight.Light
            ),
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.width(100.dp)
        )
        Box{
            Text(
                text = value,
                style = MaterialTheme.typography.body1.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                ),
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)
                    .clickable(onClick = { toggleMenu(true) })
            )
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { toggleMenu(false) },
                modifier = Modifier.fillMaxWidth()
            ) {
                items.forEachIndexed { index, s ->
                    DropdownMenuItem(onClick = {
                        toggleMenu(false)
                        indexSelected(index)
                    }) {
                        Text(text = s)
                    }
                }
            }
        }

        Image(
            CommunityMaterial.Icon.cmd_chevron_down,
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
            modifier = Modifier.size(20.dp).clickable(onClick = { toggleMenu(true) })
        )
    }
}

