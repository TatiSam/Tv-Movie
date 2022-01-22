package com.tatisam.movie.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey
    val favId: Long,
    val favImage: String,
    val favTitle: String?,
    val favType: String
        )