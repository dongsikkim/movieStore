package com.sundaydev.kakaoTest.ui.frament

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sundaydev.kakaoTest.BR
import com.sundaydev.kakaoTest.R
import com.sundaydev.kakaoTest.data.People
import com.sundaydev.kakaoTest.databinding.FragmentPeopleBinding
import com.sundaydev.kakaoTest.util.BindingViewHolder
import com.sundaydev.kakaoTest.viewmodel.PeopleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PeopleFragment : Fragment() {
    private val viewModel: PeopleViewModel by viewModel()
    private val adapter: PeopleAdapter by lazy { PeopleAdapter(onClick) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentPeopleBinding = FragmentPeopleBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.peopleRecycler.adapter = adapter
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.list.observe(viewLifecycleOwner, Observer { adapter.submitList(it) })
    }

    private val onClick: ((People) -> Unit)? = {
        findNavController().navigate(
            R.id.action_peopleFragment_to_peopleDetailFragment,
            androidx.core.os.bundleOf(KEY_PEOPLE to it.toPeopleDetail())
        )
    }
}

class PeopleAdapter(private val onClick: ((People) -> Unit)?) : PagedListAdapter<People, BindingViewHolder>(diffPeopleUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder =
        BindingViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_people, parent, false))

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        holder.binding.setVariable(BR.item, getItem(position))
        holder.binding.executePendingBindings()
        holder.binding.root.setOnClickListener { getItem(position)?.let { onClick?.invoke(it) } }
    }
}

val diffPeopleUtil = object : DiffUtil.ItemCallback<People>() {
    override fun areItemsTheSame(oldItem: People, newItem: People): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: People, newItem: People): Boolean = oldItem == newItem
}