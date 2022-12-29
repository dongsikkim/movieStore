@file:OptIn(ExperimentalPagerApi::class)

package com.sundaydev.kakaoTest.ui.tv

import androidx.annotation.IdRes
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
import com.sundaydev.kakaoTest.ui.activity.getTvDetailRoute
import kotlinx.coroutines.launch

@Composable
fun TvScreen(
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    val size = remember {
        TvTabInfo.values().size
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
            TvTabInfo.values().forEachIndexed { _, tvTabInfo ->
                Tab(
                    text = { Text(text = stringResource(id = tvTabInfo.resourceId)) },
                    selected = pagerState.currentPage == tvTabInfo.ordinal,
                    onClick = {
                        coroutineScope.launch {
                            // 클릭시 pagerState의 현재 선택된 페이지를 변경
                            pagerState.animateScrollToPage(page = tvTabInfo.ordinal)
                        }
                    }
                )
            }
        }

        HorizontalPager(
            count = size,
            state = pagerState
        ) { page ->
            TvListContents(
                filterName = TvTabInfo.values()[page].name,
                onClick = {
                    navController.navigate(getTvDetailRoute(it.id))
                }
            )
        }
    }
}

enum class TvTabInfo(@IdRes val resourceId: Int) {
    TV_POPULAR(R.string.popular),
    TV_TODAY(R.string.today_playing),
    TV_NOW_PLAYING(R.string.tv_now_playing),
    TV_TOP_RATE(R.string.top_rate)
}