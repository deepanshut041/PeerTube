package com.squrlabs.peertube.common.remote.dto.video

import com.squrlabs.peertube.common.remote.dto.users.AccountDto

data class VideoCommentDto(val id: Long? = null,
                           val url: String? = null,
                           val text: String? = null,
                           val threadId: String? = null,
                           val inReplyToCommentId: String? = null,
                           val videoId: String? = null,
                           val createdAt: String? = null,
                           val updatedAt: String? = null,
                           val deletedAt: String? = null,
                           val isDeleted: Boolean? = null,
                           val totalRepliesFromVideoAuthor: Int? = null,
                           val totalReplies: Int? = null,
                           val account: AccountDto? = null)