package com.sundaydev.kakaoTest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sundaydev.kakaoTest.data.PeopleDetail
import com.sundaydev.kakaoTest.repository.PeopleRepository
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class PeopleDetailViewModel : BaseViewModel(), KoinComponent {
    private val repository: PeopleRepository by inject()

    val detailData = MutableLiveData<PeopleDetail>()
    fun loadPeopleDetail(peopleId: Int) {
        viewModelScope.launch {
            detailData.postValue(repository.getPeopleDetail(peopleId))
        }
    }
}