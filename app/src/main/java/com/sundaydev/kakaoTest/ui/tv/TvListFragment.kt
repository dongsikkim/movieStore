package com.sundaydev.kakaoTest.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.paging.PagingData
import com.sundaydev.kakaoTest.data.Tv
import com.sundaydev.kakaoTest.ui.movie.FILTER_NAME
import com.sundaydev.kakaoTest.viewmodel.TvContentsViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

fun createTvContentsFragment(tvInfo: TvTabInfo) = TvListFragment().apply {
    arguments = bundleOf(FILTER_NAME to tvInfo)
}

class TvListFragment : Fragment() {
    private val viewModel: TvContentsViewModel by viewModel { parametersOf(filterName) }
    lateinit var filterName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tab = arguments?.getSerializable(FILTER_NAME) as TvTabInfo
        filterName = tab.name
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        ComposeView(inflater.context).apply {
            val pager: Flow<PagingData<Tv>> = viewModel.tvList
            setContent {
                TvListContents(
                    pager = pager,
                    onClick = null
                )
            }
        }
}