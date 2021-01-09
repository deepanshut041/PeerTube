package com.squrlabs.peertube.ui.tv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.squrlabs.peertube.ui.MobileDestinations
import com.squrlabs.peertube.util.theme.PeerTubeTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvActivity : AppCompatActivity() {

    private val viewModel: TvViewModel by viewModel()
    private lateinit var navController: NavHostController

    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PeerTubeTheme {
                TvScreen(viewModel.getStartDestination())
            }
        }
    }

    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    @Composable
    fun TvScreen(startDestination: String) {
//        navController = rememberNavController()
        Text(text = "Hello")

//        NavHost(navController = navController, startDestination = startDestination) {
//            composable(MobileDestinations.INSTANCES_ROUTE) { InstanceScreen(viewModel::setCurrentHost) }
//            composable(MobileDestinations.HOME_ROUTE) { HomeScreen(viewModel::setVideoModel, viewModel::navigateTo) }
//            composable("${MobileDestinations.CHANNEL_ROUTE}/{${MobileDestinations.CHANNEL_ROUTE_ID_KEY}}",
//                arguments = listOf(navArgument(MobileDestinations.CHANNEL_ROUTE_ID_KEY) { type = NavType.LongType })
//            ) {
//                ChannelScreen(it.arguments?.getLong(MobileDestinations.CHANNEL_ROUTE_ID_KEY)?: 0)
//            }
//        }
    }

}