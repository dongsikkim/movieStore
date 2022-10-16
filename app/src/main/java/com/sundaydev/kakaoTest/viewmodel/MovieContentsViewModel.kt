package com.sundaydev.kakaoTest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sundaydev.kakaoTest.data.Movie
import com.sundaydev.kakaoTest.datasource.MoviePagingSource
import com.sundaydev.kakaoTest.repository.CONTENTS_PAGE_SIZE
import com.sundaydev.kakaoTest.repository.ContentsRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieContentsViewModel(filterName: String) : BaseViewModel(), KoinComponent {
    private val repository: ContentsRepository by inject()

    val list: Flow<PagingData<Movie>> = Pager(
        PagingConfig(
            pageSize = CONTENTS_PAGE_SIZE,
            enablePlaceholders = true,
            prefetchDistance = CONTENTS_PAGE_SIZE * 3
        )
    ) {
        MoviePagingSource(
            repository = repository,
            filterName = filterName
        )
    }.flow
}

class MovieContentsViewModelFactory(private val filterName: String) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = MovieContentsViewModel(filterName) as T
}