package com.squrlabs.peertube.common.service.repository

import com.squrlabs.peertube.common.remote.adapter.VideoRemoteAdapter
import com.squrlabs.peertube.common.service.Resource
import com.squrlabs.peertube.common.service.model.VideoModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class VideoRepositoryImpl(
    private val remote: VideoRemoteAdapter,
    private val coroutineDispatcher: CoroutineDispatcher
) : VideoRepository {

    override suspend fun getVideos(count: Int, start: Int?, sort: String?, filter: String?): Resource<List<VideoModel>> {
        return withContext(coroutineDispatcher) {
            try {
                Resource.success(remote.getVideos(count = count, start = start, filter = filter, sort = sort))
            } catch (e: Exception) {
                Resource.error(e.toString(), emptyList())
            }
        }
    }

    override suspend fun getVideo(id: Long): Resource<VideoModel> {
        return withContext(coroutineDispatcher) {
            try {
                Resource.success(remote.getVideo(id.toString()))
            } catch (e: Exception) {
                Resource.error(e.toString(), null)
            }
        }
    }
}

interface VideoRepository {
    suspend fun getVideos(count: Int, start: Int?, sort: String?, filter: String?): Resource<List<VideoModel>>
    suspend fun getVideo(id: Long): Resource<VideoModel>
}