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
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import androidx.paging.PagingConfig
//import androidx.paging.PagingData
//import com.squrlabs.peertube.domain.model.video.VideoModel
//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
//import io.reactivex.rxjava3.disposables.CompositeDisposable
//import io.reactivex.rxjava3.schedulers.Schedulers
//
//abstract class BaseFeedViewModel() : ViewModel() {
//    protected val disposable = CompositeDisposable()
//    protected val config = PagingConfig(pageSize = 20)
//
//    val timeline: LiveData<PagingData<VideoModel>>
//        get() = _timeline
//
//    protected lateinit var _timeline: LiveData<PagingData<VideoModel>>
//
//    abstract fun setTimeline(model: Any, reInitialize: Boolean = false)
//
//    fun favouriteStatus(model: VideoModel) {
//
//    }
//
//    fun reblogStatus(model: VideoModel) {
//
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        disposable.clear()
//    }
//}