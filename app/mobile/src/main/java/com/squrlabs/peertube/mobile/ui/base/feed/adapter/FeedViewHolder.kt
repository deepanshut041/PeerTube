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
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.squrlabs.peertube.common.service.model.VideoModel
import kohii.v1.exoplayer.Kohii
import kotlinx.android.synthetic.main.feed_list_item.view.*

class FeedViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val thumbnail = view.ivThumbnail!!
    private val avatar = view.ivAccountAvatar!!
    private val name = view.txtName!!
    private val author = view.txtAuthor!!
    private val views = view.txtViews!!
    private val duration = view.txtDuration!!
    private val date = view.txtDate!!

    fun bind(context: Context, kohii: Kohii, item: VideoModel, position: Int) {
        item.previewPath?.let{ loadAvatarImage(context, it, thumbnail) }
        item.channel?.let { channelModel ->
            channelModel.avatar?.let { loadAvatarImage(context, it.path!!, avatar) }
            author.text = channelModel.displayName ?: ""
        }
        name.text = item.name?: ""
        views.text = "${item.views?: 0} views"
        duration.text = (item.duration?: 0).toString()
    }

    private fun loadAvatarImage(context: Context, imgUrl: String?, view: ImageView) {
        Glide.with(context)
            .load(imgUrl)
            .transform(RoundedCorners(10))
            .fitCenter()
            .into(view)
    }

}