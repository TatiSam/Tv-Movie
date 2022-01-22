package com.tatisam.movie.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.tatisam.lec11.models.Genre
import kotlinx.parcelize.Parcelize

@Entity(primaryKeys = ["movieId", "genreId"])
data class MovieGenreCrossRef(
    val movieId: Long,
    val genreId: Long
)

@Parcelize
data class MoviesWithGenres(
    @Embedded
    val movie: Movie,

    @Relation(
        parentColumn = "movieId",
        entityColumn = "genreId",
        associateBy = Junction(MovieGenreCrossRef::class)
    )
    val genres: List<Genre>
) : Parcelable
