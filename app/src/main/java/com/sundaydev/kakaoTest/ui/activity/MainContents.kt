package com.sundaydev.kakaoTest.ui.activity

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.sundaydev.kakaoTest.R
import com.sundaydev.kakaoTest.ui.detail.MovieDetailScreen
import com.sundaydev.kakaoTest.ui.movie.MovieScreen
import com.sundaydev.kakaoTest.ui.people.PeopleScreen
import com.sundaydev.kakaoTest.ui.splash.SplashContents
import com.sundaydev.kakaoTest.ui.tv.TvScreen

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainContents() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                bottomNavigationItems.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painterResource(id = screen.resourceId),
                                contentDescription = ""
                            )
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        })
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SCREEN_SPLASH,
            Modifier.padding(innerPadding)
        ) {
            composable(SCREEN_SPLASH) { SplashContents(navController = navController) }
            composable(SCREEN_MOVIE) { MovieScreen(navController = navController) }
            composable(
                route = "movieDetail/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) { backStackEntry ->
                MovieDetailScreen(movieId = backStackEntry.arguments?.getInt("movieId") ?: 0)
            }
            composable(SCREEN_TV) {
                TvScreen(navController = navController)
            }
            composable(
                route = "$SCREEN_TV/{tvId}",
                arguments = listOf(navArgument("tvId") { type = NavType.IntType })
            ) { backStackEntry ->
                MovieDetailScreen(movieId = backStackEntry.arguments?.getInt("movieId") ?: 0)
            }
            composable(SCREEN_PEOPLE) { PeopleScreen(navController = navController) }
            //TODO-동식 배우 디테일 화면 추가 필요
        }
    }
    navController.popBackStack(route = SCREEN_SPLASH, inclusive = true)
}

val bottomNavigationItems = listOf(
    BottomScreen.Movie,
    BottomScreen.Tv,
    BottomScreen.People,
)

sealed class BottomScreen(val route: String, @DrawableRes val resourceId: Int) {
    object Movie : BottomScreen(route = SCREEN_MOVIE, resourceId = R.drawable.ic_movie)
    object Tv : BottomScreen(route = SCREEN_TV, resourceId = R.drawable.ic_tv)
    object People : BottomScreen(route = SCREEN_PEOPLE, resourceId = R.drawable.ic_people)
}

const val SCREEN_SPLASH = "splash"
const val SCREEN_MOVIE = "movie"
const val SCREEN_MOVIE_DETAIL = "movieDetail"
const val SCREEN_TV = "tv"
const val SCREEN_PEOPLE = "people"

fun getMovieDetailRoute(movieId: Int): String = "${SCREEN_MOVIE_DETAIL}/$movieId"
fun getTvDetailRoute(tvId: Int): String = "${SCREEN_TV}/$tvId"

