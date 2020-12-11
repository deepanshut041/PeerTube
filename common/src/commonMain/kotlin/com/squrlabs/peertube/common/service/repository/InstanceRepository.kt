package com.squrlabs.peertube.common.service.repository

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
}

interface InstanceRepository {
    fun getInstances(): Flow<Resource<List<InstanceModel>>>
}