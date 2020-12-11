package com.squrlabs.peertube.common.local.dao

import com.squrlabs.peertube.common.local.entity.InstanceEntity
import org.kodein.db.*

class InstanceDao(private val db:DB) {
    suspend fun getInstances(): List<InstanceEntity> {
        return db.find<InstanceEntity>().all().useModels{
            it.toList()
        }
    }

    suspend fun updateInstances(instances: List<InstanceEntity>){
        val oldEntities = db.find<InstanceEntity>().all()
        db.execBatch{
            deleteAll(oldEntities)
            instances.map {
                put(it)
            }
        }
    }
}