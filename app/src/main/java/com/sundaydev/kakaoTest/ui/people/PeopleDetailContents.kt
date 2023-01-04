package com.sundaydev.kakaoTest.ui.people

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sundaydev.kakaoTest.data.PeopleDetail
import com.sundaydev.kakaoTest.theme.typography

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PeopleDetailContents(peopleDetail: PeopleDetail) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        GlideImage(
            model = peopleDetail.getProfileUrl(isOriginal = true),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
        Text(
            text = peopleDetail.name, style = typography.h6, modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = peopleDetail.birthday, style = typography.subtitle2, modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = peopleDetail.getAlsoKnownAs(), modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = peopleDetail.biography, style = typography.body2, modifier = Modifier.padding(top = 16.dp)
        )
    }
}