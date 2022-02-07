package com.tatisam.movie.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tatisam.movie.models.Genre

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGenre(genre: Genre)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGenres(genres: List<Genre>)

    @Transaction
    @Query("SELECT * FROM Genre")
    fun getGenres(): LiveData<List<Genre>>

}
