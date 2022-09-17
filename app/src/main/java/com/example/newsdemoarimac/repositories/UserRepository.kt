package com.example.newsdemoarimac.repositories

import com.example.newsdemoarimac.db.UserDB
import com.example.newsdemoarimac.models.User
import javax.inject.Inject

class UserRepository
@Inject constructor(private val dao: UserDB) {
    suspend fun insertUser(user:User) = dao.insertUser(user)
    fun getUser(userName: String) = dao.getUser(userName)
    fun setLogin(userName: String,value:Boolean) = dao.setLogin(userName,value)
    fun getLogin() = dao.getUserLogin()
}