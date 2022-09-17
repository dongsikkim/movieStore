package com.sundaydev.kakaoTest.ui.people

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.skydoves.landscapist.glide.GlideImage
import com.sundaydev.kakaoTest.data.People
import com.sundaydev.kakaoTest.theme.typography
import kotlinx.coroutines.flow.Flow


@Composable
fun PeopleListContents(
    pager: Flow<PagingData<People>>,
    onClick : ((People) -> Unit)? = null
) {
    val lazyPagingItems = pager.collectAsLazyPagingItems()
    if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
        Text(
            text = "Waiting for items to load from the backend",
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )
        return
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        if (lazyPagingItems.loadState.append == LoadState.Loading) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }

        items(count = lazyPagingItems.itemCount) { item ->
            lazyPagingItems[item]?.let {
                PeopleGridItem(people = it, onClick = onClick)
            }
        }
    }
}

@Composable
fun PeopleGridItem(
    people: People,
    onClick : ((People) -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        onClick?.invoke(people)
                    }
                )
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 10.dp,
    ) {
        Column {
            GlideImage(
                imageModel = people.displayProfileUrl(isOriginal = false), modifier = Modifier.height(220.dp)
            )
            Text(
                text = people.name,
                style = typography.body2,
                modifier = Modifier.padding(
                    start = 8.dp,
                    top = 4.dp,
                    bottom = 8.dp
                )
            )
        }

    }
}