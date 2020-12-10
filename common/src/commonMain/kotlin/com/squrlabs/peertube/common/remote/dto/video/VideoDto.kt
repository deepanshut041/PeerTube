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

package com.squrlabs.peertube.common.remote.dto.video

import com.squrlabs.peertube.common.remote.dto.LabelDto
import com.squrlabs.peertube.common.remote.dto.users.AccountDto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoDto(
    @SerialName("account")
    val account: AccountDto?,
    @SerialName("blacklisted")
    val blacklisted: Boolean?,
    @SerialName("blacklistedReason")
    val blacklistedReason: String?,
    @SerialName("category")
    val category: LabelDto?,
    @SerialName("channel")
    val channel: VideoChannelDto?,
    @SerialName("createdAt")
    val createdAt: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("dislikes")
    val dislikes: Int?,
    @SerialName("duration")
    val duration: Int?,
    @SerialName("embedPath")
    val embedPath: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("isLocal")
    val isLocal: Boolean?,
    @SerialName("language")
    val language: LabelDto?,
    @SerialName("licence")
    val licence: LabelDto?,
    @SerialName("likes")
    val likes: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("nsfw")
    val nsfw: Boolean?,
    @SerialName("originallyPublishedAt")
    val originallyPublishedAt: String?,
    @SerialName("previewPath")
    val previewPath: String?,
    @SerialName("privacy")
    val privacy: LabelDto?,
    @SerialName("publishedAt")
    val publishedAt: String?,
    @SerialName("scheduledUpdate")
    val scheduledUpdate: ScheduledUpdate?,
    @SerialName("state")
    val state: LabelDto?,
    @SerialName("thumbnailPath")
    val thumbnailPath: String?,
    @SerialName("updatedAt")
    val updatedAt: String?,
    @SerialName("userHistory")
    val userHistory: UserHistory?,
    @SerialName("uuid")
    val uuid: String?,
    @SerialName("views")
    val views: Int?,
    @SerialName("waitTranscoding")
    val waitTranscoding: Boolean?
) {
    @Serializable
    data class ScheduledUpdate(
        @SerialName("privacy")
        val privacy: Int?,
        @SerialName("updateAt")
        val updateAt: String?
    )

    @Serializable
    data class UserHistory(
        @SerialName("currentTime")
        val currentTime: Int?
    )
}