package com.deepanshut041.peertube

import android.app.Application
import androidx.preference.PreferenceManager
import com.mikepenz.iconics.Iconics
import com.deepanshut041.peertube.common.appContext
import com.deepanshut041.peertube.common.delegate
import com.deepanshut041.peertube.common.di.initKoin
import com.deepanshut041.peertube.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        delegate = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        Iconics.init(applicationContext)
        initKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(viewModelModule))
        }
    }
}