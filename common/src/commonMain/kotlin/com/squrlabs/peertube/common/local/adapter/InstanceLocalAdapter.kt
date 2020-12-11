package com.squrlabs.peertube.common.local.adapter

import com.squrlabs.peertube.common.local.dao.InstanceDao
import com.squrlabs.peertube.common.local.entity.InstanceEntity
import com.squrlabs.peertube.common.service.model.InstanceModel


class InstanceLocalAdapterImpl(private val dao: InstanceDao): InstanceLocalAdapter {
    override suspend fun getInstances(): List<InstanceModel> {
        return dao.getInstances().map { it.mapToDomain() }
    }

    override suspend fun updateInstances(models: List<InstanceModel>) {
        dao.updateInstances(models.map { InstanceEntity.mapFromDomain(it) })
    }
}
interface InstanceLocalAdapter {
    suspend fun getInstances(): List<InstanceModel>
    suspend fun updateInstances(models: List<InstanceModel>)
}