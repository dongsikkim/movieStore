package com.sundaydev.kakaoTest.ui.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.sundaydev.kakaoTest.data.Movie
import com.sundaydev.kakaoTest.theme.colorPrimary
import com.sundaydev.kakaoTest.theme.colorPrimaryDark
import com.sundaydev.kakaoTest.theme.typography
import com.sundaydev.kakaoTest.viewmodel.MovieContentsViewModel

@Composable
fun MovieListContents(
    filterName: String,
    onClick: ((Movie) -> Unit)? = null,
    refresh: (() -> Unit)? = null
) {
    val movieViewModel: MovieContentsViewModel = remember { MovieContentsViewModel(filterName) }
    val lazyPagingItems = movieViewModel.list.collectAsLazyPagingItems()
    val swipeRefreshState = rememberSwipeRefreshState(false)

    SwipeRefresh(
        state = swipeRefreshState, onRefresh = {
            refresh?.invoke()
        }) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items(count = lazyPagingItems.itemCount) { item ->
                lazyPagingItems[item]?.let {
                    MovieGridItem(movie = it, onClick = onClick)
                }
            }
        }

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieGridItem(
    movie: Movie,
    onClick: ((Movie) -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    onClick?.invoke(movie)
                })
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp,
    ) {
        // ConstrainLayout 예제
        ConstraintLayout {
            val (poster, name, date, rate) = createRefs()

            GlideImage(
                contentDescription = null,
                model = movie.displayPosterUrl(isOriginal = false),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(220.dp)
                    .constrainAs(poster) {
                        top.linkTo(parent.top)
                        bottom.linkTo(name.top)
                    }
                    .padding(bottom = 16.dp)
            )

            Text(text = movie.original_title, style = typography.body2, modifier = Modifier
                .padding(
                    start = 8.dp, top = 0.dp, bottom = 8.dp
                )
                .constrainAs(name) {
                    bottom.linkTo(date.top)
                })

            Text(text = movie.release_date, style = typography.body2, modifier = Modifier
                .padding(
                    start = 8.dp, top = 0.dp, bottom = 8.dp
                )
                .constrainAs(date) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(name.start)
                })
            Box(
                modifier = Modifier
                    .padding(
                        start = 8.dp
                    )
                    .height(48.dp)
                    .width(48.dp)
                    .constrainAs(rate) {
                        bottom.linkTo(name.top)
                    }
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(
                            color = colorPrimary
                        )
                )
                Text(
                    text = movie.getDisplayRatePercentage().toString(),
                    style = typography.overline,
                    modifier = Modifier.align(alignment = Alignment.Center),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(48.dp, 48.dp),
                    progress = movie.vote_average / 10f,
                    color = colorPrimaryDark
                )
            }
        }
    }
}
