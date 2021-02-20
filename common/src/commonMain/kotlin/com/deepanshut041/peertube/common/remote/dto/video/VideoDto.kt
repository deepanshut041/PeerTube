/*
 *  Copyright (c) 2020 Squrlabs @ http://squrlabs.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *              http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.deepanshut041.peertube.common.remote.dto.video

import com.deepanshut041.peertube.common.remote.DTO
import com.deepanshut041.peertube.common.remote.dto.LabelLongDto
import com.deepanshut041.peertube.common.remote.dto.LabelStringDto
import com.deepanshut041.peertube.common.remote.dto.users.AccountDto
import com.deepanshut041.peertube.common.service.model.VideoModel
import kotlinx.datetime.Instant

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoDto(
    @SerialName("account") val account: AccountDto? = null,
    @SerialName("blacklisted") val blacklisted: Boolean? = null,
    @SerialName("blacklistedReason") val blacklistedReason: String? = null,
    @SerialName("category") val category: LabelLongDto? = null,
    @SerialName("channel") val channel: ChannelDto? = null,
    @SerialName("createdAt") val createdAt: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("dislikes") val dislikes: Long? = null,
    @SerialName("duration") val duration: Long? = null,
    @SerialName("embedPath") val embedPath: String? = null,
    @SerialName("id") val id: Long? = null,
    @SerialName("isLocal") val isLocal: Boolean? = null,
    @SerialName("language") val language: LabelStringDto? = null,
    @SerialName("licence") val licence: LabelLongDto? = null,
    @SerialName("likes") val likes: Long? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("nsfw") val nsfw: Boolean? = null,
    @SerialName("originallyPublishedAt") val originallyPublishedAt: String? = null,
    @SerialName("previewPath") val previewPath: String? = null,
    @SerialName("privacy") val privacy: LabelLongDto? = null,
    @SerialName("publishedAt") val publishedAt: String? = null,
    @SerialName("scheduledUpdate") val scheduledUpdate: ScheduledUpdateDto? = null,
    @SerialName("state") val state: LabelStringDto? = null,
    @SerialName("thumbnailPath") val thumbnailPath: String? = null,
    @SerialName("updatedAt") val updatedAt: String? = null,
    @SerialName("userHistory") val userHistory: UserHistoryDto? = null,
    @SerialName("uuid") val uuid: String? = null,
    @SerialName("views") val views: Long? = null,
    @SerialName("waitTranscoding") val waitTranscoding: Boolean? = null,
    @SerialName("descriptionPath") val descriptionPath: String? = null,
    @SerialName("support") val support: String? = null,
    @SerialName("tags") val tags: List<String>? = null,
    @SerialName("files") val files: List<FileDto>? = null,
    @SerialName("commentsEnabled") val commentsEnabled: Boolean? = null,
    @SerialName("downloadEnabled") val downloadEnabled: Boolean? = null,
    @SerialName("trackerUrls") val trackerUrls: List<String>? = null,
    @SerialName("streamingPlaylists") val streamingPlaylists: List<StreamingPlaylistDto>? = null
) : DTO<VideoModel> {
    override fun mapToDomain(currentHost: String): VideoModel = VideoModel(
        currentHost = currentHost,
        id = id,
        uuid = uuid,
        createdAt = createdAt?.let { Instant.parse(it) },
        publishedAt = publishedAt?.let { Instant.parse(it) },
        updatedAt = updatedAt?.let { Instant.parse(it) },
        originallyPublishedAt = originallyPublishedAt?.let { Instant.parse(it) },
        category = category?.mapToDomain(currentHost),
        licence = licence?.mapToDomain(currentHost),
        language = language?.mapToDomain(currentHost),
        privacy = privacy?.mapToDomain(currentHost),
        description = description,
        duration = duration,
        isLocal = isLocal,
        name = name,
        thumbnailPath = thumbnailPath,
        previewPath = previewPath,
        embedPath = embedPath,
        views = views,
        likes = likes,
        dislikes = dislikes,
        nsfw = nsfw,
        waitTranscoding = waitTranscoding,
        state = state?.mapToDomain(currentHost),
        scheduledUpdate = scheduledUpdate?.mapToDomain(currentHost),
        blacklisted = blacklisted,
        blacklistedReason = blacklistedReason,
        account = account?.mapToDomain(currentHost),
        channel = channel?.mapToDomain(currentHost),
        userHistory = userHistory?.mapToDomain(currentHost),
        descriptionPath = descriptionPath,
        support = support,
        tags = tags,
        files = files?.let { models -> models.map { it.mapToDomain(currentHost) } },
        commentsEnabled = commentsEnabled,
        downloadEnabled = downloadEnabled,
        trackerUrls = trackerUrls,
        streamingPlaylists = streamingPlaylists?.let { models -> models.map { it.mapToDomain(currentHost) } }
    )
}

