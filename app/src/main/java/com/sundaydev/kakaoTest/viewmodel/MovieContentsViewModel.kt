package com.sundaydev.kakaoTest.viewmodel

import androidx.lifecycle.MutableLiveData
import com.sundaydev.kakaoTest.data.Movie
import com.sundaydev.kakaoTest.repository.ContentsRepository
import com.sundaydev.kakaoTest.ui.frament.MovieTabInfo
import com.sundaydev.kakaoTest.util.subscribeByCommon
import io.reactivex.rxkotlin.addTo
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieContentsViewModel(filterName: String) : BaseViewModel(), KoinComponent {
    private val repository: ContentsRepository by inject()
    val list = MutableLiveData<List<Movie>>()

    init {
        when (filterName) {
            MovieTabInfo.MOVIE_POPULAR.name -> repository.getPopularMovie()
            MovieTabInfo.MOVIE_NOW_PLAYING.name -> repository.getNowPlayingMovie()
            MovieTabInfo.MOVIE_UPCOMING.name -> repository.getUpComingMovie()
            MovieTabInfo.MOVIE_TOP_RATE.name -> repository.getTopRatedMovie()
            else -> repository.getUpComingMovie()
        }.subscribeByCommon(onSuccess = { list.postValue(it.results) }).addTo(disposable)
    }
}