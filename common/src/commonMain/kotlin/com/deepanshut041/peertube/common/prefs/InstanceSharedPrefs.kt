package com.deepanshut041.peertube.common.prefs

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

class InstanceSharedPrefs(private val settings: Settings) {

    fun getCurrentHost() = settings.getString(INSTANCE_CURRENT_HOST, "")

    fun setCurrentHost(value: String) = settings.set(INSTANCE_CURRENT_HOST, value)

    companion object {
        private const val INSTANCE_CURRENT_HOST = "instance_current_host"
    }
}