package com.squrlabs.peertube.common.local.dao

import com.squrlabs.peertube.common.local.entity.InstanceEntity
import com.squrlabs.peertube.common.service.params.GetInstancesParams
import org.kodein.db.*

class InstanceDao(private val db: DB) {
    suspend fun getInstances(params: GetInstancesParams): List<InstanceEntity> {
        return db.find<InstanceEntity>().all().useModels {
            it.toList()
        }.filter { model ->
            params.text?.let {
                return@filter model.name!!.contains(it, true)
            }
            true
        }
    }

    suspend fun updateInstances(instances: List<InstanceEntity>) {
        val oldEntities = db.find<InstanceEntity>().all()
        db.execBatch {
            deleteAll(oldEntities)
            instances.map {
                put(it)
            }
        }
    }
}