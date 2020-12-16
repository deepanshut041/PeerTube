package com.squrlabs.peertube.common.remote.dto.video

import com.squrlabs.peertube.common.remote.DTO
import com.squrlabs.peertube.common.service.model.VideoDescriptionModel

data class VideoDescriptionDto(val description: String? = null) : DTO<VideoDescriptionModel> {
    override fun mapToDomain(currentHost: String): VideoDescriptionModel = VideoDescriptionModel(
        description = description
    )
}