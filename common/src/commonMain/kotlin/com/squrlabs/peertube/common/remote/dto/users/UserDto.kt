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

package com.squrlabs.peertube.common.remote.dto.users

import com.squrlabs.peertube.common.remote.DTO
import com.squrlabs.peertube.common.remote.dto.video.ChannelDto
import com.squrlabs.peertube.common.service.model.UserModel
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("id") val id: Int,
    @SerialName("account") val account: AccountDto,
    val username: String? = null,
    val email: String? = null,
    val displayNSFW: Boolean? = null,
    val autoPlayVideo: Boolean? = null,
    val role: Int? = null,
    val roleLabel: String? = null,
    val videoQuota: Int? = null,
    val videoQuotaDaily: Int? = null,
    val createdAt: String? = null,
    var videoChannels: List<ChannelDto>? = null
) : DTO<UserModel> {
    override fun mapToDomain(host:String?) = UserModel(
        id = id,
        username = username,
        email = email,
        displayNSFW = displayNSFW,
        autoPlayVideo = autoPlayVideo,
        role = role,
        roleLabel = roleLabel,
        videoQuota = videoQuota,
        videoQuotaDaily = videoQuotaDaily,
        createdAt = createdAt?.let { Instant.parse(it) },
        videoChannels = videoChannels?.let { models -> models.map { it.mapToDomain() } },
    )
}
