package com.sundaydev.kakaoTest.ui.movie

import androidx.annotation.IdRes
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sundaydev.kakaoTest.R
import com.sundaydev.kakaoTest.ui.activity.getMovieDetailRoute
import com.sundaydev.kakaoTest.viewmodel.MovieContentsViewModel
import com.sundaydev.kakaoTest.viewmodel.MovieContentsViewModelFactory

@Composable
fun MovieScreen(
    navController: NavController,
    movieViewModel: MovieContentsViewModel = viewModel(factory = MovieContentsViewModelFactory(MovieTabInfo.MOVIE_POPULAR.name))
) {
    MovieListContents(
        pager = movieViewModel.list,
        onClick = {
            navController.navigate(getMovieDetailRoute(it.id))
        },
        refresh = {

        }
    )
}

enum class MovieTabInfo(@IdRes val resourceId: Int) {
    MOVIE_POPULAR(R.string.popular),
    MOVIE_NOW_PLAYING(R.string.now_playing),
    MOVIE_UPCOMING(R.string.upcoming),
    MOVIE_TOP_RATE(R.string.top_rate),
}