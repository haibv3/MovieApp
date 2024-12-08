package com.example.movieapp.core.common.extension

import kotlin.math.roundToInt

fun Double.toRating(): Float {
    return (this * 10).roundToInt() / 10f
}

fun Float.toPercentage(): String {
    return "${(this * 100).roundToInt()}%"
}