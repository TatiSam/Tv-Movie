package com.tatisam.movie.models

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Long,
    @SerializedName("total_results")
    val totalResults: Long,
    //error handling: api can give us more information:
    @SerializedName("status_message")
    val statusMessage: String?,
    @SerializedName("status_code")
    val statusCode: Int?,
    val success: Boolean?
)
