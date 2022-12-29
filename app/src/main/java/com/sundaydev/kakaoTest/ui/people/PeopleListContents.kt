package com.sundaydev.kakaoTest.ui.people

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.sundaydev.kakaoTest.data.People
import com.sundaydev.kakaoTest.theme.typography
import com.sundaydev.kakaoTest.viewmodel.PeopleViewModel


@Composable
fun PeopleListContents(
    onClick: ((People) -> Unit)? = null
) {
    val peopleViewModel: PeopleViewModel = remember { PeopleViewModel() }
    val lazyPagingItems = peopleViewModel.peopleList.collectAsLazyPagingItems()
    val swipeRefreshState = rememberSwipeRefreshState(false)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            lazyPagingItems.refresh()
        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items(count = lazyPagingItems.itemCount) { item ->
                lazyPagingItems[item]?.let {
                    PeopleGridItem(people = it, onClick = onClick)
                }
            }
        }

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PeopleGridItem(
    people: People,
    onClick: ((People) -> Unit)? = null
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
                model = people.displayProfileUrl(isOriginal = false),
                modifier = Modifier.height(220.dp),
                contentScale = ContentScale.Crop,
                contentDescription = null
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