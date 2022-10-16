package com.sundaydev.kakaoTest.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.sundaydev.kakaoTest.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashContents(
    navController: NavHostController
) {
    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        delay(1000)
        navController.navigate("movie")
    }
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxHeight()
            .fillMaxWidth(),
    ) {
        Image(
            modifier = Modifier.align(alignment = Alignment.Center),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = ""
        )
    }
}