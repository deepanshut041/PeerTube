package com.squrlabs.peertube.common.remote.dto.others

import com.squrlabs.peertube.common.remote.DTO
import com.squrlabs.peertube.common.service.model.InstanceModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InstanceDto(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String? = null,
    @SerialName("host") val lhost: String? = null,
    @SerialName("shortDescription") val shortDescription: String? = null,
    @SerialName("version") val version: String? = null,
    @SerialName("signupAllowed") val signupAllowed: Boolean? = null,
    @SerialName("userVideoQuota") val userVideoQuota: Long? = null,
    @SerialName("totalUsers") val totalUsers: Long? = null,
    @SerialName("totalVideos") val totalVideos: Long? = null,
    @SerialName("country") val country: String? = null,
    @SerialName("totalLocalVideos") val totalLocalVideos: Long? = null,
    @SerialName("totalInstanceFollowers") val totalInstanceFollowers: Long? = null,
    @SerialName("totalInstanceFollowing") val totalInstanceFollowing: Long? = null,
    @SerialName("supportsIPv6") val supportsIPv6: Boolean? = null,
    @SerialName("health") val health: Long? = null
) : DTO<InstanceModel> {
    override fun mapToDomain() = InstanceModel(
        id = id,
        name = name,
        host = lhost,
        shortDescription = shortDescription,
        version = version,
        signupAllowed = signupAllowed,
        userVideoQuota = userVideoQuota,
        totalUsers = totalUsers,
        totalVideos = totalVideos,
        country = country,
        totalLocalVideos = totalLocalVideos,
        totalInstanceFollowers = totalInstanceFollowers,
        totalInstanceFollowing = totalInstanceFollowing,
        supportsIPv6 = supportsIPv6,
        health = health
    )
}