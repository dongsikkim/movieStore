package com.sundaydev.kakaoTest.ui.movie

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.sundaydev.kakaoTest.R
import com.sundaydev.kakaoTest.ui.activity.getMovieDetailRoute
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun MovieScreen(
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    val size = remember {
        MovieTabInfo.values().size
    }

    val pagerState = rememberPagerState()
    Column {
        // 스테이터스바 높이 만큼 spacer 추가
        Spacer(
            modifier = Modifier
                .height(28.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.primarySurface)
        )
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
                        coroutineScope.launch {
                            // 클릭시 pagerState의 현재 선택된 페이지를 변경
                            pagerState.animateScrollToPage(page = movieTabInfo.ordinal)
                        }
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