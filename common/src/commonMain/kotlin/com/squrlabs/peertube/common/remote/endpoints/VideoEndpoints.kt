package com.squrlabs.peertube.common.remote.endpoints

import com.squrlabs.peertube.common.prefs.InstanceSharedPrefs
import com.squrlabs.peertube.common.remote.dto.ListResponseDto
import com.squrlabs.peertube.common.remote.dto.video.VideoDescriptionDto
import com.squrlabs.peertube.common.remote.dto.video.VideoDto
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class VideoEndpoints(private val client: HttpClient, private val prefs: InstanceSharedPrefs) {

    suspend fun getVideos(
        categoryOneOf: List<Int>? = null,
        count: Int? = null,
        filter: String? = null,
        languageOneOf: List<String>? = null,
        licenceOneOf: List<String>? = null,
        nsfw: Boolean? = null,
        skipCount: Boolean? = null,
        sort: String? = null,
        start: Int? = null,
        tagsAllOf: List<String>? = null,
        tagsOneOf: List<String>? = null
    ) = client.request<ListResponseDto<VideoDto>>("${prefs.getCurrentHost()}/videos") {
        method = HttpMethod.Get

        //category id of the video
        parameter("categoryOneOf", categoryOneOf)
        //Number of items
        parameter("count", count)
        //Special filters (local for instance) which might require special rights:
        parameter("filter", filter)
        //language id of the video
        parameter("languageOneOf", languageOneOf)
        //licence id of the video
        parameter("licenceOneOf", licenceOneOf)
        //whether to include nsfw videos, if any
        parameter("nsfw", nsfw)
        //If you don't need the total in the response
        parameter("skipCount", skipCount)
        //Sort videos by criteria ("-name" "-duration" "-createdAt" "-publishedAt" "-views" "-likes" "-trending")
        parameter("sort", sort)
        //Offset
        parameter("start", start)
        //tag(s) of the video, where all should be present in the video
        parameter("tagsAllOf", tagsAllOf)
        //tag(s) of the video
        parameter("tagsOneOf", tagsOneOf)
    }

    suspend fun searchVideos(
        search: String,
        categoryOneOf: List<Int>? = null,
        count: Int? = null,
        durationMax: Int? = null,
        durationMin: Int? = null,
        filter: String? = null,
        languageOneOf: List<String>? = null,
        licenceOneOf: List<String>? = null,
        nsfw: Boolean? = null,
        originallyPublishedEndDate: String? = null,
        originallyPublishedStartDate: String? = null,
        searchTarget: String? = null,
        skipCount: Boolean? = null,
        sort: String? = null,
        start: Int? = null,
        startDate: String? = null,
        endDate: String? = null,
        tagsAllOf: List<String>? = null,
        tagsOneOf: List<String>? = null
    ) = client.request<ListResponseDto<VideoDto>>("${prefs.getCurrentHost()}/search/videos") {
        method = HttpMethod.Get

        parameter("search", search)
        //category id of the video
        parameter("categoryOneOf", categoryOneOf)
        //Number of items
        parameter("count", count)
        //Get videos that have this maximum duration
        parameter("durationMax", durationMax)
        //Get videos that have this minimum duration
        parameter("durationMin", durationMin)
        //Special filters (local for instance) which might require special rights:
        parameter("filter", filter)
        //language id of the video
        parameter("languageOneOf", languageOneOf)
        //licence id of the video
        parameter("licenceOneOf", licenceOneOf)
        //whether to include nsfw videos, if any
        parameter("nsfw", nsfw)
        //Get videos that are originally published before this date
        parameter("originallyPublishedEndDate", originallyPublishedEndDate)
        //Get videos that are originally published after this date
        parameter("originallyPublishedStartDate", originallyPublishedStartDate)
        //If the administrator enabled search index support, you can override the default search target. ("local" "search-index")
        parameter("searchTarget", searchTarget)
        //If you don't need the total in the response
        parameter("skipCount", skipCount)
        //Sort videos by criteria ("name" "-duration" "-createdAt" "-publishedAt" "-views" "-likes" "-match")
        parameter("sort", sort)
        //Offset used to paginate results
        parameter("start", start)
        //Get videos that are published after this date
        parameter("startDate", startDate)
        //Get videos that are published before this date
        parameter("endDate", endDate)
        //tag(s) of the video, where all should be present in the video
        parameter("tagsAllOf", tagsAllOf)
        //tag(s) of the video
        parameter("tagsOneOf", tagsOneOf)
    }

    suspend fun getVideo(
        id: String
    ) = client.request<VideoDto>("${prefs.getCurrentHost()}/videos/${id}") {
        method = HttpMethod.Get
    }

    suspend fun getVideoDescription(
        id: String
    ) = client.request<VideoDescriptionDto>("${prefs.getCurrentHost()}/videos/${id}/description") {
        method = HttpMethod.Get
    }

//    @FormUrlEncoded
//    @PUT("videos/{id}/rate")
//    suspend fun rateVideo(
//        @Path("id", id: String,
//        @Field("rating", @Rate rating: String
//    )= client.request<Any> {
//
//    }

//    @GET("videos/{id}/comment-threads")
//    fun getComments(
//        @Path("id", id: String,
//        //Sort videos by criteria ("-createdAt" "-updatedAt")
//        parameter("sort", sort: String? = null,
//        //Number of items
//        parameter("count", count: Int? = null,
//        //Offset
//        parameter("start", start: Int? = null
//    ) = client.request<ListResponseDto<Video>> {
//        
//    }

}