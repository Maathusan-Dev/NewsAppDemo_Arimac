package com.example.newsdemoarimac.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val username:String,
    val password:String,
    val isLogin:Boolean
)