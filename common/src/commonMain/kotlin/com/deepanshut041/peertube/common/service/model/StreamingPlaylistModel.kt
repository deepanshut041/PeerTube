package com.deepanshut041.peertube.common.service.model

data class StreamingPlaylistModel(
    val id: Long? = null,
    val type: Int? = null,
    val playlistUrl: String? = null,
    val segmentsSha256Url: String? = null,
    val files: List<FileModel>? = null,
    val redundancies: List<RedundancyModel>? = null
)