package com.squrlabs.peertube.ui.mobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.mikepenz.iconics.Iconics
import com.squrlabs.peertube.ui.mobile.instance.InstanceScreen
import com.squrlabs.peertube.ui.mobile.main.MainScreen
import com.squrlabs.peertube.util.PeerTubeTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MobileActivity : AppCompatActivity() {

    private val viewModel: MobileViewModel by viewModel()
    private lateinit var navController: NavHostController

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
        with(viewModel){
            navigate.observe(this@MobileActivity, {
                navController.navigate(it.path){
                    if (it.clearBackStack) {
                        popUpTo(navController.graph.startDestination){inclusive=true}
                    }
                    launchSingleTop=it.launchSingleTop
                }
            })
        }
    }

    @Composable
    fun MobileScreen(startDestination: String) {
        navController = rememberNavController()
        NavHost(navController = navController, startDestination = startDestination){
            composable("instances"){ InstanceScreen(viewModel) }
            composable("main") { MainScreen() }
        }
    }

    override fun onBackPressed() {
        navController.currentBackStackEntry?.let {
            super.onBackPressed()
        }?: run {
            finish()
        }
    }
}