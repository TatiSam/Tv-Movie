package com.tatisam.movie.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tatisam.movie.database.dao.*
import com.tatisam.movie.models.*

const val DB_VERSION = 1
const val DB_NAME = "TMDbDatabase"

@Database(entities = [
    Movie::class, Genre::class, MovieGenreCrossRef::class,
    Tv::class, TvGenreCrossRef::class,
    TrendingNow::class, TrendingGenreCrossRef::class,
    Favorite::class], version = DB_VERSION)
abstract class TMDbDatabase: RoomDatabase() {
    companion object{
        fun create(context: Context): TMDbDatabase {
            return Room.databaseBuilder(context, TMDbDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun movieDao(): MovieDao
    abstract fun genreDao(): GenreDao
    abstract fun tvDao(): TvDao
    abstract fun trendingDao(): TrendingNowDao
    abstract fun favoriteDao(): FavoriteDao

}