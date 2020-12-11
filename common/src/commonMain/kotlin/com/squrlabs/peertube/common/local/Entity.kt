package com.squrlabs.peertube.common.local

interface Entity<M> {
    fun mapToDomain(): M
}