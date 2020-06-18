package com.sundaydev.kakaoTest.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sundaydev.kakaoTest.data.PeopleDetail
import com.sundaydev.kakaoTest.repository.PeopleRepository
import com.sundaydev.kakaoTest.util.subscribeByCommon
import io.reactivex.rxkotlin.addTo
import org.koin.core.KoinComponent
import org.koin.core.inject

class PeopleDetailViewModel : BaseViewModel(), KoinComponent {
    private val repository: PeopleRepository by inject()
    val detailData = MutableLiveData<PeopleDetail>()

    fun loadPeopleDetail(peopleId: Int) {
        repository.getPeopleDetail(peopleId).subscribeByCommon { detailData.postValue(it) }.addTo(disposable)
    }
}