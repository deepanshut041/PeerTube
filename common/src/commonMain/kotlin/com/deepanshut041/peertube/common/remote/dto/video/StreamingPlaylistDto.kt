package com.deepanshut041.peertube.common.remote.dto.video

import com.deepanshut041.peertube.common.remote.DTO
import com.deepanshut041.peertube.common.remote.dto.users.RedundancyDto
import com.deepanshut041.peertube.common.service.model.StreamingPlaylistModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StreamingPlaylistDto(
    @SerialName("id") val id: Long? = null,
    @SerialName("type") val type: Int? = null,
    @SerialName("playlistUrl") val playlistUrl: String? = null,
    @SerialName("segmentsSha256Url") val segmentsSha256Url: String? = null,
    @SerialName("files") val files: List<FileDto>? = null,
    @SerialName("redundancies") val redundancies: List<RedundancyDto>? = null
) : DTO<StreamingPlaylistModel> {
    override fun mapToDomain(currentHost: String): StreamingPlaylistModel = StreamingPlaylistModel(
        id = id,
        type = type,
        playlistUrl = playlistUrl,
        segmentsSha256Url = segmentsSha256Url,
        files = files?.let { models -> models.map { it.mapToDomain(currentHost) } },
        redundancies = redundancies?.let { models -> models.map { it.mapToDomain(currentHost) } }
    )
}