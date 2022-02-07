package com.tatisam.movie.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tatisam.movie.models.*

@Dao
interface TvDao {

    //Tv
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTv(tv: Tv)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTvs(tvs: List<Tv>)

    @Transaction
    @Query("SELECT * FROM Tv")
    fun getTvs(): LiveData<List<Tv>>

    //Tv with genres
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTvGenreCrossRef(tvGenreCrossRef: TvGenreCrossRef)

    @Transaction
    @Query("SELECT * FROM Tv")
    fun getTvsWithGenres(): LiveData<List<TvWithGenres>>

    @Transaction
    @Query("SELECT * FROM Tv WHERE tvId = :id")
    fun getTvWithGenresById(id: Long): TvWithGenres

}
