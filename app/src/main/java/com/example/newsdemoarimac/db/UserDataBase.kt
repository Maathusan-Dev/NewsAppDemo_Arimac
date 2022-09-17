package com.example.newsdemoarimac.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsdemoarimac.models.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {
    abstract fun userDB(): UserDB
}