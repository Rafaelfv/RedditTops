package com.rafaelfv.reddittops.viewModel

import com.rafaelfv.reddittops.repository.model.Children

class ViewModelDetailTop : BaseViewModel() {

    private lateinit var children: Children

    fun setChildren(children: Children) {
        this.children = children
    }

    fun getChildren(): Children = children
}