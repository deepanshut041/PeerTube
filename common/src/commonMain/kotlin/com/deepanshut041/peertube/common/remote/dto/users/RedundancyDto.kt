package com.deepanshut041.peertube.common.remote.dto.users

import com.deepanshut041.peertube.common.remote.DTO
import com.deepanshut041.peertube.common.service.model.RedundancyModel
import kotlinx.serialization.Serializable

@Serializable
data class RedundancyDto(
    val baseUrl: String? = null
) : DTO<RedundancyModel> {
    override fun mapToDomain(currentHost: String): RedundancyModel = RedundancyModel(
        baseUrl = baseUrl
    )
}