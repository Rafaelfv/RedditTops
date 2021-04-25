package com.rafaelfv.reddittops

import android.app.Application
import com.rafaelfv.reddittops.injection.component.ComponentInjector
import com.rafaelfv.reddittops.injection.component.DaggerComponentInjector
import com.rafaelfv.reddittops.injection.modules.NetworkModule

class MyApplication : Application() {

    companion object {
        lateinit var component: ComponentInjector
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerComponentInjector.builder().networkModule(NetworkModule).build()
    }

}