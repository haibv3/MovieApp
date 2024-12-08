package com.example.movieapp.core.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LanguageDto(
    @Json(name = "iso_639_1")
    val iso: String,
    val name: String,
    @Json(name = "english_name")
    val englishName: String
)