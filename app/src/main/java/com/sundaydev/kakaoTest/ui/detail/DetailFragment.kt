package com.sundaydev.kakaoTest.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.sundaydev.kakaoTest.data.MovieDetail
import com.sundaydev.kakaoTest.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

const val KEY_MOVIE = "movie"

class DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModel { parametersOf(movieId) }
    private var movieId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getParcelable<MovieDetail>(KEY_MOVIE)?.let { detail ->
            movieId = detail.id
            viewModel.detailData.postValue(detail)
        }
//        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(inflater.context).apply {
            setContent {
                val detail by viewModel.detailData.observeAsState()
                detail?.let {
                    DetailContents(detail = it)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO-동식 트랜지션 문제 해결하기
//        ViewCompat.setTransitionName(poster, getString(R.string.transition_image, movieId))
//        setImageViewSize()
    }
}