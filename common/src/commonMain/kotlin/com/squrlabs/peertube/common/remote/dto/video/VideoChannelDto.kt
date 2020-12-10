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

import com.squrlabs.peertube.common.remote.dto.users.AccountDto
import com.squrlabs.peertube.common.remote.dto.users.AvatarDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoChannelDto(
    @SerialName("avatar")
    val avatar: AvatarDto?,
    @SerialName("displayName")
    val displayName: String?,
    @SerialName("host")
    val host: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("url")
    val url: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("isLocal")
    val isLocal: Boolean?,
    @SerialName("ownerAccount")
    val ownerAccount: AccountDto?
)