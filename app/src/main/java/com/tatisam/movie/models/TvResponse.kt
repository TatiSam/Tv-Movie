package com.tatisam.movie.models

import com.google.gson.annotations.SerializedName

data class TvResponse(
    val page: Int,
    val results: List<Tv>,
    @SerializedName("total_pages")
    val totalPages: Long,
    @SerializedName("total_results")
    val totalResults: Long,
    @SerializedName("status_message")
    val statusMessage: String?,
    @SerializedName("status_code")
    val statusCode: Int?,
    val success: Boolean?
)