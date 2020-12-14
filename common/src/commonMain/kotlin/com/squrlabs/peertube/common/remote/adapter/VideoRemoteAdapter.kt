package com.squrlabs.peertube.common.remote.adapter

import com.squrlabs.peertube.common.prefs.InstanceSharedPrefs
import com.squrlabs.peertube.common.remote.endpoints.VideoEndpoints
import com.squrlabs.peertube.common.service.model.VideoDescriptionModel
import com.squrlabs.peertube.common.service.model.VideoModel

class VideoRemoteAdapterImpl(
    private val videoEndpoints: VideoEndpoints,
    private val prefs: InstanceSharedPrefs
) : VideoRemoteAdapter {

    override suspend fun getVideos(
        categoryOneOf: List<Int>?,
        count: Int?,
        filter: String?,
        languageOneOf: List<String>?,
        licenceOneOf: List<String>?,
        nsfw: Boolean?,
        skipCount: Boolean?,
        sort: String?,
        start: Int?,
        tagsAllOf: List<String>?,
        tagsOneOf: List<String>?
    ): List<VideoModel> = videoEndpoints.getVideos(
        prefs.getCurrentHost(),
        categoryOneOf,
        count,
        filter,
        languageOneOf,
        licenceOneOf,
        nsfw,
        skipCount,
        sort,
        start,
        tagsAllOf,
        tagsOneOf
    ).data.map { it.mapToDomain(prefs.getCurrentHost()) }

    override suspend fun searchVideos(
        search: String,
        categoryOneOf: List<Int>?,
        count: Int?,
        durationMax: Int?,
        durationMin: Int?,
        filter: String?,
        languageOneOf: List<String>?,
        licenceOneOf: List<String>?,
        nsfw: Boolean?,
        originallyPublishedEndDate: String?,
        originallyPublishedStartDate: String?,
        searchTarget: String?,
        skipCount: Boolean?,
        sort: String?,
        start: Int?,
        startDate: String?,
        endDate: String?,
        tagsAllOf: List<String>?,
        tagsOneOf: List<String>?
    ): List<VideoModel> = videoEndpoints.searchVideos(
        prefs.getCurrentHost(),
        search,
        categoryOneOf,
        count,
        durationMax,
        durationMin,
        filter,
        languageOneOf,
        licenceOneOf,
        nsfw,
        originallyPublishedEndDate,
        originallyPublishedStartDate,
        searchTarget,
        skipCount,
        sort,
        start,
        startDate,
        endDate,
        tagsAllOf,
        tagsOneOf
    ).data.map { it.mapToDomain(prefs.getCurrentHost()) }

    override suspend fun getVideo(id: String) = videoEndpoints.getVideo(prefs.getCurrentHost(), id).mapToDomain(prefs.getCurrentHost())

    override suspend fun getVideoDescription(id: String) =
        videoEndpoints.getVideoDescription(prefs.getCurrentHost(), id).mapToDomain(prefs.getCurrentHost())
}

interface VideoRemoteAdapter {
    suspend fun getVideos(
        categoryOneOf: List<Int>? = null,
        count: Int? = null,
        filter: String? = null,
        languageOneOf: List<String>? = null,
        licenceOneOf: List<String>? = null,
        nsfw: Boolean? = null,
        skipCount: Boolean? = null,
        sort: String? = null,
        start: Int? = null,
        tagsAllOf: List<String>? = null,
        tagsOneOf: List<String>? = null
    ): List<VideoModel>

    suspend fun searchVideos(
        search: String,
        categoryOneOf: List<Int>? = null,
        count: Int? = null,
        durationMax: Int? = null,
        durationMin: Int? = null,
        filter: String? = null,
        languageOneOf: List<String>? = null,
        licenceOneOf: List<String>? = null,
        nsfw: Boolean? = null,
        originallyPublishedEndDate: String? = null,
        originallyPublishedStartDate: String? = null,
        searchTarget: String? = null,
        skipCount: Boolean? = null,
        sort: String? = null,
        start: Int? = null,
        startDate: String? = null,
        endDate: String? = null,
        tagsAllOf: List<String>? = null,
        tagsOneOf: List<String>? = null
    ): List<VideoModel>

    suspend fun getVideo(id: String): VideoModel

    suspend fun getVideoDescription(id: String): VideoDescriptionModel

}