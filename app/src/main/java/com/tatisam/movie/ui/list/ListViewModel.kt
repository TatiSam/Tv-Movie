package com.tatisam.movie.ui.list

import androidx.lifecycle.ViewModel
import com.tatisam.movie.MoviesApplication

class ListViewModel : ViewModel() {
    val popularMovies = MoviesApplication.repository.moviesWithGenres
    val popularTvs = MoviesApplication.repository.tvsWithGenres
    val trendingNow = MoviesApplication.repository.trendingWithGenres
}