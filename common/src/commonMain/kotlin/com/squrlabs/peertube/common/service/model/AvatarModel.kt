package com.squrlabs.peertube.common.service.model

import kotlinx.datetime.Instant

data class AvatarModel(
    val path: String? = null,
    val createdAt: Instant? = null,
    val updatedAt: Instant? = null
)