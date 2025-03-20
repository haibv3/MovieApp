package com.example.movieapp.core.common.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val API_DATE_FORMAT = "yyyy-MM-dd"
const val DISPLAY_DATE_FORMAT = "dd MMM yyyy"

fun String.toDate(pattern: String = API_DATE_FORMAT): Date? {
    return try {
        SimpleDateFormat(pattern, Locale.getDefault()).parse(this)
    } catch (e: Exception) {
        null
    }
}

fun Date.format(pattern: String = DISPLAY_DATE_FORMAT): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(this)
}
