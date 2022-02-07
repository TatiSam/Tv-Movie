package com.tatisam.movie.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Tv constructor(
    @PrimaryKey
    @SerializedName("id")
    val tvId: Long,
    val name: String?,
    @SerializedName("original_name")
    val originalName: String?,
    @SerializedName("original_language")
    val originalLanguage: String?,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    val overview: String?,
    val popularity: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Long?
) : Parcelable {
    @Ignore
    @SerializedName("genre_ids")
    val genreIds: List<Int> = mutableListOf()
}