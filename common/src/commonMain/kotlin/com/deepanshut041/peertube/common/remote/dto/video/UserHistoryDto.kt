package com.deepanshut041.peertube.common.remote.dto.video

import com.deepanshut041.peertube.common.remote.DTO
import com.deepanshut041.peertube.common.service.model.UserHistoryModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserHistoryDto(
    @SerialName("currentTime") val currentTime: Int?
) : DTO<UserHistoryModel> {
    override fun mapToDomain(currentHost: String): UserHistoryModel = UserHistoryModel(currentTime = currentTime)
}