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

package com.squrlabs.peertube.mobile.ui.base.feed

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squrlabs.peertube.mobile.ui.base.feed.adapter.FeedAdapter
import com.squrlabs.peertube.mobile.ui.base.feed.adapter.FeedDiffUtil
import kohii.v1.core.MemoryMode
import kohii.v1.exoplayer.Kohii
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

open class BaseFeedFragment : Fragment() {

    protected lateinit var model: BaseFeedViewModel
    private lateinit var kohii:Kohii
    protected lateinit var feedAdapter: FeedAdapter
    protected lateinit var feedRecyclerView: RecyclerView

    fun setupRecyclerView(statusRecyclerView: RecyclerView) {
        this.feedRecyclerView = statusRecyclerView

        kohii = KohiiProvider.get(requireContext())
        kohii.register(this, memoryMode = MemoryMode.AUTO).addBucket(this.feedRecyclerView)

        this.feedRecyclerView.layoutManager = LinearLayoutManager(context)
        feedAdapter = FeedAdapter(FeedDiffUtil(), requireContext(), kohii)
        this.feedRecyclerView.adapter = feedAdapter

        bindAdapterListener()

        lifecycleScope.launch {
            model.timeline.collectLatest {
                Log.i("FeedFragment", it.toString())
                feedAdapter.submitData(it)
            }
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            kohii.lockBucket(this.feedRecyclerView);
        } else {
            kohii.unlockBucket(this.feedRecyclerView);
        }
    }

    private fun bindAdapterListener() {

    }
}