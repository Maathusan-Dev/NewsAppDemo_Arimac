package com.example.newsdemoarimac.api

import com.example.newsdemoarimac.models.News
import com.example.newsdemoarimac.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsInterface {

    @Headers("Content-Type:application/json")
    @GET(Constants.NEWS_END_POINT)
    suspend fun getNewsData():Response<News>
}