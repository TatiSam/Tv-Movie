package com.tatisam.movie.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Entity(primaryKeys = ["trendingNowId", "genreId"])
data class TrendingGenreCrossRef(
    val trendingNowId: Long,
    val genreId: Long
)

@Parcelize
data class TrendingWithGenres(
    @Embedded
    val trendingNow: TrendingNow,

    @Relation(
        parentColumn = "trendingNowId",
        entityColumn = "genreId",
        associateBy = Junction(TrendingGenreCrossRef::class)
    )
    val genres: List<Genre>
) : Parcelable
