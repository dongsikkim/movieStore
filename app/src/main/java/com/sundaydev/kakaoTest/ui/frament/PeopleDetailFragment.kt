package com.sundaydev.kakaoTest.ui.frament

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.ArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialContainerTransform.FIT_MODE_HEIGHT
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.sundaydev.kakaoTest.R
import com.sundaydev.kakaoTest.theme.typography
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
        val rootView = LayoutInflater.from(context).inflate(R.layout.fragment_people_detail, container, false)

        rootView.findViewById<ComposeView>(R.id.people_detail_compose_view).apply {
            setContent {
                val imageUrl by viewModel.peopleImageUrl.observeAsState()
                val name by viewModel.peopleName.observeAsState()
                val birthday by viewModel.peopleBirthDay.observeAsState()
                val alsoKnownAs by viewModel.alsoKnowAs.observeAsState()
                val biography by viewModel.biography.observeAsState()

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    GlideImage(
                        imageModel = imageUrl,
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center
                        )
                    )
                    Text(
                        text = name ?: "",
                        style = typography.h6,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = birthday ?: "",
                        style = typography.subtitle2,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = alsoKnownAs ?: "",
                        modifier = Modifier
                            .padding(top = 8.dp)
                    )
                    Text(
                        text = biography ?: "",
                        style = typography.body2,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        TODO-동식 Transition 어떻거 넣을지 확인하기
//        viewModel.detailData.value?.let { detail ->
//            ViewCompat.setTransitionName(people_image, getString(R.string.transition_image, detail.id))
//        }
    }
}