package com.example.movieapp.core.ui.utils

import androidx.compose.ui.graphics.Color
import com.example.movieapp.core.ui.theme.rating_high
import com.example.movieapp.core.ui.theme.rating_low
import com.example.movieapp.core.ui.theme.rating_medium

object RatingUtils {
    fun getRatingColor(rating: Double): Color {
        return when {
            rating >= 7.0 -> rating_high
            rating >= 5.0 -> rating_medium
            else -> rating_low
        }
    }

    fun getRatingPercentage(rating: Double): Float {
        return (rating / 10f).toFloat()
    }
}