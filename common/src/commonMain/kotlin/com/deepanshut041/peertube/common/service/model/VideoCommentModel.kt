package com.deepanshut041.peertube.common.service.model

import com.deepanshut041.peertube.common.remote.dto.users.AccountDto
import kotlinx.datetime.Instant

data class VideoCommentModel(val id: Long? = null,
                             val url: String? = null,
                             val text: String? = null,
                             val threadId: String? = null,
                             val inReplyToCommentId: String? = null,
                             val videoId: String? = null,
                             val createdAt: Instant? = null,
                             val updatedAt: Instant? = null,
                             val deletedAt: Instant? = null,
                             val isDeleted: Boolean? = null,
                             val totalRepliesFromVideoAuthor: Int? = null,
                             val totalReplies: Int? = null,
                             val account: AccountDto? = null)