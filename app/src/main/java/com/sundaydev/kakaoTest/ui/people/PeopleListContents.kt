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
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sundaydev.kakaoTest.data.People
import com.sundaydev.kakaoTest.theme.typography
import kotlinx.coroutines.flow.Flow


@Composable
fun PeopleListContents(
    pager: Flow<PagingData<People>>,
    onClick: ((People) -> Unit)? = null
) {
    val lazyPagingItems = pager.collectAsLazyPagingItems()
//TODO-동식 추후 리프레시 필요시 추가
//    if (lazyPagingItems.loadState.refresh == LoadState.Loading) {
//        Text(
//            text = "Waiting for items to load from the backend",
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(120.dp)
//        )
//        return
//    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
//TODO-동식 추후 로딩 표시 필요시 추가(푸터로 넣는것 고민필요)
//        if (lazyPagingItems.loadState.append == LoadState.Loading) {
//            item {
//                CircularProgressIndicator(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .wrapContentWidth(Alignment.CenterHorizontally)
//                )
//            }
//        }

        items(count = lazyPagingItems.itemCount) { item ->
            lazyPagingItems[item]?.let {
                PeopleGridItem(people = it, onClick = onClick)
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