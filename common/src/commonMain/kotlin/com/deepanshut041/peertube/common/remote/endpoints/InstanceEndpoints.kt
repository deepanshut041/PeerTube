package com.deepanshut041.peertube.common.remote.endpoints

import com.deepanshut041.peertube.common.remote.dto.ListResponseDto
import com.deepanshut041.peertube.common.remote.dto.others.InstanceDto
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class InstanceEndpoints(private val client: HttpClient) {

    suspend fun fetchInstances() =
        client.request<ListResponseDto<InstanceDto>>("https://instances.joinpeertube.org/api/v1/instances") {
            method = HttpMethod.Get
            parameter("count", Int.MAX_VALUE)
        }

}