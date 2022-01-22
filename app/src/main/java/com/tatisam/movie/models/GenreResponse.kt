package com.tatisam.movie.models

import com.google.gson.annotations.SerializedName
import com.tatisam.lec11.models.Genre

data class GenreResponse(
    val genres: List<Genre>?,
    @SerializedName("status_message")
    val statusMessage: String?,
    @SerializedName("status_code")
    val statusCode: Int?,
    val success: Boolean?
)