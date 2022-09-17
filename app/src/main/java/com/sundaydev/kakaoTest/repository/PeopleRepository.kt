package com.sundaydev.kakaoTest.repository

import com.sundaydev.kakaoTest.data.PeopleCredits
import com.sundaydev.kakaoTest.data.PeopleDetail
import com.sundaydev.kakaoTest.data.Peoples
import com.sundaydev.kakaoTest.network.MovieClient
import com.sundaydev.kakaoTest.util.workOnSchedulerIo
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.inject

interface PeopleRepository {
    fun getPeoples(): Single<Peoples>
    fun getPeopleDetail(id: Int): Single<PeopleDetail>
    fun getCredits(id: Int): Single<PeopleCredits>
    suspend fun getPeopleList(page: Int): Peoples
}

class PeopleRepositoryImpl : PeopleRepository, KoinComponent {
    private val apiClient: MovieClient by inject()
    override fun getPeoples(): Single<Peoples> = apiClient.movieApi.getPeoples().workOnSchedulerIo()
    override fun getPeopleDetail(id: Int): Single<PeopleDetail> = apiClient.movieApi.getPeopleDetail(id).workOnSchedulerIo()
    override fun getCredits(id: Int): Single<PeopleCredits> = apiClient.movieApi.getCredits(id).workOnSchedulerIo()
    override suspend fun getPeopleList(page: Int): Peoples = apiClient.movieApi.getPeopleList(page = page)
}