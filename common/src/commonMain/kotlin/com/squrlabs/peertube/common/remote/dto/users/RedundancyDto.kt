package com.squrlabs.peertube.common.remote.dto.users

import com.squrlabs.peertube.common.remote.DTO
import com.squrlabs.peertube.common.service.model.RedundancyModel
import kotlinx.serialization.Serializable

@Serializable
data class RedundancyDto(
    val baseUrl: String? = null
) : DTO<RedundancyModel> {
    override fun mapToDomain(host:String?) = RedundancyModel(
        baseUrl = baseUrl
    )
}