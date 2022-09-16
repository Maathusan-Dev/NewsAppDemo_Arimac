package com.example.newsdemoarimac.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsdemoarimac.models.News
import com.example.newsdemoarimac.repositories.NewsRepository
import com.example.newsdemoarimac.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel
@Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    private val newsLiveData = MutableLiveData<News>()
    private val topNewsLiveData = MutableLiveData<News>()


    val newsResponse: LiveData<News> get() = newsLiveData
    val topNewsResponse: LiveData<News> get() = topNewsLiveData

    fun getAllNewsData(params: MutableMap<String,Any>) = viewModelScope.launch {
        newsRepository.getAllNewsData(params).let { response ->
            if (response.isSuccessful) {
                newsLiveData.postValue(response.body())
            } else {
                Log.e("Error", response.errorBody().toString())
            }
        }
    }

    fun getAllTopNewsData(params: MutableMap<String,Any>) = viewModelScope.launch {
        newsRepository.getAllTopNewsData(params).let { response ->
            if (response.isSuccessful) {
                topNewsLiveData.postValue(response.body())
            } else {
                Log.e("Error", response.errorBody().toString())
            }
        }
    }
}