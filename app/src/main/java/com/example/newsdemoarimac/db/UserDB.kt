package com.example.newsdemoarimac.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsdemoarimac.models.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDB {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE username IN(:userName)")
    fun getUser(userName:String): Flow<User>

    @Query("UPDATE user SET isLogin = :value  WHERE username IN(:userName)")
    fun setLogin(userName:String,value:Boolean)

    @Query("SELECT * FROM user")
    fun getUserLogin(): Flow<User>
}