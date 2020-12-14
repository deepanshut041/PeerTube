package com.squrlabs.peertube.common.service.model

import kotlinx.datetime.Instant

data class ChannelModel(
    val id: Long? = null,
    val uuid: String? = null,
    val url: String? = null,
    val name: String? = null,
    val host: String? = null,
    val hostRedundancyAllowed: Boolean? = null,
    val followingCount: Long? = null,
    val followersCount: Long? = null,
    val avatar: AvatarModel? = null,
    val createdAt: Instant? = null,
    val updatedAt: Instant? = null,
    val displayName: String? = null,
    val description: String? = null,
    val support: String? = null,
    val isLocal: Boolean? = null,
)
