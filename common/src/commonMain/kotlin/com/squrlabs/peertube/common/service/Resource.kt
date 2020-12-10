package com.squrlabs.peertube.common.service


data class Resource<T> (val state: String, val data: T? = null, val message: String? = null, var code: Int? = null) {

    companion object {
        const val NOT_FOUND = "NOT FOUND"
        const val ERROR = "ERROR"
        const val SUCCESS = "SUCCESS"
        const val LOADING = "LOADING"
        const val UNAUTHORISED = "UNAUTHORISED"
        const val EMPTY = "EMPTY"

        fun <T> empty(): Resource<T> {
            return Resource(EMPTY)
        }

        fun <T> custom(state: String, data: T? = null): Resource<T> {
            return Resource(state, data)
        }

        fun <T> unauthorised(data: T? = null): Resource<T> {
            return Resource(UNAUTHORISED, data, null)
        }

        fun <T> success(data: T? = null): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> notFound(): Resource<T> {
            return Resource(NOT_FOUND)
        }

        fun <T> error(msg: String? = null, data: T? = null, code: Int? = null): Resource<T> {
            return Resource(ERROR, data, msg, code)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}