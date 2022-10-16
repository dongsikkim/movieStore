package com.sundaydev.kakaoTest.ui.activity

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sundaydev.kakaoTest.ui.movie.MovieScreen
import com.sundaydev.kakaoTest.ui.splash.SplashContents

@Composable
fun MainContents() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash",
    ) {
        composable("splash") { SplashContents(navController = navController) }
        composable("movie") { MovieScreen(navController = navController) }
        composable("tv") { /*TvListContents(pager = )*/ }
        composable("people") { /*PeopleListContents(pager = )*/ }
    }
    navController.navigate("splash")
}