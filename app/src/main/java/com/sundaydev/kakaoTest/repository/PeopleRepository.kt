package com.sundaydev.kakaoTest.repository

import com.sundaydev.kakaoTest.data.PeopleCredits
import com.sundaydev.kakaoTest.data.PeopleDetail
import com.sundaydev.kakaoTest.data.Peoples
import io.reactivex.Single
import org.koin.core.KoinComponent

interface PeopleRepository {
    fun getPeoples(): Single<Peoples>
    fun getPeopleDetail(id: Int): Single<PeopleDetail>
    fun getCredits(id: Int): Single<PeopleCredits>
}

class PeopleRepositoryImpl : PeopleRepository, KoinComponent {
    override fun getPeoples(): Single<Peoples> {
        TODO("Not yet implemented")
    }

    override fun getPeopleDetail(id: Int): Single<PeopleDetail> {
        TODO("Not yet implemented")
    }

    override fun getCredits(id: Int): Single<PeopleCredits> {
        TODO("Not yet implemented")
    }
}