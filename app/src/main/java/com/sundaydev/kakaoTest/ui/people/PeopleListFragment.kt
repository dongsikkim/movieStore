package com.sundaydev.kakaoTest.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import com.sundaydev.kakaoTest.data.People
import com.sundaydev.kakaoTest.viewmodel.PeopleViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.ext.android.viewModel

class PeopleListFragment : Fragment() {
    private val viewModel: PeopleViewModel by viewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(inflater.context).apply {
            setContent {
                val page: Flow<PagingData<People>> = viewModel.peopleList
                PeopleListContents(pager = page)
            }
        }

    private val onClick: ((Pair<AppCompatImageView, People>) -> Unit)? = { pair ->
        val extras = androidx.navigation.fragment.FragmentNavigatorExtras(pair.first to pair.first.transitionName)
        val action = PeopleListFragmentDirections.actionPeopleFragmentToPeopleDetailFragment(pair.second.toPeopleDetail())
        findNavController().navigate(action, extras)
    }
}