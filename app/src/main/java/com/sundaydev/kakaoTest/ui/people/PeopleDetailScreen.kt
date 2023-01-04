package com.sundaydev.kakaoTest.ui.people

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import com.sundaydev.kakaoTest.viewmodel.PeopleDetailViewModel

@Composable
fun PeopleDetailScreen(peopleId: Int) {
    val peopleDetailViewModel: PeopleDetailViewModel = remember { PeopleDetailViewModel().apply { loadPeopleDetail(peopleId = peopleId) } }
    val detailData by peopleDetailViewModel.detailData.observeAsState()
    detailData?.let {
        PeopleDetailContents(peopleDetail = it)
    }
}