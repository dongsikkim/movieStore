package com.sundaydev.kakaoTest.repository

import com.sundaydev.kakaoTest.data.MovieDetail
import com.sundaydev.kakaoTest.data.Movies
import com.sundaydev.kakaoTest.data.Tvs
import com.sundaydev.kakaoTest.network.MovieClient
import com.sundaydev.kakaoTest.ui.movie.MovieTabInfo
import com.sundaydev.kakaoTest.ui.tv.TvTabInfo
import org.koin.core.KoinComponent
import org.koin.core.inject

interface ContentsRepository {
    suspend fun getMovieDetail(id: Int): MovieDetail
    suspend fun getTvDetail(id: Int): MovieDetail
    suspend fun loadTvs(filterName: String, page: Int): Tvs
    suspend fun loadMovies(filterName: String, page: Int): Movies
}

const val CONTENTS_PAGE_SIZE = 20

class ContentsRepositoryImpl : ContentsRepository, KoinComponent {
    private val apiClient: MovieClient by inject()

    override suspend fun getMovieDetail(id: Int): MovieDetail = apiClient.movieApi.getMovieDetail(id)

    override suspend fun getTvDetail(id: Int): MovieDetail = apiClient.movieApi.getTvDetail(id)

    override suspend fun loadMovies(filterName: String, page: Int): Movies =
        when (filterName) {
            MovieTabInfo.MOVIE_POPULAR.name -> apiClient.movieApi.getPopularMovie(page)
            MovieTabInfo.MOVIE_NOW_PLAYING.name -> apiClient.movieApi.getNowPlayingMovie(page)
            MovieTabInfo.MOVIE_UPCOMING.name -> apiClient.movieApi.getUpComingMovie(page)
            MovieTabInfo.MOVIE_TOP_RATE.name -> apiClient.movieApi.getTopRatedMovie(page)
            else -> apiClient.movieApi.getUpComingMovie(page)
        }

    override suspend fun loadTvs(filterName: String, page: Int): Tvs =
        when (filterName) {
            TvTabInfo.TV_POPULAR.name -> apiClient.movieApi.getPopularTv(page)
            TvTabInfo.TV_TODAY.name -> apiClient.movieApi.getTodayTv(page)
            TvTabInfo.TV_NOW_PLAYING.name -> apiClient.movieApi.getNowPlayingTv(page)
            TvTabInfo.TV_TOP_RATE.name -> apiClient.movieApi.getTopRatedTv(page)
            else -> apiClient.movieApi.getPopularTv(page)
        }
}