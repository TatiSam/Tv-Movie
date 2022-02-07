package com.tatisam.movie.ui.favorite

import androidx.lifecycle.ViewModel
import com.tatisam.movie.MoviesApplication

class FavoriteViewModel : ViewModel() {

    val favoriteList = MoviesApplication.favoriteRepository.favorites

}