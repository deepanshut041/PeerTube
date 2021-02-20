package com.deepanshut041.peertube.common.remote

interface DTO<M> {
    fun mapToDomain(currentHost: String): M
}