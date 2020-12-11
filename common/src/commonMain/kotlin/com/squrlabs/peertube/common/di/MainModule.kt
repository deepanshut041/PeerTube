package com.squrlabs.peertube.common.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        networkModule,
        coroutinesModule,
        databaseModule,
        instanceModule
    )
}

//used by ios etc.
fun initKoin() = initKoin {}