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

    init {
        getAllHotelData()
    }

    val newsResponse: LiveData<News> get() = newsLiveData

    private fun getAllHotelData() = viewModelScope.launch {
        newsRepository.getAllNewsData().let { response ->
            if (response.isSuccessful) {
                newsLiveData.postValue(response.body())
            } else {
                Log.e("Error", response.errorBody().toString())
            }
        }
    }
}