package com.tatisam.movie.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatisam.movie.MoviesApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val popularMovies = MoviesApplication.repository.moviesWithGenres
    val popularTvs = MoviesApplication.repository.tvsWithGenres
    val trendingNow = MoviesApplication.repository.trendingWithGenres

    init {
        MoviesApplication.networkStatusChecker.performIfConnectedToInternet {
            viewModelScope.launch(Dispatchers.IO) {
                MoviesApplication.repository.fetchMoviesAndGenres()
                MoviesApplication.repository.fetchTvsAndGenres()
                MoviesApplication.repository.fetchTrendingAndGenres()
            }
        }
    }
}