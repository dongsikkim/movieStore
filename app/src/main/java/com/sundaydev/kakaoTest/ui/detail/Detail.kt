package com.sundaydev.kakaoTest.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sundaydev.kakaoTest.data.MovieDetail
import com.sundaydev.kakaoTest.theme.typography

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailContents(detail: MovieDetail) {
    Box(modifier = Modifier.padding(all = 16.dp)) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Row {
                // 전체 화면의 2분의1로 크기 잡음
                val configuration = LocalConfiguration.current
                val currentWidth = configuration.screenWidthDp.dp / 2

                GlideImage(
                    model = detail.getDisplayPosterUrl(isOriginal = true),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                    modifier = Modifier
                        .height(currentWidth)
                        .width(currentWidth)
                )

                Column(modifier = Modifier.padding(start = 8.dp, end = 16.dp, bottom = 8.dp)) {
                    GlideImage(
                        model = detail.getDisplayBackdropUrl(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.padding(top = 16.dp))
                    Text(
                        text = detail.title,
                        style = typography.h6
                    )
                    Spacer(modifier = Modifier.padding(top = 8.dp))
                    Text(
                        text = detail.release_date,
                        style = typography.body2,
                    )
                    Spacer(modifier = Modifier.padding(top = 8.dp))
                    Text(
                        text = detail.getDisplayGenres(),
                        style = typography.body2,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.padding(top = 16.dp))
                    Text(
                        text = detail.getDisplayRuntime(),
                        textAlign = TextAlign.End
                    )
                }
            }

            Text(
                text = detail.tagline ?: "",
                style = typography.subtitle1,
                modifier = Modifier.padding(top = 16.dp),
                fontStyle = FontStyle.Italic
            )

            Text(
                text = "개요",
                modifier = Modifier.padding(top = 16.dp),
                style = typography.subtitle1
            )

            Text(
                text = detail.overview ?: "",
                style = typography.body2,
            )
        }
    }
}