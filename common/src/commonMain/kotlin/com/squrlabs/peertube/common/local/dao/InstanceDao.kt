package com.squrlabs.peertube.common.local.dao

import com.squrlabs.peertube.common.local.entity.InstanceEntity
import com.squrlabs.peertube.common.service.params.InstancesFilterParams
import org.kodein.db.*

class InstanceDao(private val db: DB) {
    suspend fun getInstances(params: InstancesFilterParams): List<InstanceEntity> {
        return db.find<InstanceEntity>().all().useModels {
            it.toList()
        }.filter { model ->
            params.text?.let {
                return@filter model.name!!.contains(it, true)
            }
            true
        }.filter { model ->
            params.signupAllowed?.let {
                return@filter model.signupAllowed!! == it
            }
            true
        }.filter { model ->
            params.healthy?.let {
                if (it)
                    return@filter model.health!! >= 90
                else
                    return@filter model.health!! <= 90
            }
            true
        }.sortedBy { instance ->
            params.sort?.let {
                when(it){
                    "Videos" -> return@sortedBy instance.totalVideos
                    "Following" -> return@sortedBy instance.totalInstanceFollowing
                    "Followers" -> return@sortedBy instance.totalInstanceFollowers
                    "Users" -> return@sortedBy instance.totalUsers
                    else -> return@sortedBy 1L - instance.id
                }
            }?: run { return@sortedBy 1L - instance.id }
        }.asReversed()
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