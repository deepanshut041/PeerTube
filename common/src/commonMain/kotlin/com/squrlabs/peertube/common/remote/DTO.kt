package com.squrlabs.peertube.common.remote

interface DTO<M> {
    fun mapToDomain(currentHost: String): M
}