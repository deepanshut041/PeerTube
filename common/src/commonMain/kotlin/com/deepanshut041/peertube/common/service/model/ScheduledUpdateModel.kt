package com.deepanshut041.peertube.common.service.model

import kotlinx.datetime.Instant

data class ScheduledUpdateModel(
    val privacy: Int? = null,
    val updateAt: Instant? = null
)