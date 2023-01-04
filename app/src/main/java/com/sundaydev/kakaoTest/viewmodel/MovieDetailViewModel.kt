package com.sundaydev.kakaoTest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sundaydev.kakaoTest.data.MovieDetail
import com.sundaydev.kakaoTest.repository.ContentsRepository
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieDetailViewModel(movieId: Int) : BaseViewModel(), KoinComponent {
    private val repository: ContentsRepository by inject()
    val detailData = MutableLiveData<MovieDetail>()

    init {
        viewModelScope.launch {
            detailData.postValue(repository.getMovieDetail(movieId))
        }
    }
}

class MovieDetailViewModelFactory(private val movieId: Int) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = MovieDetailViewModel(movieId) as T
}