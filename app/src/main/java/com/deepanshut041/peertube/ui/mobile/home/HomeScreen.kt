package com.deepanshut041.peertube.ui.mobile.home

import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import com.deepanshut041.peertube.R
import com.deepanshut041.peertube.common.service.model.VideoModel
import com.deepanshut041.peertube.ui.MobileActions
import com.deepanshut041.peertube.ui.NavigationModel
import com.deepanshut041.peertube.ui.mobile.base.MainInputText
import com.deepanshut041.peertube.ui.mobile.base.TextIcon
import com.mikepenz.iconics.compose.ExperimentalIconics
import com.mikepenz.iconics.compose.Image
import com.mikepenz.iconics.typeface.IIcon
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

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

typealias BackToFirstTab = () -> Unit

val localBackToFirstTab = compositionLocalOf<BackToFirstTab> {
    error("Invalid backstack")
}

@ExperimentalIconics
@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    setVideoModel: (Long) -> Unit,
    navigateTo: (NavigationModel) -> Unit,
    viewModel: HomeViewModel = getViewModel()
) {
    val scope = rememberCoroutineScope()
    var currentTab by rememberSaveable { mutableStateOf(bottomItems[0].route) }
    val inSearchMode by viewModel.inSearchMode.collectAsState()
    val videoParams by viewModel.videoSearchParams.collectAsState()
    val drawerState = rememberBottomSheetScaffoldState()


    var localTimeline: LazyPagingItems<VideoModel>? = null
    var trendingTimeline: LazyPagingItems<VideoModel>? = null

    BottomSheetScaffold(
        scaffoldState = drawerState,
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopAppBar(
                title = {
                    if (!inSearchMode) {
                        Row {
                            CoilImage(
                                data = R.drawable.logo,
                                contentDescription = "Button",
                                modifier = Modifier
                                    .size(32.dp)
                                    .align(Alignment.CenterVertically)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "PeerTube", style = MaterialTheme.typography.h6)
                        }
                    } else {
                        MainInputText(
                            text = videoParams.query,
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
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.bottomSheetState.expand()
                            }
                        }) {
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
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(56.dp)) {
                IconButton(onClick = {
                    scope.launch {
                        drawerState.bottomSheetState.collapse()
                    }
                }) {
                    Image(
                        CommunityMaterial.Icon.cmd_close,
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Account", style = MaterialTheme.typography.h6)
            }
            Divider()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 20.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Do more with PeerTube",
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Sign up now to upload, save, and \n comment on videos",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = { }) {
                    Text(text = "SIGN IN")
                }
            }
            Divider()
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 15.dp)
                        .fillMaxWidth()
                        .clickable(onClick = { })
                ) {
                    Image(
                        CommunityMaterial.Icon.cmd_cog,
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Settings", style = MaterialTheme.typography.h6)
                }
                Row(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 15.dp)
                        .fillMaxWidth()
                        .clickable(onClick = { navigateTo(NavigationModel(path = MobileActions.navigateToInstances())) })
                ) {
                    Image(
                        CommunityMaterial.Icon.cmd_circle_multiple,
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Instances", style = MaterialTheme.typography.h6)
                }
            }
        }
    ) {
        Scaffold(
            bottomBar = {
                BottomNavigation(
                    backgroundColor = MaterialTheme.colors.background,
                    contentColor = MaterialTheme.colors.onBackground
                ) {
                    bottomItems.forEach { item ->
                        TextIcon(
                            text = item.title,
                            asset = if (currentTab == item.route) item.iconSelected else item.icon,
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .weight(1f)
                                .fillMaxHeight(1f)
                                .clickable(onClick = { currentTab = item.route })
                        )
                    }
                }
            }
        ) {
            CompositionLocalProvider(localBackToFirstTab provides {
                currentTab = bottomItems[0].route
            }) {
                TabContent(
                    route = currentTab,
                    setVideoModel = setVideoModel,
                    navigateTo = navigateTo,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun TabWrapper(
    navState: MutableState<Bundle>,
    content: @Composable (NavHostController) -> Unit
) {
    val navController = rememberNavController()
    val callback by rememberUpdatedState(
        NavController.OnDestinationChangedListener { _, _, _ ->
            navState.value = navController.saveState() ?: Bundle()
        }
    )
    DisposableEffect(navController) {
        navController.addOnDestinationChangedListener(callback)
        navController.restoreState(navState.value)
        onDispose {
            navController.removeOnDestinationChangedListener(callback)
            navController.enableOnBackPressed(false)
        }
    }
    content(navController)
}

@Composable
fun TabContent(
    route: String,
    setVideoModel: (Long) -> Unit,
    navigateTo: (NavigationModel) -> Unit,
    viewModel: HomeViewModel
) {
    val globalState = rememberSaveable { mutableStateOf(Bundle()) }
    val localState = rememberSaveable { mutableStateOf(Bundle()) }
    val trendingState = rememberSaveable { mutableStateOf(Bundle()) }
    val subscriptionstate = rememberSaveable { mutableStateOf(Bundle()) }
    val libraryState = rememberSaveable { mutableStateOf(Bundle()) }

    when (route) {
        bottomItems[0].route -> {
            TabWrapper(globalState) { navContoller ->
                HomeGlobalScreen(viewModel, setVideoModel, navigateTo)
            }
        }
        bottomItems[1].route -> {
            TabWrapper(localState) { navContoller ->
                HomeLocalScreen(viewModel, setVideoModel, navigateTo)
            }
        }
        bottomItems[2].route -> {
            TabWrapper(trendingState) { navContoller ->
                HomeTrendingScreen(viewModel, setVideoModel, navigateTo)
            }
        }
        bottomItems[3].route -> {
            TabWrapper(subscriptionstate) { navContoller ->
                HomeSubscriptionScreen(
                    viewModel,
                    setVideoModel
                )
            }
        }
        bottomItems[3].route -> {
            TabWrapper(libraryState) { navContoller -> HomeLibraryScreen(viewModel, setVideoModel) }
        }
    }
}