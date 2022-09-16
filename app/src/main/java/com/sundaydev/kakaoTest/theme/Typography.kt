package com.sundaydev.kakaoTest.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.sundaydev.kakaoTest.R

val typography = androidx.compose.material.Typography(
    h6 = TextStyle(
        fontFamily = FontFamily(Font(resId = R.font.roboto)),
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = FontFamily(Font(resId = R.font.roboto)),
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = FontFamily(Font(resId = R.font.roboto)),
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = FontFamily(Font(resId = R.font.roboto)),
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamily(Font(resId = R.font.roboto)),
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily(Font(resId = R.font.roboto)),
        fontSize = 12.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily(Font(resId = R.font.roboto)),
        fontSize = 14.sp
    ),
    overline = TextStyle(
        fontFamily = FontFamily(Font(resId = R.font.roboto)),
        fontSize = 10.sp
    )
)