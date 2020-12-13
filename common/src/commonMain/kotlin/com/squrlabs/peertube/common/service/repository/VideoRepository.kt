package com.squrlabs.peertube.common.service.repository

import com.squrlabs.peertube.common.remote.adapter.VideoRemoteAdapter
import com.squrlabs.peertube.common.service.Resource
import com.squrlabs.peertube.common.service.model.VideoModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class VideoRepositoryImpl(
    private val remote: VideoRemoteAdapter,
    private val coroutineDispatcher: CoroutineDispatcher
) : VideoRepository {

    override fun getVideos(): Flow<Resource<List<VideoModel>>> {
        return flow<Resource<List<VideoModel>>> {
            emit(Resource.loading(emptyList()))
            try {
                emit(Resource.success(remote.getVideos()))
            } catch (e: Exception) {
                emit(Resource.error(e.toString(), emptyList()))
            }
        }.flowOn(coroutineDispatcher)
    }
}

interface VideoRepository {
    fun getVideos(): Resource<Resource<List<VideoModel>>>
}