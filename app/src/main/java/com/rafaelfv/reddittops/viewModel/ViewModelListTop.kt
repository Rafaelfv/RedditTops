package com.rafaelfv.reddittops.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.rafaelfv.reddittops.repository.ApiRedditTops
import com.rafaelfv.reddittops.repository.model.RedditTopResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import javax.inject.Inject

class ViewModelListTop : BaseViewModel() {

    @Inject
    lateinit var api: ApiRedditTops

    lateinit var disposable: Disposable

    var loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    private var counter = 1

    init {
        getTopList()
    }

    fun getTopList() {
        disposable = api.getListTop("${10*counter}")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onSubscribeStart()}
            .doOnTerminate { onTerminate() }
            .subscribe({ it -> onSuccessListTop(it) },
                { error -> showError(error) })
    }

    private fun showError(error: Throwable?) {
        error?.printStackTrace()
    }

    private fun onSuccessListTop(it: RedditTopResponse?) {
        val a = it

    }

    private fun onTerminate() {
        loadingVisibility.value = View.GONE
    }

    private fun onSubscribeStart() {
        loadingVisibility.value = View.VISIBLE
    }

}