package com.deepanshut041.peertube.ui

object MobileDestinations {
    const val INSTANCES_ROUTE = "instances"
    const val HOME_ROUTE = "home"
    const val CHANNEL_ROUTE = "channel"
    const val CHANNEL_ROUTE_ID_KEY = "channelId"
    const val BACK_ROUTE = "back_route"
}

object MobileActions {
    fun navigateToInstances(): String  = MobileDestinations.INSTANCES_ROUTE
    fun navigateToHome(): String  = MobileDestinations.HOME_ROUTE
    fun navigateToChannel(channelId:Long): String = "${MobileDestinations.CHANNEL_ROUTE}/$channelId"
    fun navigateBack(): String  = MobileDestinations.BACK_ROUTE
}

object TvDestinations {
    const val INSTANCES_ROUTE = "instances"
    const val HOME_ROUTE = "home"
    const val CHANNEL_ROUTE = "channel"
    const val VIDEO_ROUTE = "video"
    const val VIDEO_ROUTE_ID_KEY = "videoId"
    const val BACK_ROUTE = "back_route"
}

object TvNavigationActions {
    fun navigateToInstances(): String  = TvDestinations.INSTANCES_ROUTE
    fun navigateToHome(): String  = TvDestinations.HOME_ROUTE
    fun navigateToChannel(channelId:Long): String = "${TvDestinations.CHANNEL_ROUTE}/$channelId"
    fun navigateToVideo(videoId:Long): String = "${TvDestinations.VIDEO_ROUTE}/$videoId"
    fun navigateBack(): String  = MobileDestinations.BACK_ROUTE
}


data class NavigationModel(val path: String, val clearBackStack: Boolean=false, val launchSingleTop: Boolean=false)