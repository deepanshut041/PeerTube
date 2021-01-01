package com.squrlabs.peertube.ui.mobile.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.compose.*
import com.mikepenz.iconics.compose.Image
import com.mikepenz.iconics.typeface.IIcon
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial
import com.squrlabs.peertube.R
import com.squrlabs.peertube.ui.mobile.MobileViewModel
import com.squrlabs.peertube.util.getViewModel
import dev.chrisbanes.accompanist.coil.CoilImage

data class HomeBottomMenu(val route: String, val title: String, val icon: IIcon)

@Composable
fun HomeScreen(
    mainViewModel: MobileViewModel = viewModel(),
    viewModel: HomeViewModel = getViewModel()
) {
    val bottomItems = listOf(
        HomeBottomMenu("global", "Global", CommunityMaterial.Icon3.cmd_web),
        HomeBottomMenu("local", "Local", CommunityMaterial.Icon2.cmd_home),
        HomeBottomMenu("trending", "Trending", CommunityMaterial.Icon3.cmd_trending_up),
        HomeBottomMenu("subscription", "Subscription", CommunityMaterial.Icon3.cmd_youtube_subscription),
        HomeBottomMenu("library", "Library", CommunityMaterial.Icon2.cmd_filmstrip_box_multiple)
    )

    val selectedTab = remember { mutableStateOf(bottomItems[0]) }
    val inSearchMode by viewModel.inSearchMode.collectAsState()

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        CoilImage(
                            data = R.drawable.logo,
                            modifier = Modifier.size(32.dp).align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.preferredWidth(10.dp))
                        Text(text = "PeerTube")
                    }
                },
                actions = {
                    if (inSearchMode) {
                        IconButton(onClick = { viewModel.switchToSearchMode(!inSearchMode) }) {
                            Image(
                                CommunityMaterial.Icon.cmd_close,
                                colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    } else {
                        IconButton(onClick = { viewModel.switchToSearchMode(!inSearchMode) }) {
                            Image(
                                CommunityMaterial.Icon3.cmd_magnify,
                                colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        IconButton(onClick = { }) {
                            Image(
                                CommunityMaterial.Icon.cmd_account_circle,
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
            when(selectedTab.value.route) {
                bottomItems[0].route -> HomeGlobalScreen(mainViewModel, viewModel)
                bottomItems[1].route -> HomeLocalScreen(mainViewModel, viewModel)
                bottomItems[2].route -> HomeTrendingScreen(mainViewModel, viewModel)
                bottomItems[3].route -> HomeSubscriptionScreen(mainViewModel, viewModel)
                bottomItems[3].route -> HomeLibraryScreen(mainViewModel, viewModel)
            }
        },
        bottomBar = {
            BottomNavigation(backgroundColor=MaterialTheme.colors.background, contentColor = MaterialTheme.colors.onBackground) {
                bottomItems.forEach { item ->
                    BottomNavigationItem(
                        icon = {
                            Image(
                                item.icon,
                                modifier = Modifier.size(24.dp),
                                colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground)
                            )
                        },
                        selected = selectedTab.value.route == item.route,
                        label = {
                            Text(
                                item.title,
                                style = MaterialTheme.typography.caption.copy(fontSize = 9.sp)
                            )
                        },
                        onClick = { selectedTab.value = item },
                        alwaysShowLabels = true
                    )
                }
            }
        }
    )
}
