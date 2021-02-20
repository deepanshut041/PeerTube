package com.deepanshut041.peertube.common

import android.content.Context
import android.content.SharedPreferences
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings

lateinit var appContext: Context
lateinit var delegate: SharedPreferences

actual fun getApplicationFilesDirectoryPath(): String =
    appContext.filesDir.absolutePath

actual fun createSettings(): Settings = AndroidSettings(delegate)
