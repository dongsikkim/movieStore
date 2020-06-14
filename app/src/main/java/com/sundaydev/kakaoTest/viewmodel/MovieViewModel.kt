package com.sundaydev.kakaoTest.viewmodel

import android.util.Log.d
import com.sundaydev.kakaoTest.repository.ContentsRepository
import com.sundaydev.kakaoTest.util.subscribeByCommon
import io.reactivex.rxkotlin.addTo
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieViewModel : BaseViewModel(), KoinComponent {
    private val repository: ContentsRepository by inject()
    fun getMovieList() =
        repository.getPopularMovie().subscribeByCommon(onSuccess = { d("sss", "success ${it.total_results}") }).addTo(disposable)
}