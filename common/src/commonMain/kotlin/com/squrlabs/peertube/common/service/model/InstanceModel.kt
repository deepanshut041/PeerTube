package com.squrlabs.peertube.common.service.model

data class InstanceModel(
    val id: Long,
    val name: String?=null,
    val host: String?=null,
    val shortDescription: String?=null,
    val version: String?=null,
    val signupAllowed: Boolean?=null,
    val userVideoQuota: Long?=null,
    val totalUsers: Long?=null,
    val totalVideos: Long?=null,
    val country: String?=null,
    val totalLocalVideos: Long?=null,
    val totalInstanceFollowers: Long?=null,
    val totalInstanceFollowing: Long?=null,
    val supportsIPv6: Boolean?=null,
    val health: Long?=null
)