package com.sundaydev.kakaoTest.ui.people

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sundaydev.kakaoTest.ui.activity.getTvDetailRoute

@Composable
fun PeopleScreen(
    navController: NavController
) {
    Column {
        // 스테이터스바 높이 만큼 spacer 추가
        Spacer(
            modifier = Modifier
                .height(28.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.primarySurface)
        )

        PeopleListContents(
            onClick = {
                navController.navigate(getTvDetailRoute(it.id))
            }
        )
    }
}