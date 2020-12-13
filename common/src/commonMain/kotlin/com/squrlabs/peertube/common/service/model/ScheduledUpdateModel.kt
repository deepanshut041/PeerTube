package com.squrlabs.peertube.common.service.model

import kotlinx.datetime.LocalDateTime

data class ScheduledUpdateModel(
    val privacy: Int? = null,
    val updateAt: LocalDateTime? = null
)