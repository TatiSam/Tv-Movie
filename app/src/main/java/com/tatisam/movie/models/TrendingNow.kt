package com.tatisam.movie.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class TrendingNow constructor(
    @PrimaryKey
    @SerializedName("id")
    val trendingNowId: Long,
    @SerializedName("media_type")
    val mediaType: String?,
    val adult: Boolean?,
    val title: String?,
    val name: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("original_name")
    val originalName: String?,
    val overview: String?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    val video: Boolean?,
    val popularity: Double?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Long?
) : Parcelable {
    @Ignore
    @SerializedName("genre_ids")
    val genreIds: List<Int> = mutableListOf()
}