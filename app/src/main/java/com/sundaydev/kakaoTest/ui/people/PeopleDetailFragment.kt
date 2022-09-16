package com.sundaydev.kakaoTest.ui.people

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.ArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialContainerTransform.FIT_MODE_HEIGHT
import com.sundaydev.kakaoTest.viewmodel.PeopleDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PeopleDetailFragment : Fragment() {
    private val args: PeopleDetailFragmentArgs by navArgs()
    val viewModel: PeopleDetailViewModel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val receiveData = args.peopleData

        receiveData?.run {
            viewModel.detailData.value = this
            viewModel.loadPeopleDetail(id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val transform = MaterialContainerTransform()
        transform.setPathMotion(ArcMotion())
        transform.fitMode = FIT_MODE_HEIGHT
        sharedElementEnterTransition = transform
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(inflater.context).apply {
            setContent {
                val detailData by viewModel.detailData.observeAsState()
                detailData?.let {
                    PeopleDetailContents(peopleDetail = it)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        TODO-동식 Transition 어떻거 넣을지 확인하기
//        viewModel.detailData.value?.let { detail ->
//            ViewCompat.setTransitionName(people_image, getString(R.string.transition_image, detail.id))
//        }
    }
}