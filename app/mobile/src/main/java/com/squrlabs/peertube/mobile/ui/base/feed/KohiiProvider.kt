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

import android.content.Context
import com.google.android.exoplayer2.DefaultLoadControl
import kohii.v1.exoplayer.ExoPlayerConfig
import kohii.v1.exoplayer.Kohii
import kohii.v1.exoplayer.createKohii
import kohii.v1.utils.Capsule

object KohiiProvider {

    private val capsule = Capsule<Kohii, Context>(creator = { context ->
        createKohii(
            context.applicationContext, ExoPlayerConfig(
                minBufferMs = DefaultLoadControl.DEFAULT_MIN_BUFFER_MS,
                maxBufferMs = DefaultLoadControl.DEFAULT_MAX_BUFFER_MS,
                bufferForPlaybackMs = DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS,
                bufferForPlaybackAfterRebufferMs = DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS
            )
        )
    })

    fun get(context: Context) = capsule.get(context)
}