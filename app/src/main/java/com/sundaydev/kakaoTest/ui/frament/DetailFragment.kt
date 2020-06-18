package com.sundaydev.kakaoTest.ui.frament

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sundaydev.kakaoTest.data.MovieDetail
import com.sundaydev.kakaoTest.databinding.FragmentDetailBinding
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
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentDetailBinding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }
}