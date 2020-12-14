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
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.paging.PagingDataAdapter
import com.squrlabs.peertube.common.service.model.VideoModel
import com.squrlabs.peertube.mobile.R
import com.squrlabs.peertube.util.SingleLiveEvent
import kohii.v1.exoplayer.Kohii

class FeedAdapterImpl(
    diffUtil: FeedDiffUtil,
    private val context: Context,
    private val kohii: Kohii
) : PagingDataAdapter<VideoModel, FeedViewHolder>(diffUtil), FeedAdapter {

    val actions: LiveData<Pair<String, VideoModel>>
        get() = _actions

    private val _actions = SingleLiveEvent<Pair<String, VideoModel>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.feed_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        getItem(position)?.let { model ->

            holder.bind(context, kohii, model, position)

//            holder.likesButton.setOnClickListener {
//                _actions.value = Pair(FEED_STATUS_ACTION_OPEN_LIKE, model)
//            }
        }
    }

    override fun itemListener(position: Int) {
        _actions.value = Pair(FEED_STATUS_ACTION_ZOOM, getItem(position)!!)
    }

    companion object {
        const val FEED_STATUS_ACTION_ZOOM = "zoom"
    }
}

interface FeedAdapter {
    fun itemListener(position: Int)
}
