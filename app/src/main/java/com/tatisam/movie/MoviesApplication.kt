package com.tatisam.movie

import android.app.Application
import android.net.ConnectivityManager
import com.tatisam.movie.database.MovieDataBase
import com.tatisam.movie.network.MoviesApi
import com.tatisam.movie.network.NetworkStatusChecker
import com.tatisam.movie.repository.MovieRepository

class MoviesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object {
        private lateinit var instance: MoviesApplication
        private val db: MovieDataBase by lazy {
            MovieDataBase.create(instance)
        }
        val repository: MovieRepository by lazy {
            MovieRepository(db.movieDao(), MoviesApi.create())
        }

        val networkStatusChecker: NetworkStatusChecker by lazy{
            NetworkStatusChecker(instance.getSystemService(ConnectivityManager::class.java))
        }
    }
}

