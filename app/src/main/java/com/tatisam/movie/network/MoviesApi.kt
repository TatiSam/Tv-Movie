package com.tatisam.movie.network

import com.tatisam.movie.BuildConfig
import com.tatisam.movie.models.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    //get popular Movie list
    @GET("3/movie/popular")
    suspend fun popularMovies(@Query("page") page : Int = 1): MovieResponse

    //get Genre list
    @GET("3/genre/movie/list")
    suspend fun genres(): GenreResponse

    //get popular Tv list
    @GET("3/tv/popular")
    suspend fun popularTvs(@Query("page") page : Int = 1): TvResponse

    //get TrendingNow list
    @GET("3/trending/all/day")
    suspend fun trendingNow(@Query("page") page: Int = 1): TrendingNowResponse

    companion object {
        private const val BASE_URL = BuildConfig.TMDB_BASE_URL
        private const val APIKEY = BuildConfig.TMDB_API_KEY
        private fun createInterceptor() = Interceptor { chain ->
            val originalRequest = chain.request()
            val urlQueryParameter = originalRequest.url.newBuilder()
                .addQueryParameter(
                    "api_key",
                    APIKEY
                ).build()
            val new = originalRequest.newBuilder().url(urlQueryParameter)
                .build()
            chain.proceed(new)
        }

        private fun getClient(interceptor: Interceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .addInterceptor(interceptor)
                .build()
        }

        fun create(): MoviesApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient(createInterceptor()))
                .build()
                .create(MoviesApi::class.java)
        }
    }
}
