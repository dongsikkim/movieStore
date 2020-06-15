package com.sundaydev.kakaoTest.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sundaydev.kakaoTest.data.People
import com.sundaydev.kakaoTest.repository.PeopleRepository
import com.sundaydev.kakaoTest.util.subscribeByCommon
import io.reactivex.rxkotlin.addTo
import org.koin.core.KoinComponent
import org.koin.core.inject

class PeopleViewModel : BaseViewModel(), KoinComponent {
    private val repository: PeopleRepository by inject()
    val list = MutableLiveData<List<People>>()

    init {
        repository.getPeoples().subscribeByCommon(onSuccess = { list.postValue(it.results) }).addTo(disposable)
    }
}