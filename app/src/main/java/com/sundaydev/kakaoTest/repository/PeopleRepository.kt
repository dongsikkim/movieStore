package com.sundaydev.kakaoTest.repository

import com.sundaydev.kakaoTest.data.PeopleCredits
import com.sundaydev.kakaoTest.data.PeopleDetail
import com.sundaydev.kakaoTest.data.Peoples
import com.sundaydev.kakaoTest.network.MovieClient
import org.koin.core.KoinComponent
import org.koin.core.inject

interface PeopleRepository {
    suspend fun getPeoples(): Peoples
    suspend fun getPeopleDetail(id: Int): PeopleDetail
    suspend fun getCredits(id: Int): PeopleCredits
    suspend fun getPeopleList(page: Int): Peoples
}

class PeopleRepositoryImpl : PeopleRepository, KoinComponent {
    private val apiClient: MovieClient by inject()
    override suspend fun getPeoples(): Peoples = apiClient.movieApi.getPeoples()
    override suspend fun getPeopleDetail(id: Int): PeopleDetail = apiClient.movieApi.getPeopleDetail(id)
    override suspend fun getCredits(id: Int): PeopleCredits = apiClient.movieApi.getCredits(id)
    override suspend fun getPeopleList(page: Int): Peoples = apiClient.movieApi.getPeopleList(page = page)
}