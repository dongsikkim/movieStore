package com.sundaydev.kakaoTest.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sundaydev.kakaoTest.data.Movie
import com.sundaydev.kakaoTest.data.Movies
import com.sundaydev.kakaoTest.repository.ContentsRepository

class MoviePagingSource(
    private val repository: ContentsRepository,
    private val filterName: String
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nexPage = params.key ?: 1
            val response: Movies = repository.loadMovies(filterName, nexPage)

            LoadResult.Page(
                data = response.results,
                prevKey = if (nexPage == 1) null else nexPage - 1,
                nextKey = response.page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}