package com.sundaydev.kakaoTest.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sundaydev.kakaoTest.data.People
import com.sundaydev.kakaoTest.data.Peoples
import com.sundaydev.kakaoTest.repository.PeopleRepository

class PeoplePagingSource(
    val repository: PeopleRepository
) : PagingSource<Int, People>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, People> {
        return try {
            val nexPage = params.key ?: 1
            val response: Peoples = repository.getPeopleList(nexPage)
            LoadResult.Page(
                data = response.results,
                prevKey = if (nexPage == 1) null else nexPage - 1,
                nextKey = response.page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, People>): Int? {
        TODO("NOT YET")
    }
}