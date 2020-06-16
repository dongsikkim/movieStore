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

enum class MovieTabInfo(@IdRes val resourceId: Int) {
    MOVIE_POPULAR(R.string.popular),
    MOVIE_NOW_PLAYING(R.string.now_playing),
    MOVIE_UPCOMING(R.string.upcoming),
    MOVIE_TOP_RATE(R.string.top_rate),
}

class MovieFragment : Fragment() {
    lateinit var viewPagerAdapter: MovieViewPagerAdapter
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
        viewPagerAdapter = MovieViewPagerAdapter(this@MovieFragment)
        move_view_pager.adapter = viewPagerAdapter
        TabLayoutMediator(tab_layout, move_view_pager) { tab, position ->
            tab.text = getString(MovieTabInfo.values()[position].resourceId)
        }.attach()
    }
}

class MovieViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4
    override fun createFragment(position: Int): Fragment = createMovieContentsFragment(MovieTabInfo.values()[position])
}