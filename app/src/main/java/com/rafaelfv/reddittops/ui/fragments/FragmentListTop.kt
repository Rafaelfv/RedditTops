package com.rafaelfv.reddittops.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.rafaelfv.reddittops.R
import com.rafaelfv.reddittops.repository.model.Children
import com.rafaelfv.reddittops.ui.activities.AdapterItemTop
import com.rafaelfv.reddittops.viewModel.ViewModelListTop
import kotlinx.android.synthetic.main.fragment_list_top.*


class FragmentListTop : Fragment() {

    private var viewModel: ViewModelListTop? = null
    private lateinit var adapter: AdapterItemTop
    private var listTops: MutableList<Children> = ArrayList()
    private var bottomPosition = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (viewModel == null) {
            viewModel = ViewModelProviders.of(this).get(ViewModelListTop::class.java)
        }
        adapter = AdapterItemTop(listTops, object : AdapterItemTop.OnEventItemTop {
            override fun onBottomScroll() {
                bottomPosition = true
            }

            override fun dismissTop(position: Int, children: Children) {
                listTops.remove(children)
                adapter.notifyItemRemoved(position)
                adapter.notifyItemRangeChanged(position, listTops.size)
                viewModel?.removeTop(children)
            }

        })
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

        recyclerview_list_top.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && bottomPosition) {
                    bottomPosition = false
                    viewModel?.getTopListApi()
                }
            }
        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerview_list_top.adapter = adapter

        val listObserver = Observer<List<Children>> { list ->
            if (!listTops.containsAll(list)) {
                listTops.addAll(list)
                adapter.notifyDataSetChanged()
            }
        }

        val progressObserver = Observer<Int> { visibility ->
            progress_bar_list_tops.visibility = visibility
        }

        viewModel?.getProgressVisibility()?.observe(viewLifecycleOwner, progressObserver)
        viewModel?.getListTop()?.observe(viewLifecycleOwner, listObserver)
    }

}