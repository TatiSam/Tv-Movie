package com.tatisam.movie.models

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity
data class Genre(
    @PrimaryKey
    @SerializedName("id")
    val genreId: String = UUID.randomUUID().toString(),
    @ColumnInfo(name="genre_name")
    val name: String
) : Parcelable
