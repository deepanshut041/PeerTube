package com.squrlabs.peertube.common.remote.dto.video

import com.squrlabs.peertube.common.remote.DTO
import com.squrlabs.peertube.common.remote.dto.LabelStringDto
import com.squrlabs.peertube.common.service.model.FileModel
import kotlinx.serialization.Serializable

@Serializable
data class FileDto(
    val resolution: LabelStringDto? = null,
    val magnetUri: String? = null,
    val size: Int? = null,
    val fps: Int? = null,
    val torrentUrl: String? = null,
    val torrentDownloadUrl: String? = null,
    val fileUrl: String? = null,
    val fileDownloadUrl: String? = null
) : DTO<FileModel> {
    override fun mapToDomain() = FileModel(
        resolution = resolution?.mapToDomain(),
        magnetUri = magnetUri,
        size = size,
        fps = fps,
        torrentUrl = torrentUrl,
        torrentDownloadUrl = torrentDownloadUrl,
        fileUrl = fileUrl,
        fileDownloadUrl = fileDownloadUrl
    )
}