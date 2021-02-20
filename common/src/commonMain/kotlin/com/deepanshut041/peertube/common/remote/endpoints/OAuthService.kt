package com.deepanshut041.peertube.common.remote.endpoints

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class OAuthEndpoints(private val client: HttpClient) {

    // GET "oauth-clients/local"
    suspend fun getClient() = client.request<Any>("") {
        method = HttpMethod.Get
    }

    // POST "users/token"
    suspend fun getToken(
        clientId: String,
        clientSecret: String,
        grantType: String,
        username: String? = null,
        password: String? = null,
        refreshToken: String? = null
    ) = client.request<Any>("") {
        method = HttpMethod.Post
        body = ""
    }

    // POST "users/token"
    suspend fun refreshToken(
        clientId: String,
        clientSecret: String,
        refreshToken: String,
        grantType: String = "refresh_token"
    ) = client.request<Any>("") {
        method = HttpMethod.Post
    }

}