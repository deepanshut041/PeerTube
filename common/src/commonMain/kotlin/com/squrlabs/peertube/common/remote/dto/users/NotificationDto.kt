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

import com.squrlabs.peertube.common.remote.dto.video.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationDto(
    @SerialName("id") val id: Int?,
    @SerialName("type") val type: Int?,
    @SerialName("read") val read: Boolean?,
    @SerialName("video") val video: VideoDto?,
    @SerialName("videoImport") val videoImport: VideoDetailDto?,
    @SerialName("videoAbuse") val videoAbuse: VideoAbuseDto?,
    @SerialName("videoBlacklist") val videoBlacklist: VideoBlacklistDto?,
    @SerialName("account") val account: AccountDto?,
    @SerialName("actorFollow") val actorFollow: VideoChannelDto?,
    @SerialName("createdAt") val createdAt: String?,
    @SerialName("updatedAt") val updatedAt: String?
)