package com.squrlabs.peertube.common.remote.dto.video

import com.squrlabs.peertube.common.remote.DTO
import com.squrlabs.peertube.common.service.model.UserHistoryModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserHistoryDto(
    @SerialName("currentTime") val currentTime: Int?
) : DTO<UserHistoryModel> {
    override fun mapToDomain(host:String?) = UserHistoryModel(currentTime = currentTime)
}