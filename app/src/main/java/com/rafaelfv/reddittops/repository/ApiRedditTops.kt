package com.rafaelfv.reddittops.repository

import com.rafaelfv.reddittops.repository.model.RedditTopResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiRedditTops {

    @Headers("Accept: application/json", "Connection: close")
    @GET("r/todayilearned/top.json")
    fun getListTop(@Query("limit") limit: String): Single<RedditTopResponse>

}