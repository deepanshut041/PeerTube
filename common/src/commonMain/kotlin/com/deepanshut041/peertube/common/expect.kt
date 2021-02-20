package com.deepanshut041.peertube.common

import com.russhwolf.settings.Settings

expect fun getApplicationFilesDirectoryPath(): String
expect fun createSettings(): Settings