package com.deepanshut041.peertube.common.service.params

data class InstancesFilterParams(
    val text: String? = null,
    val sort: String? = null,
    val signupAllowed: Boolean? = null,
    val healthy: Boolean? = null
    )