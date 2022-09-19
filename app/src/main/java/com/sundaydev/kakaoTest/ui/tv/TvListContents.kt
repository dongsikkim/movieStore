package com.sundaydev.kakaoTest.ui.tv

import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.skydoves.landscapist.glide.GlideImage
import com.sundaydev.kakaoTest.data.Tv
import com.sundaydev.kakaoTest.theme.typography
import kotlinx.coroutines.flow.Flow

@Composable
fun TvListContents(
    pager: Flow<PagingData<Tv>>, onClick: ((Tv) -> Unit)? = null
) {
    val lazyPagingItems = pager.collectAsLazyPagingItems()
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(count = lazyPagingItems.itemCount) { item ->
            lazyPagingItems[item]?.let {
                TvGridItem(tv = it, onClick = onClick)
            }
        }
    }
}

@Composable
fun TvGridItem(
    tv: Tv, onClick: ((Tv) -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    onClick?.invoke(tv)
                })
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp,
    ) {
        ConstraintLayout {
            val (poster, name, date, rate) = createRefs()

            GlideImage(imageModel = tv.displayPosterUrl(isOriginal = false), modifier = Modifier
                .height(220.dp)
                .constrainAs(poster) {
                    top.linkTo(parent.top)
                    bottom.linkTo(name.top)
                }
                .padding(bottom = 16.dp))

            Text(text = tv.original_name, style = typography.body2, modifier = Modifier
                .padding(
                    start = 8.dp, top = 0.dp, bottom = 8.dp
                )
                .constrainAs(name) {
                    bottom.linkTo(date.top)
                })

            Text(text = tv.first_air_date, style = typography.body2, modifier = Modifier
                .padding(
                    start = 8.dp, top = 0.dp, bottom = 8.dp
                )
                .constrainAs(date) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(name.start)
                })

            TvProgressBar(tv = tv, modifier = Modifier
                .constrainAs(rate) {
                    bottom.linkTo(name.top)
                    start.linkTo(name.start)
                }
                .padding(
                    start = 8.dp
                ))
        }
    }
}

@Composable
fun TvProgressBar(tv: Tv, modifier: Modifier) {
// compose에서 굳이 xml 불러오기
    AndroidView(modifier = modifier, factory = { context ->
        val view: View = LayoutInflater.from(context).inflate(com.sundaydev.kakaoTest.R.layout.widget_progress, null, true)
        view
    }, update = { viewGroup ->
        val view = viewGroup.findViewById<ProgressBar>(com.sundaydev.kakaoTest.R.id.rate)
        view.progress = tv.displayVote()

        val text = viewGroup.findViewById<TextView>(com.sundaydev.kakaoTest.R.id.vote_rate)
        text.text = tv.getDisplayRatePercentage()
    })
}