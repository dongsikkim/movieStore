package com.sundaydev.kakaoTest.ui.activity

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sundaydev.kakaoTest.ui.detail.MovieDetailScreen
import com.sundaydev.kakaoTest.ui.movie.MovieScreen
import com.sundaydev.kakaoTest.ui.splash.SplashContents

@Composable
fun MainContents() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = SCREEN_SPLASH,
    ) {
        composable(SCREEN_SPLASH) { SplashContents(navController = navController) }
        composable(SCREEN_MOVIE) { MovieScreen(navController = navController) }
        composable(
            route = "movieDetail/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            MovieDetailScreen(movieId = backStackEntry.arguments?.getInt("movieId") ?: 0)
        }
        composable(SCREEN_TV) { /*TvListContents(pager = )*/ }
        composable(SCREEN_PEOPLE) { /*PeopleListContents(pager = )*/ }
    }
}

const val SCREEN_SPLASH = "splash"
const val SCREEN_MOVIE = "movie"
const val SCREEN_MOVIE_DETAIL = "movieDetail"
const val SCREEN_TV = "tv"
const val SCREEN_PEOPLE = "people"

fun getMovieDetailRoute(movieId: Int): String = "${SCREEN_MOVIE_DETAIL}/$movieId"

