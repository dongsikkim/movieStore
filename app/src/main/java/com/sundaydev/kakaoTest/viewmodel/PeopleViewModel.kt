package com.sundaydev.kakaoTest.viewmodel

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sundaydev.kakaoTest.data.People
import com.sundaydev.kakaoTest.datasource.PeoplePagingSource
import com.sundaydev.kakaoTest.repository.CONTENTS_PAGE_SIZE
import com.sundaydev.kakaoTest.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.KoinComponent
import org.koin.core.inject

class PeopleViewModel : BaseViewModel(), KoinComponent {
    private val repository: PeopleRepository by inject()

    val peopleList: Flow<PagingData<People>> = Pager(PagingConfig(
        pageSize = CONTENTS_PAGE_SIZE,
        enablePlaceholders = true,
        prefetchDistance = CONTENTS_PAGE_SIZE * 3
    )) {
        PeoplePagingSource(repository)
    }.flow
}