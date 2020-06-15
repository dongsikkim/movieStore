package com.sundaydev.kakaoTest.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sundaydev.kakaoTest.data.Tv
import com.sundaydev.kakaoTest.repository.ContentsRepository
import com.sundaydev.kakaoTest.ui.frament.TvTabInfo
import com.sundaydev.kakaoTest.util.subscribeByCommon
import io.reactivex.rxkotlin.addTo
import org.koin.core.KoinComponent
import org.koin.core.inject

class TvContentsViewModel(filterName: String) : BaseViewModel(), KoinComponent {
    private val repository: ContentsRepository by inject()
    val list = MutableLiveData<List<Tv>>()

    init {
        when (filterName) {
            TvTabInfo.TV_POPULAR.name -> repository.getPopularTv()
            TvTabInfo.TV_NOW_PLAYING.name -> repository.getNowPlayingTv()
            TvTabInfo.TV_TODAY.name -> repository.getTodayTv()
            TvTabInfo.TV_TOP_RATE.name -> repository.getTopRatedTv()
            else -> repository.getPopularTv()
        }.subscribeByCommon(onSuccess = { list.postValue(it.results) }).addTo(disposable)
    }
}