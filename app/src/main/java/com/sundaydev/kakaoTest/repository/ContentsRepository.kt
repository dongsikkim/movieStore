package com.sundaydev.kakaoTest.repository

import androidx.lifecycle.LiveData
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sundaydev.kakaoTest.data.Movie
import com.sundaydev.kakaoTest.data.MovieDetail
import com.sundaydev.kakaoTest.data.Tv
import com.sundaydev.kakaoTest.datasource.MovieDataSourceFactory
import com.sundaydev.kakaoTest.datasource.TvDataSourceFactory
import com.sundaydev.kakaoTest.network.MovieClient
import com.sundaydev.kakaoTest.util.workOnSchedulerIo
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.inject

interface ContentsRepository {
    fun getMovieDetail(id: Int): Single<MovieDetail>
    fun getTvDetail(id: Int): Single<MovieDetail>

    fun loadTvs(filterName: String, factory: TvDataSourceFactory, disposable: CompositeDisposable): LiveData<PagedList<Tv>>
    fun loadMovies(filterName: String, disposable: CompositeDisposable): MovieResult

    fun refreshTv(factory: TvDataSourceFactory?): Unit?
    fun refreshMovie(factory: MovieDataSourceFactory?): Unit?
}

const val CONTENTS_PAGE_SIZE = 10

class ContentsRepositoryImpl : ContentsRepository, KoinComponent {
    private val apiClient: MovieClient by inject()

    override fun getMovieDetail(id: Int): Single<MovieDetail> = apiClient.movieApi.getMovieDetail(id).workOnSchedulerIo()

    override fun getTvDetail(id: Int): Single<MovieDetail> = apiClient.movieApi.getTvDetail(id).workOnSchedulerIo()

    override fun loadMovies(filterName: String, disposable: CompositeDisposable) =
        MovieDataSourceFactory(apiClient.movieApi, filterName, disposable).let { factory ->
            MovieResult(factory, LivePagedListBuilder(factory, Config(pageSize = CONTENTS_PAGE_SIZE)).build())
        }

    override fun loadTvs(filterName: String, factory: TvDataSourceFactory, disposable: CompositeDisposable) =
        LivePagedListBuilder(factory, Config(pageSize = CONTENTS_PAGE_SIZE)).build()

    override fun refreshTv(factory: TvDataSourceFactory?) = factory?.refresh()

    override fun refreshMovie(factory: MovieDataSourceFactory?) = factory?.refresh()
}

data class MovieResult(val factory: MovieDataSourceFactory, val movieData: LiveData<PagedList<Movie>>)