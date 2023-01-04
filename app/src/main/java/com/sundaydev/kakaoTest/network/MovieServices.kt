package com.sundaydev.kakaoTest.network

import com.sundaydev.kakaoTest.data.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("page") page: Int = 1): Movies

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(@Query("page") page: Int = 1): Movies

    @GET("movie/upcoming")
    suspend fun getUpComingMovie(@Query("page") page: Int = 1): Movies

    @GET("movie/top_rated")
    suspend fun getTopRatedMovie(@Query("page") page: Int = 1): Movies

    @GET("tv/popular")
    suspend fun getPopularTv(@Query("page") page: Int = 1): Tvs

    @GET("tv/on_the_air")
    suspend fun getNowPlayingTv(@Query("page") page: Int = 1): Tvs

    @GET("tv/airing_today")
    suspend fun getTodayTv(@Query("page") page: Int = 1): Tvs

    @GET("tv/top_rated")
    suspend fun getTopRatedTv(@Query("page") page: Int = 1): Tvs

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movie_id: Int): MovieDetail

    @GET("tv/{tv_id}")
    suspend fun getTvDetail(@Path("tv_id") tv_id: Int): MovieDetail

    @GET("person/popular")
    suspend fun getPeoples(@Query("page") page: Int = 1): Peoples

    @GET("person/popular")
    suspend fun getPeopleList(@Query("page") page: Int = 1): Peoples

    @GET("person/{person_id}")
    suspend fun getPeopleDetail(@Path("person_id") person_id: Int): PeopleDetail

    @GET("person/{person_id}/combined_credits")
    suspend fun getCredits(@Path("person_id") person_id: Int): PeopleCredits
}