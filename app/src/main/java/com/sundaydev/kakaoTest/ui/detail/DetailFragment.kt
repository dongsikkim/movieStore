package com.sundaydev.kakaoTest.ui.detail

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Surface
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.sundaydev.kakaoTest.data.MovieDetail
import com.sundaydev.kakaoTest.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
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
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(inflater.context).apply {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO-동식 트랜지션 문제 해결하기
//        ViewCompat.setTransitionName(poster, getString(R.string.transition_image, movieId))
//        setImageViewSize()
    }

    private fun setImageViewSize() = activity?.let {
        val displayMetrics = DisplayMetrics()
        it.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val w = displayMetrics.widthPixels / 2
        val h = w * 1.5
        poster.updateLayoutParams {
            width = w
            height = h.toInt()
        }
    }
}