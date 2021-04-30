package com.rafaelfv.reddittops.viewModel

import androidx.fragment.app.Fragment
import com.rafaelfv.reddittops.ui.fragments.FragmentListTop

class ViewModelMainActivity: BaseViewModel() {

    private val fragmentListTop = FragmentListTop()

    fun getFragmentListTop() : Fragment {
        return fragmentListTop
    }
}