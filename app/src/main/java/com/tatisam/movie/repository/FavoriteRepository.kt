package com.tatisam.movie.repository

import com.tatisam.movie.database.dao.*
import com.tatisam.movie.models.*

class FavoriteRepository(
    private val favoriteDao: FavoriteDao
) {
    val favorites = favoriteDao.getFavoriteList()

    suspend fun addFavorite(favorite: Favorite){
        favoriteDao.addFavorite(favorite)
    }

    suspend fun deleteFavorite(favorite: Favorite){
        favoriteDao.deleteFavorite(favorite)
    }

    fun isFavorite(favorite: Favorite): Int{
        return favoriteDao.isFavorite(favorite.favId)
    }

}
