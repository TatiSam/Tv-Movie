package com.tatisam.movie.ui.list

import androidx.lifecycle.ViewModel
import com.tatisam.movie.MoviesApplication

class ListViewModel : ViewModel() {

    val popularMovies = MoviesApplication.movieRepository.moviesWithGenres
    val popularTvs = MoviesApplication.tvRepository.tvsWithGenres
    val trendingNow = MoviesApplication.trendingRepository.trendingWithGenres

}