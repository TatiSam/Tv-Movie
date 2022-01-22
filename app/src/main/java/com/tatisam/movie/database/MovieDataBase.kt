package com.tatisam.movie.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tatisam.lec11.models.*
import com.tatisam.movie.database.dao.MovieDao
import com.tatisam.movie.models.*

const val DB_VERSION = 2
const val DB_NAME = "MoviesDatabase"

@Database(entities = [
    Movie::class, Genre::class, MovieGenreCrossRef::class,
    Tv::class, TvGenreCrossRef::class,
    TrendingNow::class, TrendingGenreCrossRef::class,
    Favorite::class], version = DB_VERSION)
abstract class MovieDataBase: RoomDatabase() {
    companion object{
        fun create(context: Context): MovieDataBase{
            val db = Room.databaseBuilder(context, MovieDataBase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
            return db
        }
    }

    abstract fun movieDao(): MovieDao
}