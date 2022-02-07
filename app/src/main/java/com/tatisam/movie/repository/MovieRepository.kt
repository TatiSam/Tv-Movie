package com.tatisam.movie.repository

import com.tatisam.movie.database.dao.*
import com.tatisam.movie.models.*
import com.tatisam.movie.network.TMDbApi

class MovieRepository(
    private val movieDao: MovieDao,
    private val genreDao: GenreDao,
    private val tmDbApi: TMDbApi
) {
    val moviesWithGenres = movieDao.getMoviesWithGenres()

    suspend fun fetchMoviesAndGenres(page:Int = 1) {
        val genres = tmDbApi.genres().genres ?: mutableListOf()
        genreDao.addGenres(genres)
        val movies = tmDbApi.popularMovies(page).results
        movieDao.addMovies(movies)
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

}
