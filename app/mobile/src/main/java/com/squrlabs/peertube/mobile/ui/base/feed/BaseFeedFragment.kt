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
//
//import com.squrlabs.peertube.mobile.ui.base.feed.adapter.FeedAdapterImpl
//import com.squrlabs.peertube.mobile.ui.base.feed.adapter.FeedAdapterImpl.Companion.FEED_STATUS_ACTION_COMMENT
//import com.squrlabs.peertube.mobile.ui.base.feed.adapter.FeedAdapterImpl.Companion.FEED_STATUS_ACTION_LIKE
//import com.squrlabs.peertube.mobile.ui.base.feed.adapter.FeedAdapterImpl.Companion.FEED_STATUS_ACTION_REBLOG
//import com.squrlabs.peertube.mobile.ui.base.feed.adapter.FeedAdapterImpl.Companion.FEED_STATUS_ACTION_ZOOM
//import com.squrlabs.peertube.mobile.ui.base.feed.adapter.FeedDiffUtil
//import android.view.View
//import androidx.appcompat.app.AppCompatActivity
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.Observer
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import kohii.v1.core.MemoryMode
//import kohii.v1.exoplayer.Kohii
//
//open class BaseFeedFragment : Fragment() {
//
//    protected lateinit var model: BaseFeedViewModel
//    private lateinit var kohii:Kohii
//    protected lateinit var feedAdapter: FeedAdapterImpl
//    protected lateinit var statusRecyclerView: RecyclerView
//    protected lateinit var progressView: LoadingView
//
//    fun setStatusRecyclerView(statusRecyclerView: RecyclerView, progressView: LoadingView) {
//        this.progressView = progressView
//        this.statusRecyclerView = statusRecyclerView
//        kohii = KohiiProvider.get(requireContext())
//        kohii.register(this, memoryMode = MemoryMode.AUTO).addBucket(this.statusRecyclerView)
//        this.statusRecyclerView.layoutManager = LinearLayoutManager(context)
//        feedAdapter = FeedAdapterImpl(FeedDiffUtil(), requireContext(), kohii)
//        this.statusRecyclerView.adapter = feedAdapter
//        bindAdapterListener()
//    }
//
//    override fun onHiddenChanged(hidden: Boolean) {
//        super.onHiddenChanged(hidden)
//        if (hidden) {
//            kohii.lockBucket(this.statusRecyclerView);
//        } else {
//            kohii.unlockBucket(this.statusRecyclerView);
//        }
//    }
//
//    private fun bindAdapterListener() {
//        progressView.startLoading(requireContext())
//        feedAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
//            override fun onChanged() {
//                super.onChanged()
//                checkEmpty()
//            }
//
//            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
//                super.onItemRangeInserted(positionStart, itemCount)
//                checkEmpty()
//            }
//
//            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
//                super.onItemRangeRemoved(positionStart, itemCount)
//                checkEmpty()
//            }
//
//            fun checkEmpty() {
//                if (feedAdapter.itemCount == 0) {
//                    progressView.visibility = View.VISIBLE
//                    progressView.startLoading(context!!)
//                } else {
//                    if (progressView.visibility != View.GONE) {
//                        progressView.endLoading(context!!)
//                    }
//                    progressView.visibility = View.GONE
//                }
//            }
//        })
//        feedAdapter.actions.observe(this, Observer {
//            when (it.first) {
//                FEED_STATUS_ACTION_ZOOM -> (activity as AppCompatActivity).startActivity(
//                    NavigationHelper.navigateToMediaView(
//                        activity as AppCompatActivity,
//                        if (it.second.reblog != null) it.second.reblog!!.mediaAttachments else it.second.mediaAttachments
//                    )
//                )
//                FEED_STATUS_ACTION_COMMENT -> {
//
//                }
//                FEED_STATUS_ACTION_LIKE -> {
//                    model.favouriteStatus(it.second)
//                }
//                FEED_STATUS_ACTION_REBLOG -> {
//                    model.reblogStatus(it.second)
//                }
//            }
//        })
//
//    }
//
//
//}