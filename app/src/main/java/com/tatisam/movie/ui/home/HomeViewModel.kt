package com.tatisam.movie.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatisam.movie.MoviesApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val popularMovies = MoviesApplication.movieRepository.moviesWithGenres
    val popularTvs = MoviesApplication.tvRepository.tvsWithGenres
    val trendingNow = MoviesApplication.trendingRepository.trendingWithGenres


    init {
        MoviesApplication.networkStatusChecker.performIfConnectedToInternet {
            viewModelScope.launch(Dispatchers.IO) {
                MoviesApplication.movieRepository.fetchMoviesAndGenres()
                MoviesApplication.tvRepository.fetchTvsAndGenres()
                MoviesApplication.trendingRepository.fetchTrendingAndGenres()
            }
        }
    }

}