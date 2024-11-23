package com.msaggik.commonutils.all

import android.util.Log

object Utils {
    fun outputStackTrace(tag: String, e: Throwable) {
        e.stackTrace.forEach { element ->
            Log.e(
                tag,
                "Class: ${element.className}, Method: ${element.methodName}, Line: ${element.lineNumber}"
            )
        }
    }
}
