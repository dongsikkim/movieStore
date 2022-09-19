package com.sundaydev.kakaoTest.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.sundaydev.kakaoTest.R

@Composable
fun SplashContents() {
    Box(modifier = Modifier.background(Color.White)) {
        Image(
            modifier = Modifier.align(alignment = Alignment.Center),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = ""
        )
    }
}