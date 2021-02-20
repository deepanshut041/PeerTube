package com.deepanshut041.peertube.common.local.adapter

import com.deepanshut041.peertube.common.local.dao.InstanceDao
import com.deepanshut041.peertube.common.local.entity.InstanceEntity
import com.deepanshut041.peertube.common.service.model.InstanceModel
import com.deepanshut041.peertube.common.service.params.InstancesFilterParams

class InstanceLocalAdapterImpl(private val dao: InstanceDao) : InstanceLocalAdapter {
    override suspend fun getInstances(params: InstancesFilterParams): List<InstanceModel> {
        return dao.getInstances(params).map { it.mapToDomain() }
    }

    override suspend fun updateInstances(models: List<InstanceModel>) {
        dao.updateInstances(models.map { InstanceEntity.mapFromDomain(it) })
    }
}

interface InstanceLocalAdapter {
    suspend fun getInstances(params: InstancesFilterParams): List<InstanceModel>
    suspend fun updateInstances(models: List<InstanceModel>)
}