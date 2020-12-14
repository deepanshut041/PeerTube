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
import com.squrlabs.peertube.common.service.model.AccountModel
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountDto(
    @SerialName("avatar") val avatar: AvatarDto? = null,
    @SerialName("displayName") val displayName: String? = null,
    @SerialName("host") val host: String? = null,
    @SerialName("id") val id: Long? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("uuid") val uuid: String? = null,
    @SerialName("hostRedundancyAllowed") val hostRedundancyAllowed: Boolean? = null,
    @SerialName("followingCount") val followingCount: Long? = null,
    @SerialName("followersCount") val followersCount: Long? = null,
    @SerialName("createdAt") val createdAt: String? = null,
    @SerialName("updatedAt") val updatedAt: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("userId") val userId: Long? = null
) : DTO<AccountModel> {
    override fun mapToDomain(host:String?) = AccountModel(
        id = id,
        uuid = uuid,
        url = url,
        name = name,
        host = host,
        hostRedundancyAllowed = hostRedundancyAllowed,
        followersCount = followersCount,
        followingCount = followingCount,
        avatar = avatar?.let { it.mapToDomain() },
        createdAt = createdAt?.let { Instant.parse(it) },
        updatedAt = updatedAt?.let { Instant.parse(it) },
        displayName = displayName,
        description = description,
        userId = userId

    )
}
