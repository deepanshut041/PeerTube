package com.squrlabs.peertube.common.service.model

import kotlinx.datetime.LocalDateTime

data class AvatarModel(
    val path: String? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)