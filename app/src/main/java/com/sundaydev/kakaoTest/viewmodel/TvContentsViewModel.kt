package com.sundaydev.kakaoTest.viewmodel

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sundaydev.kakaoTest.data.Tv
import com.sundaydev.kakaoTest.datasource.TvPagingSource
import com.sundaydev.kakaoTest.repository.CONTENTS_PAGE_SIZE
import com.sundaydev.kakaoTest.repository.ContentsRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.KoinComponent
import org.koin.core.inject

class TvContentsViewModel(filterName: String) : BaseViewModel(), KoinComponent {
    private val repository: ContentsRepository by inject()

    val tvList: Flow<PagingData<Tv>> = Pager(
        PagingConfig(
            pageSize = CONTENTS_PAGE_SIZE,
            enablePlaceholders = true,
            prefetchDistance = CONTENTS_PAGE_SIZE * 3
        )
    ) {
        TvPagingSource(
            repository = repository,
            filterName = filterName
        )
    }.flow
}