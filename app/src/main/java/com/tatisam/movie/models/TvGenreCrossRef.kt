package com.tatisam.movie.models

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.tatisam.lec11.models.Genre
import kotlinx.parcelize.Parcelize

@Entity(primaryKeys = ["tvId", "genreId"])
data class TvGenreCrossRef(
    val tvId: Long,
    val genreId: Long
)

@Parcelize
data class TvWithGenres(
    @Embedded
    val tv: Tv,

    @Relation(
        parentColumn = "tvId",
        entityColumn = "genreId",
        associateBy = Junction(TvGenreCrossRef::class)
    )
    val genres: List<Genre>
) : Parcelable
