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

package com.squrlabs.peertube.mobile.ui.main.global

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.cachedIn
import com.squrlabs.peertube.common.service.repository.VideoRepository
import com.squrlabs.peertube.mobile.ui.base.feed.BaseFeedViewModel
import com.squrlabs.peertube.mobile.ui.base.feed.FeedRemotePagingSource


class MainGlobalViewModel(private val videoRepository: VideoRepository): BaseFeedViewModel() {

    init {
        timeline = Pager(config){ FeedRemotePagingSource(videoRepository, 20) }.flow.cachedIn(viewModelScope)
    }

}