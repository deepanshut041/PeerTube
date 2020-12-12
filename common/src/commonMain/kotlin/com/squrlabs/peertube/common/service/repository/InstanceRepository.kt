package com.squrlabs.peertube.common.service.repository

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import com.squrlabs.peertube.common.local.adapter.InstanceLocalAdapter
import com.squrlabs.peertube.common.remote.adapter.InstanceRemoteAdapter
import com.squrlabs.peertube.common.service.Resource
import com.squrlabs.peertube.common.service.model.InstanceModel
import com.squrlabs.peertube.common.service.params.GetInstancesParams
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class InstanceRepositoryImpl(
    private val remote: InstanceRemoteAdapter,
    private val local: InstanceLocalAdapter,
    private val settings: Settings,
    private val coroutineDispatcher: CoroutineDispatcher
) : InstanceRepository {

    override suspend fun fetchInstances(): Resource<Unit> {
        return try {
            withContext(coroutineDispatcher) {
                val instances = remote.fetchInstances()
                local.updateInstances(instances)
                Resource.success()
            }
        } catch (e: Exception) {
            Resource.error(e.toString())
        }
    }

    override suspend fun getInstances(params: GetInstancesParams): Resource<List<InstanceModel>> {
        return withContext(coroutineDispatcher) {
            Resource.success(data = local.getInstances(params))
        }
    }

    override fun getCurrentHost(): String? {
        return settings.getStringOrNull("current_host")
    }

    override fun setCurrentHost(host: String) {
        settings["current_host"] = host
    }

    override fun getCurrentInstance(): InstanceModel? {
        return null
    }
}

interface InstanceRepository {
    suspend fun fetchInstances(): Resource<Unit>
    suspend fun getInstances(params: GetInstancesParams): Resource<List<InstanceModel>>
    fun getCurrentHost(): String?
    fun getCurrentInstance(): InstanceModel?
    fun setCurrentHost(host: String)
}