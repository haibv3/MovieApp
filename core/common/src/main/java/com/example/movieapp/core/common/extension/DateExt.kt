package com.example.movieapp.core.common.extension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.toDate(pattern: String = "yyyy-MM-dd"): Date? {
    return try {
        SimpleDateFormat(pattern, Locale.getDefault()).parse(this)
    } catch (e: Exception) {
        null
    }
}

fun Date.format(pattern: String = "dd MMM yyyy"): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(this)
}
