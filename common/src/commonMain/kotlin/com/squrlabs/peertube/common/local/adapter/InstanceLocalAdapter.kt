package com.squrlabs.peertube.common.local.adapter

import com.squrlabs.peertube.common.local.dao.InstanceDao
import com.squrlabs.peertube.common.local.entity.InstanceEntity
import com.squrlabs.peertube.common.service.model.InstanceModel
import com.squrlabs.peertube.common.service.params.GetInstancesParams

class InstanceLocalAdapterImpl(private val dao: InstanceDao) : InstanceLocalAdapter {
    override suspend fun getInstances(params: GetInstancesParams): List<InstanceModel> {
        return dao.getInstances(params).map { it.mapToDomain() }
    }

    override suspend fun updateInstances(models: List<InstanceModel>) {
        dao.updateInstances(models.map { InstanceEntity.mapFromDomain(it) })
    }
}

interface InstanceLocalAdapter {
    suspend fun getInstances(params: GetInstancesParams): List<InstanceModel>
    suspend fun updateInstances(models: List<InstanceModel>)
}