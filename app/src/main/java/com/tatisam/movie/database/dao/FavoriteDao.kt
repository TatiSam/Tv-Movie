package com.tatisam.movie.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tatisam.movie.models.*

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: Favorite)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteList(favoriteList: List<Favorite>)

    @Transaction
    @Query("SELECT * FROM favorites")
    fun getFavoriteList(): LiveData<List<Favorite>>

    @Transaction
    @Query("SELECT EXISTS (SELECT 1 FROM favorites WHERE favId = :id)")
    fun isFavorite(id: Long): Int

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

}
