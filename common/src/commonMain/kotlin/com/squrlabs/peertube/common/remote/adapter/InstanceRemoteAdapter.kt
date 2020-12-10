package com.squrlabs.peertube.common.remote.adapter

import com.squrlabs.peertube.common.remote.endpoints.InstanceEndpoints
import com.squrlabs.peertube.common.service.model.InstanceModel


class InstanceRemoteAdapterImpl(private val instanceEndpoints: InstanceEndpoints): InstanceRemoteAdapter {

    override suspend fun getInstances(): List<InstanceModel> {
        return instanceEndpoints.getInstances().data.map { it.mapToDomain() }
    }
}
interface InstanceRemoteAdapter {
    suspend fun getInstances(): List<InstanceModel>
}