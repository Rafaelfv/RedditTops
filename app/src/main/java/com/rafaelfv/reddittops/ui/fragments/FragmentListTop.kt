package com.rafaelfv.reddittops.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.rafaelfv.reddittops.R
import com.rafaelfv.reddittops.viewModel.ViewModelListTop

class FragmentListTop : Fragment() {

    private lateinit var viewModel: ViewModelListTop

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ViewModelListTop::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_top, container, false)
    }

}