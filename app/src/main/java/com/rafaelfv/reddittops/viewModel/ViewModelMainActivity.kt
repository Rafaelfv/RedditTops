package com.rafaelfv.reddittops.viewModel

import androidx.fragment.app.Fragment
import com.rafaelfv.reddittops.repository.model.Children
import com.rafaelfv.reddittops.ui.fragments.FragmentDetailTop
import com.rafaelfv.reddittops.ui.fragments.FragmentListTop

class ViewModelMainActivity : BaseViewModel() {

    private val fragmentListTop = FragmentListTop()
    private val fragmentDetailTop = FragmentDetailTop()
    private var children: Children? = null
    var currentDetailAdded = false


    fun getFragmentListTop(): Fragment {
        return fragmentListTop
    }

    fun getFragmentDetailTop(): FragmentDetailTop {
        return fragmentDetailTop
    }

    fun setChildren(children: Children) {
        this.children = children
    }

    fun getChildren(): Children? = children

    fun cleanChildren() {
        children = null
    }

}