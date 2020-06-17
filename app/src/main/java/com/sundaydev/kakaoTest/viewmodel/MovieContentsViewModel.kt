package com.sundaydev.kakaoTest.viewmodel

import com.sundaydev.kakaoTest.repository.ContentsRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieContentsViewModel(filterName: String) : BaseViewModel(), KoinComponent {
    private val repository: ContentsRepository by inject()
    val list = repository.loadMovies(filterName, disposable)
}