package com.example.movieapp.core.common.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {
    private const val API_DATE_FORMAT = "yyyy-MM-dd"
    private const val DISPLAY_DATE_FORMAT = "dd MMM yyyy"
    
    private val apiDateFormatter = SimpleDateFormat(API_DATE_FORMAT, Locale.US)
    private val displayDateFormatter = SimpleDateFormat(DISPLAY_DATE_FORMAT, Locale.US)

    fun parseApiDate(dateString: String?): Date? {
        if (dateString.isNullOrEmpty()) return null
        return try {
            apiDateFormatter.parse(dateString)
        } catch (e: Exception) {
            null
        }
    }

    fun formatToDisplayDate(date: Date?): String {
        if (date == null) return ""
        return displayDateFormatter.format(date)
    }

    fun formatApiDateToDisplay(dateString: String?): String {
        val date = parseApiDate(dateString)
        return formatToDisplayDate(date)
    }
}