package net.hafiznaufalr.mobilequestion.network

import kotlinx.coroutines.Deferred
import net.hafiznaufalr.mobilequestion.model.Model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") apiKey: String
    ): Deferred<MovieResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovie(
        @Query("api_key") apiKey: String
    ): Deferred<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovie(
        @Query("api_key") apiKey: String
    ): Deferred<MovieResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovie(
        @Query("api_key") apiKey: String
    ): Deferred<MovieResponse>
}