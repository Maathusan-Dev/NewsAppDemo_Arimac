package com.example.newsdemoarimac.api

import com.example.newsdemoarimac.models.News
import com.example.newsdemoarimac.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NewsInterface {

    @Headers("Content-Type:application/json")
    @GET(Constants.NEWS_END_POINT)
    suspend fun getNewsData(@QueryMap params: MutableMap<String,Any>):Response<News>

    @Headers("Content-Type:application/json")
    @GET(Constants.TOP_NEWS_END_POINT)
    suspend fun getTopNewsData(@QueryMap params: MutableMap<String,Any>):Response<News>
}