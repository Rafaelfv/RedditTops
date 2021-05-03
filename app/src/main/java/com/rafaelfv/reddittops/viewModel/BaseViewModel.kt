package com.rafaelfv.reddittops.viewModel

import androidx.lifecycle.ViewModel
import com.rafaelfv.reddittops.MyApplication

/**
 * Base model to handle the injectors in each view model
 */
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