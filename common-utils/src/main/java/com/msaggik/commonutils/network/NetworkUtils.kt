package com.msaggik.commonutils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.msaggik.commonui.R
import com.msaggik.commonutils.all.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun Context.isConnected(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    return (
        capabilities != null
            && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
        )
}

@Suppress("CyclomaticComplexMethod")
fun <T, S> Context.executeNetworkRequest(
    request: suspend () -> T,
    successHandler: (T) -> Response<S>,
): Flow<Response<S>> =
    flow {
        val response: T = request.invoke()
        when ((response as HttpResponse).resultCode) {
            HttpStatus.NO_INTERNET -> { emit(Response.Error(getString(R.string.check_network_connection))) }
            HttpStatus.OK -> {
                with(response as T) { emit(successHandler(response)) }
            }
            HttpStatus.CLIENT_ERROR -> {
                emit(
                    Response.Error(getString(R.string.request_was_not_accepted, response.resultCode))
                )
            }
            HttpStatus.CLIENT_ERROR_BAD_REQUEST -> {
                emit(Response.Error(getString(R.string.the_request_contains_invalid_data)))
            }
            HttpStatus.CLIENT_ERROR_UNAUTHORIZED -> {
                emit(Response.Error(getString(R.string.authentication_required)))
            }
            HttpStatus.CLIENT_ERROR_FORBIDDEN -> {
                emit(Response.Error(getString(R.string.access_denied)))
            }
            HttpStatus.CLIENT_ERROR_NOT_FOUND -> {
                emit(Response.Error(getString(R.string.the_requested_resource_was_not_found)))
            }
            HttpStatus.SERVER_ERROR -> {
                emit(Response.Error(getString(R.string.unexpected_error, response.resultCode)))
            }
        }
    }
