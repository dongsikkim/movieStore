package com.sundaydev.kakaoTest.ui.frament

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.sundaydev.kakaoTest.R
import com.sundaydev.kakaoTest.databinding.FragmentMovieBinding
import com.sundaydev.kakaoTest.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import org.koin.androidx.viewmodel.ext.android.viewModel

enum class TabInfo(@IdRes val resourceId: Int) {
    MOVIE_POPULAR(R.string.popular),
    MOVIE_NOW_PLAYING(R.string.now_playing),
    MOVIE_UPCOMING(R.string.upcoming),
    MOVIE_TOP_RATE(R.string.top_rate),

    TV_POPULAR(R.string.popular),
    TV_NOW_PLAYING(R.string.now_playing),
    TV_UPCOMING(R.string.upcoming),
    TV_TOP_RATE(R.string.top_rate)
}

class MovieFragment : Fragment() {
    private val viewPagerAdapter: MovieViewPagerAdapter by lazy { MovieViewPagerAdapter(this) }
    private val viewModel: MovieViewModel by viewModel()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentMovieBinding = FragmentMovieBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        move_view_pager.adapter = viewPagerAdapter
        TabLayoutMediator(tab_layout, move_view_pager) { tab, position ->
            tab.text = getString(TabInfo.values()[position].resourceId)
        }.attach()
    }
}

class MovieViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment = createContentsFragment(TabInfo.values()[position])
}