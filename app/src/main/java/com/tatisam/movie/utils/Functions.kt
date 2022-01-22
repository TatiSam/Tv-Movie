package com.tatisam.movie.utils

import android.widget.ImageButton
import com.tatisam.lec11.models.Genre
import com.tatisam.movie.MoviesApplication
import com.tatisam.movie.R
import com.tatisam.movie.models.Favorite
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun getGenresStr(genres: List<Genre>): String{
    val genresStr: StringBuilder = StringBuilder()
    for (g in genres){
        genresStr.append(" ${g.name}")
        if(genres.lastIndexOf(g) < genres.size - 1)
            genresStr.append(" |")
    }
    return genresStr.toString()
}

@DelicateCoroutinesApi
fun favBtnClick(favorite: Favorite, favBtn: ImageButton){
    GlobalScope.launch {
        if(MoviesApplication.repository.isFavorite(favorite) != 1){
            favBtn.setImageResource(R.drawable.red_favorite)
            MoviesApplication.repository.addFavorite(favorite)
        }else{
            favBtn.setImageResource(R.drawable.favorite)
            MoviesApplication.repository.deleteFavorite(favorite)
        }
    }
}

@DelicateCoroutinesApi
fun changeFavoriteIcon(favorite: Favorite, favBtn: ImageButton){
    GlobalScope.launch {
        if (MoviesApplication.repository.isFavorite(favorite) == 1) {
            favBtn.setImageResource(R.drawable.red_favorite)
        } else {
            favBtn.setImageResource(R.drawable.favorite)
        }
    }
}

