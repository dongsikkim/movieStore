package com.sundaydev.kakaoTest.ui.frament

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sundaydev.kakaoTest.BR
import com.sundaydev.kakaoTest.R
import com.sundaydev.kakaoTest.data.Movie
import com.sundaydev.kakaoTest.databinding.FragmentMovieContentsBinding
import com.sundaydev.kakaoTest.util.BindingViewHolder
import com.sundaydev.kakaoTest.viewmodel.MovieContentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

const val FILTER_NAME = "filterNAME"

fun createMovieContentsFragment(movieTabInfo: MovieTabInfo) = MovieContentsFragment().apply {
    arguments = bundleOf(FILTER_NAME to movieTabInfo)
}

class MovieContentsFragment : Fragment() {
    private val viewModelMovie: MovieContentsViewModel by viewModel { parametersOf(filterName) }
    private val adapterMovie: MovieContentsAdapter by lazy { MovieContentsAdapter(onClicks) }
    lateinit var filterName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tab = arguments?.getSerializable(FILTER_NAME) as MovieTabInfo
        filterName = tab.name
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentMovieContentsBinding = FragmentMovieContentsBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModelMovie
        binding.contentsRecycler.adapter = adapterMovie
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelMovie.list.observe(viewLifecycleOwner, Observer { adapterMovie.submitList(it) })
    }

    private val onClicks: ((Movie) -> Unit)? = {
        findNavController().navigate(R.id.detailFragment, bundleOf(KEY_MOVIE_ID to it.id))
    }
}

class MovieContentsAdapter(private val onClicks: ((Movie) -> Unit)? = null) : PagedListAdapter<Movie, BindingViewHolder>(diffMovieUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder =
        BindingViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movie_contents, parent, false))

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        holder.binding.setVariable(BR.item, getItem(position))
        holder.binding.executePendingBindings()
        holder.binding.root.setOnClickListener { getItem(position)?.let { onClicks?.invoke(it) } }
    }
}

val diffMovieUtil = object : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
}