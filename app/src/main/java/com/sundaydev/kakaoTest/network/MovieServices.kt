package com.sundaydev.kakaoTest.network

import com.sundaydev.kakaoTest.data.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface NullServices {
    @GET("/movie/popular")
    fun getPopularMovie(page: Int = 1): Single<Movies>

    @GET("/movie/now_playing")
    fun getNowPlayingMovie(page: Int = 1): Single<Movies>

    @GET("/movie/upcoming")
    fun getUpComingMovie(page: Int = 1): Single<Movies>

    @GET("/movie/top_rated")
    fun getTopRatedMovie(page: Int = 1): Single<Movies>

    @GET("/tv/popular")
    fun getPopularTv(page: Int = 1): Single<Tvs>

    @GET("/tv/on_the_air")
    fun getNowPlayingTv(page: Int = 1): Single<Tvs>

    @GET("/tv/airing_today")
    fun getTodayTv(page: Int = 1): Single<Tvs>

    @GET("/tv/top_rated")
    fun getTopRatedTv(page: Int = 1): Single<Tvs>

    @GET("/movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movie_id: Int): Single<MovieDetail>

    @GET("/tv/{tv_id}")
    fun getTvDetail(@Path("tv_id") tv_id: Int): Single<MovieDetail>

    @GET("/person/popular")
    fun getPeoples(page: Int = 1): Single<Peoples>

    @GET("/person/{person_id}")
    fun getPeopleDetail(@Path("person_id") person_id: Int): Single<PeopleDetail>

    @GET("/person/{person_id}/combined_credits")
    fun getCredits(@Path("person_id") person_id: Int): Single<PeopleCredits>
}