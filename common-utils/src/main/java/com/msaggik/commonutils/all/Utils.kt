package com.msaggik.commonutils.all

import android.content.Context
import android.util.Log
import com.msaggik.commonui.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object Utils {
    fun outputStackTrace(tag: String, e: Throwable) {
        e.stackTrace.forEach { element ->
            Log.e(
                tag,
                "Class: ${element.className}, Method: ${element.methodName}, Line: ${element.lineNumber}"
            )
        }
    }
    fun formatDate(
        context: Context,
        locale: Locale,
        inputDate: String
    ): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val outputFormatter = DateTimeFormatter.ofPattern("dd MMM", locale)

        return runCatching {
            val dateTime = LocalDateTime.parse(inputDate, inputFormatter)
            outputFormatter.format(dateTime)
        }.getOrElse {
            context.getString(R.string.incorrect_date)
        }
    }
}
