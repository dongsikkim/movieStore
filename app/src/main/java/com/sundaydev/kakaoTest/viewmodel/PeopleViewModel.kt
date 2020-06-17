package com.sundaydev.kakaoTest.viewmodel

import com.sundaydev.kakaoTest.repository.PeopleRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class PeopleViewModel : BaseViewModel(), KoinComponent {
    private val repository: PeopleRepository by inject()
    val list = repository.loadPeoples(disposable)
}