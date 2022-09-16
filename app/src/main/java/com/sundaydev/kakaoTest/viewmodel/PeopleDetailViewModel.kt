package com.sundaydev.kakaoTest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.sundaydev.kakaoTest.data.PeopleDetail
import com.sundaydev.kakaoTest.network.URL_ORIGIN_IMAGE
import com.sundaydev.kakaoTest.repository.PeopleRepository
import com.sundaydev.kakaoTest.util.subscribeByCommon
import io.reactivex.rxkotlin.addTo
import org.koin.core.KoinComponent
import org.koin.core.inject

class PeopleDetailViewModel : BaseViewModel(), KoinComponent {
    private val repository: PeopleRepository by inject()

    val detailData = MutableLiveData<PeopleDetail>()
    val peopleImageUrl: LiveData<String?> = Transformations.map(detailData) { detailData ->
        "${URL_ORIGIN_IMAGE}${detailData.profile_path}"
    }

    val peopleName: LiveData<String> = Transformations.map(detailData) {
        it.name
    }

    val peopleBirthDay: LiveData<String> = Transformations.map(detailData) {
        it.birthday
    }

    val alsoKnowAs: LiveData<String> = Transformations.map(detailData) {
        val nameList = it.also_known_as
        if (nameList.isNotEmpty()) {
            nameList.joinToString()
        } else {
            ""
        }
    }

    val biography: LiveData<String?> = Transformations.map(detailData) {
        it.biography
    }

    fun loadPeopleDetail(peopleId: Int) {
        repository.getPeopleDetail(peopleId).subscribeByCommon { detailData.postValue(it) }.addTo(disposable)
    }
}