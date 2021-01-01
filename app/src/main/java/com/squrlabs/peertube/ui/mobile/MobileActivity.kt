package com.squrlabs.peertube.ui.mobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.setContent
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.mikepenz.iconics.Iconics
import com.squrlabs.peertube.ui.mobile.MainDestinations.CHANNEL_ROUTE_ID_KEY
import com.squrlabs.peertube.ui.mobile.channel.ChannelScreen
import com.squrlabs.peertube.ui.mobile.instance.InstanceScreen
import com.squrlabs.peertube.ui.mobile.home.HomeScreen
import com.squrlabs.peertube.ui.mobile.video.VideoOverlayPlayer
import com.squrlabs.peertube.util.theme.PeerTubeTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MobileActivity : AppCompatActivity() {

    private val viewModel: MobileViewModel by viewModel()
    private lateinit var navController: NavHostController

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Iconics.init(this)
        setContent {
            PeerTubeTheme {
                MobileScreen(viewModel.getStartDestination())
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        with(viewModel) {
            navigate.observe(this@MobileActivity, {
                if (it.path == MainDestinations.BACK_ROUTE)
                    onBackPressed()
                else
                    navController.navigate(it.path) {
                        if (it.clearBackStack) {
                            popUpTo(navController.graph.startDestination) { inclusive = true }
                        }
                        launchSingleTop = it.launchSingleTop
                    }
            })
        }
    }

    @ExperimentalAnimationApi
    @Composable
    fun MobileScreen(startDestination: String) {
        navController = rememberNavController()
        val videoOverlay by viewModel.videoOverlay.collectAsState()
        Box {
            NavHost(navController = navController, startDestination = startDestination) {
                composable(MainDestinations.INSTANCES_ROUTE) { InstanceScreen(viewModel) }
                composable(MainDestinations.HOME_ROUTE) { HomeScreen(viewModel) }
                composable("${MainDestinations.CHANNEL_ROUTE}/{$CHANNEL_ROUTE_ID_KEY}",
                    arguments = listOf(navArgument(CHANNEL_ROUTE_ID_KEY) { type = NavType.LongType })
                ) {
                    ChannelScreen(viewModel, it.arguments?.getLong(CHANNEL_ROUTE_ID_KEY)?: 0)
                }
            }
            videoOverlay?.let {
                VideoOverlayPlayer(videoId = it, requestClose = {
                    viewModel.setVideoModel(null)
                })
            }
        }
    }

    override fun onBackPressed() {
        navController.currentBackStackEntry?.let { super.onBackPressed() } ?: run { finish() }
    }
}