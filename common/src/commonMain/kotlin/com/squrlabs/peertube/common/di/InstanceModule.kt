package com.squrlabs.peertube.common.di

import com.squrlabs.peertube.common.remote.adapter.InstanceRemoteAdapter
import com.squrlabs.peertube.common.remote.adapter.InstanceRemoteAdapterImpl
import com.squrlabs.peertube.common.remote.endpoints.InstanceEndpoints
import com.squrlabs.peertube.common.service.repository.InstanceRepository
import com.squrlabs.peertube.common.service.repository.InstanceRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val instanceModule = module {
    single { InstanceEndpoints(get()) }
    single<InstanceRemoteAdapter> { InstanceRemoteAdapterImpl(get()) }
    single<InstanceRepository> { InstanceRepositoryImpl(get(), get(named(DefaultDispatcher))) }
}