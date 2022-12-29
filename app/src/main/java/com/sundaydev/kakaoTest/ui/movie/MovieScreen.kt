package com.sundaydev.kakaoTest.ui.movie

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.sundaydev.kakaoTest.R
import com.sundaydev.kakaoTest.ui.activity.getMovieDetailRoute

@ExperimentalPagerApi
@Composable
fun MovieScreen(
    navController: NavController
) {
    val size = remember {
        MovieTabInfo.values().size
    }

    val pagerState = rememberPagerState()
    Column {
        TabRow(
            // Our selected tab is our current page
            selectedTabIndex = pagerState.currentPage,
            // Override the indicator, using the provided pagerTabIndicatorOffset modifier
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {
            MovieTabInfo.values().forEachIndexed { _, movieTabInfo ->
                Tab(
                    text = { Text(text = stringResource(id = movieTabInfo.resourceId)) },
                    selected = pagerState.currentPage == movieTabInfo.ordinal,
                    onClick = {
                        //TODO-동식 index에 따라 클릭시 해당 Pager로 이동
                    }
                )
            }
        }

        HorizontalPager(
            count = size,
            state = pagerState
        ) { page ->
            MovieListContents(
                filterName = MovieTabInfo.values()[page].name,
                onClick = {
                    navController.navigate(getMovieDetailRoute(it.id))
                },
                refresh = {

                }
            )
        }
    }
}

enum class MovieTabInfo(@StringRes val resourceId: Int) {
    MOVIE_POPULAR(R.string.popular),
    MOVIE_NOW_PLAYING(R.string.now_playing),
    MOVIE_UPCOMING(R.string.upcoming),
    MOVIE_TOP_RATE(R.string.top_rate),
}