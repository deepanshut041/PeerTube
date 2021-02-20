package com.deepanshut041.peertube.common.service.model

import kotlinx.datetime.Instant

data class UserModel(
    val currentHost: String,
    val id: Int? = null,
    val username: String? = null,
    val email: String? = null,
    val displayNSFW: Boolean? = null,
    val autoPlayVideo: Boolean? = null,
    val role: Int? = null,
    val roleLabel: String? = null,
    val videoQuota: Int? = null,
    val videoQuotaDaily: Int? = null,
    val createdAt: Instant? = null,
    var account: AccountModel? = null,
    var videoChannels: List<ChannelModel>? = null
)