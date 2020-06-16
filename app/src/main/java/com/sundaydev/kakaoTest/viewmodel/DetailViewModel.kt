package com.sundaydev.kakaoTest.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sundaydev.kakaoTest.data.MovieDetail
import com.sundaydev.kakaoTest.repository.ContentsRepository
import com.sundaydev.kakaoTest.util.subscribeByCommon
import io.reactivex.rxkotlin.addTo
import org.koin.core.KoinComponent
import org.koin.core.inject

class DetailViewModel(movieId: Int) : BaseViewModel(), KoinComponent {
    private val repository: ContentsRepository by inject()
    val detailData = MutableLiveData<MovieDetail>()

    init {
        repository.getMovieDetail(movieId).subscribeByCommon(onSuccess = {detailData.postValue(it)}).addTo(disposable)
    }
}