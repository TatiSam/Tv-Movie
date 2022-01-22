package com.tatisam.movie.models

import com.google.gson.annotations.SerializedName

data class TrendingNowResponse (
    val page: Int,
    val results: List<TrendingNow>,
    @SerializedName("total_pages")
    val totalPages: Long,
    @SerializedName("total_results")
    val totalResults: Long
)