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
import com.squrlabs.peertube.common.service.model.AvatarModel
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AvatarDto(
    @SerialName("createdAt")
    val createdAt: String?,
    @SerialName("path")
    val path: String?,
    @SerialName("updatedAt")
    val updatedAt: String?
) : DTO<AvatarModel> {
    override fun mapToDomain() = AvatarModel(
        path = path,
        createdAt = createdAt?.let { Instant.parse(it) },
        updatedAt = updatedAt?.let { Instant.parse(it) }
    )
}