package com.sundaydev.kakaoTest.viewmodel

import android.util.Log.d
import androidx.lifecycle.MutableLiveData
import com.sundaydev.kakaoTest.data.Movie
import com.sundaydev.kakaoTest.repository.ContentsRepository
import com.sundaydev.kakaoTest.ui.frament.TabInfo
import com.sundaydev.kakaoTest.util.subscribeByCommon
import io.reactivex.rxkotlin.addTo
import org.koin.core.KoinComponent
import org.koin.core.inject

class ContentsViewModel(filterName : String) : BaseViewModel(), KoinComponent {
    private val repository: ContentsRepository by inject()
    val list = MutableLiveData<List<Movie>>()

    init {
        d("sss", "viewModels Order ${filterName}")
        when(filterName) {
            TabInfo.MOVIE_POPULAR.name -> repository.getPopularMovie()
            TabInfo.MOVIE_NOW_PLAYING.name -> repository.getNowPlayingMovie()
            TabInfo.MOVIE_UPCOMING.name -> repository.getUpComingMovie()
            TabInfo.MOVIE_TOP_RATE.name -> repository.getTopRatedMovie()
            else -> repository.getUpComingMovie()
        }.subscribeByCommon(
            onSuccess = {
                list.postValue(it.results)
            }, onError = {
                d("sss", "error ${it.message}")
            }
        ).addTo(disposable)
    }
}