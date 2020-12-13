package com.squrlabs.peertube.common.remote.dto.video

import com.squrlabs.peertube.common.remote.DTO
import com.squrlabs.peertube.common.service.model.ScheduledUpdateModel
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScheduledUpdateDto(
    @SerialName("privacy")
    val privacy: Int?,
    @SerialName("updateAt")
    val updateAt: String?
) : DTO<ScheduledUpdateModel> {
    override fun mapToDomain() = ScheduledUpdateModel(
        privacy = privacy,
        updateAt = updateAt?.let { LocalDateTime.parse(it) }
    )
}