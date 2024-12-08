package com.example.movieapp.core.common.utils

import android.annotation.SuppressLint

object NumberUtils {
    @SuppressLint("DefaultLocale")
    fun formatRating(rating: Double): String {
        return String.format("%.1f", rating)
    }

    @SuppressLint("DefaultLocale")
    fun formatVoteCount(voteCount: Int): String {
        return when {
            voteCount >= 1_000_000 -> String.format("%.1fM", voteCount / 1_000_000.0)
            voteCount >= 1_000 -> String.format("%.1fK", voteCount / 1_000.0)
            else -> voteCount.toString()
        }
    }
}