package com.sundaydev.kakaoTest.repository

import androidx.lifecycle.LiveData
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sundaydev.kakaoTest.data.People
import com.sundaydev.kakaoTest.data.PeopleCredits
import com.sundaydev.kakaoTest.data.PeopleDetail
import com.sundaydev.kakaoTest.data.Peoples
import com.sundaydev.kakaoTest.datasource.PeopleDataSourceFactory
import com.sundaydev.kakaoTest.network.MovieClient
import com.sundaydev.kakaoTest.util.workOnSchedulerIo
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.inject

interface PeopleRepository {
    fun getPeoples(): Single<Peoples>
    fun getPeopleDetail(id: Int): Single<PeopleDetail>
    fun getCredits(id: Int): Single<PeopleCredits>
    fun loadPeoples(disposable: CompositeDisposable): LiveData<PagedList<People>>
}

class PeopleRepositoryImpl : PeopleRepository, KoinComponent {
    private val apiClient: MovieClient by inject()
    override fun getPeoples(): Single<Peoples> = apiClient.movieApi.getPeoples().workOnSchedulerIo()
    override fun getPeopleDetail(id: Int): Single<PeopleDetail> = apiClient.movieApi.getPeopleDetail(id).workOnSchedulerIo()
    override fun getCredits(id: Int): Single<PeopleCredits> = apiClient.movieApi.getCredits(id).workOnSchedulerIo()

    override fun loadPeoples(disposable: CompositeDisposable): LiveData<PagedList<People>> {
        val factory = PeopleDataSourceFactory(apiClient.movieApi, disposable)
        return LivePagedListBuilder(factory, Config(CONTENTS_PAGE_SIZE)).build()
    }
}