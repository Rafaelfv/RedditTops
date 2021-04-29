package com.rafaelfv.reddittops.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rafaelfv.reddittops.R
import com.rafaelfv.reddittops.repository.model.Children
import com.rafaelfv.reddittops.ui.activities.AdapterItemTop
import com.rafaelfv.reddittops.viewModel.ViewModelListTop
import kotlinx.android.synthetic.main.fragment_list_top.*


class FragmentListTop : Fragment() {

    private lateinit var viewModel: ViewModelListTop
    private lateinit var adapter: AdapterItemTop
    private var listTops: MutableList<Children> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ViewModelListTop::class.java)
        adapter = AdapterItemTop(listTops)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_top, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerview_list_top.adapter = adapter

        val listObserver = Observer<List<Children>> { list ->
            listTops.addAll(list)
            adapter.notifyDataSetChanged()
        }

        viewModel.getListTop().observe(viewLifecycleOwner, listObserver)
    }

}