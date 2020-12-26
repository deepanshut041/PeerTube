package com.squrlabs.peertube.ui.mobile.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.compose.*
import com.mikepenz.iconics.compose.Image
import com.mikepenz.iconics.typeface.IIcon
import com.mikepenz.iconics.typeface.library.community.material.CommunityMaterial
import com.squrlabs.peertube.ui.mobile.MobileViewModel
import com.squrlabs.peertube.util.getViewModel

data class HomeBottomMenu(val route: String, val title: String, val icon: IIcon)

@Composable
fun HomeScreen(
    mainViewModel: MobileViewModel = viewModel(),
    viewModel: HomeViewModel = getViewModel()
) {
    val nestedNavController = rememberNavController()
    val bottomItems = listOf(
        HomeBottomMenu("global", "Global", CommunityMaterial.Icon3.cmd_web),
        HomeBottomMenu("local", "Local", CommunityMaterial.Icon2.cmd_home),
        HomeBottomMenu("trending", "Trending", CommunityMaterial.Icon3.cmd_trending_up),
        HomeBottomMenu("subscription", "Subscription", CommunityMaterial.Icon3.cmd_youtube_subscription),
        HomeBottomMenu("library", "Library", CommunityMaterial.Icon2.cmd_filmstrip_box_multiple)
    )

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "PeerTube") }) },
        bodyContent = {
            NavHost(navController = nestedNavController, startDestination = bottomItems[0].route) {
                composable(bottomItems[0].route) { HomeGlobalScreen(mainViewModel, viewModel) }
                composable(bottomItems[1].route) { HomeLocalScreen(mainViewModel, viewModel) }
                composable(bottomItems[2].route) { HomeTrendingScreen(mainViewModel, viewModel) }
                composable(bottomItems[3].route) { HomeSubscriptionScreen(mainViewModel, viewModel) }
                composable(bottomItems[4].route) { HomeLibraryScreen(mainViewModel, viewModel) }
            }
        },
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by nestedNavController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                bottomItems.forEach { item ->
                    BottomNavigationItem(
                        icon = {
                            Image(
                                item.icon,
                                colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary),
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        selected = currentRoute == item.route,
                        onClick = {
                            nestedNavController.navigate(item.route) {
                                popUpTo = nestedNavController.graph.startDestination
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    )
}
