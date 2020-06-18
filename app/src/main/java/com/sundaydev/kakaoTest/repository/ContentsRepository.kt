package com.sundaydev.kakaoTest.repository

import androidx.lifecycle.LiveData
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sundaydev.kakaoTest.data.*
import com.sundaydev.kakaoTest.datasource.MovieDataSourceFactory
import com.sundaydev.kakaoTest.datasource.TvDataSourceFactory
import com.sundaydev.kakaoTest.network.MovieClient
import com.sundaydev.kakaoTest.util.workOnSchedulerIo
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.inject

interface ContentsRepository {
    fun getPopularTv(page: Int = 1): Single<Tvs>
    fun getNowPlayingTv(page: Int = 1): Single<Tvs>
    fun getTodayTv(page: Int = 1): Single<Tvs>
    fun getTopRatedTv(page: Int = 1): Single<Tvs>

    fun getPopularMovie(page: Int = 1): Single<Movies>
    fun getNowPlayingMovie(page: Int = 1): Single<Movies>
    fun getUpComingMovie(page: Int = 1): Single<Movies>
    fun getTopRatedMovie(page: Int = 1): Single<Movies>

    fun getMovieDetail(id: Int): Single<MovieDetail>
    fun getTvDetail(id: Int): Single<MovieDetail>

    fun loadTvs(filterName: String, disposable: CompositeDisposable): LiveData<PagedList<Tv>>
    fun loadMovies(filterName: String, disposable: CompositeDisposable): LiveData<PagedList<Movie>>

    fun refreshTv()
    fun refreshMovie()
}

const val CONTENTS_PAGE_SIZE = 10

class ContentsRepositoryImpl : ContentsRepository, KoinComponent {
    private val apiClient: MovieClient by inject()
    private lateinit var tvDataSourceFactory: TvDataSourceFactory
    private lateinit var movieDataSourceFactory: MovieDataSourceFactory

    override fun getPopularTv(page: Int): Single<Tvs> = apiClient.movieApi.getPopularTv(page).workOnSchedulerIo()

    override fun getNowPlayingTv(page: Int): Single<Tvs> = apiClient.movieApi.getNowPlayingTv(page).workOnSchedulerIo()

    override fun getTodayTv(page: Int): Single<Tvs> = apiClient.movieApi.getTodayTv(page).workOnSchedulerIo()

    override fun getTopRatedTv(page: Int): Single<Tvs> = apiClient.movieApi.getTopRatedTv(page).workOnSchedulerIo()

    override fun getPopularMovie(page: Int): Single<Movies> = apiClient.movieApi.getPopularMovie(page).workOnSchedulerIo()

    override fun getNowPlayingMovie(page: Int): Single<Movies> = apiClient.movieApi.getNowPlayingMovie(page).workOnSchedulerIo()

    override fun getUpComingMovie(page: Int): Single<Movies> = apiClient.movieApi.getUpComingMovie(page).workOnSchedulerIo()

    override fun getTopRatedMovie(page: Int): Single<Movies> = apiClient.movieApi.getTopRatedMovie(page).workOnSchedulerIo()

    override fun getMovieDetail(id: Int): Single<MovieDetail> = apiClient.movieApi.getMovieDetail(id).workOnSchedulerIo()

    override fun getTvDetail(id: Int): Single<MovieDetail> = apiClient.movieApi.getTvDetail(id).workOnSchedulerIo()
    override fun loadTvs(filterName: String, disposable: CompositeDisposable): LiveData<PagedList<Tv>> {
        tvDataSourceFactory = TvDataSourceFactory(apiClient.movieApi, filterName, disposable)
        return LivePagedListBuilder(tvDataSourceFactory, Config(pageSize = CONTENTS_PAGE_SIZE)).build()
    }

    override fun loadMovies(filterName: String, disposable: CompositeDisposable): LiveData<PagedList<Movie>> {
        movieDataSourceFactory = MovieDataSourceFactory(apiClient.movieApi, filterName, disposable)
        return LivePagedListBuilder(movieDataSourceFactory, Config(pageSize = CONTENTS_PAGE_SIZE)).build()
    }

    override fun refreshTv() = tvDataSourceFactory.refresh()
    override fun refreshMovie() = movieDataSourceFactory.refresh()
}