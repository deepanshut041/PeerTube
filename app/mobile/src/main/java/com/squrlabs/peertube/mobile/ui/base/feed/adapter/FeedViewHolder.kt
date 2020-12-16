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

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.exoplayer2.ui.PlayerView
import com.squrlabs.peertube.common.service.model.VideoModel
import com.squrlabs.peertube.mobile.utils.duration
import com.squrlabs.peertube.mobile.utils.getTimeAgo
import com.squrlabs.peertube.mobile.utils.humanReadableBigNumber
import kohii.v1.core.Common
import kohii.v1.core.Playback
import kohii.v1.exoplayer.Kohii
import kotlinx.android.synthetic.main.base_feed_item.view.*

class FeedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val layout = view.clLayout!!
    private val thumbnail = view.ivThumbnail!!
    val avatar = view.ivAccountAvatar!!
    private val name = view.txtName!!
    private val author = view.txtAuthor!!
    private val views = view.txtViews!!
    private val duration = view.txtDuration!!
    private val date = view.txtDate!!
    private val videoView = view.exoVideoPlayer!!

    fun bind(context: Context, kohii: Kohii, item: VideoModel, position: Int) {
        item.previewPath?.let{ loadAvatarImage(context, item.currentHost + it, thumbnail) }
        item.channel?.let { model ->
            model.avatar?.let { loadAvatarImage(context, item.currentHost + it.path!!, avatar) }
            author.text = model.displayName ?: ""
        }
        setVideo(item, kohii, position)
        name.text = item.name?: ""
        date.text = item.publishedAt?.getTimeAgo()
        views.text = "${(item.views?: 0).humanReadableBigNumber()} views"
        duration.text = (item.duration?: 0).duration()
    }

    private fun loadAvatarImage(context: Context, imgUrl: String?, view: ImageView) {
        Glide.with(context)
            .load(imgUrl)
            .transform(RoundedCorners(10))
            .fitCenter()
            .into(view)
    }

    fun setVideo(model: VideoModel, kohii: Kohii, position: Int) {
        kohii.setUp("https://${model.account!!.host}/static/webseed/${model.uuid}-360.mp4") {
            tag = "${model.uuid!!}+${position}"
            preload = true
            repeatMode = Common.REPEAT_MODE_ONE
            artworkHintListener = object: Playback.ArtworkHintListener {
                override fun onArtworkHint(playback: Playback, shouldShow: Boolean, position: Long, state: Int) {
                    thumbnail.isVisible = shouldShow && position <= 0
                }
            }
        }.bind(videoView)
    }

}