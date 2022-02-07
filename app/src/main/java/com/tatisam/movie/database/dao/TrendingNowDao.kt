package com.tatisam.movie.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tatisam.movie.models.*

@Dao
interface TrendingNowDao {

    //TrendingNow
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTrendingNow(trendingNow: TrendingNow)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTrendingNowList(trendingNowList: List<TrendingNow>)

    @Transaction
    @Query("SELECT * FROM TrendingNow")
    fun getTrendingNowList(): LiveData<List<TrendingNow>>

    //TrendingNow with genres
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTrendingGenreCrossRef(trendingGenreCrossRef: TrendingGenreCrossRef)

    @Transaction
    @Query("SELECT * FROM TrendingNow")
    fun getTrendingListWithGenres(): LiveData<List<TrendingWithGenres>>

    @Transaction
    @Query("SELECT * FROM TrendingNow WHERE trendingNowId = :id")
    fun getTrendingWithGenresById(id: Long): TrendingWithGenres

}
