package com.squrlabs.peertube.common.remote.dto

interface DTO<M> {
    fun mapToDomain(): M
}