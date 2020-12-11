package com.squrlabs.peertube.common.service.model

data class InstanceModel(
    val id: Long,
    val name:  String?,
    val host: String?,
    val shortDescription: String?,
    val version: String?,
    val signupAllowed: Boolean?,
    val userVideoQuota: Long?,
    val totalUsers: Long?,
    val totalVideos: Long?,
    val totalLocalVideos: Long?,
    val totalInstanceFollowers: Long?,
    val totalInstanceFollowing: Long?,
    val supportsIPv6: Boolean?,
    val health: Long?
)