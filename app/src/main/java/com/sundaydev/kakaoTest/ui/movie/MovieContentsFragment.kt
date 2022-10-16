package com.sundaydev.kakaoTest.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sundaydev.kakaoTest.R
import com.sundaydev.kakaoTest.data.Movie
import com.sundaydev.kakaoTest.ui.detail.KEY_MOVIE
import com.sundaydev.kakaoTest.viewmodel.MovieContentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

const val FILTER_NAME = "filterNAME"

fun createMovieContentsFragment(movieTabInfo: MovieTabInfo) = MovieContentsFragment().apply {
    arguments = bundleOf(FILTER_NAME to movieTabInfo)
}

class MovieContentsFragment : Fragment() {
    private val viewModelMovie: MovieContentsViewModel by viewModel { parametersOf(filterName) }
    lateinit var filterName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tab = arguments?.getSerializable(FILTER_NAME) as MovieTabInfo
        filterName = tab.name
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(inflater.context).apply {
            setContent {
                MovieListContents(
                    pager = viewModelMovie.list,
                    onClick = { onClicks.invoke(it) },
                    refresh = {

                    }
                )
            }
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_refresh -> {
                //TODO-동식 리프레시 구현
//                refresh_layout.isRefreshing = true;
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val onClicks: (Movie) -> Unit = { movie ->
//        findNavController().navigate(R.id.detailFragment, bundleOf(KEY_MOVIE to movie.toDetail()), null)
    }
}