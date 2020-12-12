package com.squrlabs.peertube.common

import com.russhwolf.settings.Settings

expect fun getApplicationFilesDirectoryPath(): String
expect fun createSettings(): Settings