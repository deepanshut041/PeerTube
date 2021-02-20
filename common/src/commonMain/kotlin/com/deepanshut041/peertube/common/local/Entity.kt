package com.deepanshut041.peertube.common.local

interface Entity<M> {
    fun mapToDomain(): M
}