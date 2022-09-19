package com.sundaydev.kakaoTest.ui.movie

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.skydoves.landscapist.glide.GlideImage
import com.sundaydev.kakaoTest.data.Movie
import com.sundaydev.kakaoTest.theme.typography
import kotlinx.coroutines.flow.Flow

@Composable
fun MovieListContents(
    pager: Flow<PagingData<Movie>>,
    onClick: ((Movie) -> Unit)? = null,
    refresh: (() -> Unit)? = null
) {
    val lazyPagingItems = pager.collectAsLazyPagingItems()
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
        ConstraintLayout {
            val (poster, name, date) = createRefs()

            GlideImage(imageModel = movie.displayPosterUrl(isOriginal = false), modifier = Modifier
                .height(220.dp)
                .constrainAs(poster) {
                    top.linkTo(parent.top)
                    bottom.linkTo(name.top)
                }
                .padding(bottom = 16.dp))

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
        }
    }
}
