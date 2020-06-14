package com.sundaydev.kakaoTest.repository

import com.sundaydev.kakaoTest.data.MovieDetail
import com.sundaydev.kakaoTest.data.Movies
import com.sundaydev.kakaoTest.data.Tvs
import io.reactivex.Single
import org.koin.core.KoinComponent

interface ContentsRepository {
    fun getPopularTv(page: Int = 0): Single<Tvs>
    fun getNowPlayingTv(page: Int = 0): Single<Tvs>
    fun getUpComingTv(page: Int = 0): Single<Tvs>
    fun getTopRatedTv(page: Int = 0): Single<Tvs>

    fun getPopularMovie(page: Int = 0): Single<Movies>
    fun getNowPlayingMovie(page: Int = 0): Single<Movies>
    fun getUpComingMovie(page: Int = 0): Single<Movies>
    fun getTopRatedMovie(page: Int = 0): Single<Movies>

    fun getMovieDetail(id: Int): Single<MovieDetail>
    fun getTvDetail(id: Int): Single<MovieDetail>

}

class ContentsRepositoryImpl : ContentsRepository, KoinComponent {
    override fun getPopularTv(page: Int): Single<Tvs> {
        TODO("Not yet implemented")
    }

    override fun getNowPlayingTv(page: Int): Single<Tvs> {
        TODO("Not yet implemented")
    }

    override fun getUpComingTv(page: Int): Single<Tvs> {
        TODO("Not yet implemented")
    }

    override fun getTopRatedTv(page: Int): Single<Tvs> {
        TODO("Not yet implemented")
    }

    override fun getPopularMovie(page: Int): Single<Movies> {
        TODO("Not yet implemented")
    }

    override fun getNowPlayingMovie(page: Int): Single<Movies> {
        TODO("Not yet implemented")
    }

    override fun getUpComingMovie(page: Int): Single<Movies> {
        TODO("Not yet implemented")
    }

    override fun getTopRatedMovie(page: Int): Single<Movies> {
        TODO("Not yet implemented")
    }

    override fun getMovieDetail(id: Int): Single<MovieDetail> {
        TODO("Not yet implemented")
    }

    override fun getTvDetail(id: Int): Single<MovieDetail> {
        TODO("Not yet implemented")
    }
}