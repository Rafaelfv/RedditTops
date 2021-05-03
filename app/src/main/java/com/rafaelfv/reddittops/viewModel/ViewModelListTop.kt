package com.rafaelfv.reddittops.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rafaelfv.reddittops.repository.ApiRedditTops
import com.rafaelfv.reddittops.repository.model.Children
import com.rafaelfv.reddittops.repository.model.RedditTopResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class ViewModelListTop : BaseViewModel() {

    @Inject
    lateinit var api: ApiRedditTops

    lateinit var disposable: Disposable
    private var loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    private var listTopsLiveData: MutableLiveData<List<Children>> = MutableLiveData()
    private var listTops: MutableList<Children> = ArrayList()

    var counter = 1

    init {
        getTopListApi()
    }

    fun getTopListApi() {
        disposable = api.getListTop("${10 * counter}")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onSubscribeStart() }
            .doOnTerminate { onTerminate() }
            .subscribe({ onSuccessListTop(it) },
                { error -> showError(error) })
    }

    private fun showError(error: Throwable?) {
        error?.printStackTrace()

    }

    private fun onSuccessListTop(it: Response<RedditTopResponse>) {
        if (it.isSuccessful) {
            if (!it.body()?.data?.children.isNullOrEmpty()) {
                it.body()?.data?.children?.let { children ->
                    if(children.size >= (counter - 1) * 10){
                        val sublist = children.subList((counter - 1) * 10, children.size)
                        Log.d("viewModelTops", "sublist size = ${sublist.size}")
                        listTops.addAll(sublist)
                        listTopsLiveData.postValue(sublist)
                        counter++

                    }
                }
            }
        }

    }

    fun removeTop(children: Children) {
        listTops.remove(children)
        listTopsLiveData.postValue(listTops)
    }

    fun removeAll( ) {
        counter = 1
        listTops.clear()
        listTopsLiveData.postValue(listTops)
    }

    fun getListTop(): LiveData<List<Children>> = listTopsLiveData

    fun getProgressVisibility(): LiveData<Int> = loadingVisibility

    private fun onTerminate() {
        loadingVisibility.value = View.GONE
    }

    private fun onSubscribeStart() {
        loadingVisibility.value = View.VISIBLE
    }

    fun notifyItemRead(position: Int) {
        listTops[position].alreadyRead = true
    }

}