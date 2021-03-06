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
import com.deepanshut041.peertube.common.remote.dto.users.AvatarDto
import com.deepanshut041.peertube.common.service.model.ChannelModel
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChannelDto(
    @SerialName("uuid") val uuid: String? = null,
    @SerialName("avatar") val avatar: AvatarDto? = null,
    @SerialName("displayName") val displayName: String? = null,
    @SerialName("host") val host: String? = null,
    @SerialName("id") val id: Long? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("isLocal") val isLocal: Boolean? = null,
    @SerialName("hostRedundancyAllowed") val hostRedundancyAllowed: Boolean? = null,
    @SerialName("followingCount") val followingCount: Long? = null,
    @SerialName("followersCount") val followersCount: Long? = null,
    @SerialName("createdAt") val createdAt: String? = null,
    @SerialName("updatedAt") val updatedAt: String? = null,
    @SerialName("support") val support: String? = null,
) : DTO<ChannelModel> {
    override fun mapToDomain(currentHost: String): ChannelModel = ChannelModel(
        currentHost= currentHost,
        id = id,
        uuid = uuid,
        url = url,
        name = name,
        host = host,
        hostRedundancyAllowed = hostRedundancyAllowed,
        followingCount = followingCount,
        followersCount = followersCount,
        avatar = avatar?.mapToDomain(currentHost),
        createdAt = createdAt?.let { Instant.parse(it) },
        updatedAt = updatedAt?.let { Instant.parse(it) },
        displayName = displayName,
        description = description,
        support = support,
        isLocal = isLocal
    )
}