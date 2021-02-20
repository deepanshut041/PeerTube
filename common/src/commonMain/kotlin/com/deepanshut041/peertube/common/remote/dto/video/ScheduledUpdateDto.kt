package com.deepanshut041.peertube.common.remote.dto.video

import com.deepanshut041.peertube.common.remote.DTO
import com.deepanshut041.peertube.common.service.model.ScheduledUpdateModel
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduledUpdateDto(
    @SerialName("privacy")
    val privacy: Int?,
    @SerialName("updateAt")
    val updateAt: String?
) : DTO<ScheduledUpdateModel> {
    override fun mapToDomain(currentHost: String): ScheduledUpdateModel = ScheduledUpdateModel(
        privacy = privacy,
        updateAt = updateAt?.let { Instant.parse(it) }
    )
}