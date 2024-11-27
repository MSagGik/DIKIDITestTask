package com.msaggik.commonutils.all

sealed class Response<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Response<T>(data)
    class Error<T>(message: String, data: T? = null) : Response<T>(data, message)

    companion object {
        fun <T> handleResponse(result: Response<T>): Pair<T?, String?> {
            return when (result) {
                is Success -> Pair(result.data, null)
                is Error -> Pair(null, result.message)
            }
        }
    }
}

fun <T, R> Response<T>.map(transform: (T) -> R): Response<R> {
    return when (this) {
        is Response.Success -> Response.Success(transform(data!!))
        is Response.Error -> Response.Error(message!!)
    }
}
