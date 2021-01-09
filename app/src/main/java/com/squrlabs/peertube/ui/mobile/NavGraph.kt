package com.squrlabs.peertube.ui.mobile

object MainDestinations {
    const val INSTANCES_ROUTE = "instances"
    const val HOME_ROUTE = "home"
    const val CHANNEL_ROUTE = "channel"
    const val CHANNEL_ROUTE_ID_KEY = "channelId"
    const val BACK_ROUTE = "back_route"
}

object MainActions {
    fun navigateToInstances(): String  = MainDestinations.INSTANCES_ROUTE
    fun navigateToHome(): String  = MainDestinations.HOME_ROUTE
    fun navigateToChannel(channelId:Long): String = "${MainDestinations.CHANNEL_ROUTE}/$channelId"
    fun navigateBack(): String  = MainDestinations.BACK_ROUTE
}


data class NavigationModel(val path: String, val clearBackStack: Boolean=false, val launchSingleTop: Boolean=false)