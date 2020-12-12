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

package com.squrlabs.peertube.mobile.ui.base.feed.adapter
//
//import android.content.Context
//import android.view.View
//import android.widget.ImageView
//import androidx.core.content.ContextCompat
//import androidx.core.text.HtmlCompat
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.resource.bitmap.RoundedCorners
//import kohii.v1.core.Manager
//import kohii.v1.exoplayer.Kohii
//import kotlinx.android.synthetic.main.item_feed_status.view.*
//
//class FeedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//    private val contentView = view.txtFeedItemText!!
//
//    private val accountAvatar = view.ivFeedItemAuthorAvatar!!
//    private val accountName = view.txtFeedItemAuthorName!!
//
//    private val reblogName = view.txtFeedItemReblogName!!
//    private val reblogToolbar = view.tlFeedItemReblog!!
//    private val reblogDivider = view.txtFeedItemReblogDivider!!
//
//    private val attachmentView = view.flFeedItemAttachment!!
//    private val attachmentVideoView = view.flFeedItemAttachmentVideo!!
//    private val attachmentSingleImageView = view.ivFeedItemAttachmentSingleImage!!
//
//    val likeButton = view.btnFeedItemLike!!
//    val shareButton = view.btnFeedItemBoost!!
//    val commentButton = view.btnFeedItemComment!!
//    val likesButton = view.txtFeedItemLikesButton!!
//    val sharesButton = view.txtFeedItemBoostButton!!
//    val commentsButton = view.txtFeedItemCommentButton!!
//
//    private val likeCount = view.txtFeedItemLike!!
//    private val shareCount = view.txtFeedItemBoost!!
//    private val commentsCount = view.txtFeedItemComment!!
//
//
//    private lateinit var model: StatusModel
//    private var contentExpanded = false
//
//    fun bind(context: Context, kohii: Kohii, item: StatusModel, position: Int) {
//        setHeader(context)
//        setContent(attachmentModels.isNotEmpty(), context)
//        bottomBar(item, context)
//    }
//
//    fun bottomBar(item: StatusModel, context: Context) {
//        likeCount.text = item.favouritesCount.toString()
//        shareCount.text = item.reblogsCount.toString()
//        commentsCount.text = item.repliesCount.toString()
//
//        item.favourited?.let {
//            if (it)
//                likeButton.setImageDrawable(
//                    ContextCompat.getDrawable(
//                        context,
//                        R.drawable.feed_liked_icon
//                    )
//                )
//            else
//                likeButton.setImageDrawable(
//                    ContextCompat.getDrawable(
//                        context,
//                        R.drawable.feed_like_icon
//                    )
//                )
//        }
//
//        item.reblogged?.let {
//            if (it)
//                shareButton.setImageDrawable(
//                    ContextCompat.getDrawable(
//                        context,
//                        R.drawable.feed_rebloged_icon
//                    )
//                )
//            else
//                shareButton.setImageDrawable(
//                    ContextCompat.getDrawable(
//                        context,
//                        R.drawable.feed_reblog_icon
//                    )
//                )
//        }
//    }
//
//    private fun setHeader(context: Context) {
//        accountName.text = model.account.displayName
//        loadAvatarImage(context, model.account.avatar, accountAvatar)
//    }
//
//    private fun setContent(hasAttachment: Boolean, context: Context) {
//        contentView.text = HtmlCompat.fromHtml(model.content, 0)
//        contentView.maxLines = if (hasAttachment) 2 else 10
//        contentView.setOnClickListener {
//            if (!contentExpanded) {
//                contentView.maxLines = 20
//                contentExpanded = true
//            } else {
//                contentView.maxLines = if (hasAttachment) 2 else 10
//                contentExpanded = false
//            }
//        }
//    }
//
//    private fun loadAvatarImage(context: Context, imgUrl: String?, view: ImageView) {
//        Glide.with(context)
//            .load(imgUrl)
//            .transform(RoundedCorners(10))
//            .fitCenter()
//            .into(view)
//    }
//
//}