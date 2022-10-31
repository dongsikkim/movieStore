package com.sundaydev.kakaoTest.ui.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sundaydev.kakaoTest.viewmodel.MovieDetailViewModel
import com.sundaydev.kakaoTest.viewmodel.MovieDetailViewModelFactory

@Composable
fun MovieDetailScreen(
    movieId: Int,
    movieDetailViewModel: MovieDetailViewModel = viewModel(factory = MovieDetailViewModelFactory(movieId = movieId))
) {
    val detailData by movieDetailViewModel.detailData.observeAsState()
    detailData?.let {
        MovieDetailContents(it)
    }
}