package com.squrlabs.peertube.common.service.repository

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.squrlabs.peertube.common.createSettings
import com.squrlabs.peertube.common.local.adapter.InstanceLocalAdapter
import com.squrlabs.peertube.common.remote.adapter.InstanceRemoteAdapter
import com.squrlabs.peertube.common.service.Resource
import com.squrlabs.peertube.common.service.model.InstanceModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class InstanceRepositoryImpl(
    private val remote: InstanceRemoteAdapter,
    private val local: InstanceLocalAdapter,
    private val settings: Settings,
    private val coroutineDispatcher: CoroutineDispatcher
) : InstanceRepository {
    override fun getInstances(): Flow<Resource<List<InstanceModel>>> {
        return flow {
            emit(Resource.loading(data=local.getInstances()))
            try {
                val instances = remote.getInstances()
                local.updateInstances(instances)
                emit(Resource.success(local.getInstances()))
            } catch (e: Exception) {
                emit(Resource.error(e.toString(), emptyList<InstanceModel>()))
            }
        }.flowOn(coroutineDispatcher)
    }

    override fun getCurrentHost(): String? {
        return settings.getStringOrNull("current_host")
    }
}

interface InstanceRepository {
    fun getInstances(): Flow<Resource<List<InstanceModel>>>
    fun getCurrentHost(): String?
}