package com.tatisam.movie.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Movie constructor(
    @PrimaryKey
    @SerializedName("id")
    val movieId: Long,
    val adult: Boolean,
    val title: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    val video: Boolean,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val popularity: Double,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Long
) : Parcelable {
    @Ignore
    @SerializedName("genre_ids")
    val genreIds: List<Int> = mutableListOf()
}