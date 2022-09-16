package com.example.newsdemoarimac.repositories

import com.example.newsdemoarimac.api.NewsInterface
import javax.inject.Inject

class NewsRepository
    @Inject constructor(private val api: NewsInterface){
        suspend fun getAllNewsData(params: MutableMap<String,Any>) = api.getNewsData(params)
        suspend fun getAllTopNewsData(params: MutableMap<String,Any>) = api.getTopNewsData(params)
}