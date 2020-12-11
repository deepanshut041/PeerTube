package com.squrlabs.peertube.common.di

import com.squrlabs.peertube.common.local.adapter.InstanceLocalAdapter
import com.squrlabs.peertube.common.local.adapter.InstanceLocalAdapterImpl
import com.squrlabs.peertube.common.local.dao.InstanceDao
import com.squrlabs.peertube.common.remote.adapter.InstanceRemoteAdapter
import com.squrlabs.peertube.common.remote.adapter.InstanceRemoteAdapterImpl
import com.squrlabs.peertube.common.remote.endpoints.InstanceEndpoints
import com.squrlabs.peertube.common.service.repository.InstanceRepository
import com.squrlabs.peertube.common.service.repository.InstanceRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val instanceModule = module {
    single { InstanceEndpoints(get()) }
    single { InstanceDao(get()) }
    single<InstanceRemoteAdapter> { InstanceRemoteAdapterImpl(get()) }
    single<InstanceLocalAdapter> { InstanceLocalAdapterImpl(get()) }
    single<InstanceRepository> { InstanceRepositoryImpl(get(),get(), get(named(DefaultDispatcher))) }
}