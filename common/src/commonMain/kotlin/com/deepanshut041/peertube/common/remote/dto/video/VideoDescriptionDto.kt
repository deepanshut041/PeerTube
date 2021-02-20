package com.deepanshut041.peertube.common.remote.dto.video

import com.deepanshut041.peertube.common.remote.DTO
import com.deepanshut041.peertube.common.service.model.VideoDescriptionModel

data class VideoDescriptionDto(val description: String? = null) : DTO<VideoDescriptionModel> {
    override fun mapToDomain(currentHost: String): VideoDescriptionModel = VideoDescriptionModel(
        description = description
    )
}