package com.squrlabs.peertube.common.remote.endpoints

import com.squrlabs.peertube.common.remote.dto.ListResponseDto
import com.squrlabs.peertube.common.remote.dto.others.InstanceDto
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class InstanceEndpoints(private val client: HttpClient) {

    suspend fun getInstances(
        count: Int = Int.MAX_VALUE,
        healthy: Boolean? = null,
        signup: Boolean? = null,
        sort: String? = null,
        start: Int? = null
    ) = client.request<ListResponseDto<InstanceDto>>("https://instances.joinpeertube.org/api/v1/instances") {
        method = HttpMethod.Get
        //Number of instances
        parameter("count", count)
        //Whether to show healthy instances only or non-healthy instances only (otherwise both are shown)
        parameter("healthy", healthy)
        //Whether to only show instances where registrations are open, or where they are closed
        parameter("signup", signup)
        //Sort order
        parameter("sort", sort)
        //Offset
        parameter("start", start)
    }
}