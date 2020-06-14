package com.sundaydev.kakaoTest.network

import com.sundaydev.kakaoTest.data.*
import io.reactivex.Single
import retrofit2.http.GET

interface NullServices {
    @GET()
    fun getPopularTv(page: Int = 0): Single<Tvs>
    @GET()
    fun getNowPlayingTv(page: Int = 0): Single<Tvs>
    @GET()
    fun getUpComingTv(page: Int = 0): Single<Tvs>
    @GET()
    fun getTopRatedTv(page: Int = 0): Single<Tvs>

    @GET()
    fun getPopularMovie(page: Int = 0): Single<Movies>
    @GET()
    fun getNowPlayingMovie(page: Int = 0): Single<Movies>
    @GET()
    fun getUpComingMovie(page: Int = 0): Single<Movies>
    @GET()
    fun getTopRatedMovie(page: Int = 0): Single<Movies>

    @GET()
    fun getMovieDetail(id: Int): Single<MovieDetail>
    @GET()
    fun getTvDetail(id: Int): Single<MovieDetail>

    @GET()
    fun getPeoples(): Single<Peoples>
    @GET()
    fun getPeopleDetail(id: Int): Single<PeopleDetail>
    @GET()
    fun getCredits(id: Int): Single<PeopleCredits>
}