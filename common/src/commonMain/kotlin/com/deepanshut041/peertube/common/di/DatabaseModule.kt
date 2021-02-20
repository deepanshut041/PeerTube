package com.deepanshut041.peertube.common.di

import com.deepanshut041.peertube.common.createSettings
import com.deepanshut041.peertube.common.getApplicationFilesDirectoryPath
import com.deepanshut041.peertube.common.local.entity.InstanceEntity
import com.deepanshut041.peertube.common.prefs.InstanceSharedPrefs
import org.kodein.db.DB
import org.kodein.db.TypeTable
import org.kodein.db.impl.factory
import org.kodein.db.inDir
import org.kodein.db.orm.kotlinx.KotlinxSerializer
import org.koin.dsl.module

val databaseModule = module {
    single {
        DB.factory
            .inDir(getApplicationFilesDirectoryPath())
            .open("peertube_db", TypeTable {
                root<InstanceEntity>()
            }, KotlinxSerializer())
    }

    single {
        createSettings()
    }

    single {
        InstanceSharedPrefs(get())
    }
}