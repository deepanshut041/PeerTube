package com.squrlabs.peertube.common.service.model

data class FileModel(
    val resolution: LabelModel? = null,
    val magnetUri: String? = null,
    val size: Int? = null,
    val fps: Int? = null,
    val torrentUrl: String? = null,
    val torrentDownloadUrl: String? = null,
    val fileUrl: String? = null,
    val fileDownloadUrl: String? = null
)