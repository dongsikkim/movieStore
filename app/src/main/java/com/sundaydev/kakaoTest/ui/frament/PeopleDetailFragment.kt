package com.sundaydev.kakaoTest.ui.frament

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sundaydev.kakaoTest.data.PeopleDetail
import com.sundaydev.kakaoTest.databinding.FragmentPeopleDetailBinding
import com.sundaydev.kakaoTest.viewmodel.PeopleDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

const val KEY_PEOPLE = "peopleData"

class PeopleDetailFragment : Fragment() {
    val viewModel: PeopleDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val receiveData = arguments?.getParcelable<PeopleDetail>(KEY_PEOPLE)

        receiveData?.run {
            viewModel.detailData.postValue(this)
            viewModel.loadPeopleDetail(id)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentPeopleDetailBinding = FragmentPeopleDetailBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }
}