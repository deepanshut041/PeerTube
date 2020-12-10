package com.squrlabs.peertube.common.service.repository

import com.squrlabs.peertube.common.remote.adapter.InstanceRemoteAdapter
import com.squrlabs.peertube.common.service.Resource
import com.squrlabs.peertube.common.service.model.InstanceModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class InstanceRepositoryImpl(
    private val instanceRemoteAdapter: InstanceRemoteAdapter,
    private val coroutineDispatcher: CoroutineDispatcher
) : InstanceRepository {
    override suspend fun getInstances(): Resource<List<InstanceModel>> {
        return try {
            withContext(coroutineDispatcher) {
                Resource.success(instanceRemoteAdapter.getInstances())
            }
        } catch (e: Exception) {
            Resource.error(e.toString())
        }
    }
}

interface InstanceRepository {
    suspend fun getInstances(): Resource<List<InstanceModel>>
}