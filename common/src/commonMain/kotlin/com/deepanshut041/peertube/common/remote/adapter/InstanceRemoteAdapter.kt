package com.deepanshut041.peertube.common.remote.adapter

import com.deepanshut041.peertube.common.prefs.InstanceSharedPrefs
import com.deepanshut041.peertube.common.remote.endpoints.InstanceEndpoints
import com.deepanshut041.peertube.common.service.model.InstanceModel

class InstanceRemoteAdapterImpl(
    private val instanceEndpoints: InstanceEndpoints,
    private val prefs: InstanceSharedPrefs
    ) : InstanceRemoteAdapter {
    override suspend fun fetchInstances(): List<InstanceModel> {
        return instanceEndpoints.fetchInstances().data.map { it.mapToDomain(prefs.getCurrentHost()) }
    }
}

interface InstanceRemoteAdapter {
    suspend fun fetchInstances(): List<InstanceModel>
}