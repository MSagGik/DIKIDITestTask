package com.msaggik.commonutils.network

/**
 * @param OK request successful
 * @param CLIENT_ERROR client error
 * @param CLIENT_ERROR_BAD_REQUEST the request contains invalid data
 * @param CLIENT_ERROR_UNAUTHORIZED authentication required
 * @param CLIENT_ERROR_FORBIDDEN access denied
 * @param CLIENT_ERROR_NOT_FOUND the requested resource was not found
 * @param SERVER_ERROR server error
 * @param NO_INTERNET no internet
 */
enum class HttpStatus {
    OK,
    CLIENT_ERROR,
    CLIENT_ERROR_BAD_REQUEST,
    CLIENT_ERROR_UNAUTHORIZED,
    CLIENT_ERROR_FORBIDDEN,
    CLIENT_ERROR_NOT_FOUND,
    SERVER_ERROR,
    NO_INTERNET
}
