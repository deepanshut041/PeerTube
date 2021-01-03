package com.squrlabs.peertube.ui.mobile.home

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mikepenz.iconics.compose.Image
import com.mikepenz.iconics.typeface.IIcon
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial
import com.squrlabs.peertube.R
import com.squrlabs.peertube.common.service.model.VideoModel
import com.squrlabs.peertube.ui.mobile.MobileViewModel
import com.squrlabs.peertube.ui.mobile.utils.MainInputText
import com.squrlabs.peertube.ui.mobile.video.TextIcon
import com.squrlabs.peertube.util.getViewModel
import dev.chrisbanes.accompanist.coil.CoilImage

data class HomeBottomMenu(
    val route: String,
    val title: String,
    val icon: IIcon,
    val iconSelected: IIcon
)
val bottomItems = listOf(
    HomeBottomMenu(
        "global",
        "Global",
        CommunityMaterial.Icon.cmd_city_variant_outline,
        CommunityMaterial.Icon.cmd_city_variant
    ),
    HomeBottomMenu(
        "local",
        "Local",
        CommunityMaterial.Icon2.cmd_home_outline,
        CommunityMaterial.Icon2.cmd_home
    ),
    HomeBottomMenu(
        "trending",
        "Trending",
        CommunityMaterial.Icon.cmd_compass_outline,
        CommunityMaterial.Icon.cmd_compass
    ),
    HomeBottomMenu(
        "subscription",
        "Subscription",
        CommunityMaterial.Icon.cmd_card_outline,
        CommunityMaterial.Icon.cmd_card
    ),
    HomeBottomMenu(
        "library", "Library",
        CommunityMaterial.Icon3.cmd_play_box_multiple_outline,
        CommunityMaterial.Icon3.cmd_play_box_multiple
    )
)

@Composable
fun HomeScreen(
    mainViewModel: MobileViewModel = viewModel(),
    viewModel: HomeViewModel = getViewModel()
) {

    val selectedTab = remember { mutableStateOf(bottomItems[0]) }
    val inSearchMode by viewModel.inSearchMode.collectAsState()
    val videoParams by viewModel.videoSearchParams.collectAsState()

    var globalTimeline: LazyPagingItems<VideoModel>? = null
    var localTimeline: LazyPagingItems<VideoModel>? = null
    var trendingTimeline: LazyPagingItems<VideoModel>? = null

    Scaffold(
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
                            Text(text = "PeerTube")
                        }
                    } else {
                        MainInputText(
                            text = videoParams.query ?: "",
                            onTextChanged = viewModel::performSearch,
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = "Search..."
                        )
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
            Crossfade(current = selectedTab.value.route) {
                when (it) {
                    bottomItems[0].route -> {
                        if (globalTimeline == null){
                            globalTimeline = viewModel.globalTimeline.collectAsLazyPagingItems()
                        }
                        HomeGlobalScreen(mainViewModel, globalTimeline!!)
                    }
                    bottomItems[1].route -> {
                        if (localTimeline == null){
                            localTimeline = viewModel.localTimeline.collectAsLazyPagingItems()
                        }
                        HomeLocalScreen(mainViewModel, localTimeline!!)
                    }
                    bottomItems[2].route -> {
                        if (trendingTimeline == null){
                            trendingTimeline = viewModel.globalTimeline.collectAsLazyPagingItems()
                        }
                        HomeTrendingScreen(mainViewModel, trendingTimeline!!)
                    }
                    bottomItems[3].route -> HomeSubscriptionScreen(mainViewModel, viewModel)
                    bottomItems[3].route -> HomeLibraryScreen(mainViewModel, viewModel)
                }
            }
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground
            ) {
                bottomItems.forEach { item ->
                    TextIcon(
                        text = item.title,
                        asset = if (selectedTab.value == item) item.iconSelected else item.icon,
                        modifier = Modifier.padding(vertical = 8.dp).weight(1f).fillMaxHeight(1f)
                            .clickable(onClick = { selectedTab.value = item })
                    )
                }
            }
        }
    )
}
