package com.rafaelfv.reddittops.viewModel

import androidx.lifecycle.ViewModel
import com.rafaelfv.reddittops.MyApplication

open class BaseViewModel : ViewModel() {

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is ViewModelListTop -> MyApplication.component.inject(this)
            is ViewModelDetailTop -> MyApplication.component.inject(this)
        }
    }

}