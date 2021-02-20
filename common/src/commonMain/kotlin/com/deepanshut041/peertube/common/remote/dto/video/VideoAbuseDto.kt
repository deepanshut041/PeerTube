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

import com.deepanshut041.peertube.common.remote.dto.LabelStringDto
import com.deepanshut041.peertube.common.remote.dto.users.AccountDto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoAbuseDto(
    @SerialName("createdAt")
    val createdAt: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("moderationComment")
    val moderationComment: String?,
    @SerialName("predefinedReasons")
    val predefinedReasons: List<String>?,
    @SerialName("reason")
    val reason: String?,
    @SerialName("reporterAccount")
    val reporterAccount: AccountDto?,
    @SerialName("state")
    val state: LabelStringDto?,
    @SerialName("video")
    val video: VideoDto?
)