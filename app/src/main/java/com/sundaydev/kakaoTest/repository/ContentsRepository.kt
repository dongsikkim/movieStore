package com.sundaydev.kakaoTest.repository

import androidx.lifecycle.LiveData
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sundaydev.kakaoTest.data.Movie
import com.sundaydev.kakaoTest.data.MovieDetail
import com.sundaydev.kakaoTest.data.Tvs
import com.sundaydev.kakaoTest.datasource.MovieDataSourceFactory
import com.sundaydev.kakaoTest.network.MovieClient
import com.sundaydev.kakaoTest.ui.tv.TvTabInfo
import com.sundaydev.kakaoTest.util.workOnSchedulerIo
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.inject

interface ContentsRepository {
    fun getMovieDetail(id: Int): Single<MovieDetail>
    fun getTvDetail(id: Int): Single<MovieDetail>

    suspend fun loadTvs(filterName: String, page : Int): Tvs
    fun loadMovies(filterName: String, disposable: CompositeDisposable): MovieResult

//    fun refreshTv(factory: TvDataSourceFactory?): Unit?
    fun refreshMovie(factory: MovieDataSourceFactory?): Unit?
}

const val CONTENTS_PAGE_SIZE = 20

class ContentsRepositoryImpl : ContentsRepository, KoinComponent {
    private val apiClient: MovieClient by inject()

    override fun getMovieDetail(id: Int): Single<MovieDetail> = apiClient.movieApi.getMovieDetail(id).workOnSchedulerIo()

    override fun getTvDetail(id: Int): Single<MovieDetail> = apiClient.movieApi.getTvDetail(id).workOnSchedulerIo()

    override fun loadMovies(filterName: String, disposable: CompositeDisposable) =
        MovieDataSourceFactory(apiClient.movieApi, filterName, disposable).let { factory ->
            MovieResult(factory, LivePagedListBuilder(factory, Config(pageSize = CONTENTS_PAGE_SIZE)).build())
        }

    override suspend fun loadTvs(filterName: String, page: Int): Tvs =
        when (filterName) {
            TvTabInfo.TV_POPULAR.name -> apiClient.movieApi.getPopularTv(page)
            TvTabInfo.TV_TODAY.name -> apiClient.movieApi.getTodayTv(page)
            TvTabInfo.TV_NOW_PLAYING.name -> apiClient.movieApi.getNowPlayingTv(page)
            TvTabInfo.TV_TOP_RATE.name -> apiClient.movieApi.getTopRatedTv(page)
            else -> apiClient.movieApi.getPopularTv(page)
        }


//    override fun refreshTv(factory: TvDataSourceFactory?) = factory?.refresh()

    override fun refreshMovie(factory: MovieDataSourceFactory?) = factory?.refresh()
}

data class MovieResult(val factory: MovieDataSourceFactory, val movieData: LiveData<PagedList<Movie>>)