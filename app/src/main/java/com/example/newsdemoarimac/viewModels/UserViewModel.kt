package com.example.newsdemoarimac.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.newsdemoarimac.models.User
import com.example.newsdemoarimac.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    fun insertUser(user: User) = viewModelScope.launch {
        userRepository.insertUser(user)
    }

    fun getUser(userName: String) = userRepository.getUser(userName).asLiveData()

    fun setLogin(userName: String,value:Boolean) = userRepository.setLogin(userName,value)
    fun getLogin() = userRepository.getLogin().asLiveData()
}