package com.squrlabs.peertube.util

import android.content.Context
import android.content.Intent

object AppNavigation {
    fun mobileMainActivity(context: Context): Intent? {
        return Intent().setClassName(context, "com.squrlabs.peertube.mobile.ui.main.MainActivity")
    }

    fun mobileInstanceActivity(context: Context): Intent? {
        return Intent().setClassName(context, "com.squrlabs.peertube.mobile.ui.instance.InstanceActivity")
    }

    fun tvMainActivity(context: Context): Intent? {
        return Intent().setClassName(context, "com.squrlabs.peertube.tv.ui.main.MainActivity")
    }

}
