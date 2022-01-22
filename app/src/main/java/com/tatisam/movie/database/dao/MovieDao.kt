package com.tatisam.movie.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tatisam.lec11.models.Genre
import com.tatisam.movie.models.*

@Dao
interface MovieDao {
    //Movie
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: Movie)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movies: List<Movie>)
    @Transaction
    @Query("SELECT * FROM Movie")
    fun getMovies(): LiveData<List<Movie>>
    //Genre
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGenre(genre: Genre)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGenres(genres: List<Genre>)
    @Transaction
    @Query("SELECT * FROM Genre")
    fun getGenres(): LiveData<List<Genre>>
    //Movies with genres
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieGenreCrossRef(movieGenreCrossRef: MovieGenreCrossRef)
    @Transaction
    @Query("SELECT * FROM Movie")
    fun getMoviesWithGenres(): LiveData<List<MoviesWithGenres>>
    @Query("SELECT * FROM Movie WHERE movieId = :id")
    fun getMovieWithGenresById(id: Long): MoviesWithGenres

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

    //Trending Now
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

    //Favorite List
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
