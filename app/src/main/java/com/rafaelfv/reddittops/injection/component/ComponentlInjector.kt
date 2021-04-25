package com.rafaelfv.reddittops.injection.component

import com.rafaelfv.reddittops.injection.modules.NetworkModule
import com.rafaelfv.reddittops.viewModel.ViewModelDetailTop
import com.rafaelfv.reddittops.viewModel.ViewModelListTop
import dagger.Component

@Component(modules = [(NetworkModule::class)])
interface ComponentInjector {

    /**
     * Injectors for viewModels
     */
    fun inject(viewModelListTop: ViewModelListTop)
    fun inject(viewModelDetailTop: ViewModelDetailTop)

    @Component.Builder
    interface Builder{
        fun build(): ComponentInjector
        fun networkModule(networkModule:NetworkModule): Builder
    }
}