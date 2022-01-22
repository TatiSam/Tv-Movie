package com.tatisam.movie.repository

import com.tatisam.movie.database.dao.MovieDao
import com.tatisam.movie.models.*
import com.tatisam.movie.network.MoviesApi

class MovieRepository(
    private val movieDao: MovieDao,
    private val moviesApi: MoviesApi
) {
    val moviesWithGenres = movieDao.getMoviesWithGenres()
    val movies = movieDao.getMovies()
    val genres = movieDao.getGenres()
    val tvs = movieDao.getTvs()
    val tvsWithGenres = movieDao.getTvsWithGenres()
    val trendingNow = movieDao.getTrendingNowList()
    val trendingWithGenres = movieDao.getTrendingListWithGenres()
    val favorites = movieDao.getFavoriteList()

    suspend fun fetchMoviesAndGenres(page: Int = 1) {
        //fetch data from the api:
        val movies = moviesApi.popularMovies(page).results
        val genres = moviesApi.genres().genres ?: mutableListOf()
        //store in local db:
        movieDao.addMovies(movies)
        movieDao.addGenres(genres)
        //cross refs:
        movies.forEach { movie ->
            movie.genreIds.forEach { genreId ->
                movieDao.addMovieGenreCrossRef(
                    MovieGenreCrossRef(movie.movieId, genreId.toLong())
                )
            }
        }
    }

    fun findMovieWithGenresById(id: Long, callback: (MoviesWithGenres) -> Unit){
        callback(movieDao.getMovieWithGenresById(id))
    }

    suspend fun fetchTvsAndGenres(page: Int = 1) {
        val tvs = moviesApi.popularTvs().results
        val genres = moviesApi.genres().genres ?: mutableListOf()
        movieDao.addTvs(tvs)
        movieDao.addGenres(genres)
        tvs.forEach { tv ->
            tv.genreIds.forEach { genreId ->
                movieDao.addTvGenreCrossRef(
                    TvGenreCrossRef(tv.tvId, genreId.toLong())
                )
            }
        }
    }

    fun findTvWithGenresById(id: Long, callback: (TvWithGenres) -> Unit){
        callback(movieDao.getTvWithGenresById(id))
    }

    suspend fun fetchTrendingNow(page: Int = 1) {
        val trendingNowList = moviesApi.trendingNow().results
        movieDao.addTrendingNowList(trendingNowList)
    }

    suspend fun fetchTrendingAndGenres(page: Int = 1) {
        val trendings = moviesApi.trendingNow(page).results
        val genres = moviesApi.genres().genres ?: mutableListOf()
        movieDao.addTrendingNowList(trendings)
        movieDao.addGenres(genres)
        trendings.forEach { trending ->
            trending.genreIds.forEach { genreId ->
                movieDao.addTrendingGenreCrossRef(
                    TrendingGenreCrossRef(trending.trendingNowId, genreId.toLong())
                )
            }
        }
    }

    fun findTrendingWithGenresById(id: Long, callback: (TrendingWithGenres) -> Unit){
        callback(movieDao.getTrendingWithGenresById(id))
    }

    suspend fun addFavorite(favorite: Favorite){
        movieDao.addFavorite(favorite)
    }

    suspend fun deleteFavorite(favorite: Favorite){
        movieDao.deleteFavorite(favorite)
    }

    fun isFavorite(favorite: Favorite): Int{
        return movieDao.isFavorite(favorite.favId)
    }
}
