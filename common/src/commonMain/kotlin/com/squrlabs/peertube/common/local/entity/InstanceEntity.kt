package com.squrlabs.peertube.common.local.entity

import com.squrlabs.peertube.common.local.Entity
import com.squrlabs.peertube.common.service.model.InstanceModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.kodein.db.model.orm.Metadata

@Serializable
data class InstanceEntity(
    @SerialName("id") override val id: Long,
    @SerialName("name") val name:  String?=null,
    @SerialName("host") val host: String?=null,
    @SerialName("shortDescription") val shortDescription: String?=null,
    @SerialName("version") val version: String?=null,
    @SerialName("signupAllowed") val signupAllowed: Boolean?=null,
    @SerialName("userVideoQuota") val userVideoQuota: Long?=null,
    @SerialName("totalUsers") val totalUsers: Long?=null,
    @SerialName("totalVideos") val totalVideos: Long?=null,
    @SerialName("totalLocalVideos") val totalLocalVideos: Long?=null,
    @SerialName("totalInstanceFollowers") val totalInstanceFollowers: Long?=null,
    @SerialName("totalInstanceFollowing") val totalInstanceFollowing: Long?=null,
    @SerialName("supportsIPv6") val supportsIPv6: Boolean?=null,
    @SerialName("health") val health: Long?=null
): Entity<InstanceModel>, Metadata {
    override fun mapToDomain() = InstanceModel(
        id = id,
        name = name,
        host = host,
        shortDescription = shortDescription,
        version = version,
        signupAllowed = signupAllowed,
        userVideoQuota = userVideoQuota,
        totalUsers = totalUsers,
        totalVideos = totalVideos,
        totalLocalVideos = totalLocalVideos,
        totalInstanceFollowers = totalInstanceFollowers,
        totalInstanceFollowing = totalInstanceFollowing,
        supportsIPv6 = supportsIPv6,
        health = health
    )

    companion object {
        fun mapFromDomain(model:InstanceModel) = InstanceEntity(
            id = model.id,
            name = model.name,
            host = model.host,
            shortDescription = model.shortDescription,
            version = model.version,
            signupAllowed = model.signupAllowed,
            userVideoQuota = model.userVideoQuota,
            totalUsers = model.totalUsers,
            totalVideos = model.totalVideos,
            totalLocalVideos = model.totalLocalVideos,
            totalInstanceFollowers = model.totalInstanceFollowers,
            totalInstanceFollowing = model.totalInstanceFollowing,
            supportsIPv6 = model.supportsIPv6,
            health = model.health
        )
    }
}