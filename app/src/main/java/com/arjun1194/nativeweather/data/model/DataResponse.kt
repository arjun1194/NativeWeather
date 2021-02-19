package com.arjun1194.nativeweather.data.model

sealed class DataResponse<out T> {
    data class Success<out T>(
        val data: T
    ) : DataResponse<T>()

    data class Error(
        val error: Throwable,
    ) : DataResponse<Nothing>()

}
