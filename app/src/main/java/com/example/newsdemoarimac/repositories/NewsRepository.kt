package com.example.newsdemoarimac.repositories

import com.example.newsdemoarimac.api.NewsInterface
import javax.inject.Inject

class NewsRepository
    @Inject constructor(private val api: NewsInterface){
        suspend fun getAllNewsData() = api.getNewsData()
}