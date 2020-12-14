package com.squrlabs.peertube.common.remote

interface DTO<M> {
    fun mapToDomain(host: String?=null): M
}