package com.sundaydev.kakaoTest.ui.frament

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sundaydev.kakaoTest.BR
import com.sundaydev.kakaoTest.R
import com.sundaydev.kakaoTest.data.Movie
import com.sundaydev.kakaoTest.databinding.FragmentContentsBinding
import com.sundaydev.kakaoTest.util.BindingViewHolder
import com.sundaydev.kakaoTest.viewmodel.ContentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

fun createContentsFragment(tabInfo : TabInfo) = ContentsFragment().apply {
    arguments = bundleOf("tab" to tabInfo)
}

class ContentsFragment : Fragment() {
    private val viewModel: ContentsViewModel by viewModel { parametersOf(filterName)}
    private val adapter: MovieAdapter by lazy { MovieAdapter() }
    lateinit var filterName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tab = arguments?.getSerializable("tab") as TabInfo
        filterName = tab.name
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentContentsBinding = FragmentContentsBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.contentsRecycler.adapter = adapter
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.list.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })
    }
}

class MovieAdapter : ListAdapter<Movie, BindingViewHolder>(diffMovieUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder =
        BindingViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_contents, parent, false))

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        holder.binding.setVariable(BR.item, getItem(position))
        holder.binding.executePendingBindings()
    }
}

val diffMovieUtil = object : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
}